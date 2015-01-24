package net.dimensionshift.mod.tileentity;

import java.util.List;
import java.util.Random;

import javax.vecmath.Vector3d;

import net.dimensionshift.mod.DimensionShift;
import net.dimensionshift.mod.block.BlockSimpleTeleporter;
import net.dimensionshift.mod.blockplacing.GetClosestBlockInRadiusRelativeToStartPoint;
import net.dimensionshift.mod.energy.DimensionFuel;
import net.dimensionshift.mod.multiblock.MultiblockTeleporter;
import net.dimensionshift.mod.world.dimensions.teleporter.TeleporterPosition;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntitySimpleTeleporter extends TileEntity implements ISidedInventory {

	private String localizedName;

	// What slots can be accesed from which side?
	private static final int[] slots_top = new int[] { 0 };
	// first take out items from slot 1, than from slot number 0
	private static final int[] slots_bottom = new int[] { 1, 0 };
	private static final int[] slots_sides = new int[] {};

	// How many Slots does Block have
	private ItemStack[] slots = new ItemStack[2];

	// stored Energy
	public int energy;

	// is Receiving?
	public boolean isReceiving = true;

	// stored Dimension Energy
	public int dimensionEnergy;// = 0;

	// needed Dimension Energy
	public final int requiredDimensionEnergyForOnePerson = 100;

	public int isComplete = 0;// 0 not complete, 1 complete

	public int hasIdCard = 0;// 0 not, 1 yes

	// max Dimension Energy
	public final int maxDimensionEnergy = 1000;

	// energy that has to be stored yet
	public int dimensionEnergyCharging = 0;

	// time until ready to tp
	public int cooldown;

	// time added to cooldown for person^2
	public final int addCooldown = 1200;

	// maxtime until ready to tp
	public final int maxCooldown = 10000;

	// needed to check if status has changed...
	public boolean activeLastTick = false;

	public int minEnergy = 0;// 100;

	@Override
	public void updateEntity() {

		// Will be set to true if anything has been done this round. Otherwise
		// it will be false
		boolean active = false;

		// getting rid of stack with size 0
		if (this.slots[1] != null) {
			if (this.slots[1].stackSize == 0) {
				this.slots[1] = this.slots[1].getItem().getContainerItem(this.slots[1]);
			}
		}

		if (this.isComplete != 1) {
			if (MultiblockTeleporter.isMultiBlockStructure(this.worldObj, this.xCoord, this.yCoord, this.zCoord)) {
				this.isComplete = 1;
			}
		}
		if (this.isComplete != 0) {
			if (!MultiblockTeleporter.isMultiBlockStructure(this.worldObj, this.xCoord, this.yCoord, this.zCoord)) {
				this.isComplete = 0;
			}
		}

		if (this.hasIdCard != 1) {
			if (this.slots[0] != null && this.slots[0].isItemEqual(new ItemStack(DimensionShift.itemDimensionIdentificationCrystal)) && this.slots[0].hasTagCompound()) {
				this.hasIdCard = 1;
			}
		}
		if (this.isComplete != 0) {
			if (this.slots[0] == null || (!this.slots[0].isItemEqual(new ItemStack(DimensionShift.itemDimensionIdentificationCrystal)) && !this.slots[0].hasTagCompound())) {
				this.hasIdCard = 0;
			}
		}

		if (this.cooldown > 0) {
			this.cooldown--;

		}

		// converting item to energy
		if (this.dimensionEnergyCharging > 0 && this.dimensionEnergy != this.maxDimensionEnergy) {
			++this.dimensionEnergy;
			--this.dimensionEnergyCharging;

			active = true;
		}

		// if is receivig setting active Block
		if (this.isReceiving && this.dimensionEnergy > this.requiredDimensionEnergyForOnePerson) {

			active = true;
		}

		if (!this.worldObj.isRemote && this.slots[1] != null) {
			if (this.dimensionEnergyCharging == 0 && DimensionFuel.isItemFuel(slots[1])) {
				this.dimensionEnergyCharging = DimensionFuel.getFuelValue(slots[1]);
				--this.slots[1].stackSize;
				active = true;
			}
		}

		// checking if status has changed
		if (active != this.activeLastTick) {
			BlockSimpleTeleporter.updateBlockType(active, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			this.activeLastTick = active;
		}

	}

	public void sendPlayer() {

		Random random = new Random();

		int x = this.xCoord;
		int y = this.yCoord;
		int z = this.zCoord;

		// getting players that are teleported
		int playerNumber = 0;
		List<EntityPlayer> playerList = this.getWorldObj().getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(x - 2, y, z - 2, x + 2, y + 3, z + 2));
		// playerList.add(world.getClosestPlayer(x, y, z, 100));

		playerNumber = playerList.size();

		if (playerNumber > 0 && this.slots[0] != null && this.slots[0].isItemEqual(new ItemStack(DimensionShift.itemDimensionIdentificationCrystal))) {

			if (this.dimensionEnergy >= this.requiredDimensionEnergyForOnePerson * playerNumber && this.cooldown <= 0 && this.slots[0].stackTagCompound != null) {
				if (!this.getWorldObj().isRemote) {

					int dim = this.slots[0].stackTagCompound.getInteger("id");

					World dWorld = MinecraftServer.getServer().worldServerForDimension(dim);

					int r = 100;

					List<Vector3d> list = GetClosestBlockInRadiusRelativeToStartPoint.getBlocks(dWorld, x, y, z, r, DimensionShift.blockSimpleTeleporterActive);

					// checking if multiblock
					for (int i = list.size(); i > 0; i--) {
						int x1 = (int) list.get(i - 1).x;
						int y1 = (int) list.get(i - 1).y;
						int z1 = (int) list.get(i - 1).z;
						TileEntity tileEntity2 = dWorld.getTileEntity(x + x1, y + y1, z + z1);
						if (tileEntity2 != null) {
							TileEntitySimpleTeleporter teleporter2 = (TileEntitySimpleTeleporter) tileEntity2;
							dWorld.getBlock(x + x1, y + y1, z + z1);// preload

							// get ints between 1 and x, but need ints between 0
							// and x-1 because array starts at 0
							int j = i - 1;

							// checking if it is receiving
							if (!teleporter2.isReceiving || !(this.cooldown <= 0)) {
								list.remove(j);

								// checking if enough energy s available
							} else if (!(teleporter2.dimensionEnergy >= teleporter2.requiredDimensionEnergyForOnePerson * playerNumber)) {
								list.remove(j);

								// checking if Multiblock...
							} else if (!MultiblockTeleporter.isMultiBlockStructure(dWorld, x + x1, y + y1, z + z1)) {
								list.remove(j);

							}
						}

					}

					if (list.size() > 0 && playerList != null) {
						for (int i = 0; i < playerList.size(); i++) {

							int chosenTeleporter = random.nextInt(list.size());

							int x1 = (int) list.get(chosenTeleporter).x;
							int y1 = (int) list.get(chosenTeleporter).y;
							int z1 = (int) list.get(chosenTeleporter).z;
							TileEntity tileEntity2 = dWorld.getTileEntity(x + x1, y + y1, z + z1);

							if (tileEntity2 != null) {

								TileEntitySimpleTeleporter teleporter2 = (TileEntitySimpleTeleporter) tileEntity2;

								NBTTagCompound tag = playerList.get(i).getEntityData();
								tag.setInteger("UsedTeleporter", 7);

								this.cooldown = this.cooldown + this.addCooldown * playerNumber * playerNumber;
								this.dimensionEnergy = this.dimensionEnergy - playerNumber * this.requiredDimensionEnergyForOnePerson;

								TeleporterPosition.teleport(playerList.get(i), dim, (playerList.get(i).posX + list.get(chosenTeleporter).x), (playerList.get(i).posY + list.get(chosenTeleporter).y), (playerList.get(i).posZ + list.get(chosenTeleporter).z));

								teleporter2.dimensionEnergy = teleporter2.dimensionEnergy - playerNumber * teleporter2.requiredDimensionEnergyForOnePerson;
								teleporter2.cooldown = teleporter2.cooldown + teleporter2.addCooldown * playerNumber * playerNumber;

							}
						}

					}

				}
			}
		}
	}

	private boolean canTeleport() {
		if (this.slots[0] == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int getSizeInventory() {
		return this.slots.length;
	}

	public boolean isInvNameLocalized() {
		return this.localizedName != null && this.localizedName.length() > 0;
	}

	public String getInvName() {
		return this.isInvNameLocalized() ? this.localizedName : "container.simpleTeleporter";
	}

	public void setGuiDisplayName(String displayName) {
		this.localizedName = displayName;

	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.slots[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.slots[i] != null) {
			ItemStack itemstack;

			if (this.slots[i].stackSize <= j) {
				itemstack = this.slots[i];

				this.slots[i] = null;
				return itemstack;
			} else {
				itemstack = this.slots[i].splitStack(j);

				if (this.slots[i].stackSize == 0) {
					this.slots[i] = null;
				}
				return itemstack;
			}
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.slots[i] != null) {
			ItemStack itemstack = this.slots[i];
			this.slots[i] = null;
			return itemstack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.slots[i] = itemstack;

		if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack item) {

		if (i == 0) { // id card

			return true;
		} else if (i == 1) { // fuel
			return DimensionFuel.isItemFuel(item);
		} else {
			return false;
		}

	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		// saving energy
		nbt.setShort("DimensionEnergy", (short) this.dimensionEnergy);
		nbt.setShort("DimensionEnergyCharging", (short) this.dimensionEnergyCharging);
		nbt.setShort("isComplete", (short) this.isComplete);
		nbt.setShort("Cooldown", (short) this.cooldown);

		// items
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < slots.length; i++) {
			if (this.slots[i] != null) {
				NBTTagCompound compound = new NBTTagCompound();
				compound.setByte("Slot", (byte) i);
				this.slots[i].writeToNBT(compound);
				list.appendTag(compound);
			}
		}
		nbt.setTag("Items", list);

		if (this.isInvNameLocalized()) {
			nbt.setString("CustomName", this.localizedName);
		}
		nbt.setBoolean("ActiveLastTick", this.activeLastTick);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		// items
		NBTTagList list = nbt.getTagList("Items", 10);
		this.slots = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound compound = list.getCompoundTagAt(i);
			byte b = compound.getByte("Slot");
			if (b >= 0 && b < this.slots.length) {
				this.slots[b] = ItemStack.loadItemStackFromNBT(compound);
			}

		}

		this.dimensionEnergy = nbt.getShort("DimensionEnergy");
		this.dimensionEnergyCharging = nbt.getShort("DimensionEnergyCharging");
		this.isComplete = nbt.getShort("isComplete");
		this.cooldown = nbt.getShort("Cooldown");

		if (nbt.hasKey("CustomName")) {
			this.localizedName = nbt.getString("CustomName");
		}
		this.activeLastTick = nbt.getBoolean("ActiveLastTick");

	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return side == 0 ? slots_bottom : (side == 1 ? slots_top : slots_sides);
	}

	@Override
	public boolean canInsertItem(int i, ItemStack item, int side) {
		return this.isItemValidForSlot(i, item);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack item, int side) {
		// if extracts from down slot 1(fuel) than true
		return side == 0 || i == 1;
	}

	@SideOnly(Side.CLIENT)
	public int getDimensionEnergyScaled(int heightDimensionEnergyBar) {
		return this.dimensionEnergy * heightDimensionEnergyBar / this.maxDimensionEnergy;

	}

	@SideOnly(Side.CLIENT)
	public int getCooldownScaled(int cooldownDistance) {
		return (this.cooldown * cooldownDistance / this.maxCooldown);

	}

}
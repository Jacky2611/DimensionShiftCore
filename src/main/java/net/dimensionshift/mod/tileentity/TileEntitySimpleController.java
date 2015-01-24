package net.dimensionshift.mod.tileentity;

import net.dimensionshift.mod.block.BlockSimpleController;
import net.dimensionshift.mod.energy.DimensionFuel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntitySimpleController extends TileEntity implements ISidedInventory {

	private String localizedName;

	// What slots can be accesed from which side?
	private static final int[] slots_top = new int[] { 0 };
	// first take out items from slot 1, than from slot number 0
	private static final int[] slots_bottom = new int[] { 1, 0 };
	private static final int[] slots_sides = new int[] {};

	// How many Slots does Block have
	private ItemStack[] slots = new ItemStack[2];

	// stored Dimension Energy
	public int energy;

	// stored Dimension Energy
	public int dimensionEnergy;// = 0;

	// needed Dimension Energy
	public final int requiredDimensionEnergy = 2000;

	// energy that has to be stored yet
	public int dimensionEnergyCharging = 0;

	// time until ready to tp
	public final int tpTime = 1000;

	// is receiving
	public int timeUntillTp;

	// needed to check if status has changed...
	public boolean activeLastTick = false;

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

		// converting item to energy
		if (this.dimensionEnergyCharging > 0 && this.dimensionEnergy != this.requiredDimensionEnergy) {
			++this.dimensionEnergy;
			--this.dimensionEnergyCharging;

			active = true;
		}

		if (!this.worldObj.isRemote && this.slots[1] != null) {
			if (this.dimensionEnergyCharging == 0 && DimensionFuel.isItemFuel(slots[1])) {
				System.out.println("adding energy to charging and removing item from slot");
				this.dimensionEnergyCharging = DimensionFuel.getFuelValue(slots[1]);
				--this.slots[1].stackSize;
				active = true;
			}
		}

		// checking if status has changed
		if (active != this.activeLastTick) {
			BlockSimpleController.updateBlockType(active, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			this.activeLastTick = active;
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
		return this.isInvNameLocalized() ? this.localizedName : "container.simpleController";
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
		nbt.setShort("Energy", (short) this.energy);

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
		this.energy = nbt.getShort("Energy");

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
		return this.dimensionEnergy * heightDimensionEnergyBar / this.requiredDimensionEnergy;

	}

	public boolean isReadyToTP() {
		return this.dimensionEnergy == this.requiredDimensionEnergy;// &&
																	// this.energy
																	// ==
																	// this.requiredEnergy;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getDimensionEnergy() {
		return dimensionEnergy;
	}

	public void setDimensionEnergy(int dimensionEnergy) {
		this.dimensionEnergy = dimensionEnergy;
	}

}

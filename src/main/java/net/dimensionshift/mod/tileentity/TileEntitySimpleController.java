package net.dimensionshift.mod.tileentity;

import net.dimensionshift.mod.block.BlockSimpleController;
import net.dimensionshift.mod.energy.DimensionFuel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntitySimpleController extends TileEntityLockable implements ISidedInventory, IUpdatePlayerListBox {

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
	public void update() {

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
			BlockSimpleController.updateBlockType(active, this.worldObj, this.getPos());
			this.activeLastTick = active;
		}

	}

	@Override
	public int getSizeInventory() {
		return this.slots.length;
	}

	@Override
	public boolean hasCustomName() {
		return this.localizedName != null && this.localizedName.length() > 0;
	}

	@Override
	public String getName() {
		return this.hasCustomName() ? this.localizedName : "container.simpleController";
	}

	public void setCustomInventoryName(String displayName) {
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
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.getPos()) != this ? false : player.getDistanceSq(this.getPos().getX() + 0.5D, this.getPos().getY() + 0.5D, this.getPos().getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer playerIn) {

	}

	@Override
	public void closeInventory(EntityPlayer playerIn) {

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

		if (this.hasCustomName()) {
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
	public int[] getSlotsForFace(EnumFacing side) {
		return side == EnumFacing.DOWN ? slots_bottom : (side == EnumFacing.UP ? slots_top : slots_sides);
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		// if extracts from down slot 1(fuel) than true
		return direction == EnumFacing.DOWN || index == 1;
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

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
        for (int i = 0; i < this.slots.length; ++i)
        {
            this.slots[i] = null;
        }
		
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory,EntityPlayer playerIn) {
		return new ContainerFurnace(playerInventory, this);
	}

	@Override
	public String getGuiID() {
		return "dimensionshift:simpleController";
	}

}

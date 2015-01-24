package net.dimensionshift.mod.container;

import net.dimensionshift.mod.DimensionShift;
import net.dimensionshift.mod.energy.DimensionFuel;
import net.dimensionshift.mod.tileentity.TileEntitySimpleTeleporter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerSimpleTeleporter extends Container {

	private TileEntitySimpleTeleporter teleporter;

	// stored energy
	public int energy;
	// energy to be stored
	public int charging = 0;
	// time until tp
	public int tpTime = 1000;

	// is just teleporting?
	public boolean isTeleporting = false;

	private int lastDimensionEnergy;

	private int lastCooldown;

	private int lastComplete;

	private int lastHasIdCard;

	public ContainerSimpleTeleporter(InventoryPlayer inventory, TileEntitySimpleTeleporter entity) {
		this.teleporter = entity;

		// goal Entity, SlotID, x and y Coordinates

		// dimension id
		this.addSlotToContainer(new Slot(entity, 0, 44, 20));

		// DimensionEnergy input
		this.addSlotToContainer(new Slot(entity, 1, 44, 58));

		// top Player Inventory
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + 18 * j, 89 + 18 * i));
			}
		}
		// bottom Player Inventory
		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i, 8 + 18 * i, 147));
		}

	}

	@Override
	public void addCraftingToCrafters(ICrafting icrafting) {
		super.addCraftingToCrafters(icrafting);
		icrafting.sendProgressBarUpdate(this, 0, this.teleporter.dimensionEnergy);
		icrafting.sendProgressBarUpdate(this, 1, this.teleporter.cooldown);
		icrafting.sendProgressBarUpdate(this, 2, this.teleporter.isComplete);
		icrafting.sendProgressBarUpdate(this, 3, this.teleporter.hasIdCard);

	}

	@Override
	public void detectAndSendChanges() {

		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (this.lastDimensionEnergy != this.teleporter.dimensionEnergy) {
				icrafting.sendProgressBarUpdate(this, 0, this.teleporter.dimensionEnergy);
			}

			if (this.lastCooldown != this.teleporter.cooldown) {
				icrafting.sendProgressBarUpdate(this, 1, this.teleporter.cooldown);
			}

			if (this.lastComplete != this.teleporter.isComplete) {
				icrafting.sendProgressBarUpdate(this, 2, this.teleporter.isComplete);

			}

			if (this.lastHasIdCard != this.teleporter.hasIdCard) {
				icrafting.sendProgressBarUpdate(this, 3, this.teleporter.hasIdCard);

			}

		}

		this.lastDimensionEnergy = this.teleporter.dimensionEnergy;
		this.lastCooldown = this.teleporter.cooldown;
		this.lastComplete = this.teleporter.isComplete;
		this.lastHasIdCard = this.teleporter.hasIdCard;

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int value) {
		if (slot == 0) this.teleporter.dimensionEnergy = value;
		if (slot == 1) this.teleporter.cooldown = value;
		if (slot == 2) this.teleporter.isComplete = value;
		if (slot == 3) this.teleporter.hasIdCard = value;

	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int clickedSlotNumber) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(clickedSlotNumber);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();// copy to not change itemstack1 if
											// itemstack is changed

			if (clickedSlotNumber == 0) {
				if (!this.mergeItemStack(itemstack1, 2, 38, false)) {// checks
																		// if
																		// the
																		// itemstack
																		// thats
																		// shift
																		// clicked
																		// can
																		// NOT
																		// be
																		// merged
																		// with
																		// an
																		// stack
																		// from
																		// playerinventory(slots
																		// 2-38)
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);

			} else if (clickedSlotNumber == 1) {
				if (!this.mergeItemStack(itemstack1, 2, 38, false)) {// checks
																		// if
																		// the
																		// itemstack
																		// thats
																		// shift
																		// clicked
																		// can
																		// NOT
																		// be
																		// merged
																		// with
																		// an
																		// stack
																		// from
																		// playerinventory(slots
																		// 2-38)
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);

			} else if (clickedSlotNumber > 1) { // checking if clicked itemstack
												// is from player inventory

				if (itemstack1.isItemEqual(new ItemStack(DimensionShift.itemDimensionIdentificationCrystal))) { // if
																												// item
																												// is
																												// fuel

					if (!this.mergeItemStack(itemstack1, 0, 1, true)) {
						return null;

					}

				} else if (DimensionFuel.getFuelValue(itemstack1) > 0) { // if
																			// item
																			// is
																			// fuel

					if (!this.mergeItemStack(itemstack1, 1, 2, true)) {
						System.out.println("now giong to return null");
						return null;

					}

				} else if (clickedSlotNumber >= 2 && clickedSlotNumber < 29) {
					if (!this.mergeItemStack(itemstack1, 29, 37, true)) {
						return null;
					}
				} else if (clickedSlotNumber >= 29 && clickedSlotNumber < 38) {
					if (!this.mergeItemStack(itemstack1, 2, 29, true)) {
						return null;
					}
				}
			} else if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}
			slot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		// TODO Auto-generated method stub
		return this.teleporter.isUseableByPlayer(player);
	}

}

package net.dimensionshift.mod.energy;

import net.dimensionshift.mod.DimensionShift;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class DimensionFuel {

	public static final ItemStack[] getFuels() {
		ItemStack[] fuels = new ItemStack[7];
		fuels[0] = new ItemStack(Items.ender_pearl);
		fuels[1] = new ItemStack(Items.ender_eye);
		fuels[3] = new ItemStack(Items.nether_star);
		fuels[4] = new ItemStack(Blocks.dragon_egg);

		fuels[5] = new ItemStack(DimensionShift.itemEnderCrystal);
		fuels[6] = new ItemStack(DimensionShift.itemEnderDust);

		return fuels;
	}

	public static int getFuelValue(ItemStack itemstack) {
		int fuelValue = 0;

		if (itemstack.getItem().equals(DimensionShift.itemEnderDust)) fuelValue = 1000;
		if (itemstack.getItem() == DimensionShift.itemEnderCrystal) fuelValue = 1500;

		if (itemstack.getItem() == Items.ender_eye) fuelValue = 800;
		if (itemstack.getItem() == Items.ender_pearl) fuelValue = 600;
		if (itemstack.getItem() == Items.nether_star) fuelValue = 5000;

		if (itemstack == new ItemStack(Blocks.dragon_egg)) fuelValue = 5500;

		return fuelValue;
	}

	public static boolean isItemFuel(ItemStack item) {

		if (getFuelValue(item) > 0) {
			return true;
		} else {
			return false;
		}

	}

}

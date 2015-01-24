package net.dimensionshift.mod.item;

import net.dimensionshift.mod.DimensionShift;
import net.minecraft.item.Item;

public class ItemBasic extends Item {

	public ItemBasic(int maxStackSize, String name) {
		setMaxStackSize(maxStackSize);
		setCreativeTab(DimensionShift.tabDimensionShift);
		setUnlocalizedName(name);
		setTextureName(DimensionShift.MODID + ":" + name);

	}

}
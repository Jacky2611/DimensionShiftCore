package net.dimensionshift.mod.block;

import net.dimensionshift.mod.DimensionShift;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BasicBlock extends Block {

	public BasicBlock(Material material, String name) {
		super(material);
		this.setUnlocalizedName(name);
		this.setCreativeTab(DimensionShift.tabDimensionShift);
	}

}

package net.dimensionshift.mod.block;

import net.dimensionshift.mod.DimensionShift;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class BlockDummy extends Block {

	public BlockDummy(Material material, String name) {
		super(material);
		this.setBlockName(name);
		this.textureName = DimensionShift.MODID + ":" + name;
		this.setCreativeTab(DimensionShift.tabDimensionShift);
	}

	@Override
	public IIcon getIcon(int side, int metadata) {

		switch (side) {
			case 0:
				return (Blocks.bedrock.getIcon(0, 0));
			case 1:
				return (Blocks.coal_block.getIcon(0, 0));
			case 2:
				return (Blocks.gravel.getIcon(0, 0));
			case 3:
				return (Blocks.dirt.getIcon(0, 0));
			case 4:
				return (Blocks.gold_block.getIcon(0, 0));
			default:
				return (Blocks.diamond_block.getIcon(0, 0));

		}

	}

	// And this tell it that you can see through this block, and neighbor blocks
	// should be rendered.
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

}

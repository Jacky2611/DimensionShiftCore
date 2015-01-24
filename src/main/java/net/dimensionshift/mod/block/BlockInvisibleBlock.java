package net.dimensionshift.mod.block;

import net.dimensionshift.mod.tileentity.TileEntityInvisibleBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockInvisibleBlock extends BlockContainer {

	public Block block = Blocks.bedrock;

	public BlockInvisibleBlock(Material material) {
		super(material);

	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityInvisibleBlock(Blocks.air);
	}

}

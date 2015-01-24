package net.dimensionshift.mod.block;

import net.dimensionshift.mod.DimensionShift;
import net.dimensionshift.mod.api.ICustomItemBlock;
import net.dimensionshift.mod.tileentity.TileEntityGlassJar;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGlassJar extends BlockContainer implements ICustomItemBlock{

	public BlockGlassJar(Material material) {
		super(material);
		this.setCreativeTab(DimensionShift.tabDimensionShift);
		this.setBlockName("blockGlassJar");
		this.setBlockBounds(1F / 16F * 3F, 0F, 1F / 16F * 3F, 1F - 1F / 16F * 3F, 1F - 1F / 16F * 2F, 1F - 1F / 16F * 3F);

	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityGlassJar();
	}

	@Override
	public int getRenderType() {
		return -1;
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
	public int getStackSize() {
		return 1;
	}

}

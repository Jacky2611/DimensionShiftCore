package net.dimensionshift.mod.energy;

import net.dimensionshift.mod.DimensionShift;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockWire extends BlockContainer {
	float pixel = 1F / 16F;

	public BlockWire() {
		super(Material.ground);
		this.setCreativeTab(DimensionShift.tabDimensionShift);
		this.useNeighborBrightness = true;
		this.setBlockBounds(11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		TileEntityBasicWire tileentity = (TileEntityBasicWire) world.getTileEntity(x, y, z);

		if (tileentity != null) {
			float minX = 11 * pixel / 2 - (tileentity.connections[5] != null ? 11 * pixel / 2 : 0);
			float maxX = 1 - 11 * pixel / 2 + (tileentity.connections[3] != null ? 11 * pixel / 2 : 0);
			float minY = 11 * pixel / 2 - (tileentity.connections[1] != null ? 11 * pixel / 2 : 0);
			float maxY = 1 - 11 * pixel / 2 + (tileentity.connections[0] != null ? 11 * pixel / 2 : 0);
			float minZ = 11 * pixel / 2 - (tileentity.connections[2] != null ? 11 * pixel / 2 : 0);
			float maxZ = 1 - 11 * pixel / 2 + (tileentity.connections[4] != null ? 11 * pixel / 2 : 0);

			this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}
		//return AxisAlignedBB.getAABBPool().getAABB(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);
		return AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		TileEntityBasicWire tileentity = (TileEntityBasicWire) world.getTileEntity(x, y, z);

		if (tileentity != null) {
			float minX = 11 * pixel / 2 - (tileentity.connections[5] != null ? 11 * pixel / 2 : 0);
			float maxX = 1 - 11 * pixel / 2 + (tileentity.connections[3] != null ? 11 * pixel / 2 : 0);
			float minY = 11 * pixel / 2 - (tileentity.connections[1] != null ? 11 * pixel / 2 : 0);
			float maxY = 1 - 11 * pixel / 2 + (tileentity.connections[0] != null ? 11 * pixel / 2 : 0);
			float minZ = 11 * pixel / 2 - (tileentity.connections[2] != null ? 11 * pixel / 2 : 0);
			float maxZ = 1 - 11 * pixel / 2 + (tileentity.connections[4] != null ? 11 * pixel / 2 : 0);

			this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}
		//return AxisAlignedBB.getAABBPool().getAABB(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);
		return AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);
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
	public TileEntity createNewTileEntity(World var1, int var2) {

		return new TileEntityBasicWire();
	}

}

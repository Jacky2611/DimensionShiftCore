package net.dimensionshift.mod.item;

import net.dimensionshift.mod.DimensionShift;
import net.dimensionshift.mod.tileentity.TileEntityInvisibleBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemDust extends Item {

	public ItemDust(int maxStackSize, String name) {
		setMaxStackSize(maxStackSize);
		setCreativeTab(DimensionShift.tabDimensionShift);
		setUnlocalizedName(name);

	}
	/*
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz) {
		System.out.println("ON ITEM USE");
		Block block = world.getBlock(x, y, z);
		if (block.isOpaqueCube()) {
			System.out.println("OPAQUE");
			world.setBlock(x, y, z, DimensionShift.blockInvisibleBlock);
			world.setTileEntity(x, y, z, new TileEntityInvisibleBlock(block));

			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile instanceof TileEntityInvisibleBlock) {
				TileEntityInvisibleBlock tileInvBlock = (TileEntityInvisibleBlock) tile;
				tileInvBlock.setBlock(block);
			}
		}

		return true;
	}
	*/

}
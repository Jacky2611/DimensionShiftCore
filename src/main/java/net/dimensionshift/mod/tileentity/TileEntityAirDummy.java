package net.dimensionshift.mod.tileentity;

import net.dimensionshift.mod.DimensionShift;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAirDummy extends TileEntity {

	static final int playerUpdateDistance = 8;

	public Block block = Blocks.air;

	public TileEntityAirDummy(Block block2) {
		this.block = block2;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateEntity() {
		EntityPlayer player = this.worldObj.getClosestPlayer(xCoord, yCoord, zCoord, playerUpdateDistance);
		if (player != null) {
			this.worldObj.setBlock(xCoord, yCoord, zCoord, DimensionShift.blockInvisibleBlock);
			this.worldObj.setTileEntity(xCoord, yCoord, zCoord, new TileEntityInvisibleBlock(this.block));

			TileEntity tile = this.worldObj.getTileEntity(xCoord, yCoord, zCoord);
			if (tile instanceof TileEntityInvisibleBlock) {
				TileEntityInvisibleBlock tileInvBlock = (TileEntityInvisibleBlock) tile;
				tileInvBlock.setBlock(this.block);
			}

		}
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	@Override
	public void writeToNBT(NBTTagCompound par1) {
		super.writeToNBT(par1);
		par1.setString("Block", block.getUnlocalizedName());
	}

	@Override
	public void readFromNBT(NBTTagCompound par1) {
		super.readFromNBT(par1);
		String blockName = par1.getString("Block");
		this.block = Block.getBlockFromName(blockName);
	}
}
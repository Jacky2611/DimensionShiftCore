package net.dimensionshift.mod.tileentity;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;

public class TileEntityInvisibleBlock extends TileEntity {

	private Block block = Blocks.stone;

	public TileEntityInvisibleBlock(Block block2) {
		this.block = block2;

	}

	@Override
	public void updateEntity() {

	}

	public void setBlock(Block block) {
		this.block = block;
		MinecraftServer.getServer().worldServerForDimension(0).markBlockForUpdate(xCoord, yCoord, zCoord);

	}

	public Block getBlock() {

		if (this.block != null) {
			return this.block;
		} else {
			return Blocks.bedrock;
		}

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
package net.dimensionshift.mod.block;

import java.util.Random;

import net.dimensionshift.mod.DimensionShift;
import net.dimensionshift.mod.tileentity.TileEntitySimpleController;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSunBlock extends BlockContainer {

	private final boolean isActive;

	@SideOnly(Side.CLIENT)
	private IIcon iconBottom;
	private IIcon iconTopRotate1;
	private IIcon iconTopRotate2;
	private IIcon iconTopRotate3;
	private IIcon iconTopRotate4;

	public BlockSunBlock(boolean isActive) {
		super(Material.rock);

		this.isActive = isActive;
	}

	@Override
	@SideOnly(Side.CLIENT)
	// textures
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.iconBottom = iconRegister.registerIcon(DimensionShift.MODID + ":" + (this.isActive ? "blockSunBlock/active_bottom" : "blockSunBlock/idle_bottom"));
		this.blockIcon = iconRegister.registerIcon(DimensionShift.MODID + ":" + (this.isActive ? "blockSunBlock/active_side" : "blockSunBlock/idle_side"));
		this.iconTopRotate1 = iconRegister.registerIcon(DimensionShift.MODID + ":" + (this.isActive ? "blockSunBlock/active_rotate1" : "blockSunBlock/idle_rotate1"));
		this.iconTopRotate2 = iconRegister.registerIcon(DimensionShift.MODID + ":" + (this.isActive ? "blockSunBlock/active_rotate2" : "blockSunBlock/idle_rotate2"));
		this.iconTopRotate3 = iconRegister.registerIcon(DimensionShift.MODID + ":" + (this.isActive ? "blockSunBlock/active_rotate3" : "blockSunBlock/idle_rotate3"));
		this.iconTopRotate4 = iconRegister.registerIcon(DimensionShift.MODID + ":" + (this.isActive ? "blockSunBlock/active_rotate4" : "blockSunBlock/idle_rotate4"));

	}

	@Override
	@SideOnly(Side.CLIENT)
	// texture direction facing
	public IIcon getIcon(int side, int metadata) {

		/*
		 * return side == 1 ? this.iconTopRotate1:(side == metadata ?
		 * this.iconFront : this.blockIcon);
		 */
		return side == 1 ? (metadata == 3 ? this.iconTopRotate1 : (metadata == 4 ? this.iconTopRotate2 : (metadata == 5 ? this.iconTopRotate4 : this.iconTopRotate3))) : (side != 0 ? this.blockIcon : this.iconBottom);

	}

	// always dropping not active version
	@Override
	public Item getItemDropped(int par1, Random random, int par2) {
		return Item.getItemFromBlock(DimensionShift.blockSunBlockIdle);

	}

	// getting coordinates when added
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}

	private void setDefaultDirection(World world, int x, int y, int z) {
		if (!world.isRemote) {
			// getting ids of blocks surround
			Block a = world.getBlock(x, y, z - 1);
			Block b = world.getBlock(x, y, z + 1);
			Block c = world.getBlock(x - 1, y, z);
			Block d = world.getBlock(x + 1, y, z);

			byte b0 = 3;

			// checking if blocks to your front/right/left/back are opaqued
			if (a.isOpaqueCube() && !b.isOpaqueCube()) {
				b0 = 3;
			}
			if (b.isOpaqueCube() && !a.isOpaqueCube()) {
				b0 = 2;
			}
			if (c.isOpaqueCube() && !d.isOpaqueCube()) {
				b0 = 5;
			}
			if (d.isOpaqueCube() && !c.isOpaqueCube()) {
				b0 = 4;
			}

			world.setBlockMetadataWithNotify(x, y, z, b0, 2);

		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz) {
		if (!world.isRemote) {

			MinecraftServer mServer = MinecraftServer.getServer();

			int dim = 0;
			if (world.provider.dimensionId == 0) {
				dim = 9;
			}
			Side sidex = FMLCommonHandler.instance().getEffectiveSide();
			if (sidex == Side.SERVER) {
				if (player instanceof EntityPlayerMP) {
					WorldServer worldserver = (WorldServer) world;
					EntityPlayerMP playerMP = (EntityPlayerMP) player;
					if (player.ridingEntity == null && player.riddenByEntity == null && player instanceof EntityPlayer) {
						FMLCommonHandler.instance().getMinecraftServerInstance();

						// playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP,
						// dim, new
						// net.dimensionshift.mod.world.dimensions.TeleporterDimensionShift(mServer.worldServerForDimension(dim)));

					}
				}
			}

			// FMLNetworkEvent.openGui(player, DimensionShift.instance,
			// DimensionShift.guiIdSimpleController, world, x, y, z);
		}
		return true;
	}

	// creating TileEntity
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySimpleController();
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemstack) {
		int l = MathHelper.floor_double(entityLivingBase.rotationYaw * 4F / 360F + 0.5D) & 3;

		if (l == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}
		if (l == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}
		if (l == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}
		if (l == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}

		// if(itemstack.hasDisplayName()) {
		// ((TileEntitySimpleController)world.getBlockTileEntity(x, y,
		// z)).setGuiDisplayName(itemstack.getDisplayName());
		// }
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		// TODO Auto-generated method stub
		return null;
	}

}

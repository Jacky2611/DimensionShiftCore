package net.dimensionshift.mod.block;

import java.util.Random;

import net.dimensionshift.mod.DimensionShift;
import net.dimensionshift.mod.blockplacing.TeleportSimpleController;
import net.dimensionshift.mod.tileentity.TileEntitySimpleController;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSimpleController extends BlockContainer {

	private final boolean isActive;
	private static boolean keepInventory;

	private Random random = new Random();

	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	private IIcon iconTopRotate1;
	private IIcon iconTopRotate2;
	private IIcon iconTopRotate3;
	private IIcon iconTopRotate4;

	public BlockSimpleController(boolean isActive) {
		super(Material.rock);

		this.isActive = isActive;

	}

	@Override
	@SideOnly(Side.CLIENT)
	// textures
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(DimensionShift.MODID + ":" + "blockSimpleController/side");
		this.iconFront = iconRegister.registerIcon(DimensionShift.MODID + ":" + "blockSimpleController/front");
		this.iconTopRotate1 = iconRegister.registerIcon(DimensionShift.MODID + ":" + (this.isActive ? "blockSimpleController/active_rotate1" : "blockSimpleController/idle_rotate1"));
		this.iconTopRotate2 = iconRegister.registerIcon(DimensionShift.MODID + ":" + (this.isActive ? "blockSimpleController/active_rotate2" : "blockSimpleController/idle_rotate2"));
		this.iconTopRotate3 = iconRegister.registerIcon(DimensionShift.MODID + ":" + (this.isActive ? "blockSimpleController/active_rotate3" : "blockSimpleController/idle_rotate3"));
		this.iconTopRotate4 = iconRegister.registerIcon(DimensionShift.MODID + ":" + (this.isActive ? "blockSimpleController/active_rotate4" : "blockSimpleController/idle_rotate4"));

	}

	@Override
	@SideOnly(Side.CLIENT)
	// texture direction facing
	public IIcon getIcon(int side, int metadata) {

		/*
		 * return side == 1 ? this.iconTopRotate1:(side == metadata ?
		 * this.iconFront : this.blockIcon);
		 */
		return side == 1 ? (metadata == 3 ? this.iconTopRotate1 : (metadata == 4 ? this.iconTopRotate2 : (metadata == 5 ? this.iconTopRotate4 : this.iconTopRotate3))) : (side == metadata ? this.iconFront : this.blockIcon);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		if (this.isActive) {

			float x1 = x + random.nextFloat();
			float y1 = y + 0.5F;
			float z1 = z + random.nextFloat();

			world.spawnParticle("portal", x1, y1, z1, 0.0D, 0.0D, 0.0D);

		}
	}

	// always dropping not active version
	@Override
	public Item getItemDropped(int par1, Random random, int par2) {
		return Item.getItemFromBlock(DimensionShift.blockSimpleControllerIdle);

	}

	// getting coordinates when added
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block oldBlock, int oldMetadata) {
		if (!keepInventory) {// if block is not broken by update status function

			TileEntity tileentity = world.getTileEntity(x, y, z);
			if (tileentity != null) {
				TileEntitySimpleController simpleController = (TileEntitySimpleController) world.getTileEntity(x, y, z);
				for (int i = 0; i < simpleController.getSizeInventory(); i++) {
					ItemStack itemstack = simpleController.getStackInSlot(i);// get
																				// itemstack
																				// from
																				// slot
					if (itemstack != null) {// if there is an itemstack

						float f = this.random.nextFloat() * 0.8F + 0.1F;// random
																		// position
																		// for
																		// item
																		// drop....
						float f1 = this.random.nextFloat() * 0.8F + 0.1F;// random
																			// position
																			// for
																			// item
																			// drop....
						float f2 = this.random.nextFloat() * 0.8F + 0.1F;// random
																			// position
																			// for
																			// item
																			// drop....

						while (itemstack.stackSize > 0) {
							int j = this.random.nextInt(21) + 10; // How many
																	// item it
																	// throws
																	// out a
																	// tick

							if (j > itemstack.stackSize) { // can't throw out
															// more than max
															// available
															// items...
								j = itemstack.stackSize;
							}

							itemstack.stackSize -= j; // removing item from gui

							// creating item
							EntityItem item = new EntityItem(world, x + f, y + f1, z + f2, new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));

							// nbt
							if (itemstack.hasTagCompound()) {
								item.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
							}

							// adding motion to item...
							float f3 = 0.05F;
							item.motionX = (float) this.random.nextGaussian() * f3;
							item.motionY = (float) this.random.nextGaussian() * f3 + 0.2F;
							item.motionZ = (float) this.random.nextGaussian() * f3;

							// spawning item
							world.spawnEntityInWorld(item);
						}

					}
				}
				world.func_147453_f(x, y, z, oldBlock);

			}

		}
		super.breakBlock(world, x, y, z, oldBlock, oldMetadata);
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

		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking()) {
			System.out.println("Returned false");
			return false;
		} else {
			System.out.println("Now going to open gui");
			player.openGui(DimensionShift.instance, DimensionShift.guiIdSimpleController, world, x, y, z);

		}

		return true;

	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which
	 * neighbor changed (coordinates passed are their own) Args: x, y, z,
	 * neighbor Block
	 */
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if (!world.isRemote) {
			if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
				TileEntity tileEntity = world.getTileEntity(x, y, z);
				if (tileEntity != null) {
					TileEntitySimpleController simpleController = (TileEntitySimpleController) tileEntity;
					if (simpleController.isReadyToTP()) {// checking if enough
															// energy is
															// available

						if (!world.isRemote) {

							// reseting Energy
							simpleController.setDimensionEnergy(0);
							simpleController.setEnergy(0);

							MinecraftServer mServer = MinecraftServer.getServer();

							int dim = 0;

							if (world.provider.dimensionId == 0) {
								dim = 9;
							}
							World dWorld = mServer.worldServerForDimension(dim);

							Side sidex = FMLCommonHandler.instance().getEffectiveSide();
							if (sidex == Side.SERVER) {

								TeleportSimpleController.start(world, dWorld, x, y, z);

							}
						}

					}
				}
			}
		}
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

		if (itemstack.hasDisplayName()) {
			((TileEntitySimpleController) world.getTileEntity(x, y, z)).setGuiDisplayName(itemstack.getDisplayName());
		}

	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		// TODO Auto-generated method stub
		return new TileEntitySimpleController();
	}

	public static void updateBlockType(boolean active, World world, int x, int y, int z) {
		int i = world.getBlockMetadata(x, y, z);
		TileEntity tileentity = world.getTileEntity(x, y, z);

		keepInventory = true;

		if (active) {
			world.setBlock(x, y, z, DimensionShift.blockSimpleControllerActive);
		} else {
			world.setBlock(x, y, z, DimensionShift.blockSimpleControllerIdle);

		}

		keepInventory = false;

		world.setBlockMetadataWithNotify(x, y, z, i, 2);

		if (tileentity != null) {
			tileentity.validate();
			world.setTileEntity(x, y, z, tileentity);
		}

	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int i) {
		return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(x, y, z));
	}

	// idpicked???????

}

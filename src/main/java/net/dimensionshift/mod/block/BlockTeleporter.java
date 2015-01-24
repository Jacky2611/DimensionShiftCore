package net.dimensionshift.mod.block;

import java.util.Random;

import net.dimensionshift.mod.DimensionShift;
import net.dimensionshift.mod.multiblock.MultiblockTeleporter;
import net.dimensionshift.mod.tileentity.TileEntityTeleporter;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTeleporter extends BlockContainer {

	private static boolean keepInventory;

	private Random random = new Random();

	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	private IIcon iconTopRotate1;
	private IIcon iconTopRotate2;
	private IIcon iconTopRotate3;
	private IIcon iconTopRotate4;

	private boolean isActive;

	public BlockTeleporter(boolean active) {
		super(Material.iron);
		this.isActive = active;

	}

	@Override
	@SideOnly(Side.CLIENT)
	// textures
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(DimensionShift.MODID + ":" + "blockTeleporter/side");
		this.iconFront = iconRegister.registerIcon(DimensionShift.MODID + ":" + (this.isActive ? "blockTeleporter/active" : "blockTeleporter/idle"));
		this.iconTopRotate1 = iconRegister.registerIcon(DimensionShift.MODID + ":" + "blockTeleporter/top_rotate1");
		this.iconTopRotate2 = iconRegister.registerIcon(DimensionShift.MODID + ":" + "blockTeleporter/top_rotate2");
		this.iconTopRotate3 = iconRegister.registerIcon(DimensionShift.MODID + ":" + "blockTeleporter/top_rotate3");
		this.iconTopRotate4 = iconRegister.registerIcon(DimensionShift.MODID + ":" + "blockTeleporter/top_rotate4");

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

	public static void updateBlockType(boolean active, World world, int x, int y, int z) {
		int i = world.getBlockMetadata(x, y, z);
		TileEntity tileentity = world.getTileEntity(x, y, z);

		keepInventory = true;

		if (active) {
			world.setBlock(x, y, z, DimensionShift.blockTeleporterActive);
		} else {
			world.setBlock(x, y, z, DimensionShift.blockTeleporterIdle);

		}

		keepInventory = false;

		world.setBlockMetadataWithNotify(x, y, z, i, 2);

		if (tileentity != null) {
			tileentity.validate();
			world.setTileEntity(x, y, z, tileentity);
		}

	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {

		// Multiblock
		if (world.isBlockIndirectlyGettingPowered(x, y, z) && MultiblockTeleporter.isMultiBlockStructure(world, x, y, z)) {

			TileEntity tileEntity = world.getTileEntity(x, y, z);
			if (tileEntity != null) {
				TileEntityTeleporter teleporter = (TileEntityTeleporter) tileEntity;

				teleporter.sendPlayer();
			}

			/*
			 * TileEntity tileEntity = world.getTileEntity(x, y, z);
			 * 
			 * 
			 * //getting players that are teleported int playerNumber=0;
			 * List<EntityPlayer> playerList =
			 * world.getEntitiesWithinAABB(EntityPlayer.class,
			 * AxisAlignedBB.getBoundingBox(x-2, y, z-2, x+2, y+3, z+2));
			 * //playerList.add(world.getClosestPlayer(x, y, z, 100));
			 * 
			 * playerNumber = playerList.size();
			 * System.out.println("Found Players: "+ playerNumber);
			 * 
			 * 
			 * if(tileEntity != null && playerNumber!=0){ TileEntityTeleporter
			 * teleporter= (TileEntityTeleporter)tileEntity;
			 * if(teleporter.dimensionEnergy
			 * >=teleporter.requiredDimensionEnergyForOnePerson*playerNumber){
			 * //&& teleporter.energy>teleporter.minEnergy){ if(!world.isRemote)
			 * {
			 * 
			 * int dim=0;
			 * 
			 * if(world.provider.dimensionId==0) { dim=9; } World dWorld =
			 * MinecraftServer.getServer().worldServerForDimension(dim);
			 * 
			 * 
			 * int r= 100;
			 * 
			 * List<Vector3d> list =
			 * GetClosestBlockInRadiusRelativeToStartPoint.getBlocks(dWorld, x,
			 * y, z, r, DimensionShift.blockTeleporterActive);
			 * System.out.println("Found possible Blocks: "+ list.size());
			 * 
			 * 
			 * 
			 * //checking if multiblock for(int i=list.size(); i>0; i--){
			 * System.out.println("Started for Loop"); int x1 = (int)
			 * list.get(i-1).x; int y1 = (int) list.get(i-1).y; int z1 = (int)
			 * list.get(i-1).z; TileEntity tileEntity2 =
			 * dWorld.getTileEntity(x+x1, y+y1, z+z1); if(tileEntity != null){
			 * TileEntityTeleporter teleporter2=
			 * (TileEntityTeleporter)tileEntity2; dWorld.getBlock(x+x1, y+y1,
			 * z+z1);//preload
			 * 
			 * //get ints between 1 and x, but need ints between 0 and x-1
			 * because array starts at 0 int j = i-1;
			 * 
			 * //checking if it is receiving if(!teleporter2.isReceiving){
			 * list.remove(j);
			 * 
			 * //checking if enough energy s available } else
			 * if(!(teleporter2.dimensionEnergy
			 * >=teleporter2.requiredDimensionEnergyForOnePerson*playerNumber)){
			 * list.remove(j);
			 * 
			 * //checking if Multiblock... }else if (!
			 * MultiblockTeleporter.isMultiBlockStructure(dWorld,x+x1, y+y1,
			 * z+z1)){
			 * System.out.println("Removing non Multiblock Teleporter from List"
			 * ); list.remove(j);
			 * 
			 * 
			 * 
			 * } } }
			 * 
			 * 
			 * System.out.println("Useable Teleportes"+ list.size());
			 * 
			 * 
			 * if(list.size()>0 && playerList!=null){ for(int i=0;
			 * i<playerList.size(); i++){ System.out.println("now teleporting");
			 * 
			 * int chosenTeleporter=random.nextInt(list.size());
			 * System.out.println(chosenTeleporter);
			 * 
			 * int x1 = (int) list.get(chosenTeleporter).x; int y1 = (int)
			 * list.get(chosenTeleporter).y; int z1 = (int)
			 * list.get(chosenTeleporter).z; TileEntity tileEntity2 =
			 * dWorld.getTileEntity(x+x1, y+y1, z+z1); if(tileEntity != null){
			 * 
			 * 
			 * TileEntityTeleporter teleporter2=
			 * (TileEntityTeleporter)tileEntity2;
			 * playerList.get(i).addPotionEffect(new
			 * PotionEffect(Potion.blindness.getId(), 160));
			 * playerList.get(i).addPotionEffect(new
			 * PotionEffect(Potion.confusion.getId(), 220));
			 * 
			 * 
			 * TeleporterPosition.teleport(playerList.get(i), dim, false,
			 * (playerList.get(i).posX+list.get(chosenTeleporter).x),
			 * (playerList.get(i).posY+list.get(chosenTeleporter).y),
			 * (playerList.get(i).posZ+list.get(chosenTeleporter).z));
			 * 
			 * teleporter.setCooldown(teleporter.addCooldown*playerNumber*
			 * playerNumber); System.out.println("PLAYERNUMBER:"+playerNumber);
			 * teleporter2
			 * .setCooldown(teleporter.addCooldown*playerNumber*playerNumber);
			 * 
			 * if (world.isRemote){ world.spawnParticle("smokelarge",
			 * (double)playerList.get(i).posX, (double)playerList.get(i).posY,
			 * (double)playerList.get(i).posZ, 0.0D, 0.0D, 0.0D);
			 * 
			 * } } }
			 * 
			 * }
			 * 
			 * } } }
			 */

		}
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
			TileEntityTeleporter tileentity = (TileEntityTeleporter) world.getTileEntity(x, y, z);
			if (tileentity != null) {
				for (int i = 0; i < tileentity.getSizeInventory(); i++) {
					ItemStack itemstack = tileentity.getStackInSlot(i);// get
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
			player.openGui(DimensionShift.instance, DimensionShift.guiIdTeleporter, world, x, y, z);

		}

		return true;

	}

	// creating TileEntity
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityTeleporter();
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
			((TileEntityTeleporter) world.getTileEntity(x, y, z)).setGuiDisplayName(itemstack.getDisplayName());
		}

	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		// TODO Auto-generated method stub
		return new TileEntityTeleporter();
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int i) {
		return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(x, y, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
		return Item.getItemFromBlock(DimensionShift.blockTeleporterIdle);
	}

	// always dropping not active version
	@Override
	public Item getItemDropped(int par1, Random random, int par2) {
		return Item.getItemFromBlock(DimensionShift.blockTeleporterIdle);

	}

}

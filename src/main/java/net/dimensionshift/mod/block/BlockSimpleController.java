package net.dimensionshift.mod.block;

import java.util.Random;

import net.dimensionshift.mod.DimensionShift;
import net.dimensionshift.mod.DimensionShiftBlocks;
import net.dimensionshift.mod.blockplacing.TeleportSimpleController;
import net.dimensionshift.mod.tileentity.TileEntitySimpleController;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSimpleController extends BlockContainer {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	private final boolean isActive;
	private static boolean keepInventory;

	private Random random = new Random();


	public BlockSimpleController(boolean isActive) {
		super(Material.rock);
		
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.isActive = isActive;

	}


	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (this.isActive) {

			float x1 = pos.getX() + random.nextFloat();
			float y1 = pos.getY() + 0.5F;
			float z1 = pos.getZ() + random.nextFloat();

			world.spawnParticle(EnumParticleTypes.PORTAL, x1, y1, z1, 0.0D, 0.0D, 0.0D);

		}
	}

	// always dropping not active version
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(DimensionShiftBlocks.blockSimpleControllerIdle);

	}

	// getting coordinates when added
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)  {
		super.onBlockAdded(world, pos, state);
		this.setDefaultDirection(world, pos, state);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!keepInventory) {// if block is not broken by update status function
	            TileEntity tileentity = worldIn.getTileEntity(pos);

	            if (tileentity instanceof TileEntitySimpleController)
	            {
	                InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntitySimpleController)tileentity);
	                worldIn.updateComparatorOutputLevel(pos, this);
	            }
	        }

		super.breakBlock(worldIn, pos, state);
	}

	private void setDefaultDirection(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            Block block = worldIn.getBlockState(pos.offsetNorth()).getBlock();
            Block block1 = worldIn.getBlockState(pos.offsetSouth()).getBlock();
            Block block2 = worldIn.getBlockState(pos.offsetWest()).getBlock();
            Block block3 = worldIn.getBlockState(pos.offsetEast()).getBlock();
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && block.isFullBlock() && !block1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock() && !block.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && block2.isFullBlock() && !block3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && block3.isFullBlock() && !block2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {

		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity == null || playerIn.isSneaking()) {
			return false;
		} else {
			playerIn.openGui(DimensionShift.instance, DimensionShift.guiIdSimpleController, worldIn, pos.getX(), pos.getY(), pos.getZ());

		}

		return true;

	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which
	 * neighbor changed (coordinates passed are their own) Args: x, y, z,
	 * neighbor Block
	 */
	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		if (!worldIn.isRemote) {
			if (worldIn.isBlockPowered(pos)) {
				TileEntity tileEntity = worldIn.getTileEntity(pos);
				if (tileEntity != null) {
					TileEntitySimpleController simpleController = (TileEntitySimpleController) tileEntity;
					if (simpleController.isReadyToTP()) {// checking if enough
															// energy is
															// available

						if (!worldIn.isRemote) {

							// reseting Energy
							simpleController.setDimensionEnergy(0);
							simpleController.setEnergy(0);

							MinecraftServer mServer = MinecraftServer.getServer();

							int dim = 0;

							if (worldIn.provider.getDimensionId() == 0) {
								dim = 9;
							}
							World dWorld = mServer.worldServerForDimension(dim);

							Side sidex = FMLCommonHandler.instance().getEffectiveSide();
							if (sidex == Side.SERVER) {

								TeleportSimpleController.start(worldIn, dWorld, pos.getX(), pos.getY(), pos.getZ());

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

    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.func_174811_aO().getOpposite());
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.func_174811_aO().getOpposite()), 2);

        if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntitySimpleController)
            {
                ((TileEntitySimpleController)tileentity).setCustomInventoryName(stack.getDisplayName());
            }
        }
    }

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		// TODO Auto-generated method stub
		return new TileEntitySimpleController();
	}

	
	public static void updateBlockType(boolean active, World worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        keepInventory = true;

        if (active)
        {
            worldIn.setBlockState(pos, Blocks.lit_furnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, Blocks.lit_furnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }
        else
        {
            worldIn.setBlockState(pos, Blocks.furnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, Blocks.furnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }

        keepInventory = false;

        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World worldIn, BlockPos pos) {
		return Container.calcRedstoneFromInventory((IInventory) worldIn.getTileEntity(pos));
	}

	// idpicked???????

}

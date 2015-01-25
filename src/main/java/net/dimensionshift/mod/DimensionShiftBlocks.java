package net.dimensionshift.mod;

import net.dimensionshift.mod.block.BasicBlock;
import net.dimensionshift.mod.block.BlockAirDummy;
import net.dimensionshift.mod.block.BlockDummy;
import net.dimensionshift.mod.block.BlockGlassJar;
import net.dimensionshift.mod.block.BlockInvisibleBlock;
import net.dimensionshift.mod.block.BlockSimpleController;
import net.dimensionshift.mod.block.BlockSimpleTeleporter;
import net.dimensionshift.mod.block.BlockSunBlock;
import net.dimensionshift.mod.block.BlockTeleporter;
import net.dimensionshift.mod.energy.BlockWire;
import net.dimensionshift.mod.energy.TileEntityBasicWire;
import net.dimensionshift.mod.item.CustomItemBlock;
import net.dimensionshift.mod.tileentity.TileEntityAirDummy;
import net.dimensionshift.mod.tileentity.TileEntityGlassJar;
import net.dimensionshift.mod.tileentity.TileEntityInvisibleBlock;
import net.dimensionshift.mod.tileentity.TileEntitySimpleController;
import net.dimensionshift.mod.tileentity.TileEntitySimpleTeleporter;
import net.dimensionshift.mod.tileentity.TileEntityTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class DimensionShiftBlocks {
	
	
	final static String MODID = DimensionShift.MODID;
	
	
	
	
	// BLOCKS
	public static Block blockDummy;

	public static Block blockMachineBlock;

	public static Block blockGlassJar;

	public static Block blockSimpleControllerIdle;
	public static Block blockSimpleControllerActive;

	public static Block blockSimpleTeleporterIdle;
	public static Block blockSimpleTeleporterActive;

	public static Block blockTeleporterIdle;
	public static Block blockTeleporterActive;

	public static Block blockSunBlockIdle;
	public static Block blockSunBlockActive;

	public static Block blockInvisibleBlock;
	public static Block blockAirDummy;

	public static Block blockWireBasic;
	
	
	public static void init()
	{
		//blockDummy = new BlockDummy(Material.ground, "blockDummy").setStepSound(Block.soundTypeStone).setResistance(10F);

		blockMachineBlock = new BasicBlock(Material.rock, "blockMachineBlock").setStepSound(Block.soundTypeMetal).setResistance(80F).setHardness(3F);
		
		blockSimpleControllerIdle = new BlockSimpleController(false).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setCreativeTab(DimensionShift.tabDimensionShift).setUnlocalizedName("blockSimpleController");
		blockSimpleControllerActive = new BlockSimpleController(true).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setLightLevel(0.7F).setUnlocalizedName("blockSimpleControllerActive");

		/*
		blockSimpleTeleporterIdle = new BlockSimpleTeleporter(false).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setCreativeTab(DimensionShift.tabDimensionShift).setUnlocalizedName("blockSimpleTeleporter");
		blockSimpleTeleporterActive = new BlockSimpleTeleporter(true).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setLightLevel(0.7F).setUnlocalizedName("blockSimpleTeleporterActive");

		blockTeleporterIdle = new BlockTeleporter(false).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setCreativeTab(DimensionShift.tabDimensionShift).setUnlocalizedName("blockTeleporter");
		blockTeleporterActive = new BlockTeleporter(true).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setLightLevel(0.7F).setUnlocalizedName("blockTeleporterActive");

		blockSunBlockIdle = new BlockSunBlock(false).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setCreativeTab(DimensionShift.tabDimensionShift).setUnlocalizedName("blockSunBlock");
		blockSunBlockActive = new BlockSunBlock(true).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setLightLevel(0.7F).setUnlocalizedName("blockSunBlockActive");

		blockInvisibleBlock = new BlockInvisibleBlock(Material.rock);

		blockAirDummy = new BlockAirDummy();

		blockGlassJar = new BlockGlassJar(Material.glass).setStepSound(Block.soundTypeWood).setUnlocalizedName("blockGlassJar");

		blockWireBasic = new BlockWire().setUnlocalizedName("wireBasic");
		*/
	}
	
	public static void register()
	{
		//GameRegistry.registerBlock(blockDummy, "blockDummy");

		GameRegistry.registerBlock(blockMachineBlock, "blockMachineBlock");
		
		
		GameRegistry.registerBlock(blockSimpleControllerIdle, "blockSimpleController");
		GameRegistry.registerBlock(blockSimpleControllerActive, "blockSimpleControllerActive");
		GameRegistry.registerTileEntity(TileEntitySimpleController.class, "tileEntitySimpleController");

		/*
		
		GameRegistry.registerBlock(blockSimpleTeleporterIdle, "blockSimpleTeleporter");
		GameRegistry.registerBlock(blockSimpleTeleporterActive, "blockSimpleTeleporterActive");
		GameRegistry.registerTileEntity(TileEntitySimpleTeleporter.class, "tileEntitySimpleTeleporter");

		GameRegistry.registerBlock(blockTeleporterIdle, "blockTeleporter");
		GameRegistry.registerBlock(blockTeleporterActive, "blockTeleporterActive");
		GameRegistry.registerTileEntity(TileEntityTeleporter.class, "tileEntityTeleporter");

		GameRegistry.registerBlock(blockSunBlockIdle, "blockSunBlock");
		GameRegistry.registerBlock(blockSunBlockActive, "blockSunBlockActive");

		GameRegistry.registerBlock(blockInvisibleBlock, "blockInvisibleBlock");
		GameRegistry.registerTileEntity(TileEntityInvisibleBlock.class, "tileEntityStoneBricks");

		GameRegistry.registerBlock(blockAirDummy, "blockAirDummy");
		GameRegistry.registerTileEntity(TileEntityAirDummy.class, "tileEntityAirDummy");

		GameRegistry.registerBlock(blockGlassJar, CustomItemBlock.class, "blockGlassJar");
		GameRegistry.registerTileEntity(TileEntityGlassJar.class, "tileEntityGlassJar");

		GameRegistry.registerBlock(blockWireBasic, "wireBasic");

		GameRegistry.registerTileEntity(TileEntityBasicWire.class, MODID + ":" + "BasicPipe");
		*/

	}	
	
	public static void registerRenders()
	{
		registerRender(blockMachineBlock);
		
		registerRender(blockSimpleControllerIdle);
		registerRender(blockSimpleControllerActive);

	}
	
	public static void registerRender(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 1, new ModelResourceLocation(MODID + ":" + item.getUnlocalizedName()));;
	}
}
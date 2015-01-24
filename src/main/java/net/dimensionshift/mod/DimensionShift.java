package net.dimensionshift.mod;

import java.util.Iterator;

import net.dimensionshift.mod.block.BasicBlock;
import net.dimensionshift.mod.block.BlockAirDummy;
import net.dimensionshift.mod.block.BlockDummy;
import net.dimensionshift.mod.block.BlockGlassJar;
import net.dimensionshift.mod.block.BlockInvisibleBlock;
import net.dimensionshift.mod.block.BlockSimpleController;
import net.dimensionshift.mod.block.BlockSimpleTeleporter;
import net.dimensionshift.mod.block.BlockSunBlock;
import net.dimensionshift.mod.block.BlockTeleporter;
import net.dimensionshift.mod.enchantments.EnchantmentDimensionTravelingStabilisation;
import net.dimensionshift.mod.energy.BlockWire;
import net.dimensionshift.mod.energy.TileEntityBasicWire;
import net.dimensionshift.mod.event.EventHandlerDimensionShift;
import net.dimensionshift.mod.gui.GuiHandler;
import net.dimensionshift.mod.item.CustomItemBlock;
import net.dimensionshift.mod.item.ItemBasic;
import net.dimensionshift.mod.item.ItemDimensionIdentificationCrystal;
import net.dimensionshift.mod.item.ItemDust;
import net.dimensionshift.mod.proxies.DimensionShiftCommonProxy;
import net.dimensionshift.mod.tileentity.TileEntityAirDummy;
import net.dimensionshift.mod.tileentity.TileEntityGlassJar;
import net.dimensionshift.mod.tileentity.TileEntityInvisibleBlock;
import net.dimensionshift.mod.tileentity.TileEntitySimpleController;
import net.dimensionshift.mod.tileentity.TileEntitySimpleTeleporter;
import net.dimensionshift.mod.tileentity.TileEntityTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = DimensionShift.MODID, name = "DimensionShift", version = DimensionShift.VERSION)
public class DimensionShift {

	public static final String MODID = "dimensionshift"; // setting MODID
	public static final String VERSION = "Experimental v0.312"; // setting MODID
	@Instance(MODID)
	public static DimensionShift instance;

	@SidedProxy(clientSide = "net.dimensionshift.mod.proxies.DimensionShiftClientProxy", serverSide = "net.dimensionshift.mod.proxies.DimensionShiftCommonProxy")
	public static DimensionShiftCommonProxy proxy;

	public static CreativeTabs tabDimensionShift = new CreativeTabs("tabDimensionShift") {
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getIconItemStack() {
			return new ItemStack(Items.ender_eye, 1, 0);
		}

		@Override
		public Item getTabIconItem() {
			return null;
		}

	};

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

	// ITEMS
	public static Item itemEnderDust;

	public static Item itemEnderCrystal;

	public static Item itemEnderLense;

	public static Item itemDimensionIdentificationCrystal;

	// GUI
	public static final int guiIdSimpleController = 0;

	public static final int guiIdSimpleTeleporter = 1;

	public static final int guiIdTeleporter = 2;

	// Enchantments
	public static final Enchantment enchantmentDimensionTravelingStabilisation = new EnchantmentDimensionTravelingStabilisation(84, 5);

	// Achievements
	public static Achievement achievementEnderDust;

	// AchievementPage
	public static AchievementPage achievementPageDimensionShift;

	/*
	 * CONFIG FILE:
	 */

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		// loading the configuration from its file
		config.load();

		// saving the configuration to its file
		config.save();

		// BLOCKS

		blockDummy = new BlockDummy(Material.ground, "blockDummy").setStepSound(Block.soundTypeStone).setResistance(10F);
		GameRegistry.registerBlock(blockDummy, "blockDummy");

		blockMachineBlock = new BasicBlock(Material.rock, "blockMachineBlock").setStepSound(Block.soundTypeMetal).setResistance(80F).setHardness(3F);
		GameRegistry.registerBlock(blockMachineBlock, "blockMachineBlock");

		blockSimpleControllerIdle = new BlockSimpleController(false).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setCreativeTab(DimensionShift.tabDimensionShift).setBlockName("blockSimpleController");
		blockSimpleControllerActive = new BlockSimpleController(true).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setLightLevel(0.7F).setBlockName("blockSimpleControllerActive");
		GameRegistry.registerBlock(blockSimpleControllerIdle, "blockSimpleController");
		GameRegistry.registerBlock(blockSimpleControllerActive, "blockSimpleControllerActive");
		GameRegistry.registerTileEntity(TileEntitySimpleController.class, "tileEntitySimpleController");

		blockSimpleTeleporterIdle = new BlockSimpleTeleporter(false).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setCreativeTab(DimensionShift.tabDimensionShift).setBlockName("blockSimpleTeleporter");
		blockSimpleTeleporterActive = new BlockSimpleTeleporter(true).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setLightLevel(0.7F).setBlockName("blockSimpleTeleporterActive");
		GameRegistry.registerBlock(blockSimpleTeleporterIdle, "blockSimpleTeleporter");
		GameRegistry.registerBlock(blockSimpleTeleporterActive, "blockSimpleTeleporterActive");
		GameRegistry.registerTileEntity(TileEntitySimpleTeleporter.class, "tileEntitySimpleTeleporter");

		blockTeleporterIdle = new BlockTeleporter(false).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setCreativeTab(DimensionShift.tabDimensionShift).setBlockName("blockTeleporter");
		blockTeleporterActive = new BlockTeleporter(true).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setLightLevel(0.7F).setBlockName("blockTeleporterActive");
		GameRegistry.registerBlock(blockTeleporterIdle, "blockTeleporter");
		GameRegistry.registerBlock(blockTeleporterActive, "blockTeleporterActive");
		GameRegistry.registerTileEntity(TileEntityTeleporter.class, "tileEntityTeleporter");

		blockSunBlockIdle = new BlockSunBlock(false).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setCreativeTab(DimensionShift.tabDimensionShift).setBlockName("blockSunBlock");
		blockSunBlockActive = new BlockSunBlock(true).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setLightLevel(0.7F).setBlockName("blockSunBlockActive");
		GameRegistry.registerBlock(blockSunBlockIdle, "blockSunBlock");
		GameRegistry.registerBlock(blockSunBlockActive, "blockSunBlockActive");

		blockInvisibleBlock = new BlockInvisibleBlock(Material.rock);
		GameRegistry.registerBlock(blockInvisibleBlock, "blockInvisibleBlock");
		GameRegistry.registerTileEntity(TileEntityInvisibleBlock.class, "tileEntityStoneBricks");

		blockAirDummy = new BlockAirDummy();
		GameRegistry.registerBlock(blockAirDummy, "blockAirDummy");
		GameRegistry.registerTileEntity(TileEntityAirDummy.class, "tileEntityAirDummy");

		blockGlassJar = new BlockGlassJar(Material.glass).setStepSound(Block.soundTypeWood).setBlockName("blockGlassJar");
		GameRegistry.registerBlock(blockGlassJar, CustomItemBlock.class, "blockGlassJar");
		GameRegistry.registerTileEntity(TileEntityGlassJar.class, "tileEntityGlassJar");

		blockWireBasic = new BlockWire().setBlockName("wireBasic");
		GameRegistry.registerBlock(blockWireBasic, "wireBasic");

		GameRegistry.registerTileEntity(TileEntityBasicWire.class, MODID + ":" + "BasicPipe");

		// ITEMS

		itemEnderDust = new ItemDust(64, "itemEnderDust");
		GameRegistry.registerItem(itemEnderDust, "itemEnderDust");

		itemEnderLense = new ItemBasic(16, "itemEnderLense");
		GameRegistry.registerItem(itemEnderLense, "itemEnderLense");

		itemEnderCrystal = new ItemBasic(64, "itemEnderCrystal");
		GameRegistry.registerItem(itemEnderCrystal, "itemEnderCrystal");

		itemDimensionIdentificationCrystal = new ItemDimensionIdentificationCrystal(1, "itemDimensionIdentificationCrystal");
		GameRegistry.registerItem(itemDimensionIdentificationCrystal, "itemDimensionIdentificationCrystal");

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

		Iterator<Item> itItem = Item.itemRegistry.iterator();
		while (itItem.hasNext()) {
			Item itemFood = itItem.next();
			if (itemFood instanceof ItemFood) {
				GameRegistry.addShapelessRecipe(new ItemStack(itemFood), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(itemFood));
				GameRegistry.addShapelessRecipe(new ItemStack(itemFood), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(itemFood));
				GameRegistry.addShapelessRecipe(new ItemStack(itemFood), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(itemFood));
				GameRegistry.addShapelessRecipe(new ItemStack(itemFood), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(itemFood));
				GameRegistry.addShapelessRecipe(new ItemStack(itemFood), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(itemFood));
				GameRegistry.addShapelessRecipe(new ItemStack(itemFood), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(itemFood));
				GameRegistry.addShapelessRecipe(new ItemStack(itemFood), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(itemFood));
				GameRegistry.addShapelessRecipe(new ItemStack(itemFood), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.potionitem, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(itemFood));
			}
		}

		// BIOMES

		// CRAFTINGS

		GameRegistry.addShapelessRecipe(new ItemStack(itemEnderDust), new ItemStack(Items.redstone, 2), new ItemStack(Items.ender_pearl, 2));

		GameRegistry.addSmelting(itemEnderDust, new ItemStack(itemEnderCrystal, 1), 0.1f);

		GameRegistry.addRecipe(new ItemStack(itemEnderLense), " x ", "x x",
				'x', new ItemStack(itemEnderCrystal));

		GameRegistry.addRecipe(new ItemStack(blockMachineBlock), "xxx", "xyx", "xzx",
				'x', new ItemStack(Items.iron_ingot), 'y', new ItemStack(itemEnderDust), 'z', new ItemStack(Items.redstone));

		GameRegistry.addRecipe(new ItemStack(blockTeleporterIdle), "xcx", "xyx", "xzx",
				'x', new ItemStack(blockMachineBlock), 'y', new ItemStack(itemEnderDust), 'c', new ItemStack(itemEnderLense), 'z', new ItemStack(Items.redstone));

		GameRegistry.addRecipe(new ItemStack(blockSimpleControllerIdle), "xcx", "czc", "xyx", 'x', new ItemStack(blockMachineBlock), 'y', new ItemStack(blockTeleporterIdle), 'z', new ItemStack(Items.redstone), 'c', new ItemStack(itemEnderLense));

		GameRegistry.addRecipe(new ItemStack(blockSunBlockIdle), "xzx", "xcx", "yyy",
				'x', new ItemStack(blockMachineBlock), 'y', new ItemStack(itemEnderLense), 'z', new ItemStack(Items.redstone), 'c', new ItemStack(Blocks.glowstone));

		GameRegistry.addRecipe(new ItemStack(blockGlassJar), "xzx", "x x", "xxx",
				'x', new ItemStack(Blocks.glass), 'z', new ItemStack(Blocks.wooden_slab));

		// POTIONS
		Potion[] potionTypes = null;
		/*
		 * for (Field f : Potion.class.getDeclaredFields()) {
		 * f.setAccessible(true); try { if (f.getName().equals("potionTypes") ||
		 * f.getName().equals("field_76425_a")) { Field modfield =
		 * Field.class.getDeclaredField("modifiers");
		 * modfield.setAccessible(true); modfield.setInt(f, f.getModifiers() &
		 * ~Modifier.FINAL);
		 * 
		 * potionTypes = (Potion[])f.get(null); final Potion[] newPotionTypes =
		 * new Potion[256]; System.arraycopy(potionTypes, 0, newPotionTypes, 0,
		 * potionTypes.length); f.set(null, newPotionTypes); } } catch
		 * (Exception e) {
		 * System.err.println("Severe error, please report this to the mod author:"
		 * ); System.err.println(e); } }
		 */

	}

	@EventHandler
	public void Init(FMLInitializationEvent e) {

		// ACHIEVEMNET
		achievementEnderDust = new Achievement("achievementEnderDust", "enderDust", 0, 0, DimensionShift.itemEnderDust, null);

		// ACHIEVMENET PAGE
		achievementPageDimensionShift = new AchievementPage("Dimension Shift", achievementEnderDust);
		AchievementPage.registerAchievementPage(achievementPageDimensionShift);

	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent e) {
		proxy.registerProxies();

		// EVENT HANDLER
		MinecraftForge.EVENT_BUS.register(new EventHandlerDimensionShift());
		FMLCommonHandler.instance().bus().register(new EventHandlerDimensionShift());

	}

}

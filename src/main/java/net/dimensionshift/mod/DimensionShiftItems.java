package net.dimensionshift.mod;

import net.dimensionshift.mod.item.ItemBasic;
import net.dimensionshift.mod.item.ItemDimensionIdentificationCrystal;
import net.dimensionshift.mod.item.ItemDust;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class DimensionShiftItems {
	
	final static String MODID = DimensionShift.MODID;

	
	
	//ITEMS
	public static Item itemEnderDust;

	public static Item itemEnderCrystal;

	public static Item itemEnderLense;

	public static Item itemDimensionIdentificationCrystal;
	
	
	
	public static void init()
	{
		itemEnderDust = new ItemDust(64, "itemEnderDust");

		itemEnderLense = new ItemBasic(16, "itemEnderLense");

		itemEnderCrystal = new ItemBasic(64, "itemEnderCrystal");

		itemDimensionIdentificationCrystal = new ItemDimensionIdentificationCrystal(1, "itemDimensionIdentificationCrystal");
	
	}
	
	public static void register()
	{
		GameRegistry.registerItem(itemEnderDust, "itemEnderDust");

		GameRegistry.registerItem(itemEnderLense, "itemEnderLense");

		GameRegistry.registerItem(itemEnderCrystal, "itemEnderCrystal");

		GameRegistry.registerItem(itemDimensionIdentificationCrystal, "itemDimensionIdentificationCrystal");

	}	
	
	public static void registerRenders()
	{
		registerRender(itemEnderDust);
		
		registerRender(itemEnderLense);

		registerRender(itemEnderCrystal);

		registerRender(itemDimensionIdentificationCrystal);

	}
	
	public static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(MODID + ":" + item.getUnlocalizedName()));;
	}
}
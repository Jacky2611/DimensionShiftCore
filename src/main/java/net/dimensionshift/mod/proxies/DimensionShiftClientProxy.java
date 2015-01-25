package net.dimensionshift.mod.proxies;

import net.dimensionshift.mod.DimensionShift;
import net.dimensionshift.mod.DimensionShiftBlocks;
import net.dimensionshift.mod.DimensionShiftItems;
import net.dimensionshift.mod.energy.TileEntityBasicWire;
import net.dimensionshift.mod.render.ItemRenderGlassJar;
import net.dimensionshift.mod.render.TileEntityRenderBasicWire;
import net.dimensionshift.mod.render.TileEntityRenderGlassJar;
import net.dimensionshift.mod.render.TileEntityRenderInvisibleBlock;
import net.dimensionshift.mod.tileentity.TileEntityGlassJar;
import net.dimensionshift.mod.tileentity.TileEntityInvisibleBlock;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;


public class DimensionShiftClientProxy extends DimensionShiftCommonProxy {
	TileEntitySpecialRenderer render;

	@Override
	public void registerRenders() {
		DimensionShiftBlocks.registerRenders();
		//DimensionShiftItems.registerRenders();
	}
	
	
	@Override
	public void registerProxies() {
		/*
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBasicWire.class, new TileEntityRenderBasicWire());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGlassJar.class, new TileEntityRenderGlassJar());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityInvisibleBlock.class, new TileEntityRenderInvisibleBlock());

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DimensionShift.blockGlassJar), new ItemRenderGlassJar(new TileEntityRenderGlassJar(), new TileEntityGlassJar()));
		*/
	}

}

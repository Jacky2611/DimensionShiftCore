package net.dimensionshift.mod.gui;

import net.dimensionshift.mod.DimensionShift;
import net.dimensionshift.mod.container.ContainerSimpleController;
import net.dimensionshift.mod.tileentity.TileEntitySimpleController;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiSimpleController extends GuiContainer {

	public static final ResourceLocation texture = new ResourceLocation(DimensionShift.MODID, "textures/gui/simpleController.png");

	public TileEntitySimpleController simpleController;

	public GuiSimpleController(InventoryPlayer inventory, TileEntitySimpleController entity) {
		super(new ContainerSimpleController(inventory, entity));

		this.simpleController = entity;

		this.xSize = 176;
		this.ySize = 166;
	}

	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2) {
		String name = this.simpleController.isInvNameLocalized() ? this.simpleController.getInvName() : I18n.format(this.simpleController.getInvName(), DimensionShift.instance);

		// Block Inventory Name
		this.fontRendererObj.drawString(name, this.xSize / 2 - fontRendererObj.getStringWidth(name) / 2, 6, 4210752);

		// Player Inventory Title
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 88, 4210752);
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		/**
		 * coords are x from left border and y from !!!!top!!!!
		 * 
		 * 
		 * this.drawTexturedModalRect(where to draw lefttop corner X, where to
		 * draw lefttop corner Y, From where to take image lefttop corner X,
		 * From where to take image lefttop corner Y, image width, image height
		 * 
		 * 
		 * 
		 */

		GL11.glColor4f(1F, 1F, 1F, 1F);

		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;

		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		// dimensionEnergy energy bar...

		// heightDimensionEnergyBar is how height the dimension energy display
		// is, needed for scaling
		int heightDimensionEnergyBar = 54;
		// width...
		int widthDimensionEnergyBar = 20;

		int currentDimensionEnergy = this.simpleController.getDimensionEnergyScaled(heightDimensionEnergyBar);

		this.drawTexturedModalRect(guiLeft + 15, guiTop + 20 + heightDimensionEnergyBar - currentDimensionEnergy, 176, heightDimensionEnergyBar - currentDimensionEnergy, widthDimensionEnergyBar, currentDimensionEnergy);

		// green light if energy is full
		if (this.simpleController.dimensionEnergy == this.simpleController.requiredDimensionEnergy) {
			this.drawTexturedModalRect(guiLeft + 160, guiTop + 49, 176, 54, 9, 9);
		}

		// TODO Auto-generated method stub

	}

}

package net.dimensionshift.mod.render;

import net.dimensionshift.mod.tileentity.TileEntityInvisibleBlock;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

public class TileEntityRenderInvisibleBlock extends TileEntitySpecialRenderer {

	public TileEntityRenderInvisibleBlock() {
		// initialize anything that will be needed later by the renderer,
		// like textures and custom 3d models
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

		// locationBlocksTexture is a "ResourceLocation" that points to a
		// texture made of many block "icons".
		// It will look very ugly, but creating our own ResourceLocation is
		// beyond the scope of this tutorial.

		TileEntityInvisibleBlock tileEntityInvisibleBlock = (TileEntityInvisibleBlock) tileentity;

		Block block = Blocks.bedrock;
		block = tileEntityInvisibleBlock.getBlock();
		block = Blocks.bookshelf;

		this.bindTexture(TextureMap.locationBlocksTexture);

		double minUSide0 = block.getBlockTextureFromSide(0).getMinU();
		double maxUSide0 = block.getBlockTextureFromSide(0).getMaxU();
		double minVSide0 = block.getBlockTextureFromSide(0).getMinV();
		double maxVSide0 = block.getBlockTextureFromSide(0).getMaxV();

		double minUSide1 = block.getBlockTextureFromSide(1).getMinU();
		double maxUSide1 = block.getBlockTextureFromSide(1).getMaxU();
		double minVSide1 = block.getBlockTextureFromSide(1).getMinV();
		double maxVSide1 = block.getBlockTextureFromSide(1).getMaxV();

		double minUSide2 = block.getBlockTextureFromSide(2).getMinU();
		double maxUSide2 = block.getBlockTextureFromSide(2).getMaxU();
		double minVSide2 = block.getBlockTextureFromSide(2).getMinV();
		double maxVSide2 = block.getBlockTextureFromSide(2).getMaxV();

		double minUSide3 = block.getBlockTextureFromSide(3).getMinU();
		double maxUSide3 = block.getBlockTextureFromSide(3).getMaxU();
		double minVSide3 = block.getBlockTextureFromSide(3).getMinV();
		double maxVSide3 = block.getBlockTextureFromSide(3).getMaxV();

		double minUSide4 = block.getBlockTextureFromSide(4).getMinU();
		double maxUSide4 = block.getBlockTextureFromSide(4).getMaxU();
		double minVSide4 = block.getBlockTextureFromSide(4).getMinV();
		double maxVSide4 = block.getBlockTextureFromSide(4).getMaxV();

		double minUSide5 = block.getBlockTextureFromSide(5).getMinU();
		double maxUSide5 = block.getBlockTextureFromSide(5).getMaxU();
		double minVSide5 = block.getBlockTextureFromSide(5).getMinV();
		double maxVSide5 = block.getBlockTextureFromSide(5).getMaxV();

		Tessellator tessellator = Tessellator.instance;

		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		
        RenderHelper.disableStandardItemLighting();

        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_CULL_FACE);

        if (Minecraft.isAmbientOcclusionEnabled())
        {
            GL11.glShadeModel(GL11.GL_SMOOTH);
        }
        else
        {
            GL11.glShadeModel(GL11.GL_FLAT);
        }
		
        GL11.glShadeModel(GL11.GL_SMOOTH);

		
		tessellator.startDrawingQuads();

		
		
        tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);

		
		
		// SIDE 0 DOWN
		tessellator.addVertexWithUV(0, 0, 1, minUSide0, maxVSide0);
		tessellator.addVertexWithUV(1, 0, 1, maxUSide0, maxVSide0);
		tessellator.addVertexWithUV(1, 0, 0, maxUSide0, minVSide0);
		tessellator.addVertexWithUV(0, 0, 0, minUSide0, minVSide0);

		tessellator.addVertexWithUV(0, 0, 0, minUSide0, minVSide0);
		tessellator.addVertexWithUV(1, 0, 0, maxUSide0, minVSide0);
		tessellator.addVertexWithUV(1, 0, 1, maxUSide0, maxVSide0);
		tessellator.addVertexWithUV(0, 0, 1, minUSide0, maxVSide0);

		// SIDE 1 UP
		/*
		tessellator.addVertexWithUV(0, 1, 1, minUSide1, maxVSide1);
		tessellator.addVertexWithUV(1, 1, 1, maxUSide1, maxVSide1);
		tessellator.addVertexWithUV(1, 1, 0, maxUSide1, minVSide1);
		tessellator.addVertexWithUV(0, 1, 0, minUSide1, minVSide1);
		*/
		tessellator.addVertexWithUV(0, 1, 0, minUSide1, minVSide1);
		tessellator.addVertexWithUV(1, 1, 0, maxUSide1, minVSide1);
		tessellator.addVertexWithUV(1, 1, 1, maxUSide1, maxVSide1);
		tessellator.addVertexWithUV(0, 1, 1, minUSide1, maxVSide1);

		// SIDE 2 NORTH
		tessellator.addVertexWithUV(0, 0, 0, minUSide2, maxVSide2);
		tessellator.addVertexWithUV(0, 1, 0, minUSide2, minVSide2);
		tessellator.addVertexWithUV(1, 1, 0, maxUSide2, minVSide2);
		tessellator.addVertexWithUV(1, 0, 0, maxUSide2, maxVSide2);

		tessellator.addVertexWithUV(1, 0, 0, maxUSide2, maxVSide2);
		tessellator.addVertexWithUV(1, 1, 0, maxUSide2, minVSide2);
		tessellator.addVertexWithUV(0, 1, 0, minUSide2, minVSide2);
		tessellator.addVertexWithUV(0, 0, 0, minUSide2, maxVSide2);

		// SIDE 3 SOUTH
		tessellator.addVertexWithUV(0, 0, 1, minUSide3, maxVSide3);
		tessellator.addVertexWithUV(0, 1, 1, minUSide3, minVSide3);
		tessellator.addVertexWithUV(1, 1, 1, maxUSide3, minVSide3);
		tessellator.addVertexWithUV(1, 0, 1, maxUSide3, maxVSide3);

		tessellator.addVertexWithUV(1, 0, 1, maxUSide3, maxVSide3);
		tessellator.addVertexWithUV(1, 1, 1, maxUSide3, minVSide3);
		tessellator.addVertexWithUV(0, 1, 1, minUSide3, minVSide3);
		tessellator.addVertexWithUV(0, 0, 1, minUSide3, maxVSide3);

		// SIDE 4 WEST
		tessellator.addVertexWithUV(0, 0, 0, minUSide4, maxVSide4);
		tessellator.addVertexWithUV(0, 0, 1, maxUSide4, maxVSide4);
		tessellator.addVertexWithUV(0, 1, 1, maxUSide4, minVSide4);
		tessellator.addVertexWithUV(0, 1, 0, minUSide4, minVSide4);

		tessellator.addVertexWithUV(0, 1, 0, minUSide4, minVSide4);
		tessellator.addVertexWithUV(0, 1, 1, maxUSide4, minVSide4);
		tessellator.addVertexWithUV(0, 0, 1, maxUSide4, maxVSide4);
		tessellator.addVertexWithUV(0, 0, 0, minUSide4, maxVSide4);

		// SIDE 5 EAST
		tessellator.addVertexWithUV(1, 1, 0, minUSide5, minVSide5);
		tessellator.addVertexWithUV(1, 1, 1, maxUSide5, minVSide5);
		tessellator.addVertexWithUV(1, 0, 1, maxUSide5, maxVSide5);
		tessellator.addVertexWithUV(1, 0, 0, minUSide5, maxVSide5);

		tessellator.addVertexWithUV(1, 0, 0, minUSide5, maxVSide5);
		tessellator.addVertexWithUV(1, 0, 1, maxUSide5, maxVSide5);
		tessellator.addVertexWithUV(1, 1, 1, maxUSide5, minVSide5);
		tessellator.addVertexWithUV(1, 1, 0, minUSide5, minVSide5);

		tessellator.draw();
		
		
        //RenderHelper.enableStandardItemLighting();

		GL11.glPopMatrix();
	}
}
package net.dimensionshift.mod.render;

import net.dimensionshift.mod.DimensionShift;
import net.dimensionshift.mod.model.ModelGlassJar;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class TileEntityRenderGlassJar extends TileEntitySpecialRenderer {

	public static final ResourceLocation texture = new ResourceLocation(DimensionShift.MODID, "textures/model/blockGlassJar.png");

	private ModelGlassJar model;

	public TileEntityRenderGlassJar() {
		this.model = new ModelGlassJar();

	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float var8) {

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			GL11.glRotatef(180, 0, 0, 1);
			GL11.glDisable(GL11.GL_CULL_FACE);
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);

			GL11.glPushMatrix();
				//GL11.glEnable(GL11.GL_BLEND);


				this.model.renderModel(0.0626F);// one pixel in float

				//GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
		
			GL11.glEnable(GL11.GL_CULL_FACE);

		GL11.glPopMatrix();

	}

}

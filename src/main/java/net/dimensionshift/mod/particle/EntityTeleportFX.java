package net.dimensionshift.mod.particle;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_GREATER;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glAlphaFunc;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDepthMask;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import net.dimensionshift.mod.DimensionShift;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityTeleportFX extends EntityFX {

	private static final ResourceLocation texture = new ResourceLocation(DimensionShift.MODID, "textures/particle/teleport.png");

	public EntityTeleportFX(World world, double x, double y, double z) {
		super(world, x, y, z);
		setMaxAge(100);
		setGravity(0);

	}

	@Override
	public void renderParticle(Tessellator tess, float partialTicks, float par3, float par4, float par5, float par6, float par7) {
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);

		glDepthMask(false);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glAlphaFunc(GL_GREATER, 0.003921569F); // transparenc????

		// tess.startDrawingQuads();
		tess.setBrightness(getBrightnessForRender(partialTicks));

		float scale = 0.1F * particleScale;

		float x = (float) (prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
		float y = (float) (prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
		float z = (float) (prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);

		tess.addVertexWithUV(x - par3 * scale - par6 * scale, y - par4 * scale, z - par5 * scale - par7 * scale, 0, 0);
		tess.addVertexWithUV(x - par3 * scale + par6 * scale, y + par4 * scale, z - par5 * scale + par7 * scale, 1, 0);
		tess.addVertexWithUV(x + par3 * scale + par6 * scale, y + par4 * scale, z - par5 * scale + par7 * scale, 1, 1);
		tess.addVertexWithUV(x + par3 * scale - par6 * scale, y - par4 * scale, z - par5 * scale - par7 * scale, 0, 1);

		// tess.draw();
		glDisable(GL_BLEND);
		glDepthMask(true);
		glAlphaFunc(GL_GREATER, 0.0F);
	}

	@Override
	public int getFXLayer() {
		return 1; // every number thats not 1 or 3???
	}

	public EntityTeleportFX setMaxAge(int maxAge) {
		particleMaxAge = maxAge;
		return this;
	}

	public EntityTeleportFX setGravity(int gravity) {
		particleGravity = gravity;
		return this;
	}

	public EntityTeleportFX setScale(int scale) {
		particleScale = scale;
		return this;
	}

}

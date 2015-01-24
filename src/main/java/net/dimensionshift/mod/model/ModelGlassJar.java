package net.dimensionshift.mod.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGlassJar extends ModelBase {
	// fields
	ModelRenderer outside;
	ModelRenderer inside;
	ModelRenderer border;
	ModelRenderer cover;

	public ModelGlassJar() {
		textureWidth = 128;
		textureHeight = 64;

		outside = new ModelRenderer(this, 0, 0);
		outside.addBox(0F, 0F, 0F, 10, 12, 10);
		outside.setRotationPoint(-5F, 12F, -5F);
		outside.setTextureSize(128, 64);
		outside.mirror = true;
		setRotation(outside, 0F, 0F, 0F);
		inside = new ModelRenderer(this, 40, 0);
		inside.addBox(0F, 0F, 0F, 8, 10, 8);
		inside.setRotationPoint(-4F, 13F, -4F);
		inside.setTextureSize(128, 64);
		inside.mirror = true;
		setRotation(inside, 0F, 0F, 0F);
		border = new ModelRenderer(this, 72, 0);
		border.addBox(0F, 0F, 0F, 4, 2, 4);
		border.setRotationPoint(-2F, 11F, -2F);
		border.setTextureSize(128, 64);
		border.mirror = true;
		setRotation(border, 0F, 0F, 0F);
		cover = new ModelRenderer(this, 88, 0);
		cover.addBox(0F, 0F, 0F, 6, 1, 6);
		cover.setRotationPoint(-3F, 10F, -3F);
		cover.setTextureSize(128, 64);
		cover.mirror = true;
		setRotation(cover, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		outside.render(f5);
		inside.render(f5);
		border.render(f5);
		cover.render(f5);
	}

	public void renderModel(float f) {

		outside.render(f);
		inside.render(f);
		border.render(f);
		cover.render(f);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {

		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}

package SimplyTools.assembler;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import SimplyTools.API.AssemblerModel;

public class AssemblerModelMK1 extends AssemblerModel {

	public ModelRenderer base, SidePart, SidePart1, SidePart2, SidePart3;

	public AssemblerModelMK1() {
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this, 0, 2);
		base.mirror = true;
		base.addBox(0F, 0F, 0F, 16, 15, 16);
		base.setRotationPoint(-8F, 0F, -8F);
		base.setTextureSize(textureWidth, textureHeight);

		// part = new ModelRenderer(this, 4, 33);
		// part.mirror = true;
		// part.addBox(0F, 0F, 0F, 14, 1, 14);
		// part.setRotationPoint(-7F, 15F, -7F);
		// part.setTextureSize(textureWidth, textureHeight);

		SidePart = new ModelRenderer(this, 48, 1);
		SidePart.mirror = true;
		SidePart.addBox(0F, 0F, 0F, 1, 1, 16);
		SidePart.setRotationPoint(7F, 15F, -8F);
		SidePart.setTextureSize(textureWidth, textureHeight);

		SidePart1 = new ModelRenderer(this, 48, 1);
		SidePart1.mirror = true;
		SidePart1.addBox(0F, 0F, 0F, 1, 1, 16);
		SidePart1.setRotationPoint(-8F, 15F, -8F);
		SidePart1.setTextureSize(textureWidth, textureHeight);

		SidePart2 = new ModelRenderer(this, 0, 0);
		SidePart2.mirror = true;
		SidePart2.addBox(0F, 0F, 0F, 14, 1, 1);
		SidePart2.setRotationPoint(-7F, 15F, -8F);
		SidePart2.setTextureSize(textureWidth, textureHeight);

		SidePart3 = new ModelRenderer(this, 0, 0);
		SidePart3.mirror = true;
		SidePart3.addBox(0F, 0F, 0F, 14, 1, 1);
		SidePart3.setRotationPoint(-7F, 15F, 7F);
		SidePart3.setTextureSize(textureWidth, textureHeight);

		setRotation(base, 0F, 0F, 0F);
		setRotation(SidePart, 0F, 0F, 0F);
		setRotation(SidePart1, 0F, 0F, 0F);
		setRotation(SidePart2, 0F, 0F, 0F);
		setRotation(SidePart3, 0F, 0F, 0F);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);

		base.render(f5);

		SidePart.render(f5);
		SidePart1.render(f5);
		SidePart2.render(f5);
		SidePart3.render(f5);
	}

	public void render(float f5) {
		base.render(f5);

		SidePart.render(f5);
		SidePart1.render(f5);
		SidePart2.render(f5);
		SidePart3.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}

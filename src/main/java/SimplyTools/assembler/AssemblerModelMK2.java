package SimplyTools.assembler;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import SimplyTools.API.AssemblerModel;

public class AssemblerModelMK2 extends AssemblerModel {

	//variables init:
	public ModelRenderer add1base;
	public ModelRenderer add1strip1;
	public ModelRenderer add1strip2;
	public ModelRenderer add1strip3;
	public ModelRenderer add2base;
	public ModelRenderer add2strip1;
	public ModelRenderer add2strip2;
	public ModelRenderer add2strip3;
	public ModelRenderer add3base;
	public ModelRenderer add3strip1;
	public ModelRenderer add3strip2;
	public ModelRenderer add3strip3;
	public ModelRenderer add4base;
	public ModelRenderer add4strip1;
	public ModelRenderer add4strip2;
	public ModelRenderer add4strip3;
	public ModelRenderer base;
	public ModelRenderer basestrip1;
	public ModelRenderer basestrip2;
	public ModelRenderer basestrip3;
	public ModelRenderer basestrip4;

	public AssemblerModelMK2() {
		textureWidth = 512;
		textureHeight = 512;

		add1base = new ModelRenderer(this, 0, 0);
		add1base.addBox(0F, 0F, 0F, 16, 1, 16);
		add1base.setRotationPoint(8F, 14F, -8F);
		add1base.setTextureSize(textureWidth, textureHeight);

		add1strip1 = new ModelRenderer(this, 30, 17);
		add1strip1.addBox(0F, 0F, 0F, 16, 1, 1);
		add1strip1.setRotationPoint(8F, 15F, -8F);
		add1strip1.setTextureSize(textureWidth, textureHeight);

		add1strip2 = new ModelRenderer(this, 30, 17);
		add1strip2.addBox(0F, 0F, 0F, 16, 1, 1);
		add1strip2.setRotationPoint(8F, 15F, 7F);
		add1strip2.setTextureSize(textureWidth, textureHeight);

		add1strip3 = new ModelRenderer(this, 2, 21);
		add1strip3.addBox(0F, 0F, 0F, 1, 1, 14);
		add1strip3.setRotationPoint(23F, 15F, -7F);
		add1strip3.setTextureSize(textureWidth, textureHeight);

		add2base = new ModelRenderer(this, 0, 0);
		add2base.addBox(0F, 0F, 0F, 16, 1, 16);
		add2base.setRotationPoint(-8F, 14F, 8F);
		add2base.setTextureSize(textureWidth, textureHeight);

		add2strip1 = new ModelRenderer(this, 0, 19);
		add2strip1.addBox(0F, 0F, 0F, 1, 1, 16);
		add2strip1.setRotationPoint(-8F, 15F, 8F);
		add2strip1.setTextureSize(textureWidth, textureHeight);

		add2strip2 = new ModelRenderer(this, 0, 17);
		add2strip2.addBox(0F, 0F, 0F, 14, 1, 1);
		add2strip2.setRotationPoint(-7F, 15F, 23F);
		add2strip2.setTextureSize(textureWidth, textureHeight);

		add2strip3 = new ModelRenderer(this, 0, 19);
		add2strip3.addBox(0F, 0F, 0F, 1, 1, 16);
		add2strip3.setRotationPoint(7F, 15F, 8F);
		add2strip3.setTextureSize(textureWidth, textureHeight);

		add3base = new ModelRenderer(this, 0, 0);
		add3base.addBox(0F, 0F, 0F, 16, 1, 16);
		add3base.setRotationPoint(-24F, 14F, -8F);
		add3base.setTextureSize(textureWidth, textureHeight);

		add3strip1 = new ModelRenderer(this, 32, 17);
		add3strip1.addBox(0F, 0F, 0F, 15, 1, 1);
		add3strip1.setRotationPoint(-23F, 15F, 7F);
		add3strip1.setTextureSize(textureWidth, textureHeight);

		add3strip2 = new ModelRenderer(this, 32, 17);
		add3strip2.addBox(0F, 0F, 0F, 15, 1, 1);
		add3strip2.setRotationPoint(-23F, 15F, -8F);
		add3strip2.setTextureSize(textureWidth, textureHeight);

		add3strip3 = new ModelRenderer(this, 0, 19);
		add3strip3.addBox(0F, 0F, 0F, 1, 1, 16);
		add3strip3.setRotationPoint(-24F, 15F, -8F);
		add3strip3.setTextureSize(textureWidth, textureHeight);

		add4base = new ModelRenderer(this, 0, 0);
		add4base.addBox(0F, 0F, 0F, 16, 1, 16);
		add4base.setRotationPoint(-8F, 14F, -24F);
		add4base.setTextureSize(textureWidth, textureHeight);

		add4strip1 = new ModelRenderer(this, 0, 17);
		add4strip1.addBox(0F, 0F, 0F, 14, 1, 1);
		add4strip1.setRotationPoint(-7F, 15F, -24F);
		add3strip1.setTextureSize(textureWidth, textureHeight);

		add4strip2 = new ModelRenderer(this, 0, 19);
		add4strip2.addBox(0F, 0F, 0F, 1, 1, 16);
		add4strip2.setRotationPoint(7F, 15F, -24F);
		add4strip2.setTextureSize(textureWidth, textureHeight);

		add4strip3 = new ModelRenderer(this, 0, 19);
		add4strip3.addBox(0F, 0F, 0F, 1, 1, 16);
		add4strip3.setRotationPoint(-8F, 15F, -24F);
		add4strip3.setTextureSize(textureWidth, textureHeight);

		base = new ModelRenderer(this, 48, 3);
		base.addBox(0F, 0F, 0F, 16, 15, 16);
		base.setRotationPoint(-8F, 0F, -8F);
		base.setTextureSize(textureWidth, textureHeight);

		basestrip1 = new ModelRenderer(this, 0, 19);
		basestrip1.addBox(0F, 0F, 0F, 1, 1, 16);
		basestrip1.setRotationPoint(-8F, 15F, -8F);
		basestrip1.setTextureSize(textureWidth, textureHeight);

		basestrip2 = new ModelRenderer(this, 0, 19);
		basestrip2.addBox(0F, 0F, 0F, 1, 1, 16);
		basestrip2.setRotationPoint(7F, 15F, -8F);
		basestrip2.setTextureSize(textureWidth, textureHeight);

		basestrip3 = new ModelRenderer(this, 0, 17);
		basestrip3.addBox(0F, 0F, 0F, 14, 1, 1);
		basestrip3.setRotationPoint(-7F, 15F, -8F);
		basestrip3.setTextureSize(textureWidth, textureHeight);

		basestrip4 = new ModelRenderer(this, 0, 17);
		basestrip4.addBox(0F, 0F, 0F, 14, 1, 1);
		basestrip4.setRotationPoint(-7F, 15F, 7F);
		basestrip4.setTextureSize(textureWidth, textureHeight);
	}

	//render:

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		add1base.render(f5);
		add1strip1.render(f5);
		add1strip2.render(f5);
		add1strip3.render(f5);
		add2base.render(f5);
		add2strip1.render(f5);
		add2strip2.render(f5);
		add2strip3.render(f5);
		add3base.render(f5);
		add3strip1.render(f5);
		add3strip2.render(f5);
		add3strip3.render(f5);
		add4base.render(f5);
		add4strip1.render(f5);
		add4strip2.render(f5);
		add4strip3.render(f5);
		base.render(f5);
		basestrip1.render(f5);
		basestrip2.render(f5);
		basestrip3.render(f5);
		basestrip4.render(f5);
	}

	public void render(float f5) {
		add1base.render(f5);
		add1strip1.render(f5);
		add1strip2.render(f5);
		add1strip3.render(f5);
		add2base.render(f5);
		add2strip1.render(f5);
		add2strip2.render(f5);
		add2strip3.render(f5);
		add3base.render(f5);
		add3strip1.render(f5);
		add3strip2.render(f5);
		add3strip3.render(f5);
		add4base.render(f5);
		add4strip1.render(f5);
		add4strip2.render(f5);
		add4strip3.render(f5);
		base.render(f5);
		basestrip1.render(f5);
		basestrip2.render(f5);
		basestrip3.render(f5);
		basestrip4.render(f5);
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

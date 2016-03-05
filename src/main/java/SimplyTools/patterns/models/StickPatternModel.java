package SimplyTools.patterns.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import SimplyTools.patterns.PatternModel;

public class StickPatternModel extends PatternModel {

	public ModelRenderer P1;
	public ModelRenderer P10;
	public ModelRenderer P3;
	public ModelRenderer P4;
	public ModelRenderer P5;
	public ModelRenderer P6;
	public ModelRenderer P7;
	public ModelRenderer P8;
	public ModelRenderer P9;
	public ModelRenderer PP1;
	public ModelRenderer PV1;
	public ModelRenderer PV2;
	public ModelRenderer PV3;
	public ModelRenderer PV4;
	public ModelRenderer PV5;
	public ModelRenderer PV6;
	public ModelRenderer PV7;
	public ModelRenderer PV8;
	public ModelRenderer PV9;
	public ModelRenderer PVV10;

	public StickPatternModel() {
		super();

		int textureWidth = 64;
		int textureHeight = 32;

		P1 = new ModelRenderer(this, 0, 14);
		P1.addBox(0F, 0F, 0F, 10, 1, 1);
		P1.setRotationPoint(-4F, 16F, -6F);

		P10 = new ModelRenderer(this, 10, 14);
		P10.addBox(0F, 0F, 0F, 1, 1, 1);
		P10.setRotationPoint(5F, 16F, 3F);

		P3 = new ModelRenderer(this, 1, 14);
		P3.addBox(0F, 0F, 0F, 8, 1, 1);
		P3.setRotationPoint(-2F, 16F, -4F);

		P4 = new ModelRenderer(this, 2, 14);
		P4.addBox(0F, 0F, 0F, 7, 1, 1);
		P4.setRotationPoint(-1F, 16F, -3F);

		P5 = new ModelRenderer(this, 1, 14);
		P5.addBox(0F, 0F, 0F, 6, 1, 1);
		P5.setRotationPoint(0F, 16F, -2F);

		P6 = new ModelRenderer(this, 1, 14);
		P6.addBox(0F, 0F, 0F, 5, 1, 1);
		P6.setRotationPoint(1F, 16F, -1F);

		P7 = new ModelRenderer(this, 0, 14);
		P7.addBox(0F, 0F, 0F, 4, 1, 1);
		P7.setRotationPoint(2F, 16F, 0F);

		P8 = new ModelRenderer(this, 13, 14);
		P8.addBox(0F, 0F, 0F, 3, 1, 1);
		P8.setRotationPoint(3F, 16F, 1F);

		P9 = new ModelRenderer(this, 3, 14);
		P9.addBox(0F, 0F, 0F, 2, 1, 1);
		P9.setRotationPoint(4F, 16F, 2F);

		PP1 = new ModelRenderer(this, 0, 14);
		PP1.addBox(0F, 0F, 0F, 9, 1, 1);
		PP1.setRotationPoint(-3F, 16F, -5F);

		PV1 = new ModelRenderer(this, 0, 14);
		PV1.mirror = true;
		PV1.addBox(-3F, 0F, 0F, 10, 1, 1);
		PV1.setRotationPoint(-5F, 16F, -1F);
		PV1.rotateAngleY = 1.5707963267948966F;

		PV2 = new ModelRenderer(this, 0, 14);
		PV2.addBox(-3F, 0F, 0F, 9, 1, 1);
		PV2.setRotationPoint(-4F, 16F, 0F);
		PV2.rotateAngleY = 1.5707963267948966F;

		PV3 = new ModelRenderer(this, 2, 14);
		PV3.addBox(-3F, 0F, 0F, 8, 1, 1);
		PV3.setRotationPoint(-3F, 16F, 1F);
		PV3.rotateAngleY = 1.5707963267948966F;

		PV4 = new ModelRenderer(this, 5, 14);
		PV4.addBox(-3F, 0F, 0F, 7, 1, 1);
		PV4.setRotationPoint(-2F, 16F, 2F);
		PV4.rotateAngleY = 1.5707963267948966F;

		PV5 = new ModelRenderer(this, 2, 14);
		PV5.addBox(-3F, 0F, 0F, 6, 1, 1);
		PV5.setRotationPoint(-1F, 16F, 3F);
		PV5.rotateAngleY = 1.5707963267948966F;

		PV6 = new ModelRenderer(this, 2, 14);
		PV6.addBox(-3F, 0F, 0F, 5, 1, 1);
		PV6.setRotationPoint(0F, 16F, 4F);
		PV6.rotateAngleY = 1.5707963267948966F;

		PV7 = new ModelRenderer(this, 12, 14);
		PV7.addBox(-3F, 0F, 0F, 4, 1, 1);
		PV7.setRotationPoint(1F, 16F, 5F);
		PV7.rotateAngleY = 1.5707963267948966F;

		PV8 = new ModelRenderer(this, 8, 14);
		PV8.addBox(-3F, 0F, 0F, 3, 1, 1);
		PV8.setRotationPoint(2F, 16F, 6F);
		PV8.rotateAngleY = 1.5707963267948966F;

		PV9 = new ModelRenderer(this, 19, 14);
		PV9.addBox(-3F, 0F, 0F, 2, 1, 1);
		PV9.setRotationPoint(3F, 16F, 7F);
		PV9.rotateAngleY = 1.5707963267948966F;

		PVV10 = new ModelRenderer(this, 21, 14);
		PVV10.addBox(-3F, 0F, 0F, 1, 1, 1);
		PVV10.setRotationPoint(4F, 16F, 8F);
		PVV10.rotateAngleY = 1.5707963267948966F;

		P1.setTextureSize(textureWidth, textureHeight);
		P10.setTextureSize(textureWidth, textureHeight);
		P3.setTextureSize(textureWidth, textureHeight);
		P4.setTextureSize(textureWidth, textureHeight);
		P5.setTextureSize(textureWidth, textureHeight);
		P6.setTextureSize(textureWidth, textureHeight);
		P7.setTextureSize(textureWidth, textureHeight);
		P8.setTextureSize(textureWidth, textureHeight);
		P9.setTextureSize(textureWidth, textureHeight);
		PP1.setTextureSize(textureWidth, textureHeight);
		PV1.setTextureSize(textureWidth, textureHeight);
		PV2.setTextureSize(textureWidth, textureHeight);
		PV3.setTextureSize(textureWidth, textureHeight);
		PV4.setTextureSize(textureWidth, textureHeight);
		PV5.setTextureSize(textureWidth, textureHeight);
		PV6.setTextureSize(textureWidth, textureHeight);
		PV7.setTextureSize(textureWidth, textureHeight);
		PV8.setTextureSize(textureWidth, textureHeight);
		PV9.setTextureSize(textureWidth, textureHeight);
		PVV10.setTextureSize(textureWidth, textureHeight);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		P1.render(f5);
		P10.render(f5);
		P3.render(f5);
		P4.render(f5);
		P5.render(f5);
		P6.render(f5);
		P7.render(f5);
		P8.render(f5);
		P9.render(f5);
		PP1.render(f5);
		PV1.render(f5);
		PV2.render(f5);
		PV3.render(f5);
		PV4.render(f5);
		PV5.render(f5);
		PV6.render(f5);
		PV7.render(f5);
		PV8.render(f5);
		PV9.render(f5);
		PVV10.render(f5);
	}

	public void render(float f5) {
		super.render(f5);
		P1.render(f5);
		P10.render(f5);
		P3.render(f5);
		P4.render(f5);
		P5.render(f5);
		P6.render(f5);
		P7.render(f5);
		P8.render(f5);
		P9.render(f5);
		PP1.render(f5);
		PV1.render(f5);
		PV2.render(f5);
		PV3.render(f5);
		PV4.render(f5);
		PV5.render(f5);
		PV6.render(f5);
		PV7.render(f5);
		PV8.render(f5);
		PV9.render(f5);
		PVV10.render(f5);
	}
}

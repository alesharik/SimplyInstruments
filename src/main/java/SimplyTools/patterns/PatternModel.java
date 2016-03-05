package SimplyTools.patterns;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class PatternModel extends ModelBase {

	public ModelRenderer line1;
	public ModelRenderer line2;
	public ModelRenderer line3;
	public ModelRenderer line4;

	public PatternModel() {
		line1 = new ModelRenderer(this, 0, 17);
		line1.addBox(0F, 0F, 0F, 1, 1, 14);
		line1.setRotationPoint(-7F, 16F, -7F);

		line2 = new ModelRenderer(this, 0, 17);
		line2.addBox(0F, 0F, 0F, 1, 1, 14);
		line2.setRotationPoint(6F, 16F, -7F);

		line3 = new ModelRenderer(this, 38, 30);
		line3.addBox(0F, 0F, 0F, 12, 1, 1);
		line3.setRotationPoint(-6F, 16F, -7F);

		line4 = new ModelRenderer(this, 38, 30);
		line4.addBox(0F, 0F, 0F, 12, 1, 1);
		line4.setRotationPoint(-6F, 16F, 6F);

		setRotation(line2, 0, 0, 0);
		setRotation(line2, 0, 0, 0);
		setRotation(line3, 0, 0, 0);
		setRotation(line4, 0, 0, 0);

		line1.setTextureSize(textureWidth, textureHeight);
		line2.setTextureSize(textureWidth, textureHeight);
		line3.setTextureSize(textureWidth, textureHeight);
		line4.setTextureSize(textureWidth, textureHeight);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		line1.render(f5);
		line2.render(f5);
		line3.render(f5);
		line4.render(f5);
	}

	public void render(float f5) {
		line1.render(f5);
		line2.render(f5);
		line3.render(f5);
		line4.render(f5);
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

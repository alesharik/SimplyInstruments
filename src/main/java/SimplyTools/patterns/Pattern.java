package SimplyTools.patterns;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class Pattern extends Item {

	private PatternModel model = null;
	private ResourceLocation texture = null;
	private ResourceLocation texturePreview = null;
	private boolean[] matrix = new boolean[9];

	public Pattern() {
		this.setMaxDamage(0);
		this.setMaxStackSize(16);
	}

	public Item setModel(PatternModel model) {
		this.model = model;
		return this;
	}

	public PatternModel getModel() {
		return this.model;
	}

	public Item setTextureName(String textureName) {
		this.texture = new ResourceLocation(textureName);
		return this;
	}

	public ResourceLocation getTexture() {
		return this.texture;
	}

	public Item setUnlocalizedName(String name) {
		this.setUnlocalizedName("SIPattern" + name);
		return this;
	}

	public Item setMatrix(boolean[] matrix) {
		this.matrix = matrix;
		return this;
	}

	public boolean[] getMatrix() {
		return this.matrix;
	}

	public Item setTexturePrewiev(ResourceLocation tp) {
		this.texturePreview = tp;
		return this;
	}

	public ResourceLocation getTexturePrewiev() {
		return this.texturePreview;
	}

}

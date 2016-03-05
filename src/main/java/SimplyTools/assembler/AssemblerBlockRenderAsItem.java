package SimplyTools.assembler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class AssemblerBlockRenderAsItem implements IItemRenderer {

	AssemblerModelMK1 assemblerModel = new AssemblerModelMK1();
	ResourceLocation texture = new ResourceLocation("SimplyTools:textures/blocks/assemblerMK1.png");

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 0, 0.5F);
		if(type == ItemRenderType.INVENTORY) {
			GL11.glScalef(1F, 1F, 1F);
			GL11.glTranslatef(0.0F, -0.1F, 0.0F);
		}
		assemblerModel.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}
}

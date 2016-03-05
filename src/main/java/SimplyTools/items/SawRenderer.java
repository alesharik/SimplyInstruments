package SimplyTools.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class SawRenderer implements IItemRenderer {
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.INVENTORY;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glPushMatrix();
		GL11.glScalef(2F, 2F, 2F);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("simplytools", "textures/items/saw2.png"));
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawing(GL11.GL_QUADS);
		tessellator.addVertexWithUV(0, 0, 0, 0, 0);
		tessellator.addVertexWithUV(0, 8, 0, 0, 1);
		tessellator.addVertexWithUV(8, 8, 0, 1, 1);
		tessellator.addVertexWithUV(8, 0, 0, 1, 0);
		tessellator.draw();
		GL11.glPopMatrix();
	}
}

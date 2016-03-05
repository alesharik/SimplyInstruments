package SimplyTools.API;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class CustomRenderer implements IItemRenderer {

	private BufferedImage texture;
	private boolean t = false;
	private Integer index = 1;
	private HashMap<Integer, ResourceLocation> texturesMap = new HashMap<Integer, ResourceLocation>();
	Tessellator tessellator = Tessellator.instance;

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_BLEND);
		if(!texturesMap.containsKey(item.getItemDamage())) {
			try {
				texturesMap.put(index, Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation("DynTex" + index, new DynamicTexture(loadCustomTexture(item))));
				item.setItemDamage(index.intValue());
				index++;
			} catch (NullPointerException e) {
				System.err.println("Oops! In item " + item + "is no NBT");
			}
		}
		try {
			if(item != null && item.getItemDamage() > 0) {
				Minecraft.getMinecraft().renderEngine.bindTexture(texturesMap.get(item.getItemDamage()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		switch(type) {
			case INVENTORY:
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glDisable(GL12.GL_RESCALE_NORMAL);
				GL11.glEnable(GL11.GL_ALPHA_TEST);
				GL11.glDisable(GL11.GL_BLEND);
				//				GL11.glEnable(GL11.GL_BLEND);
				GL11.glColor4f(1, 1, 1, 0.5F);
				OpenGlHelper.glBlendFunc(770, 771, 1, 0);
				//				RenderHelper.disableStandardItemLighting();
				GL11.glScalef(2F, 2F, 2F);
				tessellator.startDrawing(GL11.GL_QUADS);
				tessellator.addVertexWithUV(0, 0, 0, 0, 0);
				tessellator.addVertexWithUV(0, 8, 0, 0, 1);
				tessellator.addVertexWithUV(8, 8, 0, 1, 1);
				tessellator.addVertexWithUV(8, 0, 0, 1, 0);
				tessellator.draw();
				//				RenderHelper.enableStandardItemLighting();
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glEnable(GL11.GL_CULL_FACE);
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				//				GL11.glEnable(GL11.GL_BLEND);

				break;
			case EQUIPPED_FIRST_PERSON:
			case EQUIPPED:
				ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 32, 32, 0.0625F);
				break;
			default:
				ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 32, 32, 0.0625F);
				break;
		}

		GL11.glPopMatrix();
	}

	private BufferedImage loadCustomTexture(ItemStack item) throws NullPointerException {
		NBTTagCompound nbt = item.stackTagCompound;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(nbt.getByteArray("texture"));
			return ImageIO.read(bais);
		} catch (IOException e) {
			System.err.println("Oops! Read error in custom renderer of" + item);
			e.printStackTrace();
		}
		return new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
	}

	// def - 0, 0, 0, 0
	// black - 50, 128, 128, 128
	// corner - 100, 255, 255, 255

}

package SimplyTools.assembler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import SimplyTools.Service;
import SimplyTools.items.Items;
import cpw.mods.fml.relauncher.Side;

public class AssemblerGUI extends GuiContainer {

	private AssemblerTileEntity tileEntity;

	public AssemblerGUI(InventoryPlayer inventoryPlayer, AssemblerTileEntity tileEntity) {
		super(new AssemblerBlockContainer(inventoryPlayer, tileEntity));
		this.tileEntity = tileEntity;
		this.isSawInTable = this.tileEntity.getSawInTable();
		this.ySize = 250;
	}

	@Override
	public void initGui() {
		super.initGui();
		this.mc.thePlayer.openContainer = this.inventorySlots;
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
		if(this.isSawInTable) {
			buttonList.add(new GuiButton(1, this.guiLeft + 10, this.guiTop + 46, 25, 18, "Saw"));
		}
		buttonList.add(new GuiButton(2, this.guiLeft + 120, this.guiTop + 55, 45, 18, "Create!"));
	}

	// ==========Start timer init!========== \\
	private final Timer timer = new Timer(333, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(tileEntity.time > 0) {
				tileEntity.time--;
				tileEntity.processDelay++;
			} else {
				tileEntity.processDelay = 0;
				tileEntity.time = 30;
				NBTTagCompound data = new NBTTagCompound();
				data.setInteger("xCoord", tileEntity.xCoord);
				data.setInteger("yCoord", tileEntity.yCoord);
				data.setInteger("zCoord", tileEntity.zCoord);
				Service.callMethodRemote("finalizeCraft", data, Side.SERVER);
				timer.stop();
			}
		}
	});

	// ==========End timer init!========== \\

	protected void actionPerformed(GuiButton guibutton) {
		switch(guibutton.id) {
			case 1:
				if(!this.isSawAnimated) {
					if(tileEntity.inv[12] != null) {
						this.sawAnimationStep = true;
						this.isSawAnimated = true;
					}
				}
				break;
			case 2:
				if(!timer.isRunning() && this.tileEntity.canCraft()) {
					NBTTagCompound data = new NBTTagCompound();
					data.setInteger("xCoord", this.tileEntity.xCoord);
					data.setInteger("yCoord", this.tileEntity.yCoord);
					data.setInteger("zCoord", this.tileEntity.zCoord);
					Service.callMethodRemote("craftItem", data, Side.SERVER);
					timer.start();
				}
				break;
		}
		this.tileEntity.sendNBTUpdate();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		this.mc.renderEngine.bindTexture(new ResourceLocation("simplytools", "textures/gui/AssemblerGui1Tier.png"));
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glColor3f(1F, 1F, 1F);
		Minecraft.getMinecraft().fontRenderer.drawString("Assembler", 8, 8, 0x4265b6);
		Minecraft.getMinecraft().fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 127, 0x4265b6);
		GL11.glDisable(GL11.GL_BLEND);

		GL11.glPushMatrix();
		mc.renderEngine.bindTexture(new ResourceLocation("simplytools", "textures/gui/AssemblerGui1Tier.png"));
		this.drawTexturedModalRect(115, 31, 177, 168, tileEntity.processDelay, 17);
		GL11.glPopMatrix();

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		this.isSawInputInTable = tileEntity.inv[12] != null;

		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation("simplytools", "textures/gui/AssemblerGui1Tier.png"));
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0F);
		long currentTime = System.nanoTime();
		float speed = (currentTime - lastTime) / 10000000F * 3F;
		if(sawAnimationStep && this.isSawAnimated) {
			this.sawAnimationPosition += speed;
			if(this.sawAnimationPosition >= 0 && !isSawAnimationSemi) {
				this.sawAnimationSteps++;
				isSawAnimationSemi = true;
			}
		} else if(!sawAnimationStep && this.isSawAnimated) {
			this.sawAnimationPosition -= speed;
			if(this.sawAnimationPosition <= 0 && !isSawAnimationSemi) {
				this.sawAnimationSteps++;
				isSawAnimationSemi = true;
			}
		}
		if(this.sawAnimationPosition <= -100F) {
			this.sawAnimationStep = true;
			this.sawAnimationPosition = -100F;
			isSawAnimationSemi = false;
		}
		if(this.sawAnimationPosition >= 100F) {
			this.sawAnimationStep = false;
			this.sawAnimationPosition = 100F;
			isSawAnimationSemi = false;
		}
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glColor3f(1F, 1F, 1F);
		GL11.glPushMatrix();

		Tessellator tessellator = Tessellator.instance;
		this.mc.renderEngine.bindTexture(new ResourceLocation("simplytools", "textures/gui/AssemblerGuiWood.png"));
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		if(this.isSawInputInTable) {
			GL11.glPushMatrix();
			GL11.glTranslatef(145F, 110F, 260F);

			GL11.glScalef(16, 16, 16);
			GL11.glRotatef(30, 0F, -1F, 0F);
			GL11.glRotatef(10F, -1F, 0F, 0F);
			tessellator.startDrawing(GL11.GL_QUADS);
			tessellator.addVertexWithUV(0.5f, -0.5f, -0.5f, 0, 0);
			tessellator.addVertexWithUV(-0.5f, -0.5f, -0.5f, 0, 1);
			tessellator.addVertexWithUV(-0.5f, 0.5f, -0.5f, 1, 1);
			tessellator.addVertexWithUV(0.5f, 0.5f, -0.5f, 1, 0);

			tessellator.addVertexWithUV(0.5f, -0.5f, 0.5f, 0, 0);
			tessellator.addVertexWithUV(0.5f, 0.5f, 0.5f, 0, 1);
			tessellator.addVertexWithUV(-0.5f, 0.5f, 0.5f, 1, 1);
			tessellator.addVertexWithUV(-0.5f, -0.5f, 0.5f, 1, 0);

			tessellator.addVertexWithUV(0.5f, -0.5f, -0.5f, 0, 0);
			tessellator.addVertexWithUV(0.5f, 0.5f, -0.5f, 0, 1);
			tessellator.addVertexWithUV(0.5f, 0.5f, 0.5f, 1, 1);
			tessellator.addVertexWithUV(0.5f, -0.5f, 0.5f, 1, 0);

			tessellator.addVertexWithUV(-0.5f, -0.5f, 0.5f, 0, 0);
			tessellator.addVertexWithUV(-0.5f, 0.5f, 0.5f, 0, 1);
			tessellator.addVertexWithUV(-0.5f, 0.5f, -0.5f, 1, 1);
			tessellator.addVertexWithUV(-0.5f, -0.5f, -0.5f, 1, 0);

			tessellator.addVertexWithUV(0.5f, 0.5f, 0.5f, 0, 0);
			tessellator.addVertexWithUV(0.5f, 0.5f, -0.5f, 0, 1);
			tessellator.addVertexWithUV(-0.5f, 0.5f, -0.5f, 1, 1);
			tessellator.addVertexWithUV(-0.5f, 0.5f, 0.5f, 1, 0);

			tessellator.addVertexWithUV(0.5f, -0.5f, -0.5f, 0, 0);
			tessellator.addVertexWithUV(0.5f, -0.5f, 0.5f, 0, 1);
			tessellator.addVertexWithUV(-0.5f, -0.5f, 0.5f, 1, 1);
			tessellator.addVertexWithUV(-0.5f, -0.5f, -0.5f, 1, 0);
			tessellator.draw();
			GL11.glPopMatrix();
		}

		this.mc.renderEngine.bindTexture(new ResourceLocation("simplytools", "textures/gui/AssemblerGui1Tier.png"));
		if(this.isSawInTable) {
			if(this.sawAnimationSteps == 3) {

				NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger("xCoord", tileEntity.xCoord);
				tag.setInteger("yCoord", tileEntity.yCoord);
				tag.setInteger("zCoord", tileEntity.zCoord);
				Service.callMethodRemote("saw", tag, Side.SERVER);

				this.sawAnimationSteps = 0;
				this.isSawAnimated = false;
			}
			for(int i = 0; i < 10; i++) {
				GL11.glPushMatrix();
				if(this.isSawAnimated) {
					GL11.glTranslatef((float) Math.sin(this.sawAnimationPosition / 100F) * 10 + 110F + i / 3, -80F + i / 3, i);
				} else {
					GL11.glTranslatef((float) 0 * 10 + 110F + i / 3, -80F + i / 3, i);
				}
				GL11.glRotatef(30, -1F, -1F, 0F);
				GL11.glScalef(10, 10, 1);
				this.drawItemStack(new ItemStack(Items.sawItem), 0, 0, "");
				GL11.glPopMatrix();
			}
			GL11.glPushMatrix();
			mc.renderEngine.bindTexture(new ResourceLocation("simplytools", "textures/gui/AssemblerGui1Tier.png"));
			this.drawTexturedModalRect(137, 103, 177, 149, 18, 18);
			GL11.glPopMatrix();

		}

		GL11.glPopMatrix();

		//		GL11.glBegin(GL11.GL_QUADS);
		//		GL11.glColor4f(0F, 0F, 0F, 0.5F);
		//		GL11.glVertex3d(0, 0, 0);
		//		GL11.glVertex3d(0, 8, 0);
		//		GL11.glVertex3d(8, 8, 0);
		//		GL11.glVertex3d(8, 0, 0);
		//		GL11.glEnd();
		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_BLEND);
		this.lastTime = currentTime;

	}

	private void drawItemStack(ItemStack p_146982_1_, int p_146982_2_, int p_146982_3_, String p_146982_4_) {
		GL11.glTranslatef(0.0F, 0.0F, 32.0F);
		this.zLevel = 200.0F;
		itemRender.zLevel = 200.0F;
		FontRenderer font = null;
		if(p_146982_1_ != null)
			font = p_146982_1_.getItem().getFontRenderer(p_146982_1_);
		if(font == null)
			font = fontRendererObj;
		itemRender.renderItemAndEffectIntoGUI(font, this.mc.getTextureManager(), p_146982_1_, p_146982_2_, p_146982_3_);
		itemRender.renderItemOverlayIntoGUI(font, this.mc.getTextureManager(), p_146982_1_, p_146982_2_, p_146982_3_, p_146982_4_);
		this.zLevel = 10F;
		itemRender.zLevel = 10F;
	}

	float sawAnimationPosition = 0;
	boolean sawAnimationStep = false;
	int sawAnimationSteps = 0;
	long lastTime = System.nanoTime();
	boolean isSawInTable = false;
	boolean isSawAnimated = false;
	boolean isSawAnimationStepChecked = false;
	boolean isSawAnimationSemi = false;
	boolean isSawInputInTable = false;

}
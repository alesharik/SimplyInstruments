package SimplyTools.assembler;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import SimplyTools.API.AssemblerModel;

public class AssemblerSpecialRenderer extends TileEntitySpecialRenderer {

	// TileEntity mte = (TileEntityAssembler) te;
	private ResourceLocation textureMK1 = new ResourceLocation("SimplyTools:textures/blocks/assemblerMK1.png");
	private ResourceLocation textureMK2 = new ResourceLocation("SimplyTools:textures/blocks/assemblerMK2.png");
	private AssemblerModel assemblerModelMK1 = new AssemblerModelMK1();
	private AssemblerModel assemblerModelMK2 = new AssemblerModelMK2();

	@Override
	public void renderTileEntityAt(TileEntity te, double d, double d1, double d2, float f1) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d, (float) d1, (float) d2);
		renderAssemblerBlock(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord, (AssemblerTileEntity) te);
		GL11.glPopMatrix();
	}

	public void renderAssemblerBlock(World w, int x, int y, int z, AssemblerTileEntity te) {
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 0, 0.5F);
		GL11.glRotatef(90 * w.getBlockMetadata(x, y, z), 0, 1, 0);
		if(te.getTier() == 1) {
			bindTexture(textureMK1);
			assemblerModelMK1.render(0.0625F);
		} else if(te.getTier() == 2) {
			bindTexture(textureMK2);
			assemblerModelMK2.render(0.0625F);
		}
		GL11.glPopMatrix();
	}

}

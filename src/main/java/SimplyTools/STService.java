package SimplyTools;

import net.minecraft.nbt.NBTTagCompound;
import SimplyTools.assembler.AssemblerTileEntity;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * Servise list of SimplyTools
 * <p>
 * <h3>WARNING! Do not call this commands, if you don't know that they do!</h3>
 * 
 * @author alesharik
 *
 */
public class STService extends Service {
	public static void craftItem(MessageContext ctx, NBTTagCompound data) {
		AssemblerTileEntity tileEnity = (AssemblerTileEntity) ctx.getServerHandler().playerEntity.worldObj.getTileEntity(data.getInteger("xCoord"), data.getInteger("yCoord"), data.getInteger("zCoord"));
		tileEnity.craft();
	}

	public static void finalizeCraft(MessageContext ctx, NBTTagCompound data) {
		AssemblerTileEntity tileEnity = (AssemblerTileEntity) ctx.getServerHandler().playerEntity.worldObj.getTileEntity(data.getInteger("xCoord"), data.getInteger("yCoord"), data.getInteger("zCoord"));
		tileEnity.finalizeCraft();
	}

	public static void saw(MessageContext ctx, NBTTagCompound data) {
		AssemblerTileEntity tileEnity = (AssemblerTileEntity) ctx.getServerHandler().playerEntity.worldObj.getTileEntity(data.getInteger("xCoord"), data.getInteger("yCoord"), data.getInteger("zCoord"));
		tileEnity.saw();
	}
}

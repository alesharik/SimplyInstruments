package SimplyTools.assembler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World w, int x, int y, int z) {
		TileEntity te = w.getTileEntity(x, y, z);
		if(te instanceof AssemblerTileEntity) {
			return new AssemblerBlockContainer(player.inventory, (AssemblerTileEntity) te);
		}
		return null;
	}

	// returns an instance of the Gui you made earlier
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World w, int x, int y, int z) {
		TileEntity tileEntity = w.getTileEntity(x, y, z);
		if(tileEntity instanceof AssemblerTileEntity) {
			return new AssemblerGUI(player.inventory, (AssemblerTileEntity) tileEntity);
		}
		return null;

	}
}

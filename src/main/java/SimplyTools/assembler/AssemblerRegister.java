package SimplyTools.assembler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import SimplyTools.SimplyTools;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class AssemblerRegister {

	public static Block AssemblerBlock;

	//public static TileEntity TileEntityAssembler = new TileEntityAssembler();

	public static void CInit() {
		GameRegistry.registerBlock(AssemblerBlock = new AssemblerBlock("AssemblerBlock", Material.wood), "AssemblerBlock");
		GameRegistry.registerTileEntity(AssemblerTileEntity.class, "512");
		NetworkRegistry.INSTANCE.registerGuiHandler(SimplyTools.MODID, new GUIHandler());
	}

	public static void ClInit() {
		ClientRegistry.bindTileEntitySpecialRenderer(AssemblerTileEntity.class, new AssemblerSpecialRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AssemblerRegister.AssemblerBlock), new AssemblerBlockRenderAsItem());
	}

}

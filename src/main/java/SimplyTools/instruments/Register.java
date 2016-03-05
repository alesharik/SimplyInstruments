package SimplyTools.instruments;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import SimplyTools.API.CustomRenderer;
import SimplyTools.API.ToolMaterialHelper;
import cpw.mods.fml.common.registry.GameRegistry;

public class Register {

	public static Item Pickaxe;

	public static void init() {
		GameRegistry.registerItem(Pickaxe = new Pickaxe(0, ToolMaterialHelper.test, null), "Pickaxe");
	}

	public static void ClInit() {
		MinecraftForgeClient.registerItemRenderer(Pickaxe, new CustomRenderer());
	}

}

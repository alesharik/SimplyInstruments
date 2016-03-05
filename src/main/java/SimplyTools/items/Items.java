package SimplyTools.items;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.common.registry.GameRegistry;

public class Items {

	public static Item sawItem;
	public static Item mk2upgradeItem;

	public static void init() {
		GameRegistry.registerItem(sawItem = new SawItem(), "SawItem");
		MinecraftForgeClient.registerItemRenderer(sawItem, new SawRenderer());
		GameRegistry.registerItem(mk2upgradeItem = new MK2UpgradeItem("MK2UpgradeItem"), "MK2UpgradeItem");
	}
}

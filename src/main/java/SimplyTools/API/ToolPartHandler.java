package SimplyTools.API;

import net.minecraft.item.ItemStack;

public abstract class ToolPartHandler {

	public static String getToolPrat(ItemStack item, String key) {
		return item.stackTagCompound.getString(key);
	}

	public static void addPartToTool(ItemStack item, String part) {
		item.stackTagCompound.setString(part, part);
	}

}

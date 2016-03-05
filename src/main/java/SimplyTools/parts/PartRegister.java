package SimplyTools.parts;

import net.minecraftforge.client.MinecraftForgeClient;
import SimplyTools.SimplyTools;
import SimplyTools.API.CustomRenderer;
import SimplyTools.API.STPart;
import cpw.mods.fml.common.registry.GameRegistry;

public class PartRegister {

	public static STPart toolRodPart;
	public static STPart pickaxeHeadPart;
	public static STPart axeHeadPart;
	public static STPart showelHeadPart;
	public static STPart swordBladePart;
	public static STPart hoeHeadPart;

	public static void init() {
		GameRegistry.registerItem(toolRodPart = new ToolRodPart("ToolRod"), "ToolRod");
		SimplyTools.parts.add(toolRodPart);
		GameRegistry.registerItem(pickaxeHeadPart = new PickaxeHeadPart("PickaxeHead"), "PickaxeHead");
		SimplyTools.parts.add(pickaxeHeadPart);
		GameRegistry.registerItem(axeHeadPart = new AxeHeadPart("AxeHead"), "AxeHead");
		SimplyTools.parts.add(axeHeadPart);
		GameRegistry.registerItem(showelHeadPart = new ShowelHeadPart("ShowelHead"), "ShowelHead");
		SimplyTools.parts.add(showelHeadPart);
		GameRegistry.registerItem(swordBladePart = new SwordBladePart("SwordBlade"), "SwordBlade");
		SimplyTools.parts.add(swordBladePart);
		GameRegistry.registerItem(hoeHeadPart = new HoeHeadPart("HoeHead"), "HoeHead");
		SimplyTools.parts.add(hoeHeadPart);
	}

	public static void clInit() {
		MinecraftForgeClient.registerItemRenderer(toolRodPart, new CustomRenderer());
		MinecraftForgeClient.registerItemRenderer(pickaxeHeadPart, new CustomRenderer());
		MinecraftForgeClient.registerItemRenderer(axeHeadPart, new CustomRenderer());
		MinecraftForgeClient.registerItemRenderer(showelHeadPart, new CustomRenderer());
		MinecraftForgeClient.registerItemRenderer(swordBladePart, new CustomRenderer());
		MinecraftForgeClient.registerItemRenderer(hoeHeadPart, new CustomRenderer());
	}

}

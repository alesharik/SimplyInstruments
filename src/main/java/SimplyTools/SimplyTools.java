package SimplyTools;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import SimplyTools.API.STPart;
import SimplyTools.proxy.CommonProxy;
import SimplyTools.proxy.STUpdateMessage;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = SimplyTools.MODID, version = SimplyTools.VERSION, name = SimplyTools.NAME)
public class SimplyTools {
	public static SimpleNetworkWrapper network;
	public static final String MODID = "SimplyTools";
	public static final String VERSION = "1.0";
	public static final String NAME = "Simply Tools";
	public static ForkJoinPool fjp;

	public static ArrayList<String> tools = new ArrayList<String>();
	public static ArrayList<STPart> parts = new ArrayList<STPart>();

	@SidedProxy(clientSide = "SimplyTools.proxy.ClientProxy", serverSide = "SimplyTools.proxy.ServerProxy")
	public static CommonProxy proxy;

	// Creative tab
	public static final CreativeTabs SimplyInstrumentsTab = new CreativeTabs("SimplyToolsTab") {

		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Items.apple;
		}
	};

	// End creative tab

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		forkJoinPoolSetup();
		//		tools.add("Picaxe");
		//
		//		parts.add("ToolRod");

		proxy.preInit(event);
		this.network = NetworkRegistry.INSTANCE.newSimpleChannel("SimplyTools");
		this.network.registerMessage(STUpdateMessage.Handler.class, STUpdateMessage.class, 0, Side.SERVER);
		this.network.registerMessage(CallMessage.Handler.class, CallMessage.class, 0, Side.SERVER);

	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

	private void forkJoinPoolSetup() {
		fjp = new ForkJoinPool(2);
	}
}

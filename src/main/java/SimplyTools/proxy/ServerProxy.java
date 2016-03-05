package SimplyTools.proxy;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy extends CommonProxy {
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}

package SimplyTools.proxy;

import SimplyTools.KeyBindings;
import SimplyTools.assembler.AssemblerRegister;
import SimplyTools.handlers.KeyInputHandler;
import SimplyTools.instruments.Register;
import SimplyTools.parts.PartRegister;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		FMLCommonHandler.instance().bus().register(new KeyInputHandler());
		KeyBindings.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		super.init(event);
		AssemblerRegister.ClInit();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
		Register.ClInit();
		PartRegister.clInit();
	}
}

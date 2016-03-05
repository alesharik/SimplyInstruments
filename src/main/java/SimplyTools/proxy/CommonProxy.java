package SimplyTools.proxy;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import SimplyTools.ConfigHandler;
import SimplyTools.STService;
import SimplyTools.Service;
import SimplyTools.SimplyToolsGenerator;
import SimplyTools.API.CraftingHelper;
import SimplyTools.assembler.AssemblerRegister;
import SimplyTools.debug.DebugItem;
import SimplyTools.instruments.Register;
import SimplyTools.items.Items;
import SimplyTools.parts.PartRegister;
import SimplyTools.processor.MaterialProcessor;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	ConfigHandler configHandler = new ConfigHandler();
	SimplyToolsGenerator SimplyToolsGenerator = new SimplyToolsGenerator();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		try {
			configHandler.preInit(event);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		AssemblerRegister.CInit();
		Items.init();
		CraftingHelper.init();
		GameRegistry.registerWorldGenerator(new SimplyToolsGenerator.SimplyInstrumentsSGenerator(), 1002);
		Service.registerService(STService.class);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		Register.init();
		GameRegistry.registerItem(new DebugItem("debugItem"), "debugItem");
		PartRegister.init();
		(new MaterialProcessor()).start();
	}
}

package SimplyTools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import SimplyTools.API.Material;
import SimplyTools.processor.MaterialProcessor;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler {
	public static String baseDirName;
	File materialsDescriptionFile;

	public void preInit(FMLPreInitializationEvent event) throws IOException, ParseException {
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		cfg.load();

		cfg.save();
		this.baseDirName = event.getSuggestedConfigurationFile().getParent().toString() + "/SimplyTools/";
		this.materialsDescriptionFile = new File(this.baseDirName + "/materialsDescription.json");
		if(!new File(this.baseDirName).exists()) {
			new File(this.baseDirName).mkdir();
		}
		if(!this.materialsDescriptionFile.exists()) {
			Helpers.createJSON(this.materialsDescriptionFile, this.initBaseMaterials());
		}
		MaterialProcessor.addMaterialDescriptionJSON(((JSONArray) Helpers.parseJSONFile(this.materialsDescriptionFile)));
	}

	public JSONArray initBaseMaterials() {
		JSONArray materialList = new JSONArray();
		//Init vanilla materials
		ArrayList<String> wood = new ArrayList<String>();
		ArrayList<String> stone = new ArrayList<String>();
		ArrayList<String> iron = new ArrayList<String>();
		ArrayList<String> gold = new ArrayList<String>();
		ArrayList<String> diamond = new ArrayList<String>();

		wood.add("logWood");
		materialList.add(new Material("wood", 59, 1.0F, wood).getDescription());

		stone.add("cobblestone");
		stone.add("stone");
		materialList.add(new Material("stone", 131, 2.0F, stone).getDescription());

		iron.add("ingotIron");
		materialList.add(new Material("iron", 250, 3.0F, iron).getDescription());

		gold.add("ingotGold");
		materialList.add(new Material("gold", 32, 5.0F, gold).getDescription());

		diamond.add("oreDiamond");
		diamond.add("gemDiamond");
		materialList.add(new Material("diamond", 1561, 4.0F, diamond).getDescription());
		//End init vanilla materials
		//Init non-vanilla materials
		ArrayList<String> obsidian = new ArrayList<String>();
		ArrayList<String> glowstone = new ArrayList<String>();
		ArrayList<String> lazuri = new ArrayList<String>();
		ArrayList<String> bone = new ArrayList<String>();
		ArrayList<String> sugarCane = new ArrayList<String>();
		ArrayList<String> redstone = new ArrayList<String>();
		ArrayList<String> tnt = new ArrayList<String>();
		ArrayList<String> glass = new ArrayList<String>();

		OreDictionary.registerOre("blockObsidian", Blocks.obsidian);
		obsidian.add("blockObsidian");
		materialList.add(new Material("blockObsidian", 1000, 3.0F, obsidian).getDescription());

		glowstone.add("glowstone");
		materialList.add(new Material("glowstone", 800, 3.5F, glowstone).getDescription());

		lazuri.add("oreLapis");
		lazuri.add("gemLapis");
		materialList.add(new Material("lazurit", 750, 3.5F, lazuri).getDescription());

		OreDictionary.registerOre("bone", Items.bone);
		OreDictionary.registerOre("boneMeal", new ItemStack(Items.dye, 1, 15).getItem());
		bone.add("boneMeal");
		bone.add("bone");
		materialList.add(new Material("bone", 100, 2.5F, bone).getDescription());

		System.out.println(Blocks.reeds);
		System.out.println(new ItemStack(Blocks.reeds).getItem());
		//		OreDictionary.registerOre("sugarCane", Blocks.reeds);
		//		sugarCane.add("sugarCane");
		//		materialList.add(new Material("sugarCane", 20, 2.0F, sugarCane).getDescription());

		redstone.add("oreRedstone");
		redstone.add("dustRedstone");
		materialList.add(new Material("redstone", 800, 3.7F, redstone).getDescription());

		OreDictionary.registerOre("tnt", Blocks.tnt);
		tnt.add("tnt");
		materialList.add(new Material("tnt", 30, 4.0F, tnt).getDescription());

		glass.add("blockGlass");
		materialList.add(new Material("glass", 20, 2.0F, glass).getDescription());
		//End init non-vanilla materials

		return materialList;
	}

	// Diamond 1561
	// Wooden 59
	// Stone 131
	// Iron 250
	// Gold 32

}

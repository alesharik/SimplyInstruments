package SimplyTools.processor;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import SimplyTools.SimplyTools;
import SimplyTools.API.CraftingHelper;
import SimplyTools.API.CraftingMatrix;
import SimplyTools.API.Material;
import SimplyTools.API.MaterialRegister;

public class MaterialProcessor extends Thread {

	private static ArrayList<JSONArray> materialsConveyor = new ArrayList<JSONArray>();

	public void start() {
		this.setName("MaterialProcessorThread");
		initBaseMaterials();
		run();
	}

	public void run() {
		for(JSONArray material : materialsConveyor) {
			registerMaterialsDescription(material);
			registerToolMaterials();
		}
		TextureProcessor tp = new TextureProcessor();
		tp.start();
		try {
			tp.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String[] test = OreDictionary.getOreNames();
		System.out.println(OreDictionary.getOreNames());
		registerRecipes();
		this.interrupt();
	}

	private void registerToolMaterials() {
		for(Material mat : MaterialRegister.materials) {
			int hs = 0;
			int mu = 0;
			float ef = 0;
			float da = 0;
			int en = 0;
			Random r = new Random();

			if(mat.quality < 120) {
				hs = 0;
			} else if(mat.quality < 240) {
				hs = 1;
			} else if(mat.quality < 1200) {
				hs = 2;
			} else {
				hs = 3;
			}

			mu = mat.quality;
			ef = mat.speed;

			da = mat.speed - 2.0F;

			if(mat.quality >= 5000) {
				en = 50;
			} else if(mat.quality > 100 && mat.quality < 500) {
				en = 10;
			} else if(mat.quality >= 500 && mat.quality < 5000) {
				en = r.nextInt(10) + 20;
			} else if(mat.quality > 5 && mat.quality < 100) {
				en = r.nextInt(5) + 5;
			}

			MaterialRegister.toolMats.add(EnumHelper.addToolMaterial(mat.name, hs, mu, ef, da, en));
		}
	}

	public static void addMaterialDescriptionJSON(JSONArray matDesc) {
		MaterialProcessor.materialsConveyor.add(matDesc);
	}

	private static void registerMaterialsDescription(JSONArray materialsDescription) {
		for(Object materialObject : materialsDescription) {
			JSONObject materialDescription = (JSONObject) materialObject;
			Material material = new Material();
			material.name = (String) materialDescription.get("name");
			material.quality = ((Long) materialDescription.get("quality")).intValue();
			material.speed = ((Double) materialDescription.get("speed")).floatValue();
			material.oreDict = new ArrayList<String>();
			for(Object oreDictName : (JSONArray) materialDescription.get("oreDict")) {
				material.oreDict.add((String) oreDictName);
			}
			MaterialRegister.materials.add(material);
		}
	}

	private static void registerRecipes() {
		SimplyTools.parts.forEach((part) -> {
			MaterialRegister.materials.forEach((material) -> {
				for(int i = 0; i < material.oreDict.size(); i++) {
					boolean[] craftingPattern = part.getCraftingPattern();
					ItemStack[] craftingMatrix = new ItemStack[9];
					for(int j = 0; j < craftingPattern.length; j++) {
						if(craftingPattern[j]) {
							craftingMatrix[j] = OreDictionary.getOres(material.oreDict.get(i)).get(0);
						}
					}
					System.out.println("Part " + part + " mat " + material + " oredict" + material.oreDict);
					CraftingHelper.registerCraftingMatrix(new CraftingMatrix(3, craftingMatrix, new ItemStack(part)));
				}
			});
		});
	}

	private void initBaseMaterials() {
		OreDictionary.registerOre("blockObsidian", Blocks.obsidian);
		OreDictionary.registerOre("bone", Items.bone);
		OreDictionary.registerOre("boneMeal", new ItemStack(Items.dye, 1, 15).getItem());
		OreDictionary.registerOre("tnt", Blocks.tnt);
	}
}

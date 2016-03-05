package SimplyTools.API;

import java.util.ArrayList;

import net.minecraft.item.Item.ToolMaterial;

public class MaterialRegister {

	public static ArrayList<Material> materials = new ArrayList<Material>();
	public static ArrayList<ToolMaterial> toolMats = new ArrayList<ToolMaterial>();

	public static Material getMaterialDescriptionFromName(String name) {
		for(Material curMat : MaterialRegister.materials) {
			if(curMat.name == name) {
				return curMat;
			}
		}
		return null;
	}

	public static Material getMaterialDescriptionFromOreDict(String oreDict) {
		for(Material curMat : MaterialRegister.materials) {
			if(curMat.oreDict.contains(oreDict)) {
				return curMat;
			}
		}
		return null;
	}

	public static ToolMaterial getToolMaterialFromMaterial(Material mat) {
		for(ToolMaterial material : toolMats) {
			if(material.name() == mat.name) {
				return material;
			}
		}
		return null;
	}

	public static ToolMaterial getToolMaterialFromOreDict(String oreDict) {
		return getToolMaterialFromMaterial(getMaterialDescriptionFromOreDict(oreDict));
	}
}

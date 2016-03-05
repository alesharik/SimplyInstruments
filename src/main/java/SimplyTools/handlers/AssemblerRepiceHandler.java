package SimplyTools.handlers;

import java.util.ArrayList;

import net.minecraft.item.Item;
import SimplyTools.API.Material;
import SimplyTools.API.Recipe;

public class AssemblerRepiceHandler {

	public static ArrayList recipeList;

	public static void registerRecipe(Recipe r) {
		recipeList.add(r);
	}

	public static Item getOutput(Material input, String spec) {
		for(int i = 0; i < recipeList.size(); i++) {
			if(((Recipe) recipeList.get(i)).spetific == spec && ((Recipe) recipeList.get(i)).input == input) {
				return ((Recipe) recipeList.get(i)).output;
			}
		}
		return null;
	}

}

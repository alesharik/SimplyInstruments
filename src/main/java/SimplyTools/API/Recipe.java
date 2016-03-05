package SimplyTools.API;

import net.minecraft.item.Item;

public class Recipe {

	public Material input;
	public Item output;
	public String spetific;

	public Recipe(Material input, Item output, String specific) {
		this.input = input;
		this.output = output;
		this.spetific = specific;
	}
}

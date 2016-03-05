package SimplyTools.API;

import net.minecraft.item.ItemStack;

/**
 * Used to create basics crafting recipes for crafting with crafting system of
 * this mod
 * 
 * @author alexey
 *
 */

public class CraftingRecipe {

	/**
	 * Ingridients
	 */
	public ItemStack[] input;
	/**
	 * Outputs
	 */
	public ItemStack[] output;

	/**
	 * Create to create recipe with multiple ingridients and outputs
	 * 
	 * @param input
	 *            ingridients to craft this
	 * @param output
	 *            outputs of this recipe
	 */
	public CraftingRecipe(ItemStack[] input, ItemStack[] output) {
		this.input = input;
		this.output = output;
	}

	/**
	 * Create to create recipe with multiple ingridients, but with one output
	 * 
	 * @param input
	 *            ingridients to craft this
	 * @param output
	 *            output of this recipe
	 */
	public CraftingRecipe(ItemStack[] input, ItemStack output) {
		this.input = input;
		this.output = new ItemStack[1];
		this.output[1] = output;
	}

	/**
	 * Create to create recipe with one ingridient and multiple outputs
	 * 
	 * @param input
	 *            ingridient to craft this
	 * @param output
	 *            outputs of this recipe
	 */
	public CraftingRecipe(ItemStack input, ItemStack[] output) {
		this.output = new ItemStack[1];
		this.input[1] = input;
		this.output = output;
	}

	/**
	 * Create to create recipe with one ingridient and one output
	 * 
	 * @param input
	 *            ingridient to craft this
	 * @param output
	 *            output of this recipe
	 */
	public CraftingRecipe(ItemStack input, ItemStack output) {
		this.output = new ItemStack[1];
		this.input[1] = input;
		this.output = new ItemStack[1];
		this.output[1] = output;
	}

	/**
	 * Get output from recipe
	 * 
	 * @return output from recipe
	 */
	public ItemStack[] getOutput() {
		return this.output;
	}

}

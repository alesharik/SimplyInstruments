package SimplyTools.API;

import net.minecraft.item.ItemStack;

/**
 * Used to create crafting recipes for craftings system of this mod
 * 
 * @author alexey
 *
 */
public class CraftingMatrix {

	/**
	 * Size of crafting matrix (ex. 3(3X3), 2(2X2))
	 */
	public int matrix;
	/**
	 * Array with crafting recipe
	 */
	public ItemStack[] input;
	/**
	 * Output of recipe
	 */
	public ItemStack output;

	/**
	 * Create matrix recipe
	 * 
	 * @param matrix
	 *            size of crafting matrix (ex. 3(3X3), 2(2X2))
	 * @param input
	 *            array with crafting recipe
	 * @param output
	 *            output of recipe
	 */
	public CraftingMatrix(int matrix, ItemStack[] input, ItemStack output) {
		this.matrix = matrix;
		this.input = new ItemStack[this.matrix ^ 2];
		this.input = input;
		this.output = output;
	}

	/**
	 * Get output from recipe
	 * 
	 * @return output from recipe
	 */
	public ItemStack getOutput() {
		return this.output;
	}
}

package SimplyTools.API;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import SimplyTools.Helpers;

public class CraftingHelper {

	private static ArrayList<CraftingMatrix> craftingMatrixes = new ArrayList<CraftingMatrix>();

	private static ArrayList<CraftingRecipe> processRecipes = new ArrayList<CraftingRecipe>();

	private static ArrayList<CraftingMatrix> toolCraftingMatrixes = new ArrayList<CraftingMatrix>();

	private static CustomRenderer cr = new CustomRenderer();

	private static Random random = new Random();

	public static void registerCraftingMatrix(CraftingMatrix matrix) {
		craftingMatrixes.add(matrix);
	}

	public static void registerProcessRecipe(CraftingRecipe recipe) {
		processRecipes.add(recipe);
	}

	public static void registerToolCraftingMatrix(CraftingMatrix matrix) {
		toolCraftingMatrixes.add(matrix);
	}

	public static void init() {
		//
		//		ItemStack[] toolRod = new ItemStack[9];
		//		toolRod[4] = new ItemStack(Blocks.log, 1, 0);
		//		toolRod[7] = new ItemStack(Blocks.log, 1, 0);
		//		craftingMatrixes.add(new CraftingMatrix(3, toolRod, new ItemStack(PartRegister.toolRodPart)));
		//
		//		ItemStack[] cobbleToolRod = { null, null, null, null, new ItemStack(Blocks.cobblestone, 1, 0), null, null, new ItemStack(Blocks.cobblestone, 1, 0), null };
		//		craftingMatrixes.add(new CraftingMatrix(3, cobbleToolRod, new ItemStack(PartRegister.toolRodPart)));
	}

	public static boolean execRecipe(CraftingMatrix matrix, ItemStack[] input) {
		boolean isItemsValid = true;
		if(input != null && matrix.input != null && matrix.output != null) {
			if(input.length == (matrix.matrix * matrix.matrix)) {
				if(matrix.input == input) {
					return true;
				} else {
					for(int i = 0; i < matrix.input.length; i++) {
						if(!Helpers.compareItemStacksWhitOreDict(matrix.input[i], input[i])) {
							isItemsValid = false;
						}
					}
					if(isItemsValid) {
						return true;
					} else if(!isItemsValid) {
						isItemsValid = true;
					}
				}
				return false;
			} else {
				System.out.println("Error");
			}
		}
		return false;
	}

	public static boolean execRecipe(CraftingRecipe recipe, ItemStack[] input) {
		boolean isItemsValid = true;
		if(input != null && recipe.input != null && recipe.output != null) {
			if(recipe.input == input) {
				return true;
			} else {
				for(int i = 0; i < recipe.input.length; i++) {
					if(recipe.input[i] != input[i]) {
						isItemsValid = false;
					}
				}
				if(isItemsValid) {
					return true;
				}
			}
		}
		return false;
	}

	public static ItemStack processRecipe(ItemStack[] input) {
		for(int i = 0; i < craftingMatrixes.size(); i++) {
			boolean xz = true;
			for(int j = 0; j < input.length; j++) {
				if(!Helpers.compareItemStacksWhitOreDict(craftingMatrixes.get(i).input[j], input[j])) {
					xz = false;
				}
			}
			if(xz) {
				return new ItemStack(Items.stick);
			}
		}
		return null;
	}

	// Create vars for multiple threads
	volatile static ItemStack ret = null;
	volatile static ItemStack texRet = null;
	volatile static byte[] output = null;

	public static ItemStack processToolRecipe(ItemStack[] input) {
		ret = null;
		texRet = null;
		for(int i = 0; i < craftingMatrixes.size(); i++) {
			boolean xz = true;
			for(int j = 0; j < input.length; j++) {
				if(!Helpers.compareItemStacksWhitOreDict(craftingMatrixes.get(i).input[j], input[j])) {
					xz = false;
				} else if(texRet == null && input[j] != null) {
					texRet = input[j];
				}
			}
			if(xz) {
				ret = craftingMatrixes.get(i).output;
				ret.stackTagCompound = new NBTTagCompound();
				ret.stackTagCompound = new NBTTagCompound();
				ret.stackTagCompound.setInteger("Formlessness", 50);
				output = null;
				Thread textureDrawer = new Thread() {
					@Override
					public void run() {
						try {
							ByteArrayOutputStream baos = new ByteArrayOutputStream();

							ImageIO.write(ImageHelper.drawCustomTexture(texRet, ret), "png", baos);
							output = baos.toByteArray();
							baos.flush();
							baos.close();
							this.interrupt();
						} catch (Exception e) {
							e.printStackTrace();
							this.interrupt();
						}
					}
				};
				textureDrawer.run();
				while(textureDrawer.isAlive()) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				ret.stackTagCompound.setByteArray("texture", output);

				int formlessness = 50 + random.nextInt(15);
				ret.stackTagCompound.setInteger("formlessness", formlessness);
				ret.stackTagCompound.setBoolean("isWood", TileEntityFurnace.isItemFuel(texRet));

				ArrayList<String> infoList = new ArrayList<String>();
				infoList.add("Formlessness : " + formlessness);

				ret.getItem().addInformation(ret, null, infoList, true);

				ToolMaterial tm = MaterialRegister.getToolMaterialFromOreDict(OreDictionary.getOreName(OreDictionary.getOreID(texRet)));
				STPart.setToolMaterialToItem(tm, ret);
				return ret;
			}
		}
		return null;
	}

	public static ItemStack[] getThingsToCreateTexture(ItemStack[] input) {
		ItemStack[] ret = new ItemStack[2];
		ItemStack texRet = null;
		for(int i = 0; i < craftingMatrixes.size(); i++) {
			boolean xz = true;
			for(int j = 0; j < input.length; j++) {
				if(!Helpers.compareItemStacksWhitOreDict(craftingMatrixes.get(i).input[j], input[j])) {
					xz = false;
				} else if(texRet == null && input[j] != null) {
					texRet = input[j];
				}
			}
			if(xz) {
				ret[0] = texRet;
				ret[1] = new ItemStack(craftingMatrixes.get(i).output.getItem());
			}
		}
		return ret;
	}

	public boolean isMatrixRepiceValid(CraftingMatrix recipe1, CraftingMatrix recipe2) {
		boolean isMatrixRecipeValid = true;
		for(int i = 0; i > 9; i++) {
			if(!Helpers.compareItemStacksWhitOreDict(recipe1.input[i], recipe2.input[i])) {
				isMatrixRecipeValid = false;
			}
		}
		return isMatrixRecipeValid;
	}

	public static ItemStack processSawRecipe(ItemStack item) {
		int formlessness = item.getTagCompound().getInteger("formlessness");
		if(item.getTagCompound().getBoolean("isWood") && formlessness > 13) {
			ToolMaterial tm = STPart.getToolMaterial(item);
			if(random.nextInt(formlessness) > 1) {
				formlessness = formlessness - (random.nextInt(9) + 4);
				item = STPart.setToolMaterialToItem(EnumHelper.addToolMaterial("Name" + random.nextInt(1000), tm.getHarvestLevel(), tm.getMaxUses() + 5, tm.getEfficiencyOnProperMaterial() + 0.1F, tm.getDamageVsEntity(), tm.getEnchantability()), item);
			} else {
				formlessness += 2;
				item = STPart.setToolMaterialToItem(EnumHelper.addToolMaterial("Name" + random.nextInt(1000), tm.getHarvestLevel(), tm.getMaxUses() - 1, tm.getEfficiencyOnProperMaterial() - 0.01F, tm.getDamageVsEntity(), tm.getEnchantability()), item);
			}
			item.stackTagCompound.setInteger("formlessness", formlessness);
			return item;
		}
		return item;
	}
}

package SimplyTools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Helpers {

	//JSONParser init
	private static JSONParser parser = new JSONParser();

	/**
	 * This method chekc if all elements of array are null
	 * 
	 * @param arr
	 *            array with elements which need to check
	 * @return Return true if all elements in array equals null
	 */
	public static boolean isAllArrayElementsNull(Object arr[]) {
		boolean empty = true;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] != null) {
				empty = false;
				break;
			}
		}
		return empty;
	}

	/**
	 * This method used to indent
	 * 
	 * @param indent
	 *            number of indents
	 * @return indent in one String
	 */

	private static String getIndent(int indent) {
		if(indent < 0) {
			return "";
		}
		return new String(new char[indent * 4]).replace('\0', ' ');
	}

	/**
	 * Simply format JSON. For example:
	 * <p>
	 * Raw: <code>{"test":"Hello, World!",["i":"First!","J":"Second!"]}</code>
	 * <p>
	 * Output:
	 * 
	 * <pre>
	 * 	{ 
	 * 		"test": "Hello, World", 
	 * 			[
	 * 				"i": "First!", 
	 * 				"J": "Second!"
	 * 			]
	 * 	}
	 * </pre>
	 * 
	 * @param inJSON
	 *            raw JSON
	 * @return formatted JSON
	 */

	public static String formatJSON(String inJSON) {
		String outJSON = "";
		int currentIndent = 0;
		for(char c : inJSON.toCharArray()) {
			switch(c) {
				case '[':
				case '{':
					outJSON += "\n" + Helpers.getIndent(currentIndent) + c;
					currentIndent++;
					outJSON += "\n" + Helpers.getIndent(currentIndent);
					break;
				case ']':
				case '}':
					currentIndent--;
					outJSON += "\n" + Helpers.getIndent(currentIndent) + c;
					break;
				case ',':
					outJSON += c + "\n" + Helpers.getIndent(currentIndent);
					break;
				case ':':
					outJSON += c + " ";
					break;
				default:
					outJSON += c;
			}
		}
		return outJSON.replaceAll("(?m)^[ \t]*\r?\n", "");
	}

	/**
	 * Parse given file as JSON file
	 * 
	 * @param file
	 *            JSON file to parse
	 * @return parced JSON file
	 * @throws IOException
	 *             if anything happens in reader
	 * @throws ParseException
	 *             if anything happens in parser
	 */
	public static Object parseJSONFile(File file) throws IOException, ParseException {
		return Helpers.parser.parse(new FileReader(file));
	}

	/**
	 * Create JSON file from JSONArray
	 * 
	 * @param f
	 *            output file
	 * @param json
	 *            JSONArray who need to write in file
	 * @return true if all right, false if exeption throwed
	 */
	public static boolean createJSON(File f, JSONArray json) {
		try {
			JSONArray materialList = new JSONArray();
			f.createNewFile();
			FileWriter fw = new FileWriter(f);
			fw.write(Helpers.formatJSON(json.toString()));
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Create JSON file from JSONObject
	 * 
	 * @param f
	 *            output file
	 * @param json
	 *            JSONObject who need to write in file
	 * @return true if all right, false if exeption throwed
	 */

	public static boolean createJSON(File f, JSONObject json) {
		try {
			JSONArray materialList = new JSONArray();
			f.createNewFile();
			FileWriter fw = new FileWriter(f);
			fw.write(Helpers.formatJSON(json.toString()));
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Comparing ItemStack with using OreDict
	 * 
	 * @param item1
	 *            first ItemStack
	 * @param item2
	 *            second ItemStack
	 * @return true if first ItemStack equals second ItemStack for OreDict
	 */

	public static boolean compareItemStacksWhitOreDict(ItemStack item1, ItemStack item2) {
		if(item1 == null && item2 == null) {
			return true;
		} else if(item1 != null && item2 != null) {
			int item1OreIds[] = OreDictionary.getOreIDs(item1);
			int item2OreIds[] = OreDictionary.getOreIDs(item2);

			String item1Ore = "Unknown";
			String item2Ore = "Unknown";

			if(item1OreIds.length > 0) {
				item1Ore = OreDictionary.getOreName(item1OreIds[0]);
			}
			if(item2OreIds.length > 0) {
				item2Ore = OreDictionary.getOreName(item2OreIds[0]);
			}
			if(!item1Ore.equals("Unknown") && !item2Ore.equals("Unknown") && item1Ore.equals(item2Ore)) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	/**
	 * Remove first specified ItemStack from inventory
	 * 
	 * @param player
	 *            player which need to remove the ItemStack
	 * @param item
	 *            ItemStack to remove
	 * @param amount
	 *            remove amount
	 */

	public static void removeItemFromInventory(EntityPlayer player, ItemStack item, int amount) {
		IInventory inv = player.inventory;
		for(int i = 0; i < inv.getSizeInventory(); i++) {
			if(inv.getStackInSlot(i) != null) {
				ItemStack j = inv.getStackInSlot(i);
				if(j.getItem() != null && j.getItem() == item.getItem()) {
					if(amount < j.stackSize) {
						System.out.println(j.stackSize);
						j.stackSize = j.stackSize - amount;
						System.out.println(j.stackSize);
						inv.setInventorySlotContents(i, new ItemStack(j.getItem(), j.stackSize));
						return;
					}
					inv.setInventorySlotContents(i, null);
					return;
				}
			}
		}
	}

	/**
	 * Remove item from equip of the specific player
	 * 
	 * @param player
	 *            player which need to remove equip item
	 */
	public static void removeItemFromEquip(EntityPlayer player) {
		removeItemFromInventory(player, player.getCurrentEquippedItem(), 1);
	}

	/**
	 * Set number positive
	 * 
	 * <pre>
	 * if(inp &gt;= 0) {
	 * 	return inp;
	 * } else {
	 * 	return inp * -1;
	 * }
	 * </pre>
	 * 
	 * @param inp
	 *            input to cast to positive
	 * @return positive number
	 */
	public static int toPositive(int inp) {
		if(inp >= 0) {
			return inp;
		} else {
			return inp * -1;
		}
	}

	/**
	 * Decode String to ItemStack
	 * 
	 * @param input
	 *            coded ItemStack
	 * @return decoded ItemStack
	 */
	public static ItemStack getItemStackFromString(String input) {
		ItemStack output = new ItemStack(Item.getItemById(Integer.getInteger(input.substring(input.indexOf("*") + 1, input.indexOf("@") - 1))));
		output.stackSize = Integer.getInteger(input.substring(0, input.indexOf("*") - 1));
		output.setItemDamage(Integer.getInteger(input.substring(input.indexOf("@"), input.length())));
		return output;
	}

	/**
	 * Code ItemStack to String
	 * 
	 * @param input
	 *            ItemStack which need to cast
	 * @return casted ItemStack
	 */
	public static String getStringOfItemStack(ItemStack input) {
		return input.stackSize + "*" + Item.getIdFromItem(input.getItem()) + "@" + input.getItemDamage();
	}

	/**
	 * Display a window with specific BufferedImage. Used for debug
	 * 
	 * @param img
	 *            BufferedImage to display
	 */
	public static void displayBufferedImage(BufferedImage img) {
		ImageIcon icon = new ImageIcon();
		icon.setImage(img);
		JOptionPane.showMessageDialog(null, icon);
	}
}

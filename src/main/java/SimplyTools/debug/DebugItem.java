package SimplyTools.debug;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import SimplyTools.Helpers;
import SimplyTools.handlers.KeyInputHandler;

public class DebugItem extends Item {

	private int minX;
	private int minY;
	private int minZ;
	private int maxX;
	private int maxY;
	private int maxZ;

	public DebugItem(String unlocalizedName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setHasSubtypes(true);
		this.setMaxDamage(16);
		this.setMaxStackSize(1);
	}

	public IIcon[] icons = new IIcon[6];

	@Override
	public IIcon getIconFromDamage(int meta) {
		return this.icons[meta];
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return this.getUnlocalizedName() + "-" + stack.getItemDamage();
	}

	@Override
	public void registerIcons(IIconRegister reg) {
		this.icons[0] = reg.registerIcon("SimplyTools:structureCodeGenerator");
	}

	public boolean onItemUse(ItemStack item, EntityPlayer player, World w, int x, int y, int z, int meta, float f1, float f2, float f3) {

		// System.out.println(item.getItemDamage());
		// System.out.println(KeyInputHandler.selectModeSwitch);

		if(item.getItemDamage() == 0) {
			String selectModeOutput = null;
			if(KeyInputHandler.selectModeSwitch == 0) {
				System.out.println("first");
				this.minX = x;
				this.minY = y;
				this.minZ = z;
				System.out.println(this.minX + "X");
				System.out.println(this.minY + "Y");
				System.out.println(this.minZ + "Z");

			} else if(KeyInputHandler.selectModeSwitch == 1) {
				this.maxX = x;
				this.maxY = y;
				this.maxZ = z;
				System.out.println(this.minX + "X");
				System.out.println(this.minY + "Y");
				System.out.println(this.minZ + "Z");

				System.out.println("second");
			} else {

				ArrayList<String> lines = new ArrayList<String>();
				Path file = Paths.get(System.getProperty("user.dir") + "/structure.txt");
				for(int i = 0; i < (Helpers.toPositive(this.maxX) - Helpers.toPositive(this.minX)); i++) {
					System.out.println("X" + i);
					for(int j = 0; j < (Helpers.toPositive(this.maxY) - Helpers.toPositive(this.minY)); j++) {
						System.out.println("Y" + j);
						for(int k = 0; k < (Helpers.toPositive(this.maxZ) - Helpers.toPositive(this.minZ)); k++) {
							System.out.println("Z" + k);

							if(!w.getBlock(this.minX + i, j + this.minY, this.minZ - k).getUnlocalizedName().equals(Blocks.air.getUnlocalizedName())) {
								lines.add("w.setBlockToAir(x + " + i + ", y + " + j + ", z + " + k + ");  ");
								lines.add("w.setBlock(x + " + i + ", y + " + j + ", z + " + k + ", Blocks." + w.getBlock(this.minX + i, j + this.minY, this.minZ - k).getUnlocalizedName().substring(5, w.getBlock(this.minX + i, j + this.minY, this.minZ - k).getUnlocalizedName().length()) + ", " + w.getBlockMetadata(this.minX + i, j + this.minY, this.minZ - k) + ", 2);    ");
							}

						}
					}
				}
				System.out.println(Helpers.toPositive(Helpers.toPositive(this.maxZ) - Helpers.toPositive(this.minZ)));
				lines.add("end");
				System.out.println("calculate");
				w.setBlock(x - 1, y - 1, z - 1, w.getBlock(x, y, z));
				try {
					Files.write(file, lines, Charset.forName("UTF-8"));
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		// Minecraft.getMinecraft().setIngameNotInFocus();
		return false;
	}

}

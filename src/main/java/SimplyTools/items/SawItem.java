package SimplyTools.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import SimplyTools.SimplyTools;
import SimplyTools.assembler.AssemblerBlock;
import SimplyTools.assembler.AssemblerRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SawItem extends Item {

	public SawItem() {
		super();
		this.setUnlocalizedName("saw");
		this.setTextureName("SimplyTools:SawItem");
		this.setMaxStackSize(1);
		this.setCreativeTab(SimplyTools.SimplyInstrumentsTab);
	}

	public boolean onItemUse(ItemStack item, EntityPlayer player, World w, int x, int y, int z, int meta, float f1, float f2, float f3) {
		System.out.println("sad");
		if(w.getBlock(x, y, z) == AssemblerRegister.AssemblerBlock) {
			((AssemblerBlock) w.getBlock(x, y, z)).setSaw(player, w, x, y, z);
		}
		// System.out.println(ImageHelper.getAverageColor(item));
		// System.out.println(ImageHelper.getPatternColorMapFromItemTexture(item).blackColor);
		// System.out.println(ImageHelper.getPatternColorMapFromItemTexture(item).cornerColor);
		// System.out.println(ImageHelper.getPatternColorMapFromItemTexture(item).defColor);
		// System.out.println(item.getItem().getIconFromDamage(meta).getIconName());
		// if (!w.isRemote) {
		// Schematic sh = Schematic.get("undergroundHome.schematic");
		//
		// if (sh == null) {
		// return false;
		// }
		//
		// int i = 0;
		// for (int sx = 0; sx < sh.width; sx++) {
		// for (int sy = 0; sy < sh.height; sy++) {
		// for (int sz = 0; sz < sh.length; sz++) {
		//
		// Block b = Block.getBlockById(sh.blocks[i]);
		// if (b != Blocks.air) {
		// w.setBlockToAir(x + sx, y + sy + sy, z + sz);
		// w.setBlock(x + sx, sy + sy - y, z + sz, b, 0, 2);
		// }
		// i++;
		// }
		// }
		// }
		//
		// if (sh.tileentities != null) {
		// for (int i1 = 0; i1 < sh.tileentities.tagCount(); ++i1) {
		// NBTTagCompound nbttagcompound4 =
		// sh.tileentities.getCompoundTagAt(i1);
		// TileEntity tileentity =
		// TileEntity.createAndLoadEntity(nbttagcompound4);
		//
		// if (tileentity != null) {
		// tileentity.xCoord = x + tileentity.xCoord;
		// tileentity.yCoord += tileentity.yCoord;
		// tileentity.zCoord = z + tileentity.zCoord;
		// w.setTileEntity(tileentity.xCoord, tileentity.yCoord,
		// tileentity.zCoord, tileentity);
		// }
		// }
		// }
		// return true;
		// }
		// SimplyInstrumentsUndergrondHouse.generate(w, x, y, z);
		// System.out.println(w.getBlockMetadata(x, y, z));
		//		player.inventory.addItemStackToInventory(new ItemStack(Blocks.tnt, 64));
		//		player.inventory.addItemStackToInventory(new ItemStack(Blocks.tnt, 64));
		//		player.inventory.addItemStackToInventory(new ItemStack(Blocks.tnt, 64));
		//		player.inventory.addItemStackToInventory(new ItemStack(Blocks.tnt, 64));
		//		player.inventory.addItemStackToInventory(new ItemStack(Blocks.tnt, 64));
		//		player.inventory.addItemStackToInventory(new ItemStack(Blocks.tnt, 64));
		//		player.inventory.addItemStackToInventory(new ItemStack(Blocks.tnt, 64));
		//		player.inventory.addItemStackToInventory(new ItemStack(Blocks.tnt, 64));
		//		player.inventory.addItemStackToInventory(new ItemStack(Blocks.tnt, 64));
		return false;
	}

	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

}

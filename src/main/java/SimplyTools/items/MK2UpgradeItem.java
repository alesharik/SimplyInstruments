package SimplyTools.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import SimplyTools.assembler.AssemblerBlock;
import SimplyTools.assembler.AssemblerRegister;

public class MK2UpgradeItem extends Item {

	public MK2UpgradeItem(String unlocalizedName) {
		this.setUnlocalizedName(unlocalizedName);
	}

	public boolean onItemUse(ItemStack item, EntityPlayer player, World w, int x, int y, int z, int meta, float f1, float f2, float f3) {
		if(w.getBlock(x, y, z).equals(AssemblerRegister.AssemblerBlock) && w.getBlockMetadata(x, y, z) < 4) {
			((AssemblerBlock) w.getBlock(x, y, z)).upgrade(player, w, x, y, z, item);
		}
		return false;
	}

}

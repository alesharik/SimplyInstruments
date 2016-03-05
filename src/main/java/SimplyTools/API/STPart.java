package SimplyTools.API;

import java.io.File;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class STPart extends Item {

	public File customTexture;

	public STPart() {
		this.setCreativeTab(null);
		this.setNoRepair();
		this.setMaxStackSize(1);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack item, EntityPlayer ep, java.util.List list, boolean b) {
		super.addInformation(item, ep, list, b);
		list.add(((Integer) item.getTagCompound().getInteger("formlessness")).toString());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public final int getRenderPasses(int metadata) {
		return 5;
	}

	public void setCustomTexture(File texture) {
		this.customTexture = texture;
	}

	public static ItemStack setToolMaterialToItem(ToolMaterial tm, ItemStack item) {
		NBTTagCompound tag = item.stackTagCompound;
		tag.setInteger("harvestLevel", tm.getHarvestLevel());
		tag.setInteger("maxUses", tm.getMaxUses());
		tag.setInteger("enchantability", tm.getEnchantability());
		tag.setFloat("efficiency", tm.getEfficiencyOnProperMaterial());
		tag.setFloat("damage", tm.getDamageVsEntity());
		item.setTagCompound(tag);
		return item;
	}

	public static ToolMaterial getToolMaterial(ItemStack item) {
		NBTTagCompound tag = item.stackTagCompound;
		return EnumHelper.addToolMaterial("TempMaterial", tag.getInteger("harvestLevel"), tag.getInteger("maxUses"), tag.getFloat("efficiency"), tag.getFloat("damage"), tag.getInteger("enchantability"));
	}

	public boolean[] getCraftingPattern() {
		return new boolean[9];
	}
}

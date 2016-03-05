package SimplyTools.assembler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import SimplyTools.SimplyTools;
import SimplyTools.items.Items;

public class AssemblerBlock extends BlockContainer {

	private ItemStack sawItem = new ItemStack(Items.sawItem);

	protected AssemblerBlock(String unlocalizedName, Material m) {
		super(m);
		this.setBlockName(unlocalizedName);
		this.isBlockContainer = true;
		this.textureName = "SimplyTools:textures/blocks/assembler.png";
	}

	@Override
	public TileEntity createNewTileEntity(World w, int p_149915_2_) {
		return new AssemblerTileEntity(w);
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase el, ItemStack item) {
		int rotation = MathHelper.floor_double((double) ((el.rotationYaw * 4F) / 360F) + 2.5D) & 3;
		if(rotation == 0) {
			rotation = 2;
		} else if(rotation == 2) {
			rotation = 0;
		}
		w.setBlockMetadataWithNotify(x, y, z, rotation, 2);
		((AssemblerTileEntity) w.getTileEntity(x, y, z)).update();
	}

	public void onBlockAdded(World w, int x, int y, int z) {
		super.onBlockAdded(w, x, y, z);
	}

	public void breakBlock(World w, int x, int y, int z, Block block, int meta) {
		//dropAllItemsFromInv(w, x, y, z);
		super.breakBlock(w, x, y, z, block, meta);

		// 0w.removeTileEntity(x, y, z);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return -1;
	}

	public void setSaw(EntityPlayer player, World w, int x, int y, int z) {
		if(player.isSneaking() && !((AssemblerTileEntity) w.getTileEntity(x, y, z)).getSawInTable()) {
			((AssemblerTileEntity) w.getTileEntity(x, y, z)).insertSawIntoTable(player);
		}
	}

	public void upgrade(EntityPlayer player, World w, int x, int y, int z, ItemStack item) {
		if(player.isSneaking()) {
			((AssemblerTileEntity) w.getTileEntity(x, y, z)).upgradeTable(player, item.getItem());
		}
	}

	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int meta, float f1, float f2, float f3) {
		if(w.getTileEntity(x, y, z) == null) {
			return false;
		}
		if(player.isSneaking()) {
			return false;
		}
		player.openGui(SimplyTools.MODID, 0, w, x, y, z);
		return true;
	}

	private void dropAllItemsFromInv(World w, int x, int y, int z) {
		TileEntity te = w.getTileEntity(x, y, z);

		if(te instanceof IInventory) {
			return;
		}
		IInventory inv = (IInventory) te;
		for(int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack item = inv.getStackInSlot(i);
			if(item != null && item.stackSize > 0) {
				net.minecraft.item.Item Item = item.getItem();
				ItemStack ItemStack = new ItemStack(Item, item.stackSize, item.getItemDamage());
				w.spawnEntityInWorld(new EntityItem(w, x, y, z, ItemStack));
			}
		}
	}

}

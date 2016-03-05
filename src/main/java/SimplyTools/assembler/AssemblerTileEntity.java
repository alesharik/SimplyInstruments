package SimplyTools.assembler;

import java.util.concurrent.CompletableFuture;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import SimplyTools.Helpers;
import SimplyTools.SimplyTools;
import SimplyTools.API.CraftingHelper;
import SimplyTools.items.Items;
import SimplyTools.proxy.STUpdateMessage;

public class AssemblerTileEntity extends TileEntity implements IInventory {

	public ItemStack[] inv;
	private int tier;
	private boolean isSawInTable = false;
	public volatile int processDelay = 0;
	public volatile int time = 30;
	private ItemStack[] craftingMatrix;

	public AssemblerTileEntity(World w) {
		this.worldObj = w;
		inv = new ItemStack[13];
	}

	public AssemblerTileEntity() {
		inv = new ItemStack[13];
	}

	public AssemblerTileEntity update() {
		this.getBlockMetadata();
		this.tier = (this.blockMetadata) / 4 + 1;
		System.out.println("tier" + this.tier + "meta" + this.blockMetadata);
		return this;
	}

	public int getTier() {
		return tier;
	}

	public boolean getSawInTable() {
		return this.isSawInTable;
	}

	// public void setPattern(PatternModel model) {
	// this.assemblerModel = new AssemblerModel(model);
	// }

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound syncData = new NBTTagCompound();
		this.writeToNBT(syncData);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	public void insertSawIntoTable(EntityPlayer player) {
		this.isSawInTable = true;
		System.out.println(this.isSawInTable);
		Helpers.removeItemFromEquip(player);
	}

	public void upgradeTable(EntityPlayer player, Item upgrade) {
		if(upgrade.equals(Items.mk2upgradeItem) && this.tier == 1) {
			this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata + 4, 2);
			this.blockMetadata = this.blockMetadata + 4;
			Helpers.removeItemFromEquip(player);
			this.update();
		}
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readFromNBT(pkt.func_148857_g());
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		NBTTagList tagList = tagCompound.getTagList("Inventory", 10);
		for(int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if(slot >= 0 && slot < inv.length) {
				inv[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}

		this.isSawInTable = tagCompound.getBoolean("isSawInTable");
		this.tier = tagCompound.getInteger("tier");
	}

	@Override
	public void updateEntity() {

	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		NBTTagList itemList = new NBTTagList();
		for(int i = 0; i < inv.length; i++) {
			ItemStack stack = inv[i];
			if(stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		tagCompound.setTag("Inventory", itemList);
		tagCompound.setBoolean("isSawInTable", this.isSawInTable);
		tagCompound.setInteger("tier", this.tier);
	}

	@Override
	public int getSizeInventory() {
		System.out.println(inv.length);
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		// inv = new ItemStack[10];
		// //System.out.println(s);
		// if(!((Integer) s).equals(null)){
		// return inv[s];
		// }
		// return null;
		return inv[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);
		if(stack != null) {
			if(stack.stackSize <= amount) {
				setInventorySlotContents(slot, null);
			} else {
				stack = stack.splitStack(amount);
				if(stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int s) {
		ItemStack item = getStackInSlot(s);
		if(item != null) {
			setInventorySlotContents(s, null);
		}
		return item;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		// if(s == 10 && item.getItem() == Items.SawItem){
		// int StackLimit = 1;
		// } else{
		// inv[s] = item;
		// int StackLimit = getInventoryStackLimit();
		// }
		// if (item != null && item.stackSize <= StackLimit) {
		// item.stackSize = StackLimit;
		// }
		inv[slot] = stack;
		if(stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return "AssemblerTE";
	}

	@Override
	public boolean hasCustomInventoryName() {

		return true;
	}

	@Override
	public int getInventoryStackLimit() {

		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item) {
		// if(slot == 10){
		// if(item.getItem() == Items.SawItem){
		// return true;
		// } else if(item.getItem() != Items.SawItem){
		// return false;
		// }
		// }
		System.out.println(123);
		return true;
	}

	public void sendNBTUpdate() {
		NBTTagCompound data = new NBTTagCompound();
		this.writeToNBT(data);
		SimplyTools.network.sendToServer(new STUpdateMessage(this.xCoord, this.yCoord, this.zCoord, data));
	}

	public void debug() {
		System.out.println(inv);
	}

	CompletableFuture<ItemStack> craft = new CompletableFuture<ItemStack>();

	public void craft() {
		this.craftingMatrix = new ItemStack[9];
		for(int i = 0; i < 9; i++) {
			this.craftingMatrix[i] = inv[i];
		}

		if(inv[11] == null && CraftingHelper.processRecipe(craftingMatrix) != null) {
			craft = CompletableFuture.supplyAsync(() -> CraftingHelper.processToolRecipe(craftingMatrix), SimplyTools.fjp);
			System.out.println(SimplyTools.fjp.getPoolSize());
			for(int i = 0; i < 9; i++) {
				inv[i] = null;
			}
		}
	}

	public void finalizeCraft() {
		while(!craft.isDone()) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		inv[11] = craft.join();
	}

	public boolean canCraft() {
		ItemStack[] craftingMatrix = new ItemStack[9];
		for(int i = 0; i < 9; i++) {
			craftingMatrix[i] = inv[i];
		}
		return inv[11] == null && CraftingHelper.processRecipe(craftingMatrix) != null;
	}

	public void saw() {
		inv[12] = CraftingHelper.processSawRecipe(inv[12]);
	}
}

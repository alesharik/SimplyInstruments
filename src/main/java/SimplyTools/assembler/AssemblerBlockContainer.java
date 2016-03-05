package SimplyTools.assembler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class AssemblerBlockContainer extends Container {

	int xSize = 176;
	int ySize = 260;

	protected AssemblerTileEntity tileEntity;

	public AssemblerBlockContainer(InventoryPlayer invPlayer, AssemblerTileEntity te) {
		super();
		tileEntity = te;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				addSlotToContainer(new Slot(tileEntity, j + i * 3, 59 + j * 18, 21 + i * 18));
			}
		}
		this.addSlotToContainer(new Slot(tileEntity, 10, 148, 10));
		this.addSlotToContainer(new Slot(tileEntity, 11, 148, 33));
		if(tileEntity.getSawInTable()) {
			this.addSlotToContainer(new Slot(tileEntity, 12, 138, 104));
		}
		bindPlayerInventory(invPlayer);
	}

	@Override
	public boolean canInteractWith(EntityPlayer p) {
		return ((IInventory) tileEntity).isUseableByPlayer(p);
	}

	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 138 + i * 18));
			}
		}

		for(int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 196));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		this.tileEntity.sendNBTUpdate();
		return null;
	}

}

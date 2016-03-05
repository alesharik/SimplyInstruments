package SimplyTools.API;

import java.util.Set;

import net.minecraft.item.ItemTool;

public class STInstrument extends ItemTool {

	int durability;
	int speed;
	ToolHelper tool;
	boolean isRepairOnAnvil;
	boolean isRepairOnTable;

	protected STInstrument(float f1, ToolMaterial tm, Set set1) {
		super(f1, tm, set1);
		this.setCreativeTab(null);
		this.canRepair = this.isRepairOnAnvil;
		this.maxStackSize = 1;
		if(this.tool == ToolHelper.Pickaxe) {
			this.setHarvestLevel("Pickaxe", this.speed);
		} else if(this.tool == ToolHelper.Axe) {
			this.setHarvestLevel("Axe", this.speed);
		} else if(this.tool == ToolHelper.Showel) {
			this.setHarvestLevel("Showel", this.speed);
		} else {
			System.out.println("Error in creating instrument!!");
		}
	}

	public int getDurability() {
		return this.durability;
	}

	public void setDurability(int d) {
		this.durability = d;
	}

	public int getSpeed() {
		return this.speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isRepairOnAnvil() {
		return this.isRepairOnAnvil;
	}

	public void setRepairOnAnvil(boolean b) {
		this.isRepairOnAnvil = b;
	}

	public boolean isRepairOnTable() {
		return this.isRepairOnTable;
	}

	public void setRepairOnTable(boolean b) {
		this.isRepairOnTable = b;
	}

	public ToolHelper getTool() {
		return this.tool;
	}

	public void setTool(ToolHelper tool) {
		this.tool = tool;
	}

	private void setDynamicTexture() {

	}
}

package SimplyTools.instruments;

import java.util.Set;

import SimplyTools.API.STInstrument;
import SimplyTools.API.ToolHelper;

public class Pickaxe extends STInstrument {

	protected Pickaxe(float f1, ToolMaterial tm, Set set1) {
		super(f1, tm, set1);
		this.setRepairOnAnvil(false);
		this.setRepairOnTable(true);
		this.setTool(ToolHelper.Pickaxe);
	}

}

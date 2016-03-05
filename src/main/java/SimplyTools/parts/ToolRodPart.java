package SimplyTools.parts;

import SimplyTools.API.STPart;

public class ToolRodPart extends STPart {

	public ToolRodPart(String unlocalizedName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setTextureName("SimplyTools:toolRod");
		// this.setTextureName("SimplyInstruments:SawItem");
	}

	@Override
	public boolean[] getCraftingPattern() {
		boolean[] ret = { false, false, false, false, true, false, false, true, false };
		return ret;
	}
}

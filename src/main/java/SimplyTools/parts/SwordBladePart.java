package SimplyTools.parts;

import SimplyTools.API.STPart;

public class SwordBladePart extends STPart {

	public SwordBladePart(String unlocalizedName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setTextureName("SimplyTools:swordBlade");
	}

	@Override
	public boolean[] getCraftingPattern() {
		boolean[] ret = { false, false, true, false, true, true, true, true, false };
		return ret;
	}
}

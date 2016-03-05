package SimplyTools.parts;

import SimplyTools.API.STPart;

public class AxeHeadPart extends STPart {

	public AxeHeadPart(String unlocalizedName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setTextureName("SimplyTools:axeHead");
	}

	@Override
	public boolean[] getCraftingPattern() {
		boolean[] ret = { true, true, true, true, true, false, true, false, false };
		return ret;
	}

}

package SimplyTools.parts;

import SimplyTools.API.STPart;

public class HoeHeadPart extends STPart {

	public HoeHeadPart(String unlocalizedName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setTextureName("SimplyTools:hoeHead");
	}

	@Override
	public boolean[] getCraftingPattern() {
		boolean[] ret = { true, true, true, false, false, true, false, false, false };
		return ret;
	}
}

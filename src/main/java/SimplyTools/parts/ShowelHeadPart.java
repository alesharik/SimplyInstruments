package SimplyTools.parts;

import SimplyTools.API.STPart;

public class ShowelHeadPart extends STPart {

	public ShowelHeadPart(String unlocalizedName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setTextureName("SimplyTools:showelHead");
	}

	@Override
	public boolean[] getCraftingPattern() {
		boolean[] ret = { true, true, true, true, true, true, false, false, false };
		return ret;
	}

}

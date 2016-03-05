package SimplyTools.parts;

import SimplyTools.API.STPart;

public class PickaxeHeadPart extends STPart {

	public PickaxeHeadPart(String unlocalizedName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setTextureName("SimplyTools:pickaxeHead");
	}

	@Override
	public boolean[] getCraftingPattern() {
		boolean ret[] = { true, true, true, true, false, true, false, false, false };
		return ret;
	}

}

package SimplyTools.API;

import java.awt.Color;

public class ARGB {

	public int a = 0;
	public int r = 0;
	public int g = 0;
	public int b = 0;

	public static final ARGB NULLCOLOR = new ARGB(0, 0, 0, 0);

	public ARGB(int a, int r, int g, int b) {
		this.a = a;
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public Color getColor() {
		return new Color(this.r, this.g, this.b, this.a);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ARGB) {
			return ((ARGB) obj).r == this.r && ((ARGB) obj).g == this.g && ((ARGB) obj).r == this.r;
		} else if(obj instanceof Color) {
			return this.getColor().equals(obj);
		} else {
			return obj.equals(this);
		}
	}
}

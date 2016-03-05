package SimplyTools.API;

public class ARGBXY {

	public int a = 0;
	public int r = 0;
	public int g = 0;
	public int b = 0;
	public int x = 0;
	public int y = 0;

	public ARGBXY(int a, int r, int g, int b, int x, int y) {
		this.a = a;
		this.r = r;
		this.g = g;
		this.b = b;
		this.x = x;
		this.y = y;
	}

	public ARGB getARGB() {
		return new ARGB(this.a, this.r, this.g, this.b);
	}

}

package utils;

public class Pixel {
	
	private int x,y,r,g,b;

	public Pixel() {}
	
	public Pixel(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Pixel(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}
	
	
}

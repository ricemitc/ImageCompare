
public class Dimension {
	

	private int height;
	private int width;
	
	
	public Dimension() {
		width = 0;
		height = 0;
	}
	
	
	public Dimension(int x, int y) {
		width = x;
		height = y;
	}
	
	
	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}

	
	
}

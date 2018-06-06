
public class Item {
	public double width, height;
	public double posiX, posiY;
	//public double aX, aY;
	public double vX, vY;
	public double m;
	
	public InputEvent ie;
	
	public Item(double massive, double width, double height, double posiX, double posiY) {
		this.width = width;
		this.height = height;
		this.posiX = posiX;
		this.posiY = posiY;
		//this.aX = 0; this.aY = 0;
		this.vX = 0; this.vY = 0;
		this.m = massive;
		this.ie = InputEvent.getInputEvent();
	}
}

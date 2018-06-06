import javafx.scene.input.KeyCode;

public class Item {
	public double width, height;
	public double posiX, posiY;
	public double aX, aY;
	public double vX, vY;
	public double m;
	public double g;
	
	public KeyCode up, down, left, right;
	
	
	public InputEvent ie;
	
	public Item(double massive, double width, double height, double posiX, double posiY, double gravity) {
		this.width = width;
		this.height = height;
		this.posiX = posiX;
		this.posiY = posiY;
		this.aX = 0; this.aY = 0;
		this.vX = 0; this.vY = 0;
		this.m = massive;
		this.g = gravity;
		this.ie = InputEvent.getInputEvent();
	}
	
	protected void triggerEvent() {
		if(up!=null&&ie.checkState(up)) {
			upEvent();
		}
		if(down!=null&&ie.checkState(down)) {
			downEvent();
		}
		if(left!=null&&ie.checkState(left)) {
			leftEvent();
		}
		if(right!=null&&ie.checkState(right)) {
			rightEvent();
		}
	}
	
	protected void upEvent() {
		
	}
	protected void downEvent() {
		
	}
	protected void leftEvent() {
		
	}
	protected void rightEvent() {
		
	}
}

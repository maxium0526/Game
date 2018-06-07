import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Item {
	public double width, height;
	public double posiX, posiY;
//	public double aX, aY;
//	public double vX, vY;
//	public double m;
//	public double g;
	
	public KeyCode up, down, left, right;
	
	public Color color;
	
	public InputEvent ie;
	
	public Item(double width, double height, double posiX, double posiY) {
		this.width = width;
		this.height = height;
		this.posiX = posiX;
		this.posiY = posiY;
//		this.aX = 0; this.aY = 0;
//		this.vX = 0; this.vY = 0;
//		this.m = massive;
//		this.g = gravity;
		this.ie = InputEvent.getInputEvent();
	}
	
	public void setKeys(KeyCode up, KeyCode down, KeyCode left, KeyCode right) {
		setKeyUp(up);
		setKeyDown(down);
		setKeyLeft(left);
		setKeyRight(right);
	}
	
	public void setKeyUp(KeyCode kc) {
		up = kc;
	}
	public void setKeyDown(KeyCode kc) {
		down = kc;
	}
	public void setKeyLeft(KeyCode kc) {
		left = kc;
	}
	public void setKeyRight(KeyCode kc) {
		right = kc;
	}
	
	public void setColor(Color color) {
		this.color = color;
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
	
	
	
	public Bound getBound(int type) {
		switch(type) {
			case Bound.UP : return new Bound(Bound.UP, posiY, posiX, posiX+width);
			case Bound.RIGHT : return new Bound(Bound.RIGHT, posiX+width, posiY, posiY+height);
			case Bound.DOWN : return new Bound(Bound.DOWN, posiY+height, posiX, posiX+width);
			case Bound.LEFT : return new Bound(Bound.LEFT, posiX, posiY, posiY+height);
			default: return null;
		}
	}
}

abstract class MovableItem extends Item implements Movable{
	public double aX, aY;
	public double vX, vY;
	public double m;
	public double g;
	
	public MovableItem(double width, double height, double posiX, double posiY,double massive, double gravity) {
		super(width, height, posiX, posiY);
		this.m = massive;
		this.g = gravity;
		this.aX = 0; this.aY = 0;
		this.vX = 0; this.aY = 0;
	}

	
}


class MapItem extends Item implements Drawable{
	public MapItem(double width, double height, double posiX, double posiY) {
		super(width,height,posiX,posiY);
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(color);
		gc.fillRect(posiX, posiY, width, height);
	}
	
}

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends Item implements Movable, Drawable{
	public String name;
	
	public double f;
	
	public Player(String name,double weight, double width, double height, double posiX, double posiY, double gravity, double fY, KeyCode up, KeyCode left, KeyCode right) {
		super(weight,width,height,posiX,posiY,gravity);
		this.name = name;
		this.up = up;
		this.left = left;
		this.right = right;
		this.f = fY;
	}
	@Override
	public void calcNxtPosi(double fX, double fY, double frameTime, double pxsPM) {//f為被動受力,非主動受力
		// TODO Auto-generated method stub
		aX = 0; aY = 0;
		aX += fX / m; aY += fY / m;
		
		aY += g;
		
		triggerEvent();
		
		vX += aX * frameTime;
		vY += aY * frameTime;
		posiX += vX * pxsPM * frameTime;
		posiY += vY * pxsPM * frameTime;
	}
	
	@Override
	protected void upEvent() {
		aY += f / m;
	}
	
	@Override
	protected void leftEvent() {
		posiX-=(1/m);
	}
	
	protected void rightEvent() {
		posiX+=(1/m);
	}
	
	public void touch(Item item) {
		if(this.getBound(Bound.DOWN).touch(item.getBound(Bound.UP), height/2)) {
			posiY=item.getBound(Bound.UP).posi-height;
			vY=0;
			
			
		}
		
		if(this.getBound(Bound.UP).touch(item.getBound(Bound.DOWN), height/2)) {
			posiY=item.getBound(Bound.DOWN).posi;
			vY=0;
		}
		if(this.getBound(Bound.LEFT).touch(item.getBound(Bound.RIGHT), height/2)) {
			posiX=item.getBound(Bound.RIGHT).posi;
			vX=0;
		}
		

		
		if(this.getBound(Bound.RIGHT).touch(item.getBound(Bound.LEFT), height/2)) {
			posiX=item.getBound(Bound.LEFT).posi-width;
			vX=0;
		}
		
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.BLACK);
		
		//if player out of the canvas, print Posi
//		if(posiY<0) {
//			gc.fillText(String.valueOf((int)(-posiY/pxsPM)), playerXPosi, 15);
//		}
		
		//print player name
		gc.fillText(name, posiX, posiY-8);
		
		//print player
		gc.fillRect(posiX, posiY , width, height);//(a>0?a:0)) is Gravity-effected Animation
		
		//print injector fire
		if(ie.checkState(up)) {
			gc.setFill(Color.ORANGE);
			double[] x = {posiX,posiX+width,posiX+width/2};
			double[] y = {posiY+height,posiY+height,posiY+height*1.5};
			gc.fillPolygon(x,y,3);			
		}
	}
	
	
}

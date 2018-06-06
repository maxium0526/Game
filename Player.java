import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends Item implements Movable, Drawable{
	public String name;
	
	public double f;
	
	public Player(String name,double weight, double width, double height, double posiX, double posiY, double gravity, double fY, KeyCode up) {
		super(weight,width,height,posiX,posiY,gravity);
		this.name = name;
		this.up = up;
		this.f = fY;
	}
	@Override
	public void calcNxtFrame(double fX, double fY, double frameTime, double pxsPM) {//f為被動受力,非主動受力
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

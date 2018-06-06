import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends Item implements Movable, Drawable{
	public String name;
	
	public Player(String name,double weight, double width, double height, double posiX, double posiY) {
		super(weight,width,height,posiX,posiY);
		this.name = name;
	}
	@Override
	public void calcNxtFrame(double fX, double fY, double frameTime, double pxsPM) {
		// TODO Auto-generated method stub
		double aX = fX / m, aY = fY / m;
		vX += aX * frameTime;
		vY += aY * frameTime;
		posiX += vX * pxsPM * frameTime;
		posiY += vY * pxsPM * frameTime;
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
		if(ie.checkState(KeyCode.UP)) {
			gc.setFill(Color.ORANGE);
			double[] x = {posiX,posiX+width,posiX+width/2};
			double[] y = {posiY+height,posiY+height,posiY+height*1.5};
			gc.fillPolygon(x,y,3);			
		}
	}
	
	
}

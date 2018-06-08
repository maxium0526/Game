import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player extends MovableItem implements Drawable{
	public String name;
	
	public double f;
	
	public boolean touchedGround = false;
	public boolean dead = false;
	
	public Player(String name,double weight, double width, double height, double posiX, double posiY, double gravity, double fY) {
		super(width,height,posiX,posiY,weight,gravity);
		this.name = name;
		this.f = fY;
	}
	
	public void calcSpeed(double fX, double fY, double frameTime, double pxsPM) {//f為被動受力,非主動受力
		aX = 0; aY = 0;
		aX += fX / m; aY += fY / m;		
		aY += g;		
				
	}
	public void calcNxtPosi(double frameTime, double pxsPM) {
			
		vX += aX * frameTime;
		vY += aY * frameTime;
		posiX += vX * pxsPM * frameTime;
		posiY += vY * pxsPM * frameTime;
		
		vX/=1.1;
	}
	
	@Override
	protected void upEvent(double frameTime, double pxsPM) {
		aY += f / m;
		
	}	
	@Override
	protected void leftEvent(double frameTime, double pxsPM) {
		vX=-3;
	}
	@Override
	protected void rightEvent(double frameTime, double pxsPM) {
		vX=3;
	}
	@Override
	protected void downEvent(double frameTime, double pxsPM) {
		
		if(touchedGround) {
			vY -= 7;
			System.out.println("JUMP!");
		}
	}
	
	public void touch(Item item) {
		if(item instanceof Player) {
			
		} else if(item instanceof MapItem){
			
			if(this.getBound(Bound.DOWN).touch(item.getBound(Bound.UP), 10)) {
				if(vY>0) {
					touchedGround = true;
					posiY=item.getBound(Bound.UP).posi-height;
					vY=-0;	
				}

			} 
			if(this.getBound(Bound.UP).touch(item.getBound(Bound.DOWN), 10)) {
				if(vY<0) {
					posiY=item.getBound(Bound.DOWN).posi;
					vY=-0;
				}
			}
			if(this.getBound(Bound.LEFT).touch(item.getBound(Bound.RIGHT), 10)) {
				posiX=item.getBound(Bound.RIGHT).posi;
				vX=0;
				
				
			}			
			if(this.getBound(Bound.RIGHT).touch(item.getBound(Bound.LEFT), 10)) {
				posiX=item.getBound(Bound.LEFT).posi-width;
				vX=0;
				
				//dead
				if(posiX<1) {
					dead = true;
				}
			}
		}
				
	}
	
	public static void impact(Player a, Player b) {
		double t1, t2;//temporarily store the speed results
		if(a.getBound(Bound.DOWN).touch(b.getBound(Bound.UP), 10)) {
			t1 = (a.vY * (a.m - b.m) + 2 * b.m * b.vY) / (a.m+b.m);
			t2 = (b.vY * (b.m - a.m) + 2 * a.m * a.vY) / (a.m+b.m);
			a.vY = t1;
			b.vY = t2;
			a.posiY=b.posiY-a.height;//prevent stuck
			a.touchedGround = true;
			System.out.println(a.posiY+a.height-b.posiY);
		}
		if(a.getBound(Bound.UP).touch(b.getBound(Bound.DOWN), 10)) {
			t1 = (a.vY * (a.m - b.m) + 2 * b.m * b.vY) / (a.m+b.m);
			t2 = (b.vY * (b.m - a.m) + 2 * a.m * a.vY) / (a.m+b.m);
			a.vY = t1;
			b.vY = t2;
			b.posiY=a.posiY-b.height;
			b.touchedGround = true;;
			System.out.println(a.posiY+a.height-b.posiY);
		}
		if(a.getBound(Bound.LEFT).touch(b.getBound(Bound.RIGHT), 10)) {
			t1 = b.posiX + b.width;
			t2 = a.posiX - b.width;
			a.posiX = t1;
			b.posiX = t2;
		}
		if(a.getBound(Bound.RIGHT).touch(b.getBound(Bound.LEFT), 10)) {
			t1 = b.posiX - a.width;
			t2 = a.posiX + a.width;
			a.posiX = t1;
			b.posiX = t2;
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
		gc.setFill(dead?Color.GRAY:color);
		gc.fillRect(posiX, posiY , width, height);//(a>0?a:0)) is Gravity-effected Animation
		
		//print injector fire
		if(ie.checkState(down)) {
			gc.setFill(Color.ORANGE);
			double[] x = {posiX,posiX+width,posiX+width/2};
			double[] y = {posiY+height,posiY+height,posiY+height*1.5};
			gc.fillPolygon(x,y,3);			
		}
	}
	
	
}

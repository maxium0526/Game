import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Game {
	private Canvas canvas;
	private GraphicsContext gc;
	
	public Game(Canvas canvas, String playerName) {
		this.canvas = canvas;
		this.gc = canvas.getGraphicsContext2D();
		
		this.playerName = playerName;
		
		player = new Player(playerName, 1, 20, 40, 50, canvas.getHeight()-40);
	}
	
	private String playerName;
	
	private double playerHeight = 40, playerWidth = 20;
	private double playerXPosi = 100;
	
	private double pxsPM = 15;//define how many pixels a meter has 
	private double gravity = 9.8;//gravity
	private double injectorA = -10;//Injector acceleration
	
	private double posi = 0;//player position
	private double a = 0;//player acceleration
	private double v = 0;//player speed
	
	private Player player;
	
	//positive down, negative up; up to down is 0-canvas.getHeight()
	
	public void calcParameters() {
		player.calcNxtFrame(0, (Controller.spaceStats?injectorA:gravity)*pxsPM, 0.01, pxsPM);
//		a = (Controller.spaceStats?injectorA:gravity) * pxsPM;
//		v += a*0.01;
//		posi += v*0.01 * pxsPM;
//		if(posi>canvas.getHeight()-playerHeight) {//Detect if player touch the ground
//			posi=canvas.getHeight()-playerHeight;//limit the posi
//			v=0;//reset speed
//		}	
		if(player.posiY>canvas.getHeight()-playerHeight) {
			player.posiY=canvas.getHeight()-playerHeight;
			player.vY = 0;
		}
	}
	
	public void displayFrame() {
		cleanDisplay();
		//drawPlayer();
		player.draw(gc);
	}
	
	private void cleanDisplay() {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	private void drawPlayer() {
		gc.setFill(Color.BLACK);
		
		//if player out of the canvas, print Posi
		if(posi<0) {
			gc.fillText(String.valueOf((int)(-posi/pxsPM)), playerXPosi, 15);
		}
		
		//print player name
		gc.fillText(playerName, playerXPosi, posi-8);
		
		//print player
		gc.fillRect(playerXPosi, posi , playerWidth, playerHeight - (a<0?-a*20/playerHeight/pxsPM:0));//(a>0?a:0)) is Gravity-effected Animation
		
		//print injector fire
		if(Controller.spaceStats) {
			gc.setFill(Color.ORANGE);
			double[] x = {playerXPosi,playerXPosi+playerWidth,playerXPosi+playerWidth/2};
			double[] y = {posi+playerHeight+a*20/playerHeight/pxsPM,posi+playerHeight+a*20/playerHeight/pxsPM,posi+playerHeight*1.5};
			gc.fillPolygon(x,y,3);			
		}
	}
}

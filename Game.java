import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Game {
	private Canvas canvas;
	private GraphicsContext gc;
	
	public Game(Canvas canvas) {
		this.canvas = canvas;
		this.gc = canvas.getGraphicsContext2D();
	}
	
	private double playerHeight = 40, playerWidth = 20;
	private double playerXPosi = 100;
	
	private double pxsPM = 15;//define how many pixels a meter has 
	
	private double posi = 0;//player position
	private double a = 0;//player acceleration
	private double v = 0;//player speed
	
	//positive down, negative up; up to down is 0-canvas.getHeight()
	
	public void calcParameters() {
		a = (Controller.spaceStats?-5:9.8) * pxsPM;
		v += a*0.01;
		posi += v*0.01 * pxsPM;
		if(posi>canvas.getHeight()-playerHeight) {//Detect if player touch the ground
			posi=canvas.getHeight()-playerHeight;//limit the posi
			v=0;//reset speed
		}	
	}
	
	public void displayFrame() {
		cleanDisplay();
		drawPlayer();
	}
	
	private void cleanDisplay() {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	private void drawPlayer() {
		gc.setFill(Color.BLACK);
		gc.fillRect(playerXPosi, posi , playerWidth, playerHeight - (a>0?-a*20/playerHeight/pxsPM:0));//(a>0?a:0)) is Gravity-effected Animation
	}
}

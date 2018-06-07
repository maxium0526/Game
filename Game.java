import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Game {
	private Canvas canvas;
	private GraphicsContext gc;
//	private InputEvent ie;
	
	public Game(Canvas canvas, String playerName1, String playerName2) {
		this.canvas = canvas;
		this.gc = canvas.getGraphicsContext2D();		
//		this.ie = InputEvent.getInputEvent();
		
		player = new Player(playerName1, 1, 20, 40, 50, 250,9.8,-20);
		player.setKeys(KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D);
		player.setColor(Color.BLUE);
		
		npc = new Player(playerName2, 1.5, 30, 50, 100, 250,9.8,-50);
		npc.setKeys(KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT);
		npc.setColor(Color.RED);
		
		ground = new MapItem(canvas.getWidth(),100,0,canvas.getHeight()-20);
		ground.setColor(Color.BLACK);
		
		ceiling = new MapItem(canvas.getWidth(),20,10,20);
		ceiling.setColor(Color.BLACK);
	}
	
	private double pxsPM = 28;//define how many pixels a meter has 
	
	private Player player, npc;
	private MapItem ground,ceiling;
	
	//positive down, negative up; up to down is 0-canvas.getHeight()
	
	public void calcParameters() {
		player.calcNxtPosi(0, 0, 0.01, pxsPM);
		npc.calcNxtPosi(0, 0, 0.01, pxsPM);
		
		player.touch(ground);
		npc.touch(ground);
		
		player.touch(ceiling);
		npc.touch(ceiling);
		
		Player.impact(player, npc);

		player.touch(npc);
		npc.touch(player);
		
//		if(player.posiY>canvas.getHeight()-player.height) {
//			player.posiY=canvas.getHeight()-player.height;
//			player.vY = 0;
//		}
//		if(npc.posiY>canvas.getHeight()-npc.height) {
//			npc.posiY=canvas.getHeight()-npc.height;
//			npc.vY = 0;
//		}
	}
	
//	private void touch() {
//		
//	}
	
	public void displayFrame() {
		cleanDisplay();
		player.draw(gc);
		npc.draw(gc);
		
		ground.draw(gc);
		ceiling.draw(gc);
		
		drawScale(420,370);
		
	}
	
	private void cleanDisplay() {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	private void drawScale(double x, double y) {
		gc.setFill(Color.BLACK);
		gc.strokeLine(x, y, x+pxsPM, y);
		gc.strokeLine(x,y-3,x,y+3);
		gc.strokeLine(x+pxsPM,y-3,x+pxsPM,y+3);
		gc.fillText("1m", x+5, y-5);
	}
	
}

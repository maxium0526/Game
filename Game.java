import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Game {
	private Canvas canvas;
	private GraphicsContext gc;
//	private InputEvent ie;
	
	public Game(Canvas canvas, String playerName) {
		this.canvas = canvas;
		this.gc = canvas.getGraphicsContext2D();		
//		this.ie = InputEvent.getInputEvent();
		
		player = new Player(playerName, 1, 20, 40, 50, 0,9.8,-20,KeyCode.W,KeyCode.A,KeyCode.D);
		npc = new Player("NPC00", 1.5, 30, 50, 100, 0,9.8,-20,KeyCode.UP,KeyCode.LEFT, KeyCode.RIGHT);
		
		ground = new Item(0,canvas.getWidth(),50,0,canvas.getHeight(),0);
	}
	
	private double pxsPM = 28;//define how many pixels a meter has 
	
	private Player player, npc;
	private Item ground;
	
	//positive down, negative up; up to down is 0-canvas.getHeight()
	
	public void calcParameters() {
		player.calcNxtPosi(0, 0, 0.01, pxsPM);
		npc.calcNxtPosi(0, 0, 0.01, pxsPM);
		
		player.touch(ground);
		npc.touch(ground);
		

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
	
	private void touch() {
		
	}
	
	public void displayFrame() {
		cleanDisplay();
		player.draw(gc);
		npc.draw(gc);
	}
	
	private void cleanDisplay() {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
}

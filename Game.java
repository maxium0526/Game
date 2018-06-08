import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Game {
	private Canvas canvas;
	private GraphicsContext gc;
//	private InputEvent ie;
	
	private double pxsPM = 50;//define how many pixels a meter has 	
	
	private ArrayList<Player> players;
	private ArrayList<MapItem> edges;
	private ArrayList<MapItem> walls;
	
	private double wallTimer;
	private double wallTime;
	
	public Game(Canvas canvas, String playerName1, String playerName2) {
		this.canvas = canvas;
		this.gc = canvas.getGraphicsContext2D();		
//		this.ie = InputEvent.getInputEvent();
		
		players = new ArrayList<Player>();
		
		Player player = new Player(playerName1, 1, 22, 40, 50, 250,9.8,-20);
		player.setKeys(KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D);
		player.setColor(Color.BLUE);
		players.add(player);
		
		Player npc = new Player(playerName2, 1, 23, 40, 80, 250,9.8,-20);
		npc.setKeys(KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT);
		npc.setColor(Color.RED);	
		players.add(npc);
		
		edges = new ArrayList<MapItem>();
		edges.add(new MapItem(canvas.getWidth(), 100, 21, -100));//д╤кс
		edges.add(new MapItem(canvas.getWidth(), 100, 0, canvas.getHeight()));//жa
		edges.add(new MapItem(100, canvas.getHeight(), -100, -0));//ек
		edges.add(new MapItem(100, canvas.getHeight(), canvas.getWidth(), 0));
		
		walls = new ArrayList<MapItem>();
		
		wallTime = 200;
		wallTimer = wallTime;
	}
	
	
	
	//positive down, negative up; up to down is 0-canvas.getHeight()
	
	public void next() {
		calcParameters();
		wallTimer --;
		if(wallTimer<=0) {
			wallTime-=75;
			wallTime /= 1.05;
			wallTime+=75;
			wallTimer = wallTime;
			int rnd1, rnd2;
			do {
				 rnd1 = (int) (Math.random() * canvas.getHeight());
				 rnd2 = (int) (Math.random() * canvas.getHeight());
				 System.out.println(rnd1+","+rnd2);
				 if(rnd2<rnd1) {
					 int t = rnd2;
					 rnd2 = rnd1;
					 rnd1 = t;
				 }
				 if(rnd2-rnd1<100 && rnd2-rnd1>70) {
					 break;
				 }
			} while(true);
			MapItem wall1 = new MapItem(20,rnd1,600,0);
			MapItem wall2 = new MapItem(20,canvas.getHeight()-rnd2,600,rnd2);
			wall1.setColor(Color.BLACK);
			wall2.setColor(Color.BLACK);
			walls.add(wall1);
			walls.add(wall2);
		}
//		for(MapItem w:walls) {
//			w.posiX--;
//			if(w.posiX<-w.width) {
//				walls.remove(w);
//			}
//		}
		for(int i=0;i<walls.size();i++) {
			walls.get(i).posiX--;
			if(walls.get(i).posiX<-walls.get(i).width) {
				Controller.score1+=players.get(0).dead?0:1000/wallTime + players.get(0).posiX/200;
				Controller.score2+=players.get(1).dead?0:1000/wallTime + players.get(0).posiX/200;
				walls.remove(i);
				i--;
			}
		}
		
		
	}
	
	private void calcParameters() {
		for(Player p:players) {
			p.calcSpeed(0, 0, Controller.frameTime, pxsPM);
		}
		
		for(Player p:players) {
			
			for(MapItem i:edges) {
				p.touch(i);
				
			}
			for(MapItem i:walls) {
				if(p.dead) {
					break;
				}
				p.touch(i);
			}
		}
		
		for(int i=0;i<players.size();i++) {
			for(int j=i;j<players.size();j++) {
				if(players.get(i).dead||players.get(i).dead) {
					break;
				}
				Player.impact(players.get(i), players.get(j));
			}
		}
		
		for(Player p:players) {
			p.doTriggerEvent(Controller.frameTime,pxsPM);
		}
		
		for(Player p:players) {
			p.calcNxtPosi(Controller.frameTime, pxsPM);			
		}
		
		for(Player p:players) {
			p.touchedGround = false;			
		}
		
	}
	
//	private void touch() {
//		
//	}
	
	public void displayFrame() {
		cleanDisplay();
		for(Player p:players) {
			p.draw(gc);		
		}
		
		for(MapItem i:edges) {
			i.draw(gc);
		}
		
		for(MapItem i:walls) {
			i.draw(gc);
		}
		
		drawScale(450-pxsPM,370);
		
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

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Game {
	private Canvas canvas;//遊戲位置
	private GraphicsContext gc;
	private double frameTime;
	
	public boolean state = true;
//	private InputEvent ie;
	
	private double pxsPM = 50;//定義像素每米	
	
	public ArrayList<Player> players;
	public HashMap<Integer,Double> playerScores;
	
	private ArrayList<MapItem> edges;
	private ArrayList<MapItem> walls;
	
	private double wallTimer;
	private WallTimeGetter  wt;
	
	public Game(Canvas canvas) {
		this.canvas = canvas;
		this.gc = canvas.getGraphicsContext2D();
//		this.ie = InputEvent.getInputEvent();
		
		players = new ArrayList<Player>();
		playerScores = new LinkedHashMap<Integer,Double>();
		
		edges = new ArrayList<MapItem>();
		walls = new ArrayList<MapItem>();
		
		wt = new WallTimeGetter(200,65,1.03);
		wallTimer = wt.getTime();
		
		
		
	}
	
	public void init() {
		//預填入玩家分數
		for(int i=0;i<players.size();i++) {
			playerScores.put(i,0d);
		}
		
		//設置邊界
		edges.add(new MapItem(canvas.getWidth(), 100, 21, -100));//天花
		edges.add(new MapItem(canvas.getWidth(), 100, 0, canvas.getHeight()));//地
		edges.add(new MapItem(100, canvas.getHeight(), -100, -0));//左
		edges.add(new MapItem(100, canvas.getHeight(), canvas.getWidth(), 0));
		
		frameTime = Settings.mainTimerPeriod;//預讀取
	}
	
	//positive down, negative up; up to down is 0-canvas.getHeight()
	
	public void next() {
		calcParameters();
		wallTimer --;
		if(wallTimer<=0) {
			wt.nxt();
			wallTimer = wt.getTime();			
			createWall();
		}
		moveWalls();
//		boolean isEnd = true;
//		for(Player p:players) {
//			isEnd = isEnd && p.dead;
//			if(!isEnd) {
//				break;
//			}
//		}
		if(playersAllDied()) {
//			isEnd = false; //avoid multiSending
//			state = false;			
			for(int i=0;i<players.size();i++) {
				Controller.uploadScore(players.get(i).name,playerScores.get(i));
			}
			Controller.stopGame();
		}
	}
	
	private void calcParameters() {
		for(Player p:players) {
			p.calcSpeed(0, 0, frameTime, pxsPM);
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
			p.doTriggerEvent(frameTime,pxsPM);
		}
		
		for(Player p:players) {
			p.calcNxtPosi(frameTime, pxsPM);			
		}
		
		for(Player p:players) {
			p.touchedGround = false;			
		}
		
	}
	
	public void createWall() {
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
		MapItem wall1 = new MapItem(20,rnd1,canvas.getWidth(),0);
		MapItem wall2 = new MapItem(20,canvas.getHeight()-rnd2,canvas.getWidth(),rnd2);
		wall1.setColor(Color.BLACK);
		wall2.setColor(Color.BLACK);
		walls.add(wall1);
		walls.add(wall2);
	}
	
	public void moveWalls() {
		for(int i=0;i<walls.size();i++) {
			walls.get(i).posiX--;
			if(walls.get(i).posiX<-walls.get(i).width) {
				for(int j=0;j<players.size();j++) {
					double t = playerScores.get(j);//暫存分數
					t += players.get(j).dead?0:1000/wt.getTime() + players.get(j).posiX/200;
					playerScores.put(j, t);
				}
//				Controller.score1+=players.get(0).dead?0:1000/wt.getTime() + players.get(0).posiX/200;
//				Controller.score2+=players.get(1).dead?0:1000/wt.getTime() + players.get(0).posiX/200;
				walls.remove(i);
				i--;
			}
		}
	}

	public boolean playersAllDied() {
		boolean result = true;
		for(Player p:players) {
			result = result && p.dead;
			if(!result) {
				return false;
			}
		}
		return true;
	}
	
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
		drawWallCreateSpeed(420,390);
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
	private void drawWallCreateSpeed(double x, double y) {
		gc.fillText(String.valueOf(new DecimalFormat("#0.00").format(100/wt.getTime())), x, y);
	}
	
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	public double getScore(int n) {
		return playerScores.get(n);
	}
	
}

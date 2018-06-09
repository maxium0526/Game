import java.net.URL;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Controller {
//	public static double score1 = 0, score2 = 0;
//	public static boolean stopGame = false;
//	public static boolean sendScore = false;
	protected InputEvent ie = InputEvent.getInputEvent();
	
	public static Game game;
	public static Timeline gameTimer;
	
	@FXML
	private SplitPane splitPane;
	@FXML
	private TextField nameField1, nameField2;
	@FXML
	private Canvas canvas;
	@FXML
	private Label score1Label;
	
	@FXML
	protected void startGame(ActionEvent event) throws InterruptedException {
		splitPane.requestFocus();
		
		game = new Game(canvas);
		
		Player player = new Player(nameField1.getText(), 1, 21, 38, 50, 250,9.8,-20);
		player.setKeys(KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D);
		player.setColor(Color.BLUE);
		game.addPlayer(player);
		
		game.init();

//		stopGame = false;
		score1Label.setText("0");
		
		gameTimer = new Timeline(new KeyFrame(
				Duration.millis(Settings.mainTimerPeriod * 1000), ae -> {
					game.next();
					
					game.displayFrame();
					
					score1Label.setText(String.valueOf((int)game.getScore(0)));

				}));		
		gameTimer.setCycleCount(Animation.INDEFINITE);
		gameTimer.play();
		
		
	}
	public static void uploadScore(String name, double score) {
		if(Settings.uploadScore) {
			try {
				HTTPRequest request = new HTTPRequest(new URL(Settings.serverURL));
				request.setPostData("name="+(name.equals("")?"nameless":name)+"&score="+(int)score+"&ver="+Settings.version);
				String[] str = request.read();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("HTTP Request failed.");
			}
//			sendScore = false;
			
		}
	}
	
	public static void stopGame() {
		gameTimer.stop();
	}
	
	@FXML
	protected void press(MouseEvent event) {

	}
	@FXML
	protected void release(MouseEvent event) {

	}
	@FXML
	protected void pressKey(KeyEvent e) {
//		if(e.getCode()==KeyCode.UP) {
//			spaceStats = true;
//		}
		ie.press(e.getCode());
	}
	@FXML
	protected void releaseKey(KeyEvent e) {
//		if(e.getCode()==KeyCode.UP) {
//			spaceStats = false;
//		}
		ie.release(e.getCode());
	}
	
	@FXML
	protected void splitFocus(MouseEvent e) {
		splitPane.requestFocus();
	}
}

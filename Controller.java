import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class Controller {
	public static final double frameTime = 0.01;
	public static double score1 = 0, score2 = 0;
	protected InputEvent ie = InputEvent.getInputEvent();
	
	@FXML
	private SplitPane splitPane;
	@FXML
	private TextField nameField1, nameField2;
	@FXML
	private Canvas canvas;
	@FXML
	private Label score1Label, score2Label;
	
	@FXML
	protected void startGame(ActionEvent event) throws InterruptedException {
		splitPane.requestFocus();
		
		Game game = new Game(canvas,nameField1.getText(),nameField2.getText());
		
		Timeline timer = new Timeline(new KeyFrame(
				Duration.millis(frameTime * 1000), ae -> {
					game.next();
					
					game.displayFrame();
					
					score1Label.setText(String.valueOf((int)score1));
					score2Label.setText(String.valueOf((int)score2));
				}));
		
		timer.setCycleCount(Animation.INDEFINITE);
		timer.play();
		
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

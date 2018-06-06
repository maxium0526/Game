import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class Controller {
//	public static boolean spaceStats=false;
	protected InputEvent ie = InputEvent.getInputEvent();
	
	@FXML
	private SplitPane splitPane;
	@FXML
	private TextField nameField;
	@FXML
	private Canvas canvas;
	
	@FXML
	protected void startGame(ActionEvent event) throws InterruptedException {
		splitPane.requestFocus();
		
		Game game = new Game(canvas,nameField.getText());
		
		Timeline timer = new Timeline(new KeyFrame(
				Duration.millis(10), ae -> {
					game.calcParameters();
					
					game.displayFrame();
					
					
					
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

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class Controller {
	public static boolean spaceStats=false;
	
	
	@FXML
	private TextField nameField;
	@FXML
	private Canvas canvas;
	
	@FXML
	protected void startGame(ActionEvent event) throws InterruptedException {
		
		
		Game game = new Game(canvas);
		
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
		spaceStats = true;
//		GraphicsContext gc = canvas.getGraphicsContext2D();
//		gc.fillRect(20, 20, 50, 50);
	}
	@FXML
	protected void release(MouseEvent event) {
		spaceStats = false;
	}
	@FXML
	protected void pressKey(KeyEvent e) {
		if(e.getCode()==KeyCode.UP) {
			spaceStats = true;
		}
	}
	@FXML
	protected void releaseKey(KeyEvent e) {
		if(e.getCode()==KeyCode.UP) {
			spaceStats = false;
		}
	}
}

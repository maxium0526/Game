import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
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
		Drawer d = new Drawer(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		Game game = new Game();
		
		Timeline timer = new Timeline(new KeyFrame(
				Duration.millis(10), ae -> {
					double posi = game.go();
					
					
					
					d.clean();
					gc.fillRect(50, 400-posi-50, 25,50);
					
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
}

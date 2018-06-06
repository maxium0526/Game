import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.*;
import javafx.stage.Stage;

public class UI extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("ui.fxml"));
		Scene scene = new Scene(root, 600, 400);
		
		arg0.addEventHandler(KeyEvent.KEY_PRESSED, 
				(KeyEvent e)->{
					if(e.getCode()==KeyCode.UP) {
						Controller.spaceStats = true;
					}
				}
			);
		arg0.addEventHandler(KeyEvent.KEY_RELEASED, 
				(KeyEvent e)->{
					if(e.getCode()==KeyCode.UP) {
						Controller.spaceStats = false;
					}
				}
			);
		
		arg0.setScene(scene);
		arg0.setTitle("My Game1 v0 powered by Maxium");
		arg0.show();
		
	}
	
	public static void main(String[] args) {
		launch();
	}
}

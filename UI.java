import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.*;
import javafx.stage.Stage;

public class UI extends Application{
	InputEvent ie = InputEvent.getInputEvent();
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("ui.fxml"));
		Scene scene = new Scene(root, 600, 400);
		
		arg0.addEventHandler(KeyEvent.KEY_PRESSED, 
				(KeyEvent e)->{
						ie.press(e.getCode());
				}
			);
		arg0.addEventHandler(KeyEvent.KEY_RELEASED, 
				(KeyEvent e)->{
						ie.release(e.getCode());
				}
			);
		
		arg0.setScene(scene);
		arg0.setTitle("My Game1 v"+ Settings.version + " powered by Maxium");
		arg0.show();
		
	}
	
	public static void main(String[] args) {
		System.out.println("Version: "+ Settings.version);
		System.out.println("Physical System Version: "+Settings.systemVersion);
		System.out.println("Server URL: "+(Settings.uploadScore?Settings.serverURL:"N/A"));
		System.out.println("Game Refersh Time: "+ Settings.mainTimerPeriod + "s");
		System.out.println();
		System.out.println("This game is written by Maxium(maxium0526@github).");
		System.out.println("Adopted external code:");
		System.out.println("HTTPRequest : Konloch @ github");
		System.out.println();
		launch();
	}
}

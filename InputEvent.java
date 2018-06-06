import java.util.HashSet;

import javafx.scene.input.KeyCode;

public class InputEvent {
	public static InputEvent getInputEvent() {
		return inputEvent;
	}
	private static InputEvent inputEvent = new InputEvent();
	private InputEvent() {
	}
	
	HashSet<KeyCode> keySet = new HashSet<KeyCode>();
	boolean mouse = false;
	
	public void press(KeyCode kc) {
		keySet.add(kc);
	}
	public void release(KeyCode kc) {
		keySet.remove(kc);
	}
	
	public void pressMouse() {
		mouse = true;
	}
	public void releaseMouse() {
		mouse = false;
	}
	
	public boolean checkState(KeyCode kc) {
		return keySet.contains(kc);
	}
	public boolean checkMouseState() {
		return mouse;
	}
}

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Drawer {
	private final GraphicsContext gc;
	private final double width, height;
	public Drawer(javafx.scene.canvas.Canvas c) {
		this.gc = c.getGraphicsContext2D();
		width = c.getWidth();
		height = c.getHeight();
	}
	
	public void clean() {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, width, height);
		gc.setFill(Color.BLACK);
	}
}

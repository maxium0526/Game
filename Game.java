
public class Game {
	private double posi = 0;
	private double a = 0;
	private double v = 0;
	
	public double go() {
		a = Controller.spaceStats?5:-9.8;
		v += a*0.016*10;
		posi += v*0.016*10;
		if(posi<0) {
			posi=0;
			v=0;
		}
		return posi;
		
//		return Controller.spaceStats?50:0;
	}
}

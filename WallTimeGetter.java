import java.util.HashMap;

public class WallTimeGetter {
	private HashMap<Integer,Double> values;
	private int iter = 0;
	
	private final double offset, level;
	
	public WallTimeGetter(double initValue,double offset, double level) {
		values = new HashMap<Integer,Double>();
		values.put(0, initValue);
		this.offset = offset;
		this.level = level;
	}
	public double getTime() {
		return values.get(iter);
	}
	
	
	public void nxt() {
		values.put(iter+1, getNxtValue());
		iter++;
	}
	private double getNxtValue() {
		double t = values.get(iter);
		t -= offset;
		t /= level;
		t += offset;
		return t;
	}
}

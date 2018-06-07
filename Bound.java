
public class Bound {
	public static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
	
	public int type;
	public double posi;
	public double p1, p2;
	
	public Bound(int type, double posi, double p1, double p2) {
		this.type = type;
		this.posi = posi;
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public boolean touch(Bound b, double offset) {
		double poffset = 1;//debug
		if(type==Bound.DOWN && b.type==Bound.UP) {
			if(Math.abs(posi-b.posi)<offset && posi-b.posi>=0) {
				
				return this.check2Points(b, poffset);
			}
		}
		if(type==Bound.UP && b.type==Bound.DOWN) {
			if(Math.abs(posi-b.posi)<offset && posi-b.posi<=0) {

				return this.check2Points(b, poffset);
			}
		}
		if(type==Bound.LEFT && b.type==Bound.RIGHT) {
			if(Math.abs(posi-b.posi)<offset && posi-b.posi<=0) {

				return this.check2Points(b, poffset);
			}
		}
		if(type==Bound.RIGHT && b.type==Bound.LEFT) {
			if(Math.abs(posi-b.posi)<offset && posi-b.posi>=0) {

				return this.check2Points(b, poffset);
			}
		}
		return false;
	}
	
	private boolean check2Points(Bound b, double offset) {
		if((p1>b.p1+offset&&p1+offset<b.p2)||(p2>b.p1+offset&&p2+offset<b.p2)||(b.p1>p1+offset&&b.p1+offset<p2)||(b.p2>p1+offset&&b.p2+offset<p2)) {
			if(posi-b.posi>0.15) {
			//	System.out.println(posi-b.posi);
			}
			return true;
		}
		return false;
	}
	
//	old version
//	private boolean check2Points(Bound b) {
//		if((p1>b.p1&&p1<b.p2)||(p2>b.p1&&p2<b.p2)||(b.p1>p1&&b.p1<p2)||(b.p2>p1&&b.p2<p2)) {
//			return true;
//		}
//		return false;
//	}
}

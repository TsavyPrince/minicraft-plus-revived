package minicraft.entity.mob;

public final class Direction {
	
	public static final int DOWN = 0;
	public static final int UP = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	public static final int NONE = -1;
	
	public static int getFromDelta(int xd, int yd) {
		if (xd == 0 && yd == 0) return NONE;
		
		if(Math.abs(xd) > Math.abs(yd)) {
			// the x distance is more prominent than the y distance
			if(xd < 0)
				return LEFT;
			else
				return RIGHT;
		} else {
			if(yd < 0)
				return UP;
			else
				return DOWN;
		}
	}
}

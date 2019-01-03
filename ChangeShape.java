import orbkit.Animation;
import processing.core.PApplet;

/**
 * Changing shape is an animation that changes a rectangle to a smooth polygon and thins the polygon until it disappears.
 * @author yashjalan	yj627@nyu.edu
 */
public class ChangeShape extends Animation {
	
	private float x;
	private float y;
	private float radius;
	private int count = 0;
	/**
	 * The x,y and radius gets the position of where the rectangle will change into the polygon.
	 * @param canvas
	 * @param durationSecs
	 * @param x
	 * @param y
	 * @param radius
	 */
	public ChangeShape(PApplet canvas, double durationSecs, float x, float y, float radius) {
		super(canvas, durationSecs);
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	/**
	 * The method draws a rectangle and the count data field constantly changes the edges and length of the rectangle, making it into a polygon.
	 */
	@Override
	public void drawFrame() {
		canvas.fill(255, 105, 180);
		canvas.noStroke();
		canvas.rect(x + radius + count, y + radius + count, x - radius + count, y - radius + count, radius - count - 15);
		count--;
		
		
	}

}

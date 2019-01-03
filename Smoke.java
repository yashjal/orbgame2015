import orbkit.Animation;
import processing.core.PApplet;

/**
 * A smoke flare comes out in the direction of the movement of the orb.
 * @author yashjalan	yj627@nyu.edu
 *
 */
public class Smoke extends Animation {

	private float x;
	private float y;
	private float radius;
	/**
	 * The params are passed to super class and the x,y and radius data fields are the positions from where the smoke is suppose to come out.
	 * @param canvas
	 * @param durationSecs
	 * @param x
	 * @param y
	 * @param radius
	 */
	protected Smoke(PApplet canvas, double durationSecs, float x, float y, float radius) {
		super(canvas, durationSecs);
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	/**
	 * The method draws little particles that look smoke. 
	 */
	@Override
	public void drawFrame() {
		if (super.getPercentageComplete() < 1) {
			canvas.fill(100);
			canvas.noStroke();
			canvas.ellipse(x, y, radius, radius);
		}
	}
	
}

import orbkit.Animation;
import orbkit.Utils;
import processing.core.PApplet;

/**
 * The multicolor animation displays a disco ball at a given position.
 * @author yashjalan	yj627@nyu.edu
 * @version 1.8
 */
public class Multicoloring extends Animation {
	
	private float x;
	private float y;
	private float radius;
	
	/**
	 * These params just get passed to the super class constructor and the other data fields represent the attributes of the disco ball.
	 * @param canvas
	 * @param durationSecs
	 * @param x
	 * @param y
	 * @param radius
	 */
	Multicoloring(PApplet canvas, double durationSecs, float x, float y, float radius) {
		super(canvas, durationSecs);
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	/**
	 * The method called constantly changes the color of the ball constantly making it disco like.
	 */
	@Override
	public void drawFrame() {
		
		if (super.getPercentageComplete() < 1) {
			
			canvas.fill( (float) (Utils.random(1,255)), (float) (Utils.random(1,255)), (float) (Utils.random(1,255)));
			canvas.noStroke();
			canvas.ellipse(x, y, radius, radius);
		}
	}

	
	
	
}

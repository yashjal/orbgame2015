import orbkit.Animation;
import orbkit.Utils;
import processing.core.PApplet;

/**
 * The animation flashes multiple balls around the window for a brief period.
 * @author yashjalan	yj627@nyu.edu
 * 
 */
public class Flashing extends Animation {

	/**
	 * These params just get passed to the super class constructor.
	 * @param canvas
	 * @param durationSecs
	 */
	public Flashing(PApplet canvas, double durationSecs) {
		super(canvas, durationSecs);

	}
	
	/**
	 * The method draws tiny multiple disks around the window that flash only for a brief period.
	 */
	@Override
	public void drawFrame() {
		
			canvas.fill(255, 255, 0);
			canvas.noStroke();
			canvas.ellipse((float) (Utils.random(0, 1000)), (float) (Utils.random(0, 600)), 4, 4);
	
	}

}

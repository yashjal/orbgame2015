import orbkit.CollidableOrb;
import orbkit.ObjectManager;
import orbkit.OrbBase;

/**
 * The blue orb is a multiplying orb. It is like a cell which on touching any other orb continuously multiplies.
 * @author yashjalan	yj627@nyu.edu
 */
public class BlueOrb extends CollidableOrb {
	
	public static final int RED 	= 0;
	public static final int GREEN	= 0;
	public static final int BLUE	= 255;
	
	/**
	 * These params just get passed to the super class constructor.
	 * @param manager
	 * @param o
	 * @param speedX
	 * @param speedY
	 */
	public BlueOrb(ObjectManager manager, OrbBase o, double speedX,
			double speedY) {
		super(manager, o, speedX, speedY);
		
	}
	
	/**
	 * When a blue orb is created, it leaves grey particles behind it, making it look like smoke. So that smoke comes out of the blue orb.
	 */
	@Override
	protected void dynamicOrbUpdate() {

		addAnimation(new Smoke(canvas, 20, (float) (getX()), (float) (getY()), 10));
	}

	/**
	 * On colliding with any other orb, it duplicates itself.
	 */
	@Override
	protected boolean handleCollisionWith(CollidableOrb o) {
		
		addOrb(BlueOrb.class, getX(), getY(), this.getRadius(), -this.getSpeedX(), -this.getSpeedY(), 
				BlueOrb.RED, BlueOrb.GREEN, BlueOrb.BLUE);
		return true;
	}

}

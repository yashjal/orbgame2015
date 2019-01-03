import orbkit.CollidableOrb;
import orbkit.ObjectManager;
import orbkit.OrbBase;
import orbkit.Timer;
/**
 * If the Green orb touches any other orb, that orb is gone. The green orb only lasts for 10 secs, after which it explodes.
 * @author yashjalan yj627@nyu.edu
 * @version 1.8
 */
public class GreenOrb extends CollidableOrb {
	
	
	public static final int RED 	= 0;
	public static final int GREEN	= 255;
	public static final int BLUE	= 0;
	private Timer timer;
	private final int EXPLOSION_TIME = 10;

	/**
	 * These params just get passed to the super class constructor.
	 * @param allOrbs
	 * @param orbSeed
	 * @param speedX
	 * @param speedY
	 */
	public GreenOrb(ObjectManager allOrbs, OrbBase orbSeed, double speedX, double speedY){
		super(allOrbs,orbSeed,speedX,speedY);
	}
	
	/**
	 * The blast method removes the orb from the game and then has a multicolored animation for 2 secs.
	 */
	public void blast() {
		if (timer.getElapsedTime() > EXPLOSION_TIME) {
			setToRemove();
			addAnimation(new Multicoloring(canvas, 2.0, (float) (getX()), (float)(getY()), (float)(getRadius())));
		}
	}
	/**
	 * We set a timer, and if it cross the max time, blast function is called.
	 */
	@Override
	public void dynamicOrbUpdate() {
		
		if (timer == null && !stillInitializing())
			timer = new Timer();
		blast();
	}
	/**
	 * If this orb meets a green or blue, it bounces, else it removes that orb.
	 */
	@Override
	public boolean handleCollisionWith(CollidableOrb o) {
		if (o instanceof GreenOrb || o instanceof BlueOrb)
			return true;
		else
			o.setToRemove();
		return false;
	}

}

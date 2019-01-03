import orbkit.CollidableOrb;
import orbkit.ObjectManager;
import orbkit.OrbBase;
import orbkit.Timer;
import orbkit.Utils;

/**
 * The yellow orb interacts with the pink orb, if they collide the two are reborn in new destinations. 
 * If it collides with any other orb, it teleports itself.
 * @author yashjalan	yj627@nyu.edu
 * @version	1.8
 */
public class YellowOrb extends CollidableOrb {
	
	Timer timer;
	
	public static final int RED 	= 255;
	public static final int GREEN	= 255;
	public static final int BLUE	= 0;
	
	/**
	 * These params just get passed to the super class constructor.
	 * @param manager
	 * @param o
	 * @param speedX
	 * @param speedY
	 */
	public YellowOrb(ObjectManager manager, OrbBase o, double speedX,
			double speedY) {
		super(manager, o, speedX, speedY);
		
	}
	/**
	 * The method removes the orb from game and makes a new orb in a different position, making the orb teleport.
	 */
	public void teleport() {
		setToRemove();
		addOrb(YellowOrb.class, Utils.random(0, 1000), Utils.random(0, 600), this.getRadius(), 
				this.getSpeedX(), this.getSpeedY(),   
				YellowOrb.RED, YellowOrb.GREEN, YellowOrb.BLUE);
	}
	/**
	 * A timer is set and after 10 secs of non activity, a flashing animation is shown and the orb is removed.
	 */
	@Override
	protected void dynamicOrbUpdate() {
		if (timer == null && !stillInitializing())
			timer = new Timer();
		
		if (timer.getElapsedTime() > 10) {
			setToRemove();
			addAnimation(new Flashing(canvas, 2.0));
		}
	}

	/**
	 * If this orb interacts with pink orb then a new pink orb is created. Else, a new yellow orb is created.
	 */
	@Override
	protected boolean handleCollisionWith(CollidableOrb o) {
		if (o instanceof PinkOrb) {
			setToRemove();
			addOrb(PinkOrb.class, Utils.random(0, 1000), Utils.random(0, 600), this.getRadius(), 
					this.getSpeedX(), this.getSpeedY(),   
					PinkOrb.RED, PinkOrb.GREEN, PinkOrb.BLUE);
		}
		else
			teleport();
			
		return false;
	}
}

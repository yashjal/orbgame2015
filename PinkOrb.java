import orbkit.CollidableOrb;
import orbkit.ObjectManager;
import orbkit.OrbBase;
import orbkit.Timer;
import orbkit.Utils;

/**
 * The pink orb interacts with the yellow orb, if they collide the two are reborn in new destinations. 
 * If it collides with any other orb, it teleports itself.
 * @author yashjalan	yj627@nyu.edu
 * @version 1.8
 */
public class PinkOrb extends CollidableOrb {

		Timer timer;
		
		public static final int RED 	= 255;
		public static final int GREEN	= 105;
		public static final int BLUE	= 180;
		
		/**
		 * These params just get passed to the super class constructor.
		 * @param manager
		 * @param o
		 * @param speedX
		 * @param speedY
		 */
		public PinkOrb(ObjectManager manager, OrbBase o, double speedX,
				double speedY) {
			super(manager, o, speedX, speedY);
			
		}

		/**
		 * The method removes the orb from game and makes a new orb in a different position, making the orb teleport.
		 */
		public void teleport() {
			setToRemove();
			addOrb(PinkOrb.class, Utils.random(0, 1000), Utils.random(0, 600), this.getRadius(), 
					this.getSpeedX(), this.getSpeedY(),   
					PinkOrb.RED, PinkOrb.GREEN, PinkOrb.BLUE);
			
		}

		/**
		 * A timer is set and after 10 secs of non activity, a changing shape animation is shown and the orb is removed.
		 */
		
		@Override
		protected void dynamicOrbUpdate() {
			if (timer == null && !stillInitializing())
				timer = new Timer();
			if (timer.getElapsedTime() > 10) {
				setToRemove();
				addAnimation(new ChangeShape(canvas, 5.0, (float) (getX()), (float)(getY()), (float)(getRadius())));
			}
			
		}

		/**
		 * If this orb interacts with yellow orb then a new pink orb is created. Else, a new pink orb is created.
		 */
		@Override
		protected boolean handleCollisionWith(CollidableOrb o) {
			if (o instanceof YellowOrb) {
				setToRemove();
				addOrb(YellowOrb.class, Utils.random(0, 1000), Utils.random(0, 600), this.getRadius(), 
						this.getSpeedX(), this.getSpeedY(),   
						YellowOrb.RED, YellowOrb.GREEN, YellowOrb.BLUE);
			}
			else
				teleport();
			
			return false;
		}
		
}

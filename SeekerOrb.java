import orbkit.CollidableOrb;
import orbkit.ObjectManager;
import orbkit.OrbBase;
import orbkit.SampleExplosion;
import orbkit.Timer;
import orbkit.Utils;

/** 
 * The seeker orb decays/shrinks to nothing over 10 seconds but it also will actively
 * will seek other nearby orbs (of different type) to eat them.  If it eats another orb,
 * it will grow in size and its decay countdown will be reset back to the full 10 seconds
 * of life.  If a seeker orb hits another seeker orb, an elastic collision will occur.
 * @author Tim Mitchell
 *
 */
public class SeekerOrb extends InfluencedOrb {
	
	// For ease of defining an orb types color, use constants RED GREEN and BLUE
	// must be between 0 and 255
	// SeekerOrbs will be red
	public static final int RED 	= 255;
	public static final int GREEN	= 0;
	public static final int BLUE	= 0;
	
	private Timer timer;		// we'll use this to keep track of elapsed time
	private double radiusOld;
	private double radiusNew;
	
	/** 
	 * Your constructor must be public.
	 * Sets SeekerOrb to be red and starts a timer to keep track of elapsed times between actions.
	 * 
	 * These params just get passed to the super class constructor.
	 * 
	 * @param allOrbs
	 * @param orbSeed
	 * @param speedX
	 * @param speedY
	 */
	public SeekerOrb(ObjectManager allOrbs, OrbBase orbSeed, double speedX, double speedY){
		super(allOrbs, orbSeed, speedX, speedY, 15);
		radiusOld = getRadius();
		radiusNew = 0;
	}
	
	/** 
	 * This orb should normally be shrinking to zero (at which point it no longer exists).
	 * However, if it eats another orb, it will be in a growing phase until its radius
	 * is equal to the larger target radius.  
	 */
	private void radiusUpdate(){

		if (radiusNew < radiusOld) {		// radius is shrinking to zero
			// shrink radius to zero over 10 seconds
			double percentElapsed = Math.min(timer.getElapsedTime() / 10, 1); 
			setRadius(Utils.interpolate(radiusOld, radiusNew, percentElapsed));				
			// if orb becomes too tiny, it should be deleted
			if (getRadius() <= MIN_RADIUS){
				setToRemove();
				// make deletions look cool (well, cooler than nothing I suppose)
				addAnimation(new SampleExplosion(canvas, 2.0, getX(), getY(), getRadius()*100));
			}		
		}
		else { // radius is growing (since recently ate another orb apparently)
			// grow radius to new size over two seconds 
			double percentElapsed = Math.min(timer.getElapsedTime() / 2, 1);
			setRadius(Utils.interpolate(radiusOld, radiusNew, percentElapsed));

			// once orb has finished growing, it should then start shrinking back to zero
			if (percentElapsed >= 1) {
				radiusOld = getRadius();
				radiusNew = 0;
				timer = new Timer();
			}			
		}
	}
		
	/**
	 * We override this function from CollidableOrb to provide
	 * additional behavior, namely that this orb will now steer towards
	 * the closest orb of another type, provided that this closest orb
	 * is within the circle of influence specified by InfluencedOrb. 
	 */
	@Override
	protected void dynamicOrbUpdate(){
		
		// Only start the first timer once the orb has solidified!  
		if (timer == null && !stillInitializing())
			timer = new Timer();
		
		// change the radius (shrink or grow)
		radiusUpdate();
		
		// if there is a nearby orb of different type, attempt to seek it!
		CollidableOrb b = getClosestOrbOfDifferentType();
		if (b == null)
			return;
		double influenceLevel = influenceLevelOf(b);
		
		if (influenceLevel > 0){	
			double orbSpeed =  getTotalSpeed();
			
			double deltaX = b.getX() - getX();
			double deltaY = b.getY() - getY();
			double speedToOtherOrb = Math.sqrt(Math.pow(deltaX,2) + Math.pow(deltaY,2));
	
			// The scaling conserves the speed of the current orb so it just changes
			// direction without changing its absolute velocity
			double newSpeedX = (deltaX) * (orbSpeed / speedToOtherOrb);
			double newSpeedY = (deltaY) * (orbSpeed / speedToOtherOrb);
			
			// This scales the speed towards the other orb so that this orb
			// seeks more aggressively the closer the other orb is to it.
			// In other words, this determines how much this orb will turn
			// towards the other orb, and it will turn more towards it the 
			// closer it is.
			newSpeedX = Utils.interpolate(getSpeedX(), newSpeedX, influenceLevel);
			newSpeedY = Utils.interpolate(getSpeedY(), newSpeedY, influenceLevel);	
			
			// Renormalize the new speed to make sure new speed matches the original speed.
			// That is, the direction can change (seeking) but the speed does not.
			double newSpeed = getTotalSpeed(newSpeedX,newSpeedY);
			newSpeedX *= (orbSpeed / newSpeed);
			newSpeedY *= (orbSpeed / newSpeed);
			
			// finally, do the actual update for the speed of this orb so that it steers towards orb b.
			setSpeed(newSpeedX,newSpeedY);
		}
	}
	
	/**
	 * This function overrides the default function from CollidableOrb 
	 * to provide collision interactions between SeekerOrbs and CollidableOrb.
	 * The SeekerOrb "eats" the CollidableOrb when they collide and the
	 * SeekerOrb enlarges. 
	 */
	@Override
	public boolean handleCollisionWith(CollidableOrb o){
		// Just request an elastic collision if o is also a SeekerOrb (or a subtype of SeekerOrb)
		if (o instanceof SeekerOrb){
			return true;
		}
		else if (contains(getX(), getY()) || this.contains(o.getX(), o.getY())){
			// Eat the other orb if either centers is inside the other orb
			o.setToRemove();  // set o to be removed (since it's being eaten)

			// If radius is already growing, make sure we keep that future growth
			// in addition to the growth from eating orb o
			// radiusNew is such that area of the this orb after fully growing
			// will be equal to its original area plus the area of the eaten orb.
			radiusOld = Math.max(getRadius(), radiusNew);
			radiusOld = Math.min(radiusOld, CollidableOrb.MAX_RADIUS);
			
			// need to cap both Old and Target radii to MAX_RADIUS otherwise orb can get stuck 
			// in the growing phase because either might be above MAX_RADIUS!
			
			radiusNew = Math.sqrt(Math.pow(getRadius(),2) + Math.pow(o.getRadius(),2));
			radiusNew = Math.min(radiusNew, CollidableOrb.MAX_RADIUS);
			
			// If the old 
			if (radiusOld == CollidableOrb.MAX_RADIUS)
				radiusNew = 0;
			
			// reset the timer to zero.
			timer = new Timer();

			double xPos = getX();
			double yPos = getY();
			
			boolean orbAdded = addOrb(	
					SeekerOrb.class, xPos, yPos, this.getRadius(), 
					-this.getSpeedX(), -this.getSpeedY(),  // make new orb go in other direction 
					SeekerOrb.RED, SeekerOrb.GREEN, SeekerOrb.BLUE);
			
			// orbAdded will be false if there are already 100 orbs in the sim
			
			// don't request an elastic collision to be performed
			return false;	
		}
		return false;
	}
}

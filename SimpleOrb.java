import orbkit.CollidableOrb;
import orbkit.ObjectManager;
import orbkit.OrbBase;

public class SimpleOrb extends CollidableOrb {
	
	// For ease of defining an orb types color, use constants RED GREEN and BLUE
	// must be between 0 and 255
	// SimpleOrbs will be white
	public static final int RED 	= 255;
	public static final int GREEN	= 255;
	public static final int BLUE	= 255;
	
	/** 
	 * Initializes the orb from orbSeed and gives it a speed.
	 * Your constructor must be public.
	 * 
	 * @param allOrbs 
	 * @param orbSeed 
	 * @param speedX
	 * @param speedY
	 */
	public SimpleOrb(ObjectManager allOrbs, OrbBase orbSeed, double speedX, double speedY){
		super(allOrbs,orbSeed,speedX,speedY);
	}	
	
	/**
	 * This method gets called every update to the screen and you can
	 * specify new dynamic behavior.
	 */
	public void dynamicOrbUpdate() {
		// do nothing since CollidableOrb has basic movement already
		// In this method we can specify additional movement, such as:
		// - seeking / evading behavior
		// - teleporting
		// We can also influence other orbs we have not collided with but that are nearby
		// Alternatively, those orbs could influence this orb.
	}
	
	/**
	 * This gets called when there is a collision with this orb and another orb o.
	 * You can specify the behaviors and interactions you want to happen.
	 * 
	 * @return true to request an elastic collision between the two orbs.
	 */
	@Override
	public boolean handleCollisionWith(CollidableOrb o){
		// This checks whether o is also just a SimpleOrb (but not a subclass of it!)
		// if so, return true to request an elastic collision between the two SimpleOrb objects.
		if (getClass().equals(o.getClass()))
			return true;
		// the above line is equivalent to: 
		// if (SimpleOrb.class.getClass().equals(o.getClass())) {
		
		return false;  // different orb types, don't request an elastic collision.
	}
}

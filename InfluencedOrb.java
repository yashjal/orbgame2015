import orbkit.CollidableOrb;
import orbkit.ObjectManager;
import orbkit.OrbBase;

/**
 * Sample class for adding functionality to CollidableOrb.
 * The class is abstract since we don't want to build these
 * orbs but rather use the added functionality to build other
 * orb classes.
 * 
 * This class provides a helper function influencedLevelOf()
 * that determines how much the given orb should affect this
 * particular orb.  
 * 
 * This class provides a finite radius scalar, that is, it is
 * currently set so that if the given orb is within 10 times the 
 * radius of this particular orb, then the given orb will an 
 * influencing level of power associated with it.  
 * The closer the given orb is to the this orb, the more influencing
 * power it has.  
 * 
 * You should probably change this to be a variable instance data field,
 * rather than a static constant, so you can set it individually for every orb.
 * 
 
 * @author Tim Mitchell
 */
public abstract class InfluencedOrb extends CollidableOrb {
	
	// You SHOULD make this configurable per object rather than it being a constant.
	public double radius_scalar;
	protected double radiusOfInfluence;
	
	/**
	 * Same constructor format as SimpleOrb.
	 * 
	 * Pass these params to the superclass constructor.
	 * @param allOrbs
	 * @param orbSeed
	 * @param speedX
	 * @param speedY
	 */
	protected InfluencedOrb(ObjectManager allOrbs, OrbBase orbSeed, double speedX, double speedY, double radius_scalar){
		super(allOrbs, orbSeed, speedX, speedY);
		radiusOfInfluence = radius_scalar * getRadius();
	}
	
	/**
	 * Calculates how much influence OrbBase o has on this orb, from 
	 * 0 (none) to 1 (100%).  
	 * 
	 * @param o orb that may influence this one
	 * @return between 0 (no influence) and 1 (strongest influence)
	 */
	public final double influenceLevelOf(OrbBase o){
		double distance = distanceBetweenCenters(this, o);
		if (distance > radiusOfInfluence)
			return 0;

		return (radiusOfInfluence - distance) / radiusOfInfluence;
	}
}

import orbkit.CollidableOrb;
import orbkit.OrbBase;
import orbkit.OrbGameBase;
import orbkit.Utils;


/**
 * Sample demo of the OrbGame using only two different orb objects,
 * SimpleOrb and SeekerOrb.
 * 
 * Use this as a template for putting your own orb creations 
 * into the game.
 * 
 * @author Yash Jalan yj627@nyu.edu
 */
@SuppressWarnings("serial")
public class OrbGame extends OrbGameBase {

	/**
	 * This function translates a character code to one of the 
	 * types you have created for the game.  Else it returns null.
	 *  
	 * @param c
	 * @return
	 */
	protected Class getOrbType(char c){
		if (c == '1')
			return SimpleOrb.class;
		else if (c == '2')
			return SeekerOrb.class;
		
		else if (c == '3')
			return GreenOrb.class;
		
		else if (c == '4')
			return BlueOrb.class;
		
		else if (c == '5')
			return YellowOrb.class;
		
		else if (c == '6')
			return PinkOrb.class;
		
		else if (c == 'r' || c == 'R')
			return getRandomOrbType();
		else 
			return null;
	}
	
	/**
	 * This function should randomly return the class for one of your 
	 * derived classes that you will create. 
	 *  
	 * @return
	 */
	protected Class getRandomOrbType(){
		
			if (Math.random() < 0.2)
				return SimpleOrb.class;
			else if (Math.random() < 0.3)
				return SeekerOrb.class;
			else if (Math.random() < 0.5)
				return GreenOrb.class;
			else if (Math.random() < 0.7)
				return BlueOrb.class;
			else if (Math.random() < 0.85)
				return YellowOrb.class;
			else
				return PinkOrb.class;
			
	}
	
	/**
	 * This function sets the initial radius and color of
	 * the orb as you deem appropriate for each of the 
	 * derived types that you have created.
	 * 
	 * @param type
	 */
	protected void initializeNewOrbSeed(){
		// orbType stores the current class for whichever orb should
		// be thrown.
		//
		// You need to set a radius and color for the
		// orb to be shown under the mouse cursor, to show the user
		// a preview of what will be thrown.  The color shouldn't 
		// change once the orb is thrown.
		//
		// However, you can also change the radius later, when your orb type is made, 
		// in case you want to tie the size of the radius to the speed of the mouse drag.
		
		if (orbType.equals(SimpleOrb.class)){
			double radius = Utils.random(4,OrbBase.MAX_RADIUS);
			setOrbSeed(radius, SimpleOrb.RED, SimpleOrb.GREEN, SimpleOrb.BLUE);  // use SimpleOrb's color
		}
		else if (orbType.equals(SeekerOrb.class)){
			double radius = Utils.random(4,OrbBase.MAX_RADIUS);
			setOrbSeed(radius, SeekerOrb.RED, SeekerOrb.GREEN, SeekerOrb.BLUE);  // use SeekerOrb's color
		}
		else if (orbType.equals(GreenOrb.class)){
			double radius = Utils.random(4,OrbBase.MAX_RADIUS);
			setOrbSeed(radius, GreenOrb.RED, GreenOrb.GREEN, GreenOrb.BLUE); 
		}
		else if (orbType.equals(BlueOrb.class)){
			double radius = Utils.random(4,OrbBase.MAX_RADIUS);
			setOrbSeed(radius, BlueOrb.RED, BlueOrb.GREEN, BlueOrb.BLUE);  
		}
		else if (orbType.equals(YellowOrb.class)){
			double radius = Utils.random(4,OrbBase.MAX_RADIUS);
			setOrbSeed(radius, YellowOrb.RED, YellowOrb.GREEN, YellowOrb.BLUE); 
		}
		else if (orbType.equals(PinkOrb.class)){
			double radius = Utils.random(4,OrbBase.MAX_RADIUS);
			setOrbSeed(radius, PinkOrb.RED, PinkOrb.GREEN, PinkOrb.BLUE); 
		}
	}
}

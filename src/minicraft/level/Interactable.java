package minicraft.level;

import minicraft.gfx.Rectangle;
import minicraft.level.entity.mob.Player;

/**
 * This class describes all things that have a representation in the actual game world,
 * that generally exist on a particular level, and can generally in interacted with in some way.
 * This class represents "things", basically.
 */
public interface Interactable {
	
	/**
	 * This is called whenever two Interactables touch.
	 * This can even be two tiles; if you attempt to place one tile on another,
	 * then it could change the tile a certain way.
	 * 
	 * @param other the Interactable that touched this one.
	 */
	void touchedBy(Interactable other);
	
	/**
	 * This is called when the player presses the "interact" key,
	 * and it's called for all the Interactables in the player's interaction area,
	 * a small rectangular area in front of the player.
	 * 
	 * @param player The player that tried to interact with this Interactable.
	 * @return Whether anything significant happened, and no further Interactables should be interacted with.
	 */
	boolean interact(Player player);
	
	/**
	 * Specifies if this Interactable takes up space, and should not generally allow other Interactables to take up the same space.
	 * 
	 * 
	 * @return Whether this Interactable should be considered tangible, and should not generally allow other Interactables to move freely across it.
	 */
	boolean isSolid();
	
	/**
	 * Fetches the boundaries of this Interactable's physical space.
	 * 
	 * @return The boundaries of the shape.
	 */
	Rectangle getBounds();
	
	/**
	 * Used to check if this Interactable is partially or completely contained within the given Rectangle.
	 * By default, uses the area returned by getBounds() for the comparison
	 * 
	 * @param area the area to check for overlap
	 * @return whether this Interactable is touching the given area.
	 */
	default boolean isTouching(Rectangle area) {
		return getBounds().intersects(area);
	}
	
	/**
	 * This value is used to put a dither effect of "light", centered on the Interactable. 
	 *
	 * @return the radius of light that this Interactable should "emit", in entity coordinates.
	 */
	default int getLightRadius() { return 0; }
}

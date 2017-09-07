package minicraft.item;

import minicraft.entity.Entity;
import minicraft.entity.Furniture;
import minicraft.entity.Player;
import minicraft.gfx.Color;
import minicraft.gfx.SpriteBuilder;

public class PowerGloveItem extends Item {
	
	public PowerGloveItem() {
		super("Power Glove", new SpriteBuilder().setSx(7).setSy(4).setColor(Color.get(-1, 100, 320, 430)).createSprite());
	}
	
	public boolean interact(Player player, Entity entity, int attackDir) {
		if (entity instanceof Furniture) { // If the power glove is used on a piece of furniture...
			Furniture f = (Furniture) entity;
			f.take(player); // Takes (picks up) the furniture
			return true;
		}
		return false; // method returns false if we were not given a furniture entity.
	}
	
	public PowerGloveItem clone() {
		return new PowerGloveItem();
	}
}

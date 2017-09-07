package minicraft.entity.particle;

import minicraft.gfx.Color;
import minicraft.gfx.SpriteBuilder;

public class FireParticle extends Particle {
	/// This is used for Spawners, when they spawn an entity.
	
	public FireParticle(int x, int y) {
		super(x, y, 30, new SpriteBuilder().setSx(9).setSy(19).setColor(Color.get(-1, 520, 550, 500)).createSprite());
	}
}

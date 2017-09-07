package minicraft.entity.particle;

import minicraft.Sound;
import minicraft.gfx.Color;
import minicraft.gfx.SpriteBuilder;

public class SmashParticle extends Particle {
	static int[][] mirrors = {{2, 3}, {0, 1}};
	
	public SmashParticle(int x, int y) {
		super(x, y, 10, new SpriteBuilder().setSx(5).setSy(12).setSw(2).setSh(2).setColor(Color.get(-1, 555)).setOnepixel(true).setMirrors(mirrors).createSprite());
		Sound.monsterHurt.play(); // plays the sound of a monster getting hit.
	}
}

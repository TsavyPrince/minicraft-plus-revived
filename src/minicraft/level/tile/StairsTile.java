package minicraft.level.tile;

import minicraft.gfx.Color;
import minicraft.gfx.Screen;
import minicraft.gfx.Sprite;
import minicraft.gfx.SpriteBuilder;
import minicraft.level.Level;

public class StairsTile extends Tile {
	private static Sprite down = new SpriteBuilder().setSx(0).setSy(2).setSw(2).setSh(2).setColor(Color.get(222, 000, 333, 444)).setMirror(0).createSprite();
	private static Sprite up = new SpriteBuilder().setSx(2).setSy(2).setSw(2).setSh(2).setColor(Color.get(222, 000, 333, 444)).setMirror(0).createSprite();
	
	private boolean leadsUp;
	
	StairsTile(String name, boolean leadsUp) {
		super(name, leadsUp?up:down);
		this.leadsUp = leadsUp;
		maySpawn = false;
	}
	
	private int getDirtColor(int depth) {
		switch(depth) {
			case 0: return 321;
			case 1: return 444;
			case -4: return 59;
			default: return 222;
		}
	}
	
	public void render(Screen screen, Level level, int x, int y) {
		int col;
		if (level.depth < 0)
			col = Color.get(getDirtColor(level.depth), 000, 333, 444);
		else
			col = Color.get(getDirtColor(level.depth), 000, 444, 555);
		
		sprite.render(screen, x*16, y*16, col);
	}
}

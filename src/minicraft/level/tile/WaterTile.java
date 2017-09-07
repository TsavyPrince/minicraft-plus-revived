package minicraft.level.tile;

import minicraft.entity.Entity;
import minicraft.gfx.*;
import minicraft.level.Level;

public class WaterTile extends Tile {
	private ConnectorSprite sprite = new ConnectorSprite(WaterTile.class, new SpriteBuilder().setSx(14).setSy(0).setSw(3).setSh(3).setColor(Color.get(3, 105, 211, 321)).setMirror(3).createSprite(), Sprite.dots(Color.get(005, 105, 115, 115)))
	{
		public boolean connectsTo(Tile tile, boolean isSide) {
			return tile.connectsToWater;
		}
		
		public int getSparseColor(Tile tile, int origCol) {
			if(!tile.connectsToWater && tile.connectsToSand) {
				//System.out.println("water tile colored as sand with " + tile.name);
				return Color.get(3, 105, 440, 550);
			} else
				return origCol;
		}
	};
	
	WaterTile(String name) {
		super(name, (ConnectorSprite)null);
		csprite = sprite;
		connectsToSand = true;
		connectsToWater = true;
	}
	
	public void render(Screen screen, Level level, int x, int y) {
		long seed = (tickCount + (x / 2 - y) * 4311) / 10 * 54687121l + x * 3271612l + y * 3412987161l;
		sprite.full = Sprite.randomDots(seed, sprite.full.getColor());
		sprite.sparse.setColor(Color.get(3, 105, 211, DirtTile.dCol(level.depth)));
		sprite.render(screen, level, x, y);
	}

	public boolean mayPass(Level level, int x, int y, Entity e) {
		return e.canSwim();
	}

	public void tick(Level level, int xt, int yt) {
		int xn = xt;
		int yn = yt;

		if (random.nextBoolean()) xn += random.nextInt(2) * 2 - 1;
		else yn += random.nextInt(2) * 2 - 1;

		if (level.getTile(xn, yn) == Tiles.get("hole")) {
			level.setTile(xn, yn, this);
		}
		if (level.getTile(xn, yn) == Tiles.get("lava")) {
			level.setTile(xn, yn, Tiles.get("Stone Bricks"));
		}
	}
}

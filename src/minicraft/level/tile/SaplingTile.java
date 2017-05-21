package minicraft.level.tile;

import minicraft.entity.Mob;
import minicraft.gfx.Color;
import minicraft.gfx.Screen;
import minicraft.gfx.Sprite;
import minicraft.level.Level;

public class SaplingTile extends Tile {
	private static Sprite sprite = new Sprite(11, 3, Color.get(20, 40, 50, -1));
	
	protected static void addInstances() {
		Tiles.add(new SaplingTile("Tree Sapling", Tiles.get("Grass"), Tiles.get("Tree")));
		Tiles.add(new SaplingTile("Cactus Sapling", Tiles.get("Sand"), Tiles.get("Cactus")));
	}
	
	private Tile onType;
	private Tile growsTo;
	
	private SaplingTile(String name, Tile onType, Tile growsTo) {
		super(name, sprite);
		this.onType = onType;
		this.growsTo = growsTo;
		connectsToSand = onType.connectsToSand;
		connectsToGrass = onType.connectsToGrass;
		connectsToWater = onType.connectsToWater;
		connectsToLava = onType.connectsToLava;
		maySpawn = true;
	}

	public void render(Screen screen, Level level, int x, int y) {
		//int col = Color.get(20, 40, 50, -1);
		
		onType.render(screen, level, x, y);
		
		sprite.render(screen, x*16, y*16);
	}

	public void tick(Level level, int x, int y) {
		int age = level.getData(x, y) + 1;
		if (age > 100) {
			level.setTile(x, y, growsTo, 0);
		} else {
			level.setData(x, y, age);
		}
	}

	public void hurt(Level level, int x, int y, Mob source, int dmg, int attackDir) {
		level.setTile(x, y, onType, 0);
	}
}

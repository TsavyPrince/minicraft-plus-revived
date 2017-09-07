package minicraft.level.tile;

import minicraft.entity.Entity;
import minicraft.entity.Player;
import minicraft.gfx.Color;
import minicraft.gfx.Sprite;
import minicraft.gfx.SpriteBuilder;
import minicraft.item.Item;
import minicraft.item.ToolItem;
import minicraft.item.ToolType;
import minicraft.level.Level;

public class FarmTile extends Tile {
	private static Sprite sprite = new SpriteBuilder().setSx(2).setSy(1).setSw(2).setSh(2).setColor(Color.get(301, 411, 422, 533)).setOnepixel(true).setMirrors(new int[][]{{1, 0}, {0, 1}}).createSprite();
	
	FarmTile(String name) {
		super(name, sprite);
	}
	
	public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir) {
		if (item instanceof ToolItem) {
			ToolItem tool = (ToolItem) item;
			if (tool.type == ToolType.Shovel) {
				if (player.payStamina(4 - tool.level)) {
					level.setTile(xt, yt, Tiles.get("dirt"));
					return true;
				}
			}
		}
		return false;
	}

	public void tick(Level level, int xt, int yt) {
		int age = level.getData(xt, yt);
		if (age < 5) level.setData(xt, yt, age + 1);
	}

	public void steppedOn(Level level, int xt, int yt, Entity entity) {
		if (random.nextInt(60) != 0) return;
		if (level.getData(xt, yt) < 5) return;
		level.setTile(xt, yt, Tiles.get("dirt"));
	}
}

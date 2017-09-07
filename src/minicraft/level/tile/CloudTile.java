package minicraft.level.tile;

import minicraft.entity.Entity;
import minicraft.entity.Player;
import minicraft.gfx.Color;
import minicraft.gfx.ConnectorSprite;
import minicraft.gfx.SpriteBuilder;
import minicraft.item.Item;
import minicraft.item.Items;
import minicraft.item.ToolItem;
import minicraft.item.ToolType;
import minicraft.level.Level;

public class CloudTile extends Tile {
	private static ConnectorSprite sprite = new ConnectorSprite(CloudTile.class, new SpriteBuilder().setSx(4).setSy(0).setSw(3).setSh(3).setColor(Color.get(333, 444, 555, -1)).setMirror(3).createSprite(), new SpriteBuilder().setSx(7).setSy(0).setSw(2).setSh(2).setColor(Color.get(333, 444, 555, -1)).setMirror(3).createSprite(), ConnectorSprite.makeSprite(2, 2, Color.get(444, 444, 555, 444), 0, false, 19, 18, 20, 19))
	{
		public boolean connectsTo(Tile tile, boolean isSide) {
			return tile != Tiles.get("Infinite Fall");
		}
	};
	
	CloudTile(String name) {
		super(name, sprite);
	}

	public boolean mayPass(Level level, int x, int y, Entity e) {
		return true;
	}

	public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir) {
		if (item instanceof ToolItem) {
			ToolItem tool = (ToolItem) item;
			if (tool.type == ToolType.Shovel && player.payStamina(5)) {
				level.setTile(xt, yt, Tiles.get("Infinite Fall")); // would allow you to shovel cloud, I think.
				level.dropItem(xt*16+8, yt*16+8, 1, 3, Items.get("cloud"));
				return true;
			}
		}
		return false;
	}
}

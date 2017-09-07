package minicraft.level.tile;

import minicraft.Sound;
import minicraft.entity.Entity;
import minicraft.entity.Player;
import minicraft.gfx.Color;
import minicraft.gfx.Sprite;
import minicraft.gfx.SpriteBuilder;
import minicraft.item.Item;
import minicraft.item.Items;
import minicraft.item.ToolItem;
import minicraft.item.ToolType;
import minicraft.level.Level;

public class FloorTile extends Tile {
	private Sprite sprite = new SpriteBuilder().setSx(19).setSy(2).setSw(2).setSh(2).setColor(0).setMirror(0).setOnepixel(true).createSprite();
	
	Material type;
	
	FloorTile(Material type) {
		super((type == Material.Wood ? "Wood Planks" : type == Material.Obsidian ? "Obsidian" : type.name()+" Bricks"), (Sprite)null);
		this.type = type;
		maySpawn = true;
		switch(type) {
			case Wood: sprite.setColor(Color.get(210, 210, 430, 320));
			break;
			case Stone: sprite.setColor(Color.get(333, 333, 444, 444));
			break;
			case Obsidian: sprite.setColor(Color.get(102, 102, 203, 203));
			break;
		}
		super.sprite = sprite;
	}
	
	public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir) {
		if (item instanceof ToolItem) {
			ToolItem tool = (ToolItem) item;
			if (tool.type == ToolType.Pickaxe) {
				if (player.payStamina(4 - tool.level)) {
					level.setTile(xt, yt, Tiles.get("hole"));
					Item drop;
					switch(type) {
						case Wood: drop = Items.get("Plank"); break;
						default: drop = Items.get(type.name()+" Brick"); break;
					}
					level.dropItem(xt*16+8, yt*16+8, drop);
					Sound.monsterHurt.play();
					return true;
				}
			}
		}
		return false;
	}

	public boolean mayPass(Level level, int x, int y, Entity e) {
		return true;
	}
}

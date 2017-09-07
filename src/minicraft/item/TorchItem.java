package minicraft.item;

import java.util.ArrayList;
import minicraft.entity.Player;
import minicraft.gfx.Color;
import minicraft.gfx.SpriteBuilder;
import minicraft.level.Level;
import minicraft.level.tile.Tile;
import minicraft.level.tile.TorchTile;

public class TorchItem extends TileItem {
	
	public static ArrayList<Item> getAllInstances() {
		ArrayList<Item> items = new ArrayList<>();
		items.add(new TorchItem());
		return items;
	}
	
	private TorchItem() { this(1); }
	private TorchItem(int count) {
		super("Torch", (new SpriteBuilder().setSx(18).setSy(4).setColor(Color.get(-1, 500, 520, 320)).createSprite()), count, "", "dirt", "Wood Planks", "Stone Bricks", "Wool", "grass", "sand");
	}
	
	public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
		if(validTiles.contains(tile.name)) {
			level.setTile(xt, yt, TorchTile.getTorchTile(tile));
			return super.interactOn(true);
		}
		return super.interactOn(false);
	}
	
	public boolean matches(Item other) {
		return other instanceof TorchItem;
	}
	
	public TorchItem clone() {
		return new TorchItem(count);
	}
}

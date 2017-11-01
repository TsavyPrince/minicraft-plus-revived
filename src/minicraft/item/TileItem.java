package minicraft.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import minicraft.core.Game;
import minicraft.level.entity.Direction;
import minicraft.level.entity.mob.Player;
import minicraft.level.entity.mob.RemotePlayer;
import minicraft.gfx.Color;
import minicraft.gfx.Sprite;
import minicraft.level.Level;
import minicraft.level.tile.Tile;
import minicraft.level.tile.Tiles;

public class TileItem extends StackableItem {
	
	protected static ArrayList<Item> getAllInstances() {
		ArrayList<Item> items = new ArrayList<>();
		
		/// TileItem sprites are all on line 4, and have 1x1 sprites.
		items.add(new TileItem("Flower", (new Sprite(0, 4, Color.get(-1, 10, 444, 330))), "Flower", "Grass"));
		items.add(new TileItem("Acorn", (new Sprite(3, 4, Color.get(-1, 100, 531, 320))), "Tree Sapling", "Grass"));
		items.add(new TileItem("Dirt", (new Sprite(2, 4, Color.get(-1, 100, 322, 432))), "Dirt", "hole", "water", "lava"));
		
		items.add(new TileItem("Wood Planks", (new Sprite(1, 4, Color.get(-1, 200, 531, 530))), "Wood Planks", "hole", "water"));
		items.add(new TileItem("Wood Wall", (new Sprite(16, 4, Color.get(-1, 200, 531, 530))), "Wood Wall", "Wood Planks"));
		items.add(new TileItem("Wood Door", (new Sprite(17, 4, Color.get(-1, 200, 531, 530))), "Wood Door", "Wood Planks"));
		items.add(new TileItem("Stone Bricks", (new Sprite(1, 4, Color.get(-1, 333, 444, 444))), "Stone Bricks", "hole", "water", "lava"));
		items.add(new TileItem("Stone Wall", (new Sprite(16, 4, Color.get(-1, 100, 333, 444))), "Stone Wall", "Stone Bricks"));
		items.add(new TileItem("Stone Door", (new Sprite(17, 4, Color.get(-1, 111, 333, 444))), "Stone Door", "Stone Bricks"));
		items.add(new TileItem("Obsidian Bricks", (new Sprite(1, 4, Color.get(-1, 159, 59, 59))), "Obsidian", "hole", "water", "lava"));
		items.add(new TileItem("Obsidian Wall", (new Sprite(16, 4, Color.get(-1, 159, 59, 59))), "Obsidian Wall", "Obsidian"));
		items.add(new TileItem("Obsidian Door", (new Sprite(17, 4, Color.get(-1, 159, 59, 59))), "Obsidian Door", "Obsidian"));
	
		// TODO make a method in Item.java; calls clone(), but then changes color, and returns itself. Call it cloneAsColor, or changeColor, or maybe *asColor()*.
		items.add(new TileItem("Wool", (new Sprite(2, 4, Color.WHITE)), "wool", "hole", "water"));
		items.add(new TileItem("Red Wool", (new Sprite(2, 4, Color.get(-1, 100, 300, 500))), "Wool_RED", "hole", "water"));
		items.add(new TileItem("Blue Wool", (new Sprite(2, 4, Color.get(-1, 005, 115, 115))), "Wool_BLUE", "hole", "water"));
		items.add(new TileItem("Green Wool", (new Sprite(2, 4, Color.get(-1, 10, 40, 50))), "Wool_GREEN", "hole", "water"));
		items.add(new TileItem("Yellow Wool", (new Sprite(2, 4, Color.get(-1, 110, 440, 552))), "Wool_YELLOW", "hole", "water"));
		items.add(new TileItem("Black Wool", (new Sprite(2, 4, Color.get(-1, 000, 111, 111))), "Wool_BLACK", "hole", "water"));
		
		items.add(new TileItem("Sand", (new Sprite(2, 4, Color.get(-1, 110, 440, 550))), "Sand", "Dirt"));
		items.add(new TileItem("Cactus", (new Sprite(4, 4, Color.get(-1, 10, 40, 50))), "Cactus Sapling", "Sand"));
		items.add(new TileItem("Seeds", (new Sprite(5, 4, Color.get(-1, 10, 40, 50))), "Wheat", "Farmland"));
		items.add(new TileItem("Grass Seeds", (new Sprite(5, 4, Color.get(-1, 10, 30, 50))), "Grass", "Dirt"));
		items.add(new TileItem("Bone", (new Sprite(15, 4, Color.get(-1, 222, 555, 555))), "Tree", "Tree Sapling")); // FIXME this is wrong..?
		items.add(new TileItem("Cloud", (new Sprite(2, 4, Color.get(-1, 222, 555, 444))), "Cloud", "Infinite Fall"));
		
		return items;
	}
	
	public final String model;
	public final List<String> validTiles;
	
	protected TileItem(String name, Sprite sprite, String model, String... validTiles) {
		this(name, sprite, 1, model, Arrays.asList(validTiles));
	}
	protected TileItem(String name, Sprite sprite, int count, String model, String... validTiles) {
		this(name, sprite, count, model, Arrays.asList(validTiles));
	}
	protected TileItem(String name, Sprite sprite, int count, String model, List<String> validTiles) {
		super(name, sprite, count);
		this.model = model.toUpperCase();
		this.validTiles = new ArrayList<>();
		for(String tile: validTiles)
			this.validTiles.add(tile.toUpperCase());
	}
	
	public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, Direction attackDir) {
		for(String tilename: validTiles) {
			//Tile t = Tiles.get(tilename.contains("_")?tilename.substring(0, tilename.indexOf("_")):tilename);
			if(tile.matches(level.getData(xt, yt), tilename)) {
				level.setTile(xt, yt, model); // TODO maybe data should be part of the saved tile..?
				return super.interactOn(true);
			}
		}
		
		if (Game.debug) System.out.println(model + " cannot be placed on " + tile.name);
		
		String note = "";
		if(model.contains("WALL")) {
			note = "Can only be placed on " + Tiles.getName(validTiles.get(0)) + "!";
		}
		else if(model.contains("DOOR")) {
			note = "Can only be placed on " + Tiles.getName(validTiles.get(0)) + "!";
		}
		else if((model.contains("BRICK") || model.contains("PLANK"))) {
			note = "Dig a hole first!";
		}
		
		if(note.length() > 0) {
			if(!Game.isValidServer())
				Game.notifications.add(note);
			else
				Game.server.getAssociatedThread((RemotePlayer)player).sendNotification(note, 0);
		}
		
		return super.interactOn(false);
	}
	
	public boolean equals(Item other) {
		return super.equals(other) && model.equals(((TileItem)other).model);
	}
	
	public TileItem clone() {
		return new TileItem(name, sprite, count, model, validTiles);
	}
}

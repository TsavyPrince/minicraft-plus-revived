package minicraft.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import minicraft.Game;
import minicraft.entity.Player;
import minicraft.gfx.Color;
import minicraft.gfx.Sprite;
import minicraft.gfx.SpriteBuilder;
import minicraft.level.Level;
import minicraft.level.tile.Tile;
import minicraft.level.tile.Tiles;

public class TileItem extends StackableItem {
	
	static ArrayList<Item> getAllInstances() {
		ArrayList<Item> items = new ArrayList<>();
		
		/// TileItem sprites are all on line 4, and have 1x1 sprites.
		items.add(new TileItem("Flower", (new SpriteBuilder().setSx(0).setSy(4).setColor(Color.get(-1, 10, 444, 330)).createSprite()), "flower", "grass"));
		items.add(new TileItem("Acorn", (new SpriteBuilder().setSx(3).setSy(4).setColor(Color.get(-1, 100, 531, 320)).createSprite()), "tree Sapling", "grass"));
		items.add(new TileItem("Dirt", (new SpriteBuilder().setSx(2).setSy(4).setColor(Color.get(-1, 100, 322, 432)).createSprite()), "dirt", "hole", "water", "lava"));
		
		items.add(new TileItem("Plank", (new SpriteBuilder().setSx(1).setSy(4).setColor(Color.get(-1, 200, 531, 530)).createSprite()), "Wood Planks", "hole", "water"));
		items.add(new TileItem("Plank Wall", (new SpriteBuilder().setSx(16).setSy(4).setColor(Color.get(-1, 200, 531, 530)).createSprite()), "Wood Wall", "Wood Planks"));
		items.add(new TileItem("Wood Door", (new SpriteBuilder().setSx(17).setSy(4).setColor(Color.get(-1, 200, 531, 530)).createSprite()), "Wood Door", "Wood Planks"));
		items.add(new TileItem("Stone Brick", (new SpriteBuilder().setSx(1).setSy(4).setColor(Color.get(-1, 333, 444, 444)).createSprite()), "Stone Bricks", "hole", "water", "lava"));
		items.add(new TileItem("Stone Wall", (new SpriteBuilder().setSx(16).setSy(4).setColor(Color.get(-1, 100, 333, 444)).createSprite()), "Stone Wall", "Stone Bricks"));
		items.add(new TileItem("Stone Door", (new SpriteBuilder().setSx(17).setSy(4).setColor(Color.get(-1, 111, 333, 444)).createSprite()), "Stone Door", "Stone Bricks"));
		items.add(new TileItem("Obsidian Brick", (new SpriteBuilder().setSx(1).setSy(4).setColor(Color.get(-1, 159, 59, 59)).createSprite()), "Obsidian", "hole", "water", "lava"));
		items.add(new TileItem("Obsidian Wall", (new SpriteBuilder().setSx(16).setSy(4).setColor(Color.get(-1, 159, 59, 59)).createSprite()), "Obsidian Wall", "Obsidian"));
		items.add(new TileItem("Obsidian Door", (new SpriteBuilder().setSx(17).setSy(4).setColor(Color.get(-1, 159, 59, 59)).createSprite()), "Obsidian Door", "Obsidian"));
	
		// TODO make a method in Item.java; calls clone(), but then changes color, and returns itself. Call it cloneAsColor, or changeColor, or maybe *asColor()*.
		items.add(new TileItem("Wool", (new SpriteBuilder().setSx(2).setSy(4).setColor(Color.get(-1, 555)).createSprite()), "wool", "hole", "water"));
		items.add(new TileItem("Red Wool", (new SpriteBuilder().setSx(2).setSy(4).setColor(Color.get(-1, 100, 300, 500)).createSprite()), "Wool_RED", "hole", "water"));
		items.add(new TileItem("Blue Wool", (new SpriteBuilder().setSx(2).setSy(4).setColor(Color.get(-1, 005, 115, 115)).createSprite()), "Wool_BLUE", "hole", "water"));
		items.add(new TileItem("Green Wool", (new SpriteBuilder().setSx(2).setSy(4).setColor(Color.get(-1, 10, 40, 50)).createSprite()), "Wool_GREEN", "hole", "water"));
		items.add(new TileItem("Yellow Wool", (new SpriteBuilder().setSx(2).setSy(4).setColor(Color.get(-1, 110, 440, 552)).createSprite()), "Wool_YELLOW", "hole", "water"));
		items.add(new TileItem("Black Wool", (new SpriteBuilder().setSx(2).setSy(4).setColor(Color.get(-1, 000, 111, 111)).createSprite()), "Wool_BLACK", "hole", "water"));
		
		items.add(new TileItem("Sand", (new SpriteBuilder().setSx(2).setSy(4).setColor(Color.get(-1, 110, 440, 550)).createSprite()), "sand", "dirt"));
		items.add(new TileItem("Cactus", (new SpriteBuilder().setSx(4).setSy(4).setColor(Color.get(-1, 10, 40, 50)).createSprite()), "cactus Sapling", "sand"));
		items.add(new TileItem("Seeds", (new SpriteBuilder().setSx(5).setSy(4).setColor(Color.get(-1, 10, 40, 50)).createSprite()), "wheat", "farmland"));
		items.add(new TileItem("Grass Seeds", (new SpriteBuilder().setSx(5).setSy(4).setColor(Color.get(-1, 10, 30, 50)).createSprite()), "grass", "dirt"));
		items.add(new TileItem("Bone", (new SpriteBuilder().setSx(15).setSy(4).setColor(Color.get(-1, 222, 555, 555)).createSprite()), "tree", "tree Sapling"));
		items.add(new TileItem("Cloud", (new SpriteBuilder().setSx(2).setSy(4).setColor(Color.get(-1, 222, 555, 444)).createSprite()), "cloud", "Infinite Fall"));
		
		return items;
	}
	
	public final String model;
	public final List<String> validTiles;
	
	TileItem(String name, Sprite sprite, String model, String... validTiles) {
		this(name, sprite, 1, model, Arrays.asList(validTiles));
	}
	TileItem(String name, Sprite sprite, int count, String model, String... validTiles) {
		this(name, sprite, count, model, Arrays.asList(validTiles));
	}
	TileItem(String name, Sprite sprite, int count, String model, List<String> validTiles) {
		super(name, sprite, count);
		this.model = model.toUpperCase();
		this.validTiles = new ArrayList<>();
		for(String tile: validTiles)
			this.validTiles.add(tile.toUpperCase());
	}
	
	public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
		for(String tilename: validTiles) {
			//Tile t = Tiles.get(tilename.contains("_")?tilename.substring(0, tilename.indexOf("_")):tilename);
			if(tile.matches(level.getData(xt, yt), tilename)) {
				level.setTile(xt, yt, model); // TODO maybe data should be part of the saved tile..?
				return super.interactOn(true);
			}
		}
		//if (Game.debug) System.out.println(model + " cannot be placed on " + tile.name);
		
		if(model.contains("Wall") && validTiles.size() == 1) {
			Game.notifications.add("Can only be placed on " + Tiles.getName(validTiles.get(0)) + "!");
		}
		return super.interactOn(false);
	}
	
	public boolean matches(Item other) {
		return super.matches(other) && model.equals(((TileItem)other).model);
	}
	
	public TileItem clone() {
		return new TileItem(name, sprite, count, model, validTiles);
	}
}

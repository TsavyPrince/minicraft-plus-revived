package minicraft.item;

import java.util.ArrayList;
import minicraft.entity.Player;
import minicraft.gfx.Color;
import minicraft.gfx.Sprite;
import minicraft.gfx.SpriteBuilder;
import minicraft.level.Level;
import minicraft.level.tile.Tile;

public class FoodItem extends StackableItem {
	
	static ArrayList<Item> getAllInstances() {
		ArrayList<Item> items = new ArrayList<>();
		
		items.add(new FoodItem("Bread", new SpriteBuilder().setSx(8).setSy(4).setColor(Color.get(-1, 110, 330, 550)).createSprite(), 2));
		items.add(new FoodItem("Apple", new SpriteBuilder().setSx(9).setSy(4).setColor(Color.get(-1, 100, 300, 500)).createSprite(), 1));
		items.add(new FoodItem("Raw Pork", new SpriteBuilder().setSx(20).setSy(4).setColor(Color.get(-1, 211, 311, 411)).createSprite(), 1));
		items.add(new FoodItem("Raw Fish", new SpriteBuilder().setSx(24).setSy(4).setColor(Color.get(-1, 660, 670, 680)).createSprite(), 1));
		items.add(new FoodItem("Raw Beef", new SpriteBuilder().setSx(20).setSy(4).setColor(Color.get(-1, 200, 300, 400)).createSprite(), 1));
		items.add(new FoodItem("Pork Chop", new SpriteBuilder().setSx(20).setSy(4).setColor(Color.get(-1, 220, 440, 330)).createSprite(), 3));
		items.add(new FoodItem("Cooked Fish", new SpriteBuilder().setSx(24).setSy(4).setColor(Color.get(-1, 220, 330, 440)).createSprite(), 3));
		items.add(new FoodItem("Cooked Pork", new SpriteBuilder().setSx(20).setSy(4).setColor(Color.get(-1, 220, 440, 330)).createSprite(), 3));
		items.add(new FoodItem("Steak", new SpriteBuilder().setSx(20).setSy(4).setColor(Color.get(-1, 100, 333, 211)).createSprite(), 3));
		items.add(new FoodItem("Gold Apple", new SpriteBuilder().setSx(9).setSy(4).setColor(Color.get(-1, 110, 440, 550)).createSprite(), 10));
		
		return items;
	}
	
	private int heal; // the amount of hunger the food "satisfies" you by.
	private int staminaCost; // the amount of stamina it costs to consume the food.
	
	private FoodItem(String name, Sprite sprite, int heal) { this(name, sprite, 1, heal); }
	private FoodItem(String name, Sprite sprite, int count, int heal) {
		super(name, sprite, count);
		this.heal = heal;
		staminaCost = 5;
	}
	
	/** What happens when the player uses the item on a tile */
	public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
		boolean success = false;
		if (count > 0 && player.hunger < 10 && player.payStamina(staminaCost)) { // if the player has hunger to fill, and stamina to pay...
			player.hunger += heal; // restore the hunger
			if (player.hunger > 10) { // make sure the hunger doesn't go above ten.
				player.hunger = 10;
			}
			success = true;
		}
		
		return super.interactOn(success);
	}
	
	public FoodItem clone() {
		return new FoodItem(name, sprite, count, heal);
	}
}

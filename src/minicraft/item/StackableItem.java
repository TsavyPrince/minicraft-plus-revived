package minicraft.item;

import java.util.ArrayList;
import minicraft.gfx.Color;
import minicraft.gfx.Screen;
import minicraft.gfx.Sprite;
import minicraft.gfx.SpriteBuilder;
import minicraft.screen.ModeMenu;

// some items are direct instances of this class; those instances are the true "items", like stone, wood, wheat, or coal; you can't do anything with them besides use them to make something else.

public class StackableItem extends Item {
	
	static ArrayList<Item> getAllInstances() {
		ArrayList<Item> items = new ArrayList<>();
	
		items.add(new StackableItem("Wood", new SpriteBuilder().setSx(1).setSy(4).setColor(Color.get(-1, 200, 531, 430)).createSprite()));
		items.add(new StackableItem("Stone", new SpriteBuilder().setSx(2).setSy(4).setColor(Color.get(-1, 111, 333, 555)).createSprite()));
		items.add(new StackableItem("Leather", new SpriteBuilder().setSx(19).setSy(4).setColor(Color.get(-1, 100, 211, 322)).createSprite()));
		items.add(new StackableItem("Wheat", new SpriteBuilder().setSx(6).setSy(4).setColor(Color.get(-1, 110, 330, 550)).createSprite()));
		items.add(new StackableItem("Key", new SpriteBuilder().setSx(26).setSy(4).setColor(Color.get(-1, -1, 444, 550)).createSprite()));
		items.add(new StackableItem("arrow", new SpriteBuilder().setSx(13).setSy(5).setColor(Color.get(-1, 111, 222, 430)).createSprite()));
		items.add(new StackableItem("string", new SpriteBuilder().setSx(25).setSy(4).setColor(Color.get(-1, 555)).createSprite()));
		items.add(new StackableItem("Coal", new SpriteBuilder().setSx(10).setSy(4).setColor(Color.get(-1, 000, 111, 111)).createSprite()));
		items.add(new StackableItem("Iron Ore", new SpriteBuilder().setSx(10).setSy(4).setColor(Color.get(-1, 100, 322, 544)).createSprite()));
		items.add(new StackableItem("Lapis", new SpriteBuilder().setSx(10).setSy(4).setColor(Color.get(-1, 005, 115, 115)).createSprite()));
		items.add(new StackableItem("Gold Ore", new SpriteBuilder().setSx(10).setSy(4).setColor(Color.get(-1, 110, 440, 553)).createSprite()));
		items.add(new StackableItem("Iron", new SpriteBuilder().setSx(11).setSy(4).setColor(Color.get(-1, 100, 322, 544)).createSprite()));
		items.add(new StackableItem("Gold", new SpriteBuilder().setSx(11).setSy(4).setColor(Color.get(-1, 110, 330, 553)).createSprite()));
		items.add(new StackableItem("Rose", new SpriteBuilder().setSx(0).setSy(4).setColor(Color.get(-1, 100, 300, 500)).createSprite()));
		items.add(new StackableItem("GunPowder", new SpriteBuilder().setSx(2).setSy(4).setColor(Color.get(-1, 111, 222, 333)).createSprite()));
		items.add(new StackableItem("Slime", new SpriteBuilder().setSx(10).setSy(4).setColor(Color.get(-1, 10, 30, 50)).createSprite()));
		items.add(new StackableItem("glass", new SpriteBuilder().setSx(12).setSy(4).setColor(Color.get(-1, 555)).createSprite()));
		items.add(new StackableItem("cloth", new SpriteBuilder().setSx(1).setSy(4).setColor(Color.get(-1, 25, 252, 141)).createSprite()));
		items.add(new StackableItem("gem", new SpriteBuilder().setSx(13).setSy(4).setColor(Color.get(-1, 101, 404, 545)).createSprite()));
		items.add(new StackableItem("Scale", new SpriteBuilder().setSx(22).setSy(4).setColor(Color.get(-1, 10, 30, 20)).createSprite()));
		items.add(new StackableItem("Shard", new SpriteBuilder().setSx(23).setSy(4).setColor(Color.get(-1, 222, 333, 444)).createSprite()));
		
		return items;
	}
	
	public int count;
	///public int maxCount; // TODO I want to implement this later.
	
	StackableItem(String name, Sprite sprite) {
		super(name, sprite);
		count = 1;
	}
	StackableItem(String name, Sprite sprite, int count) {
		this(name, sprite);
		this.count = count;
	}
	
	public boolean matches(Item other) {
		return super.matches(other) && other instanceof StackableItem;
	}
	
	/** Renders the icon, name, and count of the item. */
	public void renderInventory(Screen screen, int x, int y, boolean ininv) {
		// If the item count is above 999, then just render 999 (for spacing reasons)
		super.renderInventory(screen, x, y, ininv, (count>999?999:count)+" "+name);
	}
	
	/// this is used by (most) subclasses, to standardize the count decrement behavior. This is not the normal interactOn method.
	boolean interactOn(boolean subClassSuccess) {
		if(subClassSuccess && !ModeMenu.creative)
			count--;
		return subClassSuccess;
	}
	
	/** Called to determine if this item should be removed from an inventory. */
	public boolean isDepleted() {
		return count <= 0;
	}
	
	public StackableItem clone() {
		return new StackableItem(name, sprite, count);
	}
	
	public String toString() {
		return super.toString() + "-Stack_Size:"+count;
	}
	
	public String getData() {
		return name+"_"+count;
	}
}

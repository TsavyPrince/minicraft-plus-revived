package minicraft.entity;

import java.util.ArrayList;
import minicraft.gfx.Color;
import minicraft.gfx.Sprite;
import minicraft.gfx.SpriteBuilder;
import minicraft.item.Recipe;
import minicraft.item.Recipes;
import minicraft.screen.CraftingMenu;

public class Crafter extends Furniture {
	
	public enum Type {
		Workbench (new SpriteBuilder().setSx(8).setSy(8).setSw(2).setSh(2).setColor(Color.get(-1, 100, 321, 431)).createSprite(), 3, 2, Recipes.workbenchRecipes),
		Oven (new SpriteBuilder().setSx(4).setSy(8).setSw(2).setSh(2).setColor(Color.get(-1, 000, 332, 442)).createSprite(), 3, 2, Recipes.ovenRecipes),
		Furnace (new SpriteBuilder().setSx(6).setSy(8).setSw(2).setSh(2).setColor(Color.get(-1, 000, 222, 333)).createSprite(), 3, 2, Recipes.furnaceRecipes),
		Anvil (new SpriteBuilder().setSx(0).setSy(8).setSw(2).setSh(2).setColor(Color.get(-1, 000, 222, 333)).createSprite(), 3, 2, Recipes.anvilRecipes),
		Enchanter (new SpriteBuilder().setSx(12).setSy(8).setSw(2).setSh(2).setColor(Color.get(-1, 623, 999, 111)).createSprite(), 7, 2, Recipes.enchantRecipes),
		Loom (new SpriteBuilder().setSx(18).setSy(8).setSw(2).setSh(2).setColor(Color.get(-1, 100, 333, 211)).createSprite(), 7, 2, Recipes.loomRecipes);
		
		public ArrayList<Recipe> recipes;
		Sprite sprite;
		int xr, yr;
		
		
		Type(Sprite sprite, int xr, int yr, ArrayList<Recipe> list) {
			this.sprite = sprite;
			this.xr = xr;
			this.yr = yr;
			recipes = list;
			Crafter.names.add(this.name());
		}
	}
	public static ArrayList<String> names = new ArrayList<>();
	
	public Crafter.Type type;
	
	public Crafter(Crafter.Type type) {
		super(type.name(), type.sprite, type.xr, type.yr);
		this.type = type;
	}
	
	public boolean use(Player player, int attackDir) {
		player.game.setMenu(new CraftingMenu(type.recipes, player));
		return true;
	}
	
	public Furniture clone() {
		return new Crafter(type);
	}
	
	public String toString() {
		return super.toString().replace("Crafter", type.name());
	}
}

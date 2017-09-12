package minicraft.entity;

import minicraft.item.Item;
import minicraft.level.Level;
import minicraft.Difficulty;

public class ItemDrop {
	
	/**
	 * - Item
	 * - chance of drop
	 * - difficulty at which it applies
	 * - min and max number of drops
	 * - exclusive to other drops?
	 */
	
	private Item drop;
	private boolean exclusive;
	private Difficulty diff;
	private int[] chances; // the chance of dropping a given amount of the item (the amount = index in this array)
	
	ItemDrop(Item i) {
		drop = i;
	}
	
	
	void drop(Level level, int x, int y, boolean droppedYet) {
		
	}
	
}

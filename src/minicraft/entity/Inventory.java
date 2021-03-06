package minicraft.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import minicraft.item.FurnitureItem;
import minicraft.item.Item;
import minicraft.item.Items;
import minicraft.item.PowerGloveItem;
import minicraft.item.StackableItem;
import minicraft.item.ToolItem;
import minicraft.item.ToolType;

public class Inventory {
	private Random random = new Random();
	private List<Item> items = new ArrayList<>(); // the list of items that is in the inventory.
	
	/**
	 * Returns all the items which are in this inventory.
	 * @return ArrayList containing all the items in the inventory.
	 */
	public List<Item> getItems() {
		List<Item> newItems = new ArrayList<>();
		newItems.addAll(items);
		return newItems;
	}
	
	/**
	 * Removes everything from the inventory.
	 */
	public void clearInv() {
		items.clear();
	}
	
	/**
	 * Returns how many items there are in the inventory.
	 * @return Size of the item list.
	 */
	public int invSize() {
		return items.size();	
	}
	
	/**
	 * Returns 
	 * @param idx
	 * @return
	 */
	public Item get(int idx) {
		return items.get(idx);
	}
	
	/**
	 * Removes an item at the given index.
	 * @param idx Index of item.
	 * @return The removed item.
	 */
	public Item remove(int idx) {
		return items.remove(idx);
	}
	
	/**
	 * Removes an item from the inventory.
	 * @param item Item to be removed.
	 * @return true if item was removed, false if not.
	 */
	public boolean remove(Item item) {
		for(Item i: items) {
			if(i.matches(item)) {
				items.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds an item to the end of the inventory.
	 * @param item Item to be added.
	 */
	public void add(Item item) {
		add(items.size(), item);  // adds the item to the end of the inventory list
	}
	
	/**
	 * Adds several copies of the same item to the end of the inventory.
	 * @param item Item to be added.
	 * @param num Amount of items to add.
	 */
	public void add(Item item, int num) {
		for(int i = 0; i < num; i++)
			add(item.clone());
	}
	
	/**
	 * Adds an item to a specific spot in the inventory.
	 * @param slot Index to place item at.
	 * @param item Item to be added.
	 */
	public void add(int slot, Item item) {
		//if (Game.debug) System.out.println("adding item to an inventory: " + item);
		if(item instanceof PowerGloveItem) {
			System.out.println("WARNING: tried to add power glove to inventory. stack trace:");
			Thread.dumpStack();
			return; // do NOT add to inventory
		}
		
		if (item instanceof StackableItem) { // if the item is a item...
			StackableItem toTake = (StackableItem) item; // ...convert it into a StackableItem object.
			
			boolean added = false;
			for(int i = 0; i < items.size(); i++) {
				if(items.get(i).matches(toTake)) {
					// matching implies that the other item is stackable, too.
					((StackableItem)items.get(i)).count += toTake.count;
					added = true;
					break;
				}
			}
			
			if(!added) items.add(slot, toTake);
		} else {
			items.add(slot, item); // add the item to the items list
		}
	}
	
	/**
	 * Removes items from your inventory; looks for stacks, and removes from each until reached count. returns amount removed.
	 * @param given Item to remove.
	 * @param count How many of the item to remove.
	 * @return The amount which was removed.
	 */
	public int removeFromStack(StackableItem given, int count) {
		int removed = 0; // to keep track of amount removed.
		for(int i = 0; i < items.size(); i++) {
			if(!(items.get(i) instanceof StackableItem)) continue;
			StackableItem curItem = (StackableItem) items.get(i);
			if(!curItem.name.equals(given.name)) continue; // can't do matches, becuase that includes the stack size.
			// matches; and current item is stackable.
			int amountRemoving = Math.min(count-removed, curItem.count); // this is the number of items that are being removed from the stack this run-through.
			curItem.count -= amountRemoving;
			if(curItem.count == 0) { // remove the item from the inventory if its stack is empty.
				items.remove(curItem);
				i--;
			}
			removed += amountRemoving;
			if(removed == count) break;
			if(removed > count) { // just in case...
				System.out.println("SCREW UP while removing items from stack: " + (removed-count) + " too many.");
				break;
			}
			// if not all have been removed, look for another stack.
		}
		
		if(removed < count) System.out.println("Inventory: could not remove all items; " + (count-removed) + " left.");
		return removed;
	}
	
	/** 
	 * Removes the item from the inventory entirely, whether it's a stack, or a lone item.
	 */
	public void removeItem(Item i) {
		Item save = i.clone(); // never used?
		//if (Game.debug) System.out.println("original item: " + i);
		if(i instanceof StackableItem)
			removeItems(i.clone(), ((StackableItem)i).count);
		else
			removeItems(i.clone(), 1);
	}
	
	/**
	 * Removes items from this inventory. Note, if passed a stackable item, this will only remove a max of count from the stack.
	 * @param given Item to remove.
	 * @param count Max amount of the item to remove.
	 */
	public void removeItems(Item given, int count) {
		if(given instanceof StackableItem)
			count -= removeFromStack((StackableItem)given, count);
		else {
			for(int i = 0; i < items.size(); i++) {
				Item curItem = items.get(i);
				if(curItem.matches(given)) {
					items.remove(curItem);
					count--;
					if(count == 0) break;
				}
			}
		}
		
		if(count > 0)
			System.out.println("WARNING: could not remove " + count + " "+given+(count>1?"s":"")+" from inventory");
	}
	
	/**
	 * Returns how many of the given item is in the inventory.
	 * @param given Item to check.
	 * @return Amount of given item in inventory.
	 */
	public int count(Item given) {
		if (given == null) return 0; // null requests get no items. :)
		
		int found = 0; // initialize counting var
		for(int i = 0; i < items.size(); i++) { // loop though items in inv
			Item curItem = items.get(i); // assign current item
			if(!curItem.matches(given)) continue; // ignore it if it doesn't match the given item
			
			if (curItem instanceof StackableItem) // if the item can be a stack...
				found += ((StackableItem)curItem).count; // add however many items are in the stack.
			else
				found++; // otherwise, just add 1 to the found count.
		}
		
		return found;
	}
	
	/**
	 * Returns the name of all the items in the inventory.
	 * @return List containing all the names of the items.
	 */
	public List<String> getItemNames() {
		List<String> names = new ArrayList<>();
		for(int i = 0; i < items.size(); i++)
			names.add(items.get(i).name);
		
		return names;
	}
	
	/**
	 * Generates a string representation of all the items in the inventory which can be sent
	 * over the network.
	 * @return String representation of all the items in the inventory.
	 */
	public String getItemData() {
		StringBuilder itemdata = new StringBuilder();
		for(Item i: items)
			itemdata.append(i.getData()).append(":");
		
		if(itemdata.length() > 0)
			itemdata = new StringBuilder(itemdata.substring(0, itemdata.length() - 1)); //remove extra ",".
		
		return itemdata.toString();
	}
	
	/**
	 * Replaces all the items in the inventory with the items in the string.
	 * @param items String representation of an inventory.
	 */
	public void updateInv(String items) {
		clearInv();
		
		if(items.length() == 0) return; // there are no items to add.
		
		for(String item: items.split(":")) // this still generates a 1-item array when "items" is blank... [""].
			add(Items.get(item));
	}
	
	/**
	 * Tries to add an item to the inventory.
	 * @param chance Chance for the item to be added.
	 * @param item Item to be added.
	 * @param num How many of the item.
	 * @param allOrNothing if true, either all items will be added or none, if false its possible to add
	 * between 0-num items.
	 */
	public void tryAdd(int chance, Item item, int num, boolean allOrNothing) {
		if(!allOrNothing || random.nextInt(chance) == 0)
			for(int i = 0; i < num; i++)
				if(allOrNothing || random.nextInt(chance) == 0)
					add(item.clone());
	}
	
	/**
	 * Tries to add an item to the inventory.
	 * Num amount of the item will be added or no items will be added.
	 * @param chance Chance for the item to be added.
	 * @param item Item to be added.
	 * @param num How many of the item.
	 */
	public void tryAdd(int chance, Item item, int num) {
		if(item instanceof StackableItem) {
			((StackableItem)item).count *= num;
			tryAdd(chance, item, 1, true);
		} else
			tryAdd(chance, item, num, false);
	}
	
	/**
	 * Tries to add an item to the inventory.
	 * Only one item will be added.
	 * @param chance Chance for the item to be added.
	 * @param item Item to be added.
	 */
	public void tryAdd(int chance, Item item) { 
		tryAdd(chance, item, 1);	
	}
	
	/**
	 * Tries to add an ToolItem to the inventory.
	 * @param chance Chance for the item to be added.
	 * @param type Type of the tool.
	 * @param lvl Level of the tool.
	 */
	public void tryAdd(int chance, ToolType type, int lvl) {
		tryAdd(chance, new ToolItem(type, lvl));
	}
	
	/**
	 * Tries to add an Furniture to the inventory.
	 * @param chance Chance for the item to be added.
	 * @param type Type of furniture to add.
	 */
	public void tryAdd(int chance, Furniture type) {
		tryAdd(chance, new FurnitureItem(type));
	}
}

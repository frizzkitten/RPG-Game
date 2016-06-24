package code;

import java.util.ArrayList;

public class Character {
	private String name;
	private int HP;
	private int maxHP;
	private ArrayList<Item> inventory;
	
	/**
	 * Makes a Character. Sets current health (HP) to maxHP.
	 * @param Name the name of the character
	 * @param MaxHP the maximum health the character can have
	 */
	public Character(String Name, int MaxHP) {
		this.setName(Name);
		this.setMaxHP(MaxHP);
		this.setHP(MaxHP);
		inventory = new ArrayList<Item>();
		System.out.println("Test");
	}
	
	/**
	 * Damages the Character by the given amount and returns true if the
	 * Character died.
	 * @param damage the HP to be removed from the character
	 * @return true if the character died
	 */
	public boolean takeDamage(int damage) {
		HP = HP - damage;
		return isDead();
	}
	
	/**
	 * Heals the Character by the given amount, unless HP is at max.
	 * @param healed increases the Characters HP by this amount
	 */
	public void heal(int healed) {
		//TODO implement and make sure health doesn't go above maxHP
		if((HP < maxHP) & (!isDead())){
		for (int i = 0; ((HP < maxHP) && (i < healed)); i++){ HP++;}
		}
	}
	
	/**
	 * Raises the max health of the Character by the given amount.
	 * @param increased max health increases by this amount
	 */
	public void raiseMaxHP(int increased) {
		//TODO
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the HP
	 */
	public int getHP() {
		return HP;
	}

	/**
	 * @param HP the HP to set
	 */
	public void setHP(int HP) {
		this.HP = HP;
	}

	/**
	 * @return the maxHP
	 */
	public int getMaxHP() {
		return maxHP;
	}

	/**
	 * @param maxHP the maxHP to set
	 */
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}
	
	/**
	 * @param item the item to add to the Character's inventory
	 */
	public void addToInventory(Item item) {
		inventory.add(item);
	}
	
	/**
	 * Removes ONE of the given item from the Character's inventory
	 * @param item the item to remove
	 * @return true if an item was removed
	 */
	public boolean removeFromInventory(Item item) {
		return inventory.remove(item);
	}
	
	/**
	 * @return the Character's inventory
	 */
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	/**
	 * Clears the Character's inventory.
	 */
	public void clearInventory() {
		inventory = new ArrayList<Item>();
	}
	/**
	 * Kills Character or not... 
	 * The choice is not yours screw you con
	 * @return kills Character or not.
	 */
	public boolean isDead(){
		if (HP <= 0) {return true;}
		else {return false;}
	}
}


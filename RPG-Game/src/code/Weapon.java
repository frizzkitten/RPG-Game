package code;

public class Weapon extends Item {
	private int wepDamage;
	public Weapon(String Name, int Price, double Weight, int damage) {
		super(Name, Price, Weight);
		this.wepDamage = damage;
	}
	public int getWepDamage(){
		return wepDamage;
	}
}
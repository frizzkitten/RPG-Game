package code;

public class Weapon extends Item {
	private int wepDamage;
	public Weapon(String Name, int damage) {
		super(Name);
		this.wepDamage = damage;
	}
	public int getWepDamage(){
		return wepDamage;
	}
}
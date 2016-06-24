/**
 * 
 */
package code;

/**
 * @author Marcus Lhotka
 *
 */
public class Tester {
	public static void main(String[] args) {
		int damage = 9001;
		//it's OVER 9000!!!!!!!!!!!
		Character marcus = new Character("Marcus", 10000);
		marcus.takeDamage(damage);
		System.out.println(marcus.getHP());
		if (marcus.isDead()){
			System.out.println("You deed");
		}
		else{System.out.println("U no deed");}
		marcus.heal(3);
		System.out.println(marcus.getHP());
		}
}
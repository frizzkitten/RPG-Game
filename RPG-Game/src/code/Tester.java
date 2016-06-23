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
		Character marcus = new Character("Marcus", 1000);
		marcus.takeDamage(damage);
		System.out.println(marcus.getHP());
		if (marcus.isDead()){
			System.out.println("You deed");
		}
		}
}
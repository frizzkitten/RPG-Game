package code;

public class Item {
	private String name;
	private int price;
	private double weight;
	public Item(String Name, int Price, double Weight) {
		this.name = Name;
		this.price = Price;
		this.weight = Weight;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice(){
		return price;
	}
	
	public double getWeight(){
		return weight;
	}
	
	
	public void setName(String newName) {
		name = newName;
	}
}
//DANGLEOOGENSHLONGLE
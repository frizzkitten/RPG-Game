package code;

public class Map {
	private int xMap, yMap;
	private Object[][] size;
	
	/**
	 * Constructor 
	 * @param xSize
	 * @param ySize
	 */
	public Map(int xSize, int ySize){
		this.xMap = xSize;
		this.yMap = ySize;
		this.size = new Object[xMap][yMap];
	}
	
	/**
	 * Makes walls around the sides of the room
	 */
	public void makeSides(){
		for (int x=0; x<xMap; x++){ 
			size[x][0]= new Wall();
			size[x][yMap]= new Wall();
		}
		for (int y=0; y<yMap; y++){
			size[0][y]= new Wall();
			size[xMap][y]= new Wall();
		}
	}
}
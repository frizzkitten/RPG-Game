package code;

public class Map {
private int xMap, yMap;
public Map(int xSize, int ySize){
	this.xMap = xSize;
	this.yMap = ySize;
}
private Object[][] size = new Object[xMap][yMap];
public void sqrSides(){
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
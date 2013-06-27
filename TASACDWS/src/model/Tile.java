package model;

import java.util.ArrayList;

public class Tile extends AbstractGraphicObject{
	
	private ArrayList<IMovableGraphicObject> graphicObjects;
	
	public Tile(double x, double y, double width, double height) {
		super(x, y, width, height);
		
		graphicObjects = new ArrayList<IMovableGraphicObject>();
	}
	
	public void addObjectToTile (IMovableGraphicObject obj) {
		graphicObjects.add(obj);
	}
	
	public void removeObjectFromTile (IMovableGraphicObject obj) {
		graphicObjects.remove(obj);
	}
	
	public ArrayList<IMovableGraphicObject> getObjectsInTile () {
		return graphicObjects;
	}

	@Override
	public void update(int delta) {}
	
}

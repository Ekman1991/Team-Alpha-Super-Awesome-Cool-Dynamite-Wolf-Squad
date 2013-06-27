package model;

import java.util.ArrayList;

public class Tile {
	private ArrayList<IMovableGraphicObject> graphicObjects;
	
	
	public Tile() {
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
	
}

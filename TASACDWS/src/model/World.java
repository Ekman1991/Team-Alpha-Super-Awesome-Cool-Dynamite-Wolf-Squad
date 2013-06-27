package model;

public class World {
	private static World instance;
	private Tile[][] worldMatrix;
	
	private World () {
		worldMatrix = new Tile[12][12];
	}
	
	public synchronized static World getWorld() {
		if(instance == null) {
			instance = new World();
		}
		
		return instance;
	}
	
}

package model;

public class World {
	private static World instance;
	private Tile[][] worldMatrix;
	
	private World () {
		worldMatrix = new Tile[Settings.getSettings().getWindowWidth()/Settings.tileSize][Settings.getSettings().getWindowHeight()/Settings.tileSize];
	}
	
	public synchronized static World getWorld() {
		if(instance == null) {
			instance = new World();
		}
		
		return instance;
	}
	
}

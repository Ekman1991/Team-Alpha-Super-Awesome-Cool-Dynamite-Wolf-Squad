package model;

public class World {
	private static World instance;
	private Tile[][] worldMatrix;
	
	private World () {
		double worldWidth = Settings.getSettings().getWindowWidth()/Settings.tileSize;
		double worldHeight = Settings.getSettings().getWindowHeight()/Settings.tileSize;
		
		
		
		worldMatrix = new Tile[0][0];
	}
	
	public synchronized static World getWorld() {
		if(instance == null) {
			instance = new World();
		}
		return instance;
	}
	
}

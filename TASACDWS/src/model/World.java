package model;

public class World {
	private static World instance;
	private Tile[][] worldMatrix;

	private World () {
		double tempWidth = Settings.getSettings().getWindowWidth()/Settings.tileSize;
		double tempHeight = Settings.getSettings().getWindowHeight()/Settings.tileSize;
		// if ( 2 % 2 == 0)

		int worldWidth = (int)tempWidth;
		int worldHeight = (int)tempHeight;

		if(((int)(tempWidth*2))%2 != 0) {
			worldWidth += 1;
		}

		if(((int)(tempHeight*2))%2 != 0) {
			worldHeight += 1;
		} 

		worldMatrix = new Tile[worldWidth][worldHeight];
	}

	public synchronized static World getWorld() {
		if(instance == null) {
			instance = new World();
		}
		return instance;
	}

}

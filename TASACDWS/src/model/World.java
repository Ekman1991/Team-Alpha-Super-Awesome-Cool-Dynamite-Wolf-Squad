package model;

public class World {
	private static World instance;
	
	private World () {
		
	}
	
	public synchronized static World getWorld() {
		if(instance == null) {
			instance = new World();
		}
		
		return instance;
	}
	
}

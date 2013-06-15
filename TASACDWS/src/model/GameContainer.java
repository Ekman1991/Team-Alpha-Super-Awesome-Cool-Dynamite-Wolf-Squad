package model;

/**
 * Singelton class containing all gamedata.
 * Here we will store the Player, the GameWorld(s), the graphics etc
 * @author bellevik
 *
 */

public class GameContainer {

	private static GameContainer container;
	
	public GameContainer() {
		
	}
	
	public synchronized static GameContainer getContainer() {
		if(container == null) {
			container = new GameContainer();
		}
		
		return container;
	}
}

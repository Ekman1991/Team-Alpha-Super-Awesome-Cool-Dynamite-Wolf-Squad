package model;

import java.util.HashMap;

import org.newdawn.slick.opengl.Texture;

import characters.Block;
import characters.TestPlayer;

/**
 * Singelton class containing all gamedata.
 * Here we will store the Player, the GameWorld(s), the graphics etc
 * @author bellevik
 *
 */

public class GameContainer {
	
	private static GameContainer container;
	
	private GameState state;
	
	private TestPlayer player;
	
	private Block[] blocks;
	
	/**
	 * First String: Type of graphic;
	 * Second String: Model
	 * Texture[] list of textures representing the GameObject
	 */
	private HashMap<String, HashMap<String , Texture[]>> graphics;
	
	private GameContainer() {
		state = GameState.INTRO;
		
		graphics = new HashMap<String, HashMap<String, Texture[]>>();
		
		blocks = new Block[1];
	}
	
	public synchronized static GameContainer getContainer() {
		if(container == null) {
			container = new GameContainer();
		}
		
		return container;
	}
	
	public void clearGraphics() {
		graphics = null;
		graphics = new HashMap<String, HashMap<String, Texture[]>>();
	}
	
	public Texture[] getTextureList(String type, String model) {
		return graphics.get(type).get(model);
	}
	
	public void addModel(String type, String model, Texture[] textures) {
		HashMap<String, Texture[]> newTextures = new HashMap<String, Texture[]>();
		newTextures.put(model, textures);
		graphics.put(type, newTextures);
	}
	
	
	
	public synchronized String[] filterDSStore(String[] array) {
		
		if(array != null) {
			if(array.length > 0) {
				int nbrOfDSStore = 0;
				for(int i = 0; i < array.length; i++) {
					if(array[i].equals(".DS_Store")) {
						nbrOfDSStore += 1;
					}
				}
				
				String[] newArray = new String[array.length-nbrOfDSStore];
				
				int counter = 0;
				for(int j = 0; j < array.length; j++) {
					if(!array[j].equals(".DS_Store")) {
						newArray[counter] = array[j];
						counter += 1;
					}
				}
				
				return newArray;
			}
		}
		
		
		return null;
	}
	
	public TestPlayer getPlayer() {
		return player;
	}
	
	public void setPlayer(TestPlayer player) {
		this.player = player;
	}
	
	public GameState getCurrentState() {
		return state;
	}
	
	public void setCurrentState(GameState state) {
		this.state = state;
	}
	
	public Block getBlock() {
		return blocks[0];
	}
	
	public void setBlock(Block block) {
		blocks[0] = block;
	}
}

package view;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.GameContainer;
import model.Settings;

import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import controller.Input;
import characters.Block;
import characters.TestPlayer;

public class MainDisplay {
	
	private long lastFrame;
	private int fps;
	private long lastFPS;
	
	// Is the game running
	private boolean isRunning = true;
	
	private boolean vsync;
	
	private Input input;
	
	public MainDisplay() {
		System.out.println();
		
		input = new Input(this);
		
		setUpDisplay();
		setupOpenGL();
		loadGraphics();
		setUpGameObjects();
		setUpTimer();
		
		// Enable vsync as default
		vsync = true;
		Display.setVSyncEnabled(vsync);
		
		while(isRunning) {
			int delta = getDelta();
			
			input.getInput();
			
			update(delta);
			renderGL();
			
			if(Display.isCloseRequested()) {
				isRunning = false;
			}
			
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	private void renderGL() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		switch(GameContainer.getContainer().getCurrentState()) {
		
		case INTRO:
			GameContainer.getContainer().getPlayer().draw();
			GameContainer.getContainer().getBlock().draw();
			break;
			
		case MAINMENU:
			
			break;
			
		case CLASSELECTOR:
			
			break;
			
		case GAME:
			
			break;
			
		case SHOP:
			
			break;
			
		case OPTIONS:
			
			break;
			
		case EXTRAS:
			
			break;
			
		case EDITORMENU:
		
			break;
		
		case EDITOR:
			
			break;
		}
	}
	
	private void update(int delta) {
		
		GameContainer.getContainer().getPlayer().update(delta);
		
		updateFPS();
	}

	private void setUpTimer() {
		lastFrame = getTime();
		lastFPS = getTime();
	}

	private void loadGraphics() {
		String[] types = GameContainer.getContainer().filterDSStore(new File(Settings.resources + "/grc").list());
		String[] models;
		String[] images;
		Texture[] textures;
		
		try {
			//System.out.println("Width:" + TextureLoader.getTexture("PNG", new FileInputStream(new File(Settings.resources + "/grc/world/world.png"))).getImageWidth());
			
			for(int i = 0; i < types.length; i++) {
				//System.out.println(types[i]);
				models = GameContainer.getContainer().filterDSStore(new File(Settings.resources + "/grc/" + types[i]).list());
				if(models != null) {
					for(int j = 0; j < models.length; j++) {
						//System.out.println(models[j]);
						images = GameContainer.getContainer().filterDSStore(new File(Settings.resources + "/grc/" + types[i] + "/" + models[j]).list());
						if(images != null) {
							textures = new Texture[images.length];
							for(int k = 0; k < images.length; k++) {
								//System.out.println(images[k]);
								textures[k] = TextureLoader.getTexture("PNG", new FileInputStream(new File(Settings.resources + "/grc/" + types[i] + "/" + models[j] + "/" + images[k])));
								GameContainer.getContainer().addModel(types[i], models[j], textures);
								// throw it into GameContainer
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setUpGameObjects() {
		GameContainer.getContainer().setPlayer(new TestPlayer(100, 100, 32, 32));
		GameContainer.getContainer().setBlock(new Block(500, 500, 32, 32));
	}

	private void setUpDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(Settings.getSettings().getWindowWidth(),Settings.getSettings().getWindowHeight()));
			Display.setTitle("Team Alpha Super Awesome Cool Dynamite Wolf Squad");
			Display.create();
		} catch(LWJGLException e) {
			e.printStackTrace();
		}
	}

	private void setupOpenGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Settings.getSettings().getWindowWidth(), Settings.getSettings().getWindowHeight(), 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	private long getTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}
	
	private int getDelta() {
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		return delta;
	}
	
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("Team Alpha Super Awesome Cool Dynamite Wolf Squad - FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	
	/**
	 * Set the display mode to be used 
	 * 
	 * @param width The width of the display required
	 * @param height The height of the display required
	 * @param fullscreen True if we want fullscreen mode
	 */
	public void setDisplayMode(int width, int height, boolean fullscreen) {

		// return if requested DisplayMode is already set
                if ((Display.getDisplayMode().getWidth() == width) && 
			(Display.getDisplayMode().getHeight() == height) && 
			(Display.isFullscreen() == fullscreen)) {
			return;
		}
		
		try {
			DisplayMode targetDisplayMode = null;
			
			if (fullscreen) {
				DisplayMode[] modes = Display.getAvailableDisplayModes();
				int freq = 0;
				
				for (int i=0;i<modes.length;i++) {
					DisplayMode current = modes[i];
					
					if ((current.getWidth() == width) && (current.getHeight() == height)) {
						if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
							if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
								targetDisplayMode = current;
								freq = targetDisplayMode.getFrequency();
							}
						}

						// if we've found a match for bpp and frequence against the 
						// original display mode then it's probably best to go for this one
						// since it's most likely compatible with the monitor
						if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) &&
						    (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
							targetDisplayMode = current;
							break;
						}
					}
				}
			} else {
				targetDisplayMode = new DisplayMode(width,height);
			}
			
			if (targetDisplayMode == null) {
				System.out.println("Failed to find value mode: "+width+"x"+height+" fs="+fullscreen);
				return;
			}

			Display.setDisplayMode(targetDisplayMode);
			Display.setFullscreen(fullscreen);
			
		} catch (LWJGLException e) {
			System.out.println("Unable to setup mode "+width+"x"+height+" fullscreen="+fullscreen + e);
		}
	}
	
	public boolean getIsRunning() {
		return isRunning;
	}
	
	public void setIsRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	public boolean getVSync() {
		return vsync;
	}
	
	public void setVSync(boolean vsync) {
		this.vsync = vsync;
	}
}

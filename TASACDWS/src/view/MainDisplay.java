package view;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class MainDisplay {
	
	private int windowWidth;
	private int windowHeight;
	
	private long lastFrame;
	private int fps;
	private long lastFPS;
	
	// Is the game running
	private boolean isRunning = true;
	
	private boolean vsync;
	
	public MainDisplay() {
		windowWidth = 800;
		windowHeight = 600;
		
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
			
			renderGL();
			update(delta);
			
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
		
		glBegin(GL_QUADS);
			glVertex2i(300, 300);
			glVertex2i(400, 300);
			glVertex2i(400, 400);
			glVertex2i(300, 400);
		glEnd();
		
	}
	
	private void update(int delta) {
		
		updateFPS();
	}

	private void setUpTimer() {
		lastFrame = getTime();
		lastFPS = getTime();
	}

	private void loadGraphics() {
		
	}
	
	private void setUpGameObjects() {
		
	}

	private void setUpDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(windowWidth,windowHeight));
			Display.setTitle("Team Alpha Super Awesome Cool Dynamite Wolf Squad");
			Display.create();
		} catch(LWJGLException e) {
			e.printStackTrace();
		}
	}

	private void setupOpenGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, windowWidth, windowHeight, 0, 1, -1);
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

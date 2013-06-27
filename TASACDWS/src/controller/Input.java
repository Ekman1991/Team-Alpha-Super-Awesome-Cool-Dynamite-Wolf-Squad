package controller;

import model.GameContainer;
import model.Settings;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import view.MainDisplay;

/**
 * Class for handling all forms of input from the user.
 * @author bellevik
 *
 */

public class Input {
	
	MainDisplay display;
	
	public Input(MainDisplay display) {
		this.display = display;
	}
	
	public void getInput() {
		while (Keyboard.next()) {
		    if (Keyboard.getEventKeyState()) {
		        if (Keyboard.getEventKey() == Keyboard.KEY_F) {
		        	display.setDisplayMode(Settings.getSettings().getWindowWidth(), Settings.getSettings().getWindowHeight(), !Display.isFullscreen());
		        } else if (Keyboard.getEventKey() == Keyboard.KEY_V) {
		        	display.setVSync(!display.getVSync());
		        	Display.setVSyncEnabled(display.getVSync());
		        } else if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
		        	display.setIsRunning(false);
		        } else if (Keyboard.getEventKey() == Keyboard.KEY_M) {
		        	Settings.getSettings().setMouseWalk(!Settings.getSettings().getMouseWalk());
		        }
		    }
		}
		
		// Move the character with keyboard or mouse
		if(Settings.getSettings().getMouseWalk()) {
			while(Mouse.next()) {
				if(Mouse.isButtonDown(Settings.getSettings().getActionButton())) {
					// Do stuff
				}
			}
			
			// Walking around with mouse
			if(Mouse.isButtonDown(Settings.getSettings().getWalkButton())) {
				GameContainer.getContainer().getPlayer().moveTo(Mouse.getX(), Settings.getSettings().getWindowHeight()-Mouse.getY());
			}
			
		} else {
			// Walking around with keyboard
			if(Keyboard.isKeyDown(Settings.getSettings().getControlKey(Settings.MOVE_LEFT)) && !Keyboard.isKeyDown(Settings.getSettings().getControlKey(Settings.MOVE_RIGHT))) {
				GameContainer.getContainer().getPlayer().setDX(-GameContainer.getContainer().getPlayer().getSpeed());
			} else if(Keyboard.isKeyDown(Settings.getSettings().getControlKey(Settings.MOVE_RIGHT)) && !Keyboard.isKeyDown(Settings.getSettings().getControlKey(Settings.MOVE_LEFT))) {
				GameContainer.getContainer().getPlayer().setDX(GameContainer.getContainer().getPlayer().getSpeed());
			} else {
				GameContainer.getContainer().getPlayer().setDX(0);
			}
			
			if(Keyboard.isKeyDown(Settings.getSettings().getControlKey(Settings.MOVE_UP)) && !Keyboard.isKeyDown(Settings.getSettings().getControlKey(Settings.MOVE_DOWN))) {
				GameContainer.getContainer().getPlayer().setDY(-GameContainer.getContainer().getPlayer().getSpeed());
			} else if(Keyboard.isKeyDown(Settings.getSettings().getControlKey(Settings.MOVE_DOWN)) && !Keyboard.isKeyDown(Settings.getSettings().getControlKey(Settings.MOVE_UP))) {
				GameContainer.getContainer().getPlayer().setDY(GameContainer.getContainer().getPlayer().getSpeed());
			} else {
				GameContainer.getContainer().getPlayer().setDY(0);
			}
		}
	}
}

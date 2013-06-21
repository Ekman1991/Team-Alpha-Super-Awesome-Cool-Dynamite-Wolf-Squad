package model;

import java.awt.Rectangle;

import org.newdawn.slick.opengl.Texture;

public abstract class AbstractGraphicObject implements IGraphicObject {

	protected double x, y, width, height;
	protected Rectangle hitbox = new Rectangle();
	protected Texture[] textures = new Texture[1];
	// The current shown image
	protected int imageIndex;
	
	public AbstractGraphicObject(double x, double y, double width, double height, String type, String model) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		imageIndex = 0;
		textures = GameContainer.getContainer().getTextureList(type, model);
	}
	
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public void setWith(double width) {
		this.width = width;
	}

	@Override
	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public int getCurrentImage() {
		return imageIndex;
	}

	@Override
	public void setCurrentImage(int index) {
		imageIndex = index;
	}

	@Override
	public boolean intersects(IGraphicObject other) {
		hitbox.setBounds((int) x, (int) y, (int) width, (int) height);
		
		return hitbox.intersects(other.getX(), other.getY(), other.getWidth(), other.getHeight());
	}

}

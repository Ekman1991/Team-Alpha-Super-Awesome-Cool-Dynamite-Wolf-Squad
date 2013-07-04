package model;

/**
 * Class representing an object in the game.
 * A GraphicObject can be anything from a background to a Tile or a Player.
 * @author bellevik
 *
 */

public interface IGraphicObject {
	public void draw();
	public void update(int delta);
	public double getX();
	public double getY();
	public double getWidth();
	public double getHeight();
	public float getRotation();
	public void setLocation(double x, double y);
	public void setX(double x);
	public void setY(double y);
	public void setWith(double width);
	public void setHeight(double height);
	public void setRotation(float rotation);
	public int getCurrentImage();
	public void setCurrentImage(int index);
	public boolean intersects(IGraphicObject other);
	public boolean northIntersect(IGraphicObject other);
	public boolean eastIntersect(IGraphicObject other);
	public boolean southIntersect(IGraphicObject other);
	public boolean westIntersect(IGraphicObject other);
}

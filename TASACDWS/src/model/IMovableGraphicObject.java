package model;

/**
 * A class representing a movable object.
 * @author bellevik
 *
 */

public interface IMovableGraphicObject extends IGraphicObject {
	public double getDX();
	public double getDY();
	public double getMoveToX();
	public double getMoveToY();
	public double getSpeed();
	public int getMinTileX();
	public int getMaxTileX();
	public int getMinTileY();
	public int getMaxTileY();
	public void setDX(double dx);
	public void setDY(double dy);
	public void setSpeed(double speed);
	public void setMinTileX(int minX);
	public void setMaxTileX(int maxX);
	public void setMinTileY(int minY);
	public void setMaxTileY(int maxY);
	public void rotate(float angle);
	public void moveTo(double x, double y);
	public boolean getIsMoving();
	public void setIsMoving(boolean isMoving);
}

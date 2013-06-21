package model;

/**
 * A class representing a movable object.
 * @author bellevik
 *
 */

public interface IMovableGraphicObject extends IGraphicObject {
	public double getDX();
	public double getDY();
	public void setDX(double dx);
	public void setDY(double dy);
	public void rotate(float angle);
	public boolean getIsMoving();
	public void setIsMoving(boolean isMoving);
}

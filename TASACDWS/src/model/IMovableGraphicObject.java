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
	public void setDX(double dx);
	public void setDY(double dy);
	public void setSpeed(double speed);
	public void rotate(float angle);
	public void moveTo(double x, double y);
	public boolean getIsMoving();
	public void setIsMoving(boolean isMoving);
}

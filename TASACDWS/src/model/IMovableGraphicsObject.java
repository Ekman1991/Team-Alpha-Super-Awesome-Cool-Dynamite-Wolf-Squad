package model;

public interface IMovableGraphicsObject extends IGraphicObject {
	public double getDX();
	public double getDY();
	public void setDX(double dx);
	public void setDY(double dy);
	public boolean getIsMoving();
	public void setIsMoving(boolean isMoving);
}

package model;

public abstract class AbstractMovableGraphicObject extends
		AbstractGraphicObject implements IMovableGraphicObject {

	protected double dx, dy;
	
	protected float angle;
	
	protected boolean isMoving = false;
	
	public AbstractMovableGraphicObject(double x, double y, double width,
			double height, String type, String model) {
		super(x, y, width, height, type, model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(int delta) {
		this.x += delta * dx;
		this.y += delta * dy;
		this.rotation += delta * angle;
	}

	@Override
	public double getDX() {
		return dx;
	}

	@Override
	public double getDY() {
		return dy;
	}

	@Override
	public void setDX(double dx) {
		this.dx = dx;
	}

	@Override
	public void setDY(double dy) {
		this.dy = dy;
	}
	
	@Override
	public void rotate(float angle) {
		this.angle = angle;
	}
	
	@Override
	public boolean getIsMoving() {
		return isMoving;
	}

	@Override
	public void setIsMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

}

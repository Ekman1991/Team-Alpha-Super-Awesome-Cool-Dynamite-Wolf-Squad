package model;

public abstract class AbstractMovableGraphicObject extends
		AbstractGraphicObject implements IMovableGraphicObject {

	protected double dx, dy, speed, moveToX, moveToY;
	
	protected float angle;
	
	protected boolean isMoving = false;
	
	public AbstractMovableGraphicObject(double x, double y, double width,
			double height, double speed, String type, String model) {
		super(x, y, width, height, type, model);
		this.speed = speed;
		moveToX = x;
		moveToY = y;
	}

	@Override
	public void update(int delta) {
		
		if(Settings.getSettings().getMouseWalk()) {
			
			if(!(moveToX - x < 3 && moveToX - x > -3) || !(moveToY - y < 3 && moveToY - y > -3)) {
			//if(moveToX != x && moveToY != y) {	
				double xLength = moveToX - x;
				double yLength = moveToY - y;
				double length = Math.sqrt(Math.pow(Math.abs(xLength), 2) + Math.pow(Math.abs(yLength), 2));
				double step = length / speed;
				
				dx = xLength / step;
				dy = yLength / step;
				
			} else {
				dx = 0;
				dy = 0;
			}
		}
		
		if(dx != 0 || dy != 0) {
			isMoving = true;
		} else {
			isMoving = false;
		}
		
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
	public double getMoveToX() {
		return moveToX;
	}
	
	@Override
	public double getMoveToY() {
		return moveToY;
	}
	
	@Override
	public double getSpeed() {
		return speed;
	}
	
	@Override
	public void setDX(double dx) {
		this.dx = dx;
	}

	@Override
	public void setDY(double dy) {
		this.dy = dy;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	@Override
	public void rotate(float angle) {
		this.angle = angle;
	}
	
	@Override
	public void moveTo(double x, double y) {
		moveToX = x;
		moveToY = y;
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

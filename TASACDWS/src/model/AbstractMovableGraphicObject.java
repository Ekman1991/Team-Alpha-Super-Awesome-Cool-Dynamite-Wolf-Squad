package model;

public abstract class AbstractMovableGraphicObject extends
		AbstractGraphicObject implements IMovableGraphicObject {

	protected double dx, dy, speed, moveToX, moveToY;
	
	protected int minTileX, maxTileX, minTileY, maxTileY;
	
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
		
		// Mouse-walk
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
		// Not Mouse-walk
		} else {
			moveToX = x;
			moveToY = y;
			
		}
		
		if(dx != 0 || dy != 0) {
			isMoving = true;
		} else {
			isMoving = false;
		}
		
		//rotation = rotation + (float)Math.toDegrees(Math.PI/10);
		
		// Here we'll insert a check for which tiles you touch
		
		//System.out.println(delta);
		
		
		
		this.x += delta * dx;
		if(this.intersects(GameContainer.getContainer().getBlock())) {
			while(this.intersects(GameContainer.getContainer().getBlock())) {
				this.x -= dx;
			}
		}
		this.y += delta * dy;
		if(this.intersects(GameContainer.getContainer().getBlock())) {
			while(this.intersects(GameContainer.getContainer().getBlock())) {
				this.y -= dy;
			}
		}
		
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
	public int getMinTileX() {
		return minTileX;
	}
	
	@Override
	public int getMaxTileX() {
		return maxTileX;
	}
	
	@Override
	public int getMinTileY() {
		return minTileY;
	}
	
	@Override
	public int getMaxTileY() {
		return maxTileY;
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
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	@Override
	public void setMinTileX(int minX) {
		minTileX = minX;
	}
	
	@Override
	public void setMaxTileX(int maxX) {
		maxTileX = maxX;
	}
	
	@Override
	public void setMinTileY(int minY) {
		minTileY = minY;
	}
	
	@Override
	public void setMaxTileY(int maxY) {
		maxTileY = maxY;
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

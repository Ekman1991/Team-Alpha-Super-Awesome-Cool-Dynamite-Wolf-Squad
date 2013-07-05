package model;

public abstract class AbstractMovableGraphicObject extends
		AbstractGraphicObject implements IMovableGraphicObject {

	protected double dx, dy, speed, moveToX, moveToY;
	
	protected int minTileX, maxTileX, minTileY, maxTileY;
	
	protected float angle;
	
	protected boolean isMoving = false;
	
	public AbstractMovableGraphicObject(double x, double y, int width,
			int height, double speed, String type, String model) {
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
		// Only checks tiles if they are on the screen
		for(int i = minTileX-1; i <= maxTileX+1; i++) {
			for(int j = minTileY-1; j <= maxTileY+1; j++) {
				if(i >= 0 && j >= 0 && i <= GameContainer.getContainer().getWorldWidth() & j <= GameContainer.getContainer().getWorldHeight()) {
					if(this.intersects(GameContainer.getContainer().getTile(i, j))) {
						// Can't figure out how to change max-min x and y...
					}
				}
			}
		}
		
		System.out.println("MinX: " + minTileX + ", MaxX: " + maxTileX + ", MinY: " + minTileY + ", MaxY: " + maxTileY);
		
		this.x += delta * dx;
		// Checks if you collide with something or try to leave the screen
		if(this.intersects(GameContainer.getContainer().getBlock()) || (x - width/2) < 0 || (x + width/2) > Settings.getSettings().getWindowWidth()) {
			while(this.intersects(GameContainer.getContainer().getBlock()) || (x - width/2) < 0 || (x + width/2) > Settings.getSettings().getWindowWidth()) {
				this.x -= dx;
			}
			//Code to move the block
			GameContainer.getContainer().getBlock().x += delta * dx;
		}
		
		this.y += delta * dy;
		if(this.intersects(GameContainer.getContainer().getBlock()) || (y - height/2) < 0 || (y + height/2) > Settings.getSettings().getWindowHeight()) {
			while(this.intersects(GameContainer.getContainer().getBlock()) || (y - height/2) < 0 || (y + height/2) > Settings.getSettings().getWindowHeight()) {
				this.y -= dy;
			}
			//Code to move the block
			GameContainer.getContainer().getBlock().y += delta * dy;
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

package drawing;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends SurfaceShape{
	Point upperLeftPoint;
	private int height;
	private int width;
	
	public Rectangle() {
	}
	
	public Rectangle(Point upperLeftPoint, int height, int width, Color color, Color innerColor, boolean isSelected) {
		this.upperLeftPoint = upperLeftPoint;
		this.height = height;
		this.width = width;
		
		setColor(color);
		setInnerColor(innerColor);
		setSelected(isSelected);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(this.upperLeftPoint.getX(), this.upperLeftPoint.getY(), this.width, this.height);
		fill(g);
		
		if (isSelected()) {
			Color tempColor = getColor();
			
			g.setColor(Color.BLUE);
			g.drawRect(this.upperLeftPoint.getX() - 3, this.upperLeftPoint.getY() - 3, 6, 6);
			g.drawRect(this.upperLeftPoint.getX() + this.width - 3, this.upperLeftPoint.getY() - 3, 6, 6);
			g.drawRect(this.upperLeftPoint.getX() - 3, this.upperLeftPoint.getY() + this.height - 3, 6, 6);
			g.drawRect(this.upperLeftPoint.getX() + this.width - 3, this.upperLeftPoint.getY() + this.height - 3, 6, 6);
			g.setColor(tempColor);
		}
	}
	
	@Override
	public boolean contains(int x, int y) {
		return (upperLeftPoint.getX() <= x && this.getUpperLeftPoint().getY() <= y && x <= this.getUpperLeftPoint().getX() + width && y <= this.getUpperLeftPoint().getY() + height);
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeftPoint.getX() + 1, this.getUpperLeftPoint().getY() + 1, width - 1, height - 1);
	}
	
	@Override
	public double area() {
		return height * width;
	}
	


	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public String toString() {
		return "Rectangle [upperLeftPoint=" + upperLeftPoint + ", height=" + height + ", width=" + width + "]";
	}
	
	
}

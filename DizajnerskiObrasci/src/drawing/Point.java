package drawing;

import java.awt.Color;
import java.awt.Graphics;

public class Point  extends Shape{
	private int x;
	private int y;
	
	public Point() {
		
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y, Color color, boolean isSelected) {
		this(x, y);
		
		setColor(color);
		setSelected(isSelected);
	}
	
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.x - 2, this.y, this.x + 2, this.y);
		g.drawLine(this.x, this.y-2, this.x, this.y+2);
		
		if (isSelected()) {
			Color tempColor = getColor();
			g.setColor(Color.BLUE);
			g.drawRect(this.x - 3, this.y - 3, 6, 6);
			g.setColor(tempColor);
		}
		
	}
	
	public double distance(int x2, int y2) {
		double dx = this.x - x2;
		double dy = this.y - y2;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d;
	}
	
	
	public boolean contains(int x, int y) {
		return this.distance(x, y) <= 3;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
	
}



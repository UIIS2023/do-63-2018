package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SurfaceShape {
	private Point center= new Point();
	private int radius;
	
	public Circle() {
	}
	
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, Color color, Color innerColor, boolean isSelected) {
		this(center, radius);
		
		setColor(color);
		setInnerColor(innerColor);
		setSelected(isSelected);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(this.center.getX() - radius, this.center.getY() - radius, this.radius * 2, this.radius * 2);
		fill(g);
		
		if (isSelected()) {
			Color tempColor = getColor();
			
			g.setColor(Color.BLUE);
			g.drawRect(this.center.getX() - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - radius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() + radius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() - radius - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() + radius - 3, 6, 6);
			g.setColor(tempColor);
		}
	}
	
	@Override
	public boolean contains(int x, int y) {
		return this.center.distance(x, y) <= radius;
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(this.center.getX() - radius + 1, this.center.getY() - radius + 1, radius * 2 - 2, radius * 2 - 2);
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		this.center.moveBy(byX, byY);
	}
	
	
	public Circle clone(Circle c) { 
		c.getCenter().setX(this.getCenter().getX());
		c.getCenter().setY(this.getCenter().getY());
		try {
			c.setRadius(this.getRadius());
		} catch(Exception e) {
			e.printStackTrace();
		}
		c.setColor(this.getColor());
		c.setInnerColor(this.getInnerColor());
		return c;
	}

	public Point getCenter() {
		return center;
	}

	public int getRadius() {
		return radius;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	@Override
	public String toString() {
		return "Circle [center=" + center + ", radius=" + radius + ", Inner Color=" + Integer.toString(getInnerColor().getRGB()) + ", Border Color=" + Integer.toString(getColor().getRGB()) +"]";
	}
}

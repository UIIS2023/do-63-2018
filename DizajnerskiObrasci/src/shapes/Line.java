package shapes;

import java.awt.Color;
import java.awt.Graphics;


public class Line extends Shape {
	private Point startPoint = new Point();
	private Point endPoint = new Point();
	
	public Line() {
	}
	
	public Line(Point startPoint, Point endPoint, Color color, boolean isSelected) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		
		setColor(color);
		setSelected(isSelected);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.startPoint.getX(), this.startPoint.getY(), this.endPoint.getX(), this.endPoint.getY());
		
		if (isSelected()) {
			Color tempColor = getColor();
			
			g.setColor(Color.BLUE);
			g.drawRect(this.startPoint.getX() - 3, this.startPoint.getY() - 3 , 6, 6);
			g.drawRect(this.endPoint.getX() - 3, this.endPoint.getY() - 3, 6, 6);
			g.drawRect(middleOfLine().getX() - 3, middleOfLine().getY() - 3, 6, 6);
			g.setColor(tempColor);
		}
	}
	
	@Override
	public boolean contains(int x, int y) {
		return ((startPoint.distance(x, y) + (endPoint.distance(x, y)) - length()) <= 0.05);
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		this.startPoint.moveBy(byX, byY);
		this.endPoint.moveBy(byX, byY);
	}
	
	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}
	
	public Point middleOfLine() {
		int middleByX = (this.startPoint.getX() + this.endPoint.getX()) / 2;
		int middleByY = (this.startPoint.getY() + this.endPoint.getY()) / 2;
		Point p = new Point(middleByX, middleByY, Color.BLACK, false);
		
		return p;
	}
	
	public Line clone(Line l) {
		l.getStartPoint().setX(this.getStartPoint().getX());
		l.getStartPoint().setY(this.getStartPoint().getY());
		l.getEndPoint().setX(this.getEndPoint().getX());
		l.getEndPoint().setY(this.getEndPoint().getY());
		l.setColor(this.getColor()); 
		
		return l;
	}
 

	public Point getEndPoint() {
		return endPoint;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	@Override
	public String toString() {
		return "Line [startPoint=" + startPoint + ", endPoint=" + endPoint + ", Color=" + Integer.toString(getColor().getRGB()) + "]";
	}
}

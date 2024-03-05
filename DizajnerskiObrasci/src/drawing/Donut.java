package drawing;

import java.awt.Color;
import java.awt.Graphics;

public class Donut extends Circle {
	private int innerRadius;
	
	public Donut() {
	}
	
	public Donut(Point center, int radius, int innerRadius, Color color, Color innerColor, boolean isSelected) {
		super(center, radius);
		
		this.innerRadius = innerRadius;
		
		setColor(color);
		setInnerColor(innerColor);
		setSelected(isSelected);
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		
		g.setColor(getColor());
		g.drawOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
	}
	
	@Override
	public boolean contains(int x, int y) {
		return super.contains(x, y) && this.getCenter().distance(x, y) > innerRadius;
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		
		super.fill(g);
		
		g.setColor(Color.WHITE);
		g.fillOval(getCenter().getX() - getInnerRadius(), getCenter().getY() - getInnerRadius(), getInnerRadius() * 2, getInnerRadius() * 2);
	}
	


	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}

	@Override
	public String toString() {
		return "Donut [innerRadius=" + innerRadius + "]";
	}
	
	
	
	
}

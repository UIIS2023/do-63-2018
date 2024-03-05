package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;


public class Donut extends Circle{
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
		
	
		Ellipse2D.Double outerCircle = new Ellipse2D.Double(this.getCenter().getX() -
		this.getRadius(), this.getCenter().getY() - this.getRadius(),
		this.getRadius() * 2, this.getRadius() * 2); 
		
		Ellipse2D.Double innerShape = new Ellipse2D.Double(this.getCenter().getX() -
		this.getInnerRadius(), this.getCenter().getY() - this.getInnerRadius(),
		this.getInnerRadius() * 2, this.getInnerRadius() * 2);
		
		Area donut = new Area(outerCircle);
        donut.subtract(new Area(innerShape));
		
		Graphics2D graphics2d = (Graphics2D) g.create();
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		graphics2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		
		graphics2d.setColor(this.getInnerColor());
		graphics2d.fill(donut);
		graphics2d.setColor(this.getColor()); 
	    graphics2d.draw(donut);
	    
	    graphics2d.dispose();
	    
	    if(isSelected()) {
	    	g.setColor(Color.BLUE);
			g.drawRect(super.getCenter().getX() - 3, super.getCenter().getY() - 3, 6, 6);
			g.drawRect(super.getCenter().getX() - this.getRadius() - 3, super.getCenter().getY() - 3, 6, 6);
			g.drawRect(super.getCenter().getX() + this.getRadius() - 3, super.getCenter().getY() - 3, 6, 6);
			g.drawRect(super.getCenter().getX() - 3, super.getCenter().getY() - this.getRadius() - 3, 6, 6);
			g.drawRect(super.getCenter().getX() - 3, super.getCenter().getY() + this.getRadius() - 3, 6, 6);
	    }

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
	
	public Donut clone(Donut d) {
		d.getCenter().setX(this.getCenter().getX());
		d.getCenter().setY(this.getCenter().getY());
		try {
			d.setRadius(this.getRadius());
			d.setInnerRadius(this.getInnerRadius());
		} catch(Exception e) {
			e.printStackTrace();
		}
		d.setColor(this.getColor());
		d.setInnerColor(this.getInnerColor());
		return d;
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	@Override
	public String toString() {
		return "Donut [" + "Center [x=" + getCenter().getX() + ", y=" + getCenter().getY() +"]" + ", radius=" + getRadius() +", innerRadius=" + innerRadius + ", Inner Color="+ Integer.toString(getInnerColor().getRGB())+", Border Color="+ Integer.toString(getColor().getRGB()) + "]";
	}
}

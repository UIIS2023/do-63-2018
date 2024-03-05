package adapter;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;
import shapes.Point;
import shapes.SurfaceShape;

public class HexagonAdapter extends SurfaceShape{

	private Hexagon hexagon;

	public HexagonAdapter() {

	}
	
	public HexagonAdapter(Point p, int r) {
		this.hexagon = new Hexagon(p.getX(), p.getY(), r);
	}

	public HexagonAdapter(Point p, int r, boolean selected) {
		this(p, r);
		this.hexagon.setSelected(selected);
	}

	public HexagonAdapter(Point p, int r, boolean selected, Color color) {
		this(p, r, selected);
		this.hexagon.setBorderColor(color);
	}

	public HexagonAdapter(Point p, int r, boolean selected, Color color, Color innerColor) {
		this(p, r, selected, color);
		this.hexagon.setAreaColor(innerColor);
	}


	@Override
	public void draw(Graphics g) {
		this.hexagon.paint(g);
	}
	
	@Override
	public void fill(Graphics g) {

	}
	


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			HexagonAdapter hexAdapter = (HexagonAdapter) obj;
			if (this.hexagon.getX() == hexAdapter.getHexagon().getX()
					&& this.hexagon.getY() == hexAdapter.getHexagon().getY()
					&& this.hexagon.getR() == hexAdapter.getHexagon().getR()) {

				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		this.hexagon.setX(this.hexagon.getX() + byX);
		this.hexagon.setY(this.hexagon.getY() + byY);
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public void setSelected(boolean selected) {
		this.hexagon.setSelected(selected);
	}

	@Override
	public boolean isSelected() {
		return this.hexagon.isSelected();
	}

	public HexagonAdapter clone(HexagonAdapter hexagon) {
		hexagon.setHexagonCenter(this.getHexagonCenter());
		hexagon.setHexagonRadius(this.getHexagonRadius());
		hexagon.setHexagonBorderColor(this.getHexagonBorderColor());
		hexagon.setHexagonInnerColor(this.getHexagonInnerColor());

		return hexagon;
	}

	public Point getHexagonCenter() {
		return new Point(this.hexagon.getX(), this.hexagon.getY());
	}

	public void setHexagonCenter(Point center) {
		this.hexagon.setX(center.getX());
		this.hexagon.setY(center.getY());
	}

	public int getHexagonRadius() {
		return this.hexagon.getR();
	}

	public void setHexagonRadius(int radius) {
		this.hexagon.setR(radius);
	}

	public Color getHexagonBorderColor() {
		return this.hexagon.getBorderColor();
	}

	public void setHexagonBorderColor(Color borderColor) {
		this.hexagon.setBorderColor(borderColor);
	}

	public Color getHexagonInnerColor() {
		return this.hexagon.getAreaColor();
	}

	public void setHexagonInnerColor(Color innerColor) {
		this.hexagon.setAreaColor(innerColor);
	}

	@Override
	public String toString() {
		return "Hexagon [" + "Center [x=" + getHexagonCenter().getX() + ", y="+ getHexagonCenter().getY() + "]" + ", radius=" + getHexagonRadius() + ", Inner Color=" + Integer.toString(getHexagonInnerColor().getRGB()) + ", Border Color=" + Integer.toString(getHexagonBorderColor().getRGB()) + "]";
	}
	
	
}

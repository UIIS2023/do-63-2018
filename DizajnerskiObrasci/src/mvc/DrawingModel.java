package mvc;

import java.util.ArrayList;

import shapes.Shape;

public class DrawingModel {

	private ArrayList<Shape> shapesList = new ArrayList<Shape>();
	
	public void add(Shape shape) {
		shapesList.add(shape);
		
	}
	
	public void remove(Shape shape) {
		shapesList.remove(shape);
		
	}
	
	public Shape getShape(int index) {
		return shapesList.get(index);
	}
	
	public ArrayList<Shape> getShapesList() {
		return shapesList;
	}
}

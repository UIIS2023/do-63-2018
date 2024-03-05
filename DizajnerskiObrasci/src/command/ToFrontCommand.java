package command;

import java.util.ArrayList;
import java.util.Collections;

import shapes.Shape;

public class ToFrontCommand implements Command{
	
	private ArrayList<Shape> shapesList;
	private Shape shape;
	private int shapeIndex;
	
	

	public ToFrontCommand(ArrayList<Shape> shapesList, Shape shape) {
		this.shapesList = shapesList;
		this.shape = shape;
		shapeIndex=this.shapesList.indexOf(this.shape);
	}

	@Override
	public void execute() {
		if(shapeIndex < this.shapesList.size() - 1) {
			Collections.swap(this.shapesList, shapeIndex, shapeIndex + 1);
			this.shapeIndex += 1;
		}
		
	}

	@Override
	public void unexecute() {
		if(shapeIndex > 0) {
			Collections.swap(this.shapesList, shapeIndex, shapeIndex - 1);
			this.shapeIndex -= 1;
		}
		
	}
	
	@Override
	public String toString() {
		return "Moved to front : " + this.shape + "\n";
	}

}

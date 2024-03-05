package command;

import mvc.DrawingController;
import shapes.Shape;

public class SelectShapeCommand implements Command{

	private DrawingController controller;
	private Shape shape;
	
	public SelectShapeCommand(DrawingController controller, Shape shape) {
		this.controller = controller;
		this.shape = shape;
	}
	
	@Override
	public void execute() {
		this.shape.setSelected(true);
		this.controller.getSelectedShapesList().add(this.shape);
		
	}

	@Override
	public void unexecute() {
		this.shape.setSelected(false);
		this.controller.getSelectedShapesList().remove(this.shape);
	}
	@Override
	public String toString() {
		return "Selected : " + this.shape + "\n";
	}
	
}

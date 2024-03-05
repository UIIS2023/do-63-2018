package command;

import mvc.DrawingController;
import shapes.Shape;

public class DeselectShapeCommand implements Command {

	private DrawingController controller;
	private Shape shape;
	
	public DeselectShapeCommand(DrawingController controller, Shape shape) {
		this.controller = controller;
		this.shape = shape;
	}
	
	@Override
	public void execute() {
		this.shape.setSelected(false);
		this.controller.getSelectedShapesList().remove(this.shape);
	}

	@Override
	public void unexecute() {
		this.shape.setSelected(true);
		this.controller.getSelectedShapesList().add(this.shape);
	}
	@Override
	public String toString() {
		return "Deselected : " + this.shape + "\n";
	}
}

package command;

import mvc.DrawingModel;
import shapes.Shape;

public class AddShapeCommand implements Command {
	
		private DrawingModel model;
		private Shape shape;
		
		public AddShapeCommand(DrawingModel model, Shape shape) {
			this.model = model;
			this.shape = shape;
		}
		@Override
		public void execute() {
			this.model.add(shape);
		}
		@Override
		public void unexecute() {
			this.model.remove(shape);
		}
		
		@Override
		public String toString() {
			return  "Added : " + this.shape + "\n";
		}
		

}

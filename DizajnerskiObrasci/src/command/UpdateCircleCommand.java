package command;

import shapes.Circle;

public class UpdateCircleCommand implements Command{

	private Circle oldState; 
	private Circle newState; 
	private Circle original = new Circle();
	
	public UpdateCircleCommand(Circle oldState, Circle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		 this.original = this.oldState.clone(original);
		 this.oldState = this.newState.clone(oldState);
	}
	
	@Override
	public void unexecute() {
		this.oldState = this.original.clone(oldState);
	}
	
	@Override
	public String toString() {
		return "Updated : " + this.original + " -> " + this.newState + "\n";
	}
}

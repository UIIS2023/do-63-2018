package command;

import shapes.Point;

public class UpdatePointCommand implements Command{

	private Point oldState;
	private Point newState;
	private Point original = new Point();
	
	public UpdatePointCommand(Point oldState, Point newState) {
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

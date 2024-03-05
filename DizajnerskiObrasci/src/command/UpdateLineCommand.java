package command;

import shapes.Line;

public class UpdateLineCommand implements Command{

	private Line oldState;
	private Line newState;
	private Line original = new Line();
	
	public UpdateLineCommand(Line oldState, Line newState) {
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

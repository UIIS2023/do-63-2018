package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class ObserverListener implements PropertyChangeListener{

	private DrawingFrame frame;
	
	
	
	public ObserverListener(DrawingFrame frame) {
		super();
		this.frame = frame;
	}



	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("btnModify")) {
			frame.getBtnModify().setEnabled((boolean) evt.getNewValue());
		}
		else if(evt.getPropertyName().equals("btnRemove")) {
			frame.getBtnRemove().setEnabled((boolean) evt.getNewValue());
		}
		else if(evt.getPropertyName().equals("btnRedo")) {
			frame.getBtnRedo().setEnabled((boolean) evt.getNewValue());
		}
		else if(evt.getPropertyName().equals("btnUndo")) {
			frame.getBtnUndo().setEnabled((boolean) evt.getNewValue());
		}
		else if(evt.getPropertyName().equals("btnToFront")) {
			frame.getBtnToFront().setEnabled((boolean) evt.getNewValue());
		}
		else if(evt.getPropertyName().equals("btnToBack")) {
			frame.getBtnToBack().setEnabled((boolean) evt.getNewValue());
		}
		else if(evt.getPropertyName().equals("btnBringToFront")) {
			frame.getBtnBringToFront().setEnabled((boolean) evt.getNewValue());
		}
		else if(evt.getPropertyName().equals("btnBringToBack") ) {
			frame.getBtnBringToBack().setEnabled((boolean) evt.getNewValue());
		}
		
		
	}
}

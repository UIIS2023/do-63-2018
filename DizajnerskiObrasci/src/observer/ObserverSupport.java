package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ObserverSupport {

	private boolean enabledModify;
	private boolean enabledRemove;
	private boolean enabledUndo;
	private boolean enabledRedo;
	private boolean enabledToFront;
	private boolean enabledToBack;
	private boolean enabledBringToFront;
	private boolean enabledBringToBack;
	
	private PropertyChangeSupport propertyChangeSupport;

	public ObserverSupport() {
		super();
		this.propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		propertyChangeSupport.removePropertyChangeListener(pcl);
	}
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		propertyChangeSupport.addPropertyChangeListener(pcl);
	}

	public boolean isEnabledModify() {
		return enabledModify;
	}

	public void setEnabledModify(boolean enabledModify) {
		propertyChangeSupport.firePropertyChange("btnModify", this.enabledModify, enabledModify);
		this.enabledModify = enabledModify;
	}

	public boolean isEnabledRemove() {
		return enabledRemove;
	}

	public void setEnabledRemove(boolean enabledRemove) {
		propertyChangeSupport.firePropertyChange("btnRemove", this.enabledRemove, enabledRemove);
		this.enabledRemove = enabledRemove;
	}

	public boolean isEnabledUndo() {
		return enabledUndo;
	}

	public void setEnabledUndo(boolean enabledUndo) {
		propertyChangeSupport.firePropertyChange("btnUndo", this.enabledUndo, enabledUndo);
		this.enabledUndo = enabledUndo;
	}

	public boolean isEnabledRedo() {
		return enabledRedo;
	}

	public void setEnabledRedo(boolean enabledRedo) {
		propertyChangeSupport.firePropertyChange("btnRedo", this.enabledRedo, enabledRedo);
		this.enabledRedo = enabledRedo;
	}

	public boolean isEnabledToFront() {
		return enabledToFront;
	}

	public void setEnabledToFront(boolean enabledToFront) {
		propertyChangeSupport.firePropertyChange("btnToFront", this.enabledToFront, enabledToFront);
		this.enabledToFront = enabledToFront;
	}

	public boolean isEnabledToBack() {
		return enabledToBack;
	}

	public void setEnabledToBack(boolean enabledToBack) {
		propertyChangeSupport.firePropertyChange("btnToBack", this.enabledToBack, enabledToBack);
		this.enabledToBack = enabledToBack;
	}

	public boolean isEnabledBringToFront() {
		return enabledBringToFront;
	}

	public void setEnabledBringToFront(boolean enabledBringToFront) {
		propertyChangeSupport.firePropertyChange("btnBringToFront", this.enabledBringToFront, enabledBringToFront);
		this.enabledBringToFront = enabledBringToFront;
	}

	public boolean isEnabledBringToBack() {
		return enabledBringToBack;
	}

	public void setEnabledBringToBack(boolean enabledBringToBack) {
		propertyChangeSupport.firePropertyChange("btnBringToBack", this.enabledBringToBack, enabledBringToBack);
		this.enabledBringToBack = enabledBringToBack;
	}

	public PropertyChangeSupport getPropertyChangeSupport() {
		return propertyChangeSupport;
	}

	public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
		this.propertyChangeSupport = propertyChangeSupport;
	}
	
	
	

}

package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import adapter.HexagonAdapter;
import command.AddShapeCommand;
import command.BringToBackCommand;
import command.BringToFrontCommand;
import command.Command;
import command.DeselectShapeCommand;
import command.RemoveShapeCommand;
import command.SelectShapeCommand;
import command.ToBackCommand;
import command.ToFrontCommand;
import command.UpdateCircleCommand;
import command.UpdateDonutCommand;
import command.UpdateHexagonCommand;
import command.UpdateLineCommand;
import command.UpdatePointCommand;
import command.UpdateRectangleCommand;
import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgModCircle;
import drawing.DlgModDonut;
import drawing.DlgModHexagon;
import drawing.DlgModLine;
import drawing.DlgModPoint;
import drawing.DlgModRectangle;
import drawing.DlgRectangle;
import observer.ObserverListener;
import observer.ObserverSupport;
import shapes.Circle;
import shapes.Donut;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import strategy.FileChooser;
import strategy.FileDrawing;
import strategy.FileLog;
import strategy.FileManager;

public class DrawingController {

	private DrawingModel model;
	private DrawingFrame frame;
	
	private Command command;
	
	private ObserverListener btnObserver;
	private ObserverSupport btnObservable = new ObserverSupport();
	
	private ArrayList<Shape> selectedShapesList = new ArrayList<>();
	private ArrayList<Shape> redoShapesList = new ArrayList<>();
	private ArrayList<Shape> undoShapesList = new ArrayList<>();
	private ArrayList<String> saveLogList = new ArrayList<>();
	private ArrayList<String> rowsFromFile = new ArrayList<String>();
	private ArrayList<String> commandList = new ArrayList<String>();
	
	private Stack<Command> redoStack = new Stack<Command>();
	private Stack<Command> undoStack = new Stack<Command>();
	
	private int redoCounter = 0; 
	private int undoCounter = 0;
	private int commandListCounter = 0;
	
	private Point startPoint = null;
	
	private JFileChooser fileChooser;
	private Scanner scanCommand;
	
	
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model; 
		this.frame = frame;
		
		btnObserver = new ObserverListener(frame);
		btnObservable.addPropertyChangeListener(btnObserver);
	}
	
	public void mouseClicked(MouseEvent e) {
		if(frame.getSelect() == "Point") {
			drawPoint(e);
			
		}
		else if(frame.getSelect() == "Line") {
			drawLine(e);
		}
		else if (frame.getSelect() == "Circle") {
			drawCircle(e);
		}
		else if (frame.getSelect() == "Rectangle") {
			drawRectangle(e);
		}	
		else if (frame.getSelect() == "Donut") {
			drawDonut(e);
		}
		else if (frame.getSelect() == "Hexagon") {
			drawHexagon(e);
		}
		else if (frame.getSelect() == "Select") {
			selectShape(e);
		}
		
	}
	
	public void selectShape(MouseEvent e) {
		
		Shape selectedShape = null;
		Shape shape = null;
		
		Iterator<Shape> it = this.model.getShapesList().iterator();
		
		while (it.hasNext()) {
			shape = it.next();

			if (shape.contains(e.getX(), e.getY())) {
				selectedShape = shape;
			}

		}
		
		if (selectedShape != null) {
			if (selectedShape.isSelected()) {
				command = new DeselectShapeCommand(this, selectedShape);
				command.execute();
				redoStack.clear();
				this.frame.getTextArea().append(command.toString());
				saveLogList.add(command.toString());
				undoStack.push(command);
			} else {
				command = new SelectShapeCommand(this, selectedShape);
				command.execute();
				redoStack.clear();
				this.frame.getTextArea().append(command.toString());
				saveLogList.add(command.toString());
				undoStack.push(command);
			
			}
			undoCounter++;
			
			checkUndoRedoButtons();
			buttonsEnableDisable();
			
			frame.repaint();
		}
			
	}

	public void deleteShape(boolean text) {
		
		Shape shape;
		int selectedShapesListLength = this.selectedShapesList.size();
		
		for(int i = 0; i < selectedShapesListLength; i++) {
			
			shape = this.selectedShapesList.get(0);
			
			command = new RemoveShapeCommand(this.model, shape, this.model.getShapesList().indexOf(shape));
			command.execute();
			
			this.frame.getTextArea().append(command.toString());
			saveLogList.add(command.toString());
			
			this.selectedShapesList.remove(shape);
			this.undoShapesList.add(shape);
			undoStack.push(command);
			undoCounter++;
			
			redoStack.clear();
			
			checkUndoRedoButtons();
			buttonsEnableDisable();
			
			frame.repaint();
		}
	}	
public void drawPoint(MouseEvent e) {
		
		Point shape = new Point(e.getX(), e.getY(), frame.getCurrentBorderColor(), false);
		
		command = new AddShapeCommand(model, shape);
		command.execute();	
		
		this.frame.getTextArea().append(command.toString());
		saveLogList.add(command.toString());
		
		undoCounter++;
		
		undoStack.push(command);
		redoStack.clear();
		
		checkUndoRedoButtons();
		buttonsEnableDisable();

		frame.repaint();
	}

public void drawLine(MouseEvent e) {
	
	if(startPoint == null) {
		startPoint = new Point(e.getX(), e.getY());
	}
	else {
		Point endPoint = new Point(e.getX(), e.getY());
		Line shape = new Line(endPoint, startPoint, frame.getCurrentBorderColor(), false);
	
		command = new AddShapeCommand(model, shape);
		command.execute();
		
		this.frame.getTextArea().append(command.toString());
		saveLogList.add(command.toString());
		
		undoCounter++;
		
		undoStack.push(command);
		redoStack.clear();
		
		checkUndoRedoButtons();
		buttonsEnableDisable();
		
		frame.repaint();
		
		startPoint = null;
	}
}

public void drawCircle(MouseEvent e) {
	Point circleCenter = new Point(e.getX(), e.getY());
	DlgCircle dlgCircle = new DlgCircle();
	
	dlgCircle.getTxtColor().setBackground(frame.getCurrentBorderColor());
	dlgCircle.getTxtColor1().setBackground(frame.getCurrentInnerColor());

	dlgCircle.setVisible(true);
	
	if (dlgCircle.isOK) {

		try {

			int radius = Integer.parseInt(dlgCircle.getTxtRadius().getText().toString());

			if (radius > 0) {
				
				Color borderColor = dlgCircle.getTxtColor().getBackground();
				Color innerColor = dlgCircle.getTxtColor1().getBackground();

				Circle shape = new Circle(circleCenter, radius, borderColor, innerColor, false);
				
				command = new AddShapeCommand(model, shape);
				command.execute();
			
				this.frame.getTextArea().append(command.toString());
				saveLogList.add(command.toString());
				
				undoCounter++;
				
				undoStack.push(command);
				redoStack.clear();
				
				checkUndoRedoButtons();
				buttonsEnableDisable();
				
				frame.repaint();

			}
			
		} catch (Exception NumberFormatException) {
			JOptionPane.showMessageDialog(null, "Invalid values, try again!");
		}
	}
}

public void drawRectangle(MouseEvent e) {
	
	Point upperLeftPoint = new Point(e.getX(), e.getY());
	DlgRectangle dlgRectangle = new DlgRectangle();
	
	dlgRectangle.getTxtColor().setBackground(frame.currentBorderColor);
	dlgRectangle.getTxtInnerColor().setBackground(frame.currentInnerColor);
	
	dlgRectangle.setVisible(true);
	
	if(dlgRectangle.isOK) {
		
		int height = Integer.parseInt(dlgRectangle.getTxtHeight().getText());
		int width = Integer.parseInt(dlgRectangle.getTxtWidth().getText());
		
		Color borderColor = dlgRectangle.getTxtColor().getBackground();
		Color innerColor = dlgRectangle.getTxtInnerColor().getBackground();
	
		Rectangle shape = new Rectangle(upperLeftPoint, height, width, borderColor, innerColor, false);
		
		command = new AddShapeCommand(model, shape);
		command.execute();
	
		this.frame.getTextArea().append(command.toString());
		saveLogList.add(command.toString());
		
		undoCounter++;
		
		undoStack.push(command);
		redoStack.clear();
		
		checkUndoRedoButtons();
		buttonsEnableDisable();

		frame.repaint();
		
	}
	
}
public void drawDonut(MouseEvent e) {
	
	Point donutCenter = new Point(e.getX(), e.getY());
	DlgDonut dlgDonut = new DlgDonut();
	
	dlgDonut.getTxtColor().setBackground(frame.currentBorderColor);
	dlgDonut.getTxtInnerColor().setBackground(frame.currentInnerColor);
	
	dlgDonut.setVisible(true);
	
	if(dlgDonut.isOk) {
		
		int radius = Integer.parseInt(dlgDonut.getTxtRadius().getText());
		int innerRadius = Integer.parseInt(dlgDonut.getTxtInnerRadius().getText());
		
		Color borderColor = dlgDonut.getTxtColor().getBackground();
		Color innerColor = dlgDonut.getTxtInnerColor().getBackground();
	
		Donut shape = new Donut(donutCenter, radius, innerRadius, borderColor, innerColor, false);
		
		command = new AddShapeCommand(model, shape);
		command.execute();
	
		this.frame.getTextArea().append(command.toString());
		saveLogList.add(command.toString());
		
		undoCounter++;
		
		undoStack.push(command);
		redoStack.clear();
		
		checkUndoRedoButtons();
		buttonsEnableDisable();
		
		frame.repaint();
	}
	
}
public void drawHexagon(MouseEvent e) {
	
	Point hexagonCenter = new Point(e.getX(), e.getY());
	DlgHexagon dlgHexagon = new DlgHexagon();
	
	dlgHexagon.getTxtColor().setBackground(frame.getCurrentBorderColor());
	dlgHexagon.getTxtInnerColor().setBackground(frame.getCurrentInnerColor());

	dlgHexagon.setVisible(true);
	
	if (dlgHexagon.isOK) {					
		
		Color borderColor = dlgHexagon.getTxtColor().getBackground();
		Color innerColor = dlgHexagon.getTxtInnerColor().getBackground();
		
		int radius = Integer.parseInt(dlgHexagon.getTxtRadius().getText());
	
		HexagonAdapter hexagon = new HexagonAdapter(hexagonCenter, radius, false, borderColor, innerColor);
		
		command = new AddShapeCommand(model, hexagon);
		command.execute();	
		
		this.frame.getTextArea().append(command.toString());
		saveLogList.add(command.toString());
		
		undoCounter++;
		
		undoStack.push(command);
		redoStack.clear();
		
		checkUndoRedoButtons();
		buttonsEnableDisable();

		frame.repaint();
	}
}
	


public void modifyShape() {
	
	for(int i = 0; i< model.getShapesList().size(); i++) {
		
		if(model.getShape(i) instanceof Point) {
			
			if(model.getShape(i).isSelected()) {

				Point p = (Point) model.getShape(i);
				if(p != null) {
					DlgModPoint dlgPoint = new DlgModPoint();
					
					dlgPoint.getTxtX().setText(Integer.toString(p.getX()));
					dlgPoint.getTxtY().setText(Integer.toString(p.getY()));
					
					dlgPoint.setVisible(true);
					
					if(dlgPoint.isOk) {
						try {
						
						int x = Integer.parseInt(dlgPoint.getTxtX().getText());
						int y = Integer.parseInt(dlgPoint.getTxtY().getText());
						
						Color color = p.getColor();
						
						Point point = new Point(x, y, color, true);
						
						command = new UpdatePointCommand(p, point);
						command.execute();
						
						this.frame.getTextArea().append(command.toString());
						saveLogList.add(command.toString());
						
						undoStack.push(command);
						redoStack.clear();
						
						undoCounter++;
						
						checkUndoRedoButtons();
						buttonsEnableDisable();
						
						frame.repaint();
						}
						
					
					catch(NumberFormatException e) 
					{
						e.printStackTrace();
					}
					}
				
				}
	
			}
			
			
		}else if(model.getShape(i) instanceof Line) {
			
			if(model.getShape(i).isSelected()) {

				Line l = (Line) model.getShape(i);
				if (l != null && l.getStartPoint() != null && l.getEndPoint() != null) {
					DlgModLine dlgLine = new DlgModLine();
					dlgLine.getTxtX().setText(Integer.toString(l.getStartPoint().getX()));
					dlgLine.getTxtY().setText(Integer.toString(l.getStartPoint().getY()));
					dlgLine.getTxtX1().setText(Integer.toString(l.getEndPoint().getX()));
					dlgLine.getTxtY1().setText(Integer.toString(l.getEndPoint().getY()));
					
					dlgLine.setVisible(true);
					
					if(dlgLine.isOk) {
						try {
						int startX = Integer.parseInt(dlgLine.getTxtX().getText());
						int startY = Integer.parseInt(dlgLine.getTxtY().getText());
						int endX = Integer.parseInt(dlgLine.getTxtX1().getText());
						int endY = Integer.parseInt(dlgLine.getTxtY1().getText());
						
						Point startPoint = new Point(startX, startY);
						Point endPoint = new Point(endX, endY);
						
						Color color = l.getColor();
						
						Line line = new Line(startPoint, endPoint, color, true);
						
						command = new UpdateLineCommand(l, line);
						command.execute();
						
						this.frame.getTextArea().append(command.toString());
						saveLogList.add(command.toString());
						
						undoStack.push(command);
						redoStack.clear();
						
						undoCounter++;
						
						checkUndoRedoButtons();
						buttonsEnableDisable();
						
						frame.repaint();
						}
						catch(NumberFormatException e) 
						{
							e.printStackTrace();
						}
						
					}
					
					
				}
				
				
				
			}
		} else if(model.getShape(i) instanceof Rectangle) {
			
			if(model.getShape(i).isSelected()) {

				Rectangle r = (Rectangle) model.getShape(i);
				if (r != null && r.getUpperLeftPoint() != null) {
					
					DlgModRectangle dlgRectangle = new DlgModRectangle();
					
					dlgRectangle.getTextField_1().setText(Integer.toString(r.getHeight()));
					dlgRectangle.getTextField().setText(Integer.toString(r.getWidth()));
					dlgRectangle.getTxtX().setText(Integer.toString(r.getUpperLeftPoint().getX()));
					dlgRectangle.getTxtY().setText(Integer.toString(r.getUpperLeftPoint().getY()));
					dlgRectangle.getTxtColor().setBackground(r.getColor());
					dlgRectangle.getTxtColor1().setBackground(r.getInnerColor());
					
					dlgRectangle.setVisible(true);
					
					if(dlgRectangle.isOK) {
						
						try {
						int x = Integer.parseInt(dlgRectangle.getTxtX().getText());
						int y = Integer.parseInt(dlgRectangle.getTxtY().getText());
						int width = Integer.parseInt(dlgRectangle.getTextField().getText());
						int height = Integer.parseInt(dlgRectangle.getTextField_1().getText());
						
						Color color = dlgRectangle.getTxtColor().getBackground();
						Color innerColor = dlgRectangle.getTxtColor1().getBackground();

						Rectangle rectangle = new Rectangle(new Point(x, y), height, width, color, innerColor , true);

						command = new UpdateRectangleCommand(r, rectangle);
						command.execute();
						
						this.frame.getTextArea().append(command.toString());
						saveLogList.add(command.toString());
						
						undoStack.push(command);
						redoStack.clear();
						
						undoCounter++;
						
						checkUndoRedoButtons();
						buttonsEnableDisable();
						
						frame.repaint();
						}
						catch(NumberFormatException e) 
						{
							e.printStackTrace();
						}
					}
				
				}
				
				
			}
		} else if(model.getShape(i) instanceof Circle) {
			
			if(model.getShape(i).isSelected()) {
				
				Circle c = (Circle) model.getShape(i);
				
				if (c != null) {
					DlgModCircle dlgCircle = new DlgModCircle();
					
					dlgCircle.getTxtX().setText(Integer.toString(c.getCenter().getX()));
					dlgCircle.getTxtY().setText(Integer.toString(c.getCenter().getY()));
					dlgCircle.getTxtRadius().setText(Integer.toString(c.getRadius()));
					
					dlgCircle.getTxtColor().setBackground(c.getColor());
					dlgCircle.getTxtInnerColor().setBackground(c.getInnerColor());
					
					dlgCircle.setVisible(true);
					
					if(dlgCircle.isOk) {
						try {

							int x = Integer.parseInt(dlgCircle.getTxtX().getText().toString());
							int y = Integer.parseInt(dlgCircle.getTxtY().getText().toString());
							int r = Integer.parseInt(dlgCircle.getTxtRadius().getText());
							
							Color color = dlgCircle.getTxtColor().getBackground();
							Color innerColor = dlgCircle.getTxtInnerColor().getBackground();
	
							Circle circle = new Circle(new Point(x, y), r, color, innerColor, true);
	
							command = new UpdateCircleCommand(c, circle);
							command.execute();
							
							this.frame.getTextArea().append(command.toString());
							saveLogList.add(command.toString());
							
							undoStack.push(command);
							redoStack.clear();
							
							undoCounter++;
							
							checkUndoRedoButtons();
							buttonsEnableDisable();
							
							frame.repaint();
							}
							catch(NumberFormatException e) 
							{
								e.printStackTrace();
							}
						}
					}

				}
		} else if(model.getShape(i) instanceof Donut) {
			
			if(model.getShape(i).isSelected()) {
				
				DlgModDonut dlgDonut = new DlgModDonut();
				
				Donut d = (Donut) model.getShape(i);
				
				dlgDonut.getTxtX().setText(Integer.toString(d.getCenter().getX()));
				dlgDonut.getTxtY().setText(Integer.toString(d.getCenter().getY()));
				dlgDonut.getTxtInnerRadius().setText(Integer.toString(d.getInnerRadius()));
				dlgDonut.getTxtRadius().setText(Integer.toString(d.getRadius()));
				
				dlgDonut.getTxtColor().setBackground(d.getColor());
				dlgDonut.getTxtColor1().setBackground(d.getInnerColor());
				
				dlgDonut.setVisible(true);
				
				if(dlgDonut.isOk) {
					
					int x = Integer.parseInt(dlgDonut.getTxtX().getText().toString());
					int y = Integer.parseInt(dlgDonut.getTxtY().getText().toString());
					int r = Integer.parseInt(dlgDonut.getTxtRadius().getText().toString());
					int innerRadius = Integer.parseInt(dlgDonut.getTxtInnerRadius().getText().toString());
					
					Color color = dlgDonut.getTxtColor().getBackground();
					Color innerColor = dlgDonut.getTxtInnerRadius().getBackground();
					
					Donut donut = new Donut(new Point(x, y), r, innerRadius, color, innerColor, true);
					
					command = new UpdateDonutCommand(d, donut);
					command.execute();
					
					this.frame.getTextArea().append(command.toString());
					saveLogList.add(command.toString());
					
					undoStack.push(command);
					redoStack.clear();
					
					undoCounter++;
					
					checkUndoRedoButtons();
					buttonsEnableDisable();
					
					frame.repaint();
				}
			}
		} else if(model.getShape(i) instanceof HexagonAdapter) {
			
			if(model.getShape(i).isSelected()) {
				
				DlgModHexagon dlgHexagon = new DlgModHexagon();
				
				HexagonAdapter hexagon = (HexagonAdapter) model.getShape(i);
				
				dlgHexagon.getTxtX().setText(Integer.toString(hexagon.getHexagonCenter().getX()));
				dlgHexagon.getTxtY().setText(Integer.toString(hexagon.getHexagonCenter().getY()));
				dlgHexagon.getTxtRadius().setText(Integer.toString(hexagon.getHexagonRadius()));
				
				dlgHexagon.getTxtColor().setBackground(hexagon.getHexagonBorderColor());
				dlgHexagon.getTxtInnerColor().setBackground(hexagon.getHexagonInnerColor());
				
				dlgHexagon.setTitle("Hexagon modify");
				
				dlgHexagon.setVisible(true);
				
				if(dlgHexagon.isOk) {
					
					int x = Integer.parseInt(dlgHexagon.getTxtX().getText());
					int y = Integer.parseInt(dlgHexagon.getTxtY().getText());
					int r = Integer.parseInt(dlgHexagon.getTxtRadius().getText());
					
					Color color = dlgHexagon.getTxtColor().getBackground();
					Color innerColor = dlgHexagon.getTxtInnerColor().getBackground();
					
					HexagonAdapter newHexagon = new HexagonAdapter(new Point(x, y), r, true, color, innerColor);
					
					command = new UpdateHexagonCommand(hexagon, newHexagon);
					command.execute();
					
					this.frame.getTextArea().append(command.toString());
					saveLogList.add(command.toString());
					
					undoStack.push(command);
					redoStack.clear();
					
					undoCounter++;
					
					checkUndoRedoButtons();
					buttonsEnableDisable();
					
					frame.repaint();
				}
			}
		}
		
		
		
	}
}
public void redoFunc() {
	command =redoStack.peek();
	
	if(command instanceof RemoveShapeCommand) {

			command.execute();
			this.undoShapesList.add(this.redoShapesList.get(this.redoShapesList.size()-1));
			this.selectedShapesList.remove(this.redoShapesList.get(this.redoShapesList.size()-1));
			this.redoShapesList.remove(this.redoShapesList.size()-1);
			this.frame.getTextArea().append("Redo : " + redoStack.peek().toString());
			saveLogList.add("Redo : " + redoStack.peek().toString());
			undoCounter++;
			redoCounter--;
			redoStack.pop();
			undoStack.push(command);
			if(!redoStack.isEmpty()) {
				command = redoStack.peek();
			}else {
				command = null;
			}

		
	}
	else {
		command.execute();
		this.frame.getTextArea().append("Redo : " + redoStack.peek().toString());
		saveLogList.add("Redo : " + redoStack.peek().toString());
		undoCounter++;
		redoCounter--;
		redoStack.pop();
		undoStack.push(command);
	}
	frame.repaint();
	checkUndoRedoButtons();
	buttonsEnableDisable();
}
public void undoFunc() {
	command = undoStack.peek();
	
	if(command instanceof RemoveShapeCommand) {

			command.unexecute();
			this.redoShapesList.add(this.undoShapesList.get(this.undoShapesList.size() - 1));
			this.selectedShapesList.add(this.undoShapesList.get(this.undoShapesList.size()-1));
			this.undoShapesList.remove(this.undoShapesList.size()-1);
			this.frame.getTextArea().append("Undo : " + undoStack.peek().toString());
			saveLogList.add("Undo : " + undoStack.peek().toString());
			redoCounter++;
			undoCounter--;
			undoStack.pop();
			redoStack.push(command);
			command = undoStack.peek();
			

	}else {
		command.unexecute();
		this.frame.getTextArea().append("Undo : " + undoStack.peek().toString());
		saveLogList.add("Undo : " + undoStack.peek().toString());
		redoCounter++;
		undoCounter--;
		undoStack.pop();
		redoStack.push(command);
	}
	frame.repaint();
	checkUndoRedoButtons();
	buttonsEnableDisable();
}
public void checkUndoRedoButtons() {
	if (undoCounter < 1) {
		frame.getBtnUndo().setEnabled(false);
	} else {
		frame.getBtnUndo().setEnabled(true);
	}

	if (redoCounter < 1 || redoStack.isEmpty()) {
		frame.getBtnRedo().setEnabled(false);
	} else {
		frame.getBtnRedo().setEnabled(true);
	}
}
public void toFront() {
	
	Shape shape = this.selectedShapesList.get(0);
	Command toFrontCommand = new ToFrontCommand(this.model.getShapesList(), shape);
	toFrontCommand.execute();
	this.frame.getTextArea().append(toFrontCommand.toString());
	saveLogList.add(toFrontCommand.toString());
	undoStack.push(toFrontCommand);
	undoCounter++;
	redoStack.clear();

	
	frame.repaint();
	
	checkUndoRedoButtons();
	buttonsEnableDisable();
}

public void toBack() {
	
	Shape shape = this.selectedShapesList.get(0);
	Command toBackCommand = new ToBackCommand(this.model.getShapesList(), shape);
	toBackCommand.execute();
	this.frame.getTextArea().append(toBackCommand.toString());
	saveLogList.add(toBackCommand.toString());
	undoStack.push(toBackCommand);
	undoCounter++;
	redoStack.clear();
	
	frame.repaint();
	
	checkUndoRedoButtons();
	buttonsEnableDisable();
}

public void bringToFront() {
	
	Shape shape = this.selectedShapesList.get(0);
	Command bringToFrontCommand = new BringToFrontCommand(this.model.getShapesList(), shape);
	bringToFrontCommand.execute();
	this.frame.getTextArea().append(bringToFrontCommand.toString());
	saveLogList.add(bringToFrontCommand.toString());
	undoStack.push(bringToFrontCommand);
	undoCounter++;
	redoStack.clear();

	
	frame.repaint();

	checkUndoRedoButtons();
	buttonsEnableDisable();
}

public void bringToBack() {
	
	Shape shape = this.selectedShapesList.get(0);
	Command bringToBackCommand = new BringToBackCommand(this.model.getShapesList(), shape);
	bringToBackCommand.execute();
	frame.getTextArea().append(bringToBackCommand.toString());
	saveLogList.add(bringToBackCommand.toString());
	undoStack.push(bringToBackCommand);
	undoCounter++;
	redoStack.clear();
	
	frame.repaint();
	
	
	
	checkUndoRedoButtons();
	buttonsEnableDisable();
}
public void buttonsEnableDisable() {
	
	if(this.selectedShapesList.size() != 0) {
		
		if(this.selectedShapesList.size() == 1) {
			btnObservable.setEnabledModify(true);
			btnObservable.setEnabledToFront(true);
			btnObservable.setEnabledToBack(true);
			btnObservable.setEnabledBringToFront(true);
			btnObservable.setEnabledBringToBack(true);
			
		}else {
			btnObservable.setEnabledModify(false);
			btnObservable.setEnabledToFront(false);
			btnObservable.setEnabledToBack(false);
			btnObservable.setEnabledBringToFront(false);
			btnObservable.setEnabledBringToBack(false);
		}
		btnObservable.setEnabledRemove(true);
		
	}
	else {
		btnObservable.setEnabledRemove(false);
		btnObservable.setEnabledModify(false);
		btnObservable.setEnabledToFront(false);
		btnObservable.setEnabledToBack(false);
		btnObservable.setEnabledBringToFront(false);
		btnObservable.setEnabledBringToBack(false);

	}
	
}

public void saveDrawing() {
	fileChooser = new JFileChooser("C:\\Users\\jocak\\OneDrive\\Desktop");
	fileChooser.setDialogTitle("Save painting");
	FileNameExtensionFilter bin = new FileNameExtensionFilter(".bin", "bin");
	fileChooser.setAcceptAllFileFilterUsed(false);
	fileChooser.setFileFilter(bin);
	
	int result = fileChooser.showOpenDialog(null);
	if (result == JFileChooser.APPROVE_OPTION) {
		String fileName = fileChooser.getSelectedFile() + ".bin";
		FileChooser fileCh = new FileDrawing();
		FileManager fileManager = new FileManager(fileCh);
		File file = new File(fileName);
		fileManager.save(model, file);
		System.out.println("Draw saved successfully!");
	} 
}

private void saveLogListToFile(ArrayList<String> saveLogList, File file) {
    try (FileWriter fw = new FileWriter(file)) {
        Iterator<String> it = saveLogList.iterator();
        while (it.hasNext()) {
            fw.write(it.next() + "\n");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void saveLog() {
    fileChooser = new JFileChooser("C:\\Users\\jocak\\OneDrive\\Desktop");
    fileChooser.setDialogTitle("Save log");
    FileNameExtensionFilter txt = new FileNameExtensionFilter(".txt", "txt");
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.setFileFilter(txt);

    int result = fileChooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
        String fileName = fileChooser.getSelectedFile().getAbsolutePath() + ".txt";
        FileChooser fileCh = new FileLog();
        FileManager fileManager = new FileManager(fileCh);
        File file = new File(fileName);

        System.out.println("File path: " + file.getAbsolutePath()); // Dodato radi provere

        if (!saveLogList.isEmpty()) {
            saveLogListToFile(saveLogList, file);
            System.out.println("Log saved successfully!");
        } else {
            System.out.println("List is empty. Nothing to save.");
        }
    }
}
public void openDrawing() throws FileNotFoundException, ClassNotFoundException, IOException {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser = new JFileChooser("C:\\Users\\jocak\\OneDrive\\Desktop");
    fileChooser.setFileFilter(new FileNameExtensionFilter(".bin", "bin"));
    fileChooser.setDialogTitle("Open drawing");

    int result = fileChooser.showOpenDialog(null);

    if (result == JFileChooser.APPROVE_OPTION) {
        File drawingToLoad = fileChooser.getSelectedFile();
        loadDrawing(drawingToLoad);
        System.out.println("Open draw successfully");
    }
}

@SuppressWarnings("unchecked")
public void loadDrawing(File drawingToLoad) throws FileNotFoundException, IOException, ClassNotFoundException {
    this.frame.getTextArea().setText("");

    if (!drawingToLoad.exists()) {
        System.out.println("Fajl ne postoji: " + drawingToLoad.getAbsolutePath());
        return;
    }

    try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(drawingToLoad))) {
        Object obj = objectInputStream.readObject();
        if (obj instanceof ArrayList<?>) {
            this.model.getShapesList().addAll((ArrayList<Shape>) obj);
        }
    } catch (InvalidClassException | SocketTimeoutException | EOFException | ClassNotFoundException ice) {
        ice.printStackTrace();
    } catch (IOException exc) {
        exc.printStackTrace();
    }

    this.frame.getView().repaint();
}
public void openLog() {
    fileChooser = new JFileChooser("C:\\Users\\jocak\\OneDrive\\Desktop");
    fileChooser.setDialogTitle("Open log");
    FileNameExtensionFilter txt = new FileNameExtensionFilter(".txt", "txt");
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.setFileFilter(txt);

    int result = fileChooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
        File chosenFile = fileChooser.getSelectedFile();
        System.out.println("Chosen file path: " + chosenFile.getAbsolutePath());

        if (chosenFile.exists()) {
            
            readLogFromFile(chosenFile);
            System.out.println("Log opened successfully!");
            frame.loadNext.setEnabled(true);
            
        } else {
            System.out.println("Chosen file does not exist.");
        }
    }
}
public void readLogLine() {
	System.out.println(commandList.size());
	if(commandList.size() != 0 ) {
		System.out.println(commandList.get(0));
		loadNext(commandList.get(0));
		commandList.remove(0);
	}
	else {
		frame.getLoadNext().setEnabled(false);
		JOptionPane.showMessageDialog(null, "Log loaded successfully");
	}
}

private void readLogFromFile(File file) {
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;

        while ((line = reader.readLine()) != null) {
            
            commandList.add(line);
            System.out.println(line);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
public void loadNext(String logLine) {
	
	String[] parts = logLine.split(" : ");
	String operation = parts[0];

	if ("Selected".equals(operation)) {
	    handleSelection(parts);
	} else if ("Updated".equals(operation)) {
	    handleUpdate(parts);
	} else if ("Deselected".equals(operation)) {
	    handleDeselection(parts);
	}else if ("Redo".equals(operation)) {
	    handleRedo(parts);
	} else if ("Undo".equals(operation)) {
	    handleUndo(parts);
	} 
	else if ("Moved to front".equals(operation)) {
	    handleToFront(parts);
	} else if ("Moved to back".equals(operation)) {
	    handleToBack(parts);
	} else if ("Bringed to front".equals(operation)) {
	    handleBringToFront(parts);
	} else if ("Bringed to back".equals(operation)) {
	    handleBringToBack(parts);
	} else if ("Removed".equals(operation)) {
	    handleRemoved(parts);
	} else if ("Added".equals(operation)) {
	    handleAdded(parts);
	}  else {
	    
	}
	
}
public Shape parseShape(String[] parts) {
	String shapeType = parts[1];
	System.out.println(shapeType);
	String[] result = shapeType.split(" ");
	System.out.println(result[0]);
	System.out.println(result[1]);
	System.out.println(result[2]);
	Shape shape = null;
	
	if("Point".equals(result[0])) {
		 int x = Integer.parseInt(result[1].substring(3, result[1].length() - 1));
	
	    int y = Integer.parseInt(result[2].substring(2, result[2].length() - 1));
	
	    shape = new Point(x, y);
	
	}
	else if("Line".equals(result[0])) {
	   	int startX = Integer.parseInt(result[2].split(",")[0].split("=")[1]);

	   	int startY = Integer.parseInt(result[3].split(",")[0].split("=")[1].replace("]", "").replace(",", ""));

	    Point startPoint = new Point(startX, startY);


	   
	    int endX = Integer.parseInt(result[5].split(",")[0].split("=")[1]);
	    int endY = Integer.parseInt(result[6].split(",")[0].split("=")[1].replace("]", "").replace(",", ""));
	    Point endPoint = new Point(endX, endY);


	    
	    int color = Integer.parseInt(result[7].split("=")[1].replace("]", ""));
	    Color lineColor = new Color(color, true);


	   
	   shape = new Line(startPoint, endPoint, lineColor, false);
		
	
	}
	else if("Circle".equals(result[0])) {
		int startX = Integer.parseInt(result[2].split(",")[0].split("=")[1]);

	   	int startY = Integer.parseInt(result[3].split(",")[0].split("=")[1].replace("]", "").replace(",", ""));

	    Point center = new Point(startX, startY);

	    
	    int radius = Integer.parseInt(result[4].split(",")[0].split("=")[1]);
	    
	    int iColor = Integer.parseInt(result[6].split("=")[1].replace(",", ""));
	    Color innerColor = new Color(iColor, true);
	    
	    int bColor = Integer.parseInt(result[8].split("=")[1].replace("]", ""));
	    Color borderColor = new Color(bColor, true);
	
		shape = new Circle(center, radius,  borderColor, innerColor, false);
	}
	else if("Rectangle".equals(result[0])) {
		int startX = Integer.parseInt(result[2].split(",")[0].split("=")[1]);

	   	int startY = Integer.parseInt(result[3].split(",")[0].split("=")[1].replace("]", "").replace(",", ""));
	    Point upperLeftPoint = new Point(startX, startY);
	    
	    int height = Integer.parseInt(result[4].split(",")[0].split("=")[1]);
	    
	    int width = Integer.parseInt(result[5].split(",")[0].split("=")[1]);

	    
	    int iColor = Integer.parseInt(result[7].split("=")[1].replace(",", ""));
	    Color innerColor = new Color(iColor, true);
	    
	    int bColor = Integer.parseInt(result[9].split("=")[1].replace("]", ""));
	    Color borderColor = new Color(bColor, true);

		
		shape = new Rectangle(upperLeftPoint, height, width,  borderColor, innerColor, false);
	}
	else if("Donut".equals(result[0])) {
		int startX = Integer.parseInt(result[2].split(",")[0].split("=")[1]);

	   	int startY = Integer.parseInt(result[3].split(",")[0].split("=")[1].replace("]", "").replace(",", ""));

	    Point center = new Point(startX, startY);

	    
	    int radius = Integer.parseInt(result[4].split(",")[0].split("=")[1]);

	    
	    int innerRadius = Integer.parseInt(result[5].split(",")[0].split("=")[1]);

	    
	    int iColor = Integer.parseInt(result[7].split("=")[1].replace(",", ""));
	    Color innerColor = new Color(iColor, true);

	    
	    int bColor = Integer.parseInt(result[9].split("=")[1].replace("]", ""));
	    Color borderColor = new Color(bColor, true);
	
	    shape = new Donut(center, radius, innerRadius, borderColor, innerColor, false);
	    
	}
	else if("Hexagon".equals(result[0])) {
		int startX = Integer.parseInt(result[2].split(",")[0].split("=")[1]);

	   	int startY = Integer.parseInt(result[3].split(",")[0].split("=")[1].replace("]", "").replace(",", ""));

	    Point center = new Point(startX, startY);
	    
	    int radius = Integer.parseInt(result[4].split(",")[0].split("=")[1]);

	    
	    int iColor = Integer.parseInt(result[6].split("=")[1].replace(",", ""));
	    Color innerColor = new Color(iColor, true);

	    
	    int bColor = Integer.parseInt(result[8].split("=")[1].replace("]", ""));
	    Color borderColor = new Color(bColor, true);

		shape = new HexagonAdapter(center, radius, false,  borderColor, innerColor);
	}
	return shape;
	
}
public void handleAdded(String[] parts) {
	
	
	Shape shape = (Shape) parseShape(parts);
	
	command = new AddShapeCommand(model,shape);
	command.execute();	
			
	this.frame.getTextArea().append(command.toString());
	
	undoCounter++;
	redoCounter--;
	
	undoStack.push(command);
	redoStack.clear();
		    
	frame.repaint();
	
}
private void handleRemoved(String[] parts) {
	Shape removeShape = this.getSelectedShapesList().get(0);
	
	for (int i = 0; i < model.getShapesList().size(); i++) {
		if ((model.getShapesList().get(i).toString()).equals(removeShape.toString())) {

			command = new RemoveShapeCommand(this.model, this.model.getShapesList().get(i), i);
			command.execute();		
			
			this.selectedShapesList.remove(removeShape);				
			this.undoShapesList.add(removeShape);
			
			this.frame.getTextArea().append(command.toString());
			
			undoCounter++;
			redoCounter--;
			
			undoStack.push(command);
			redoStack.clear();
				
			frame.repaint();
			
			}
		}
	
}

private void handleBringToBack(String[] parts) {
	Shape shape = this.selectedShapesList.get(0);
	Command bringToBackCommand = new BringToBackCommand(this.model.getShapesList(), shape);
	bringToBackCommand.execute();
	frame.getTextArea().append(bringToBackCommand.toString());
	
	undoCounter++;
	redoCounter--;
	
	undoStack.push(bringToBackCommand);
	redoStack.clear();
	
	frame.repaint();
	
}

private void handleBringToFront(String[] parts) {
	Shape shape = this.selectedShapesList.get(0);
	Command bringToFrontCommand = new BringToFrontCommand(this.model.getShapesList(), shape);
	bringToFrontCommand.execute();
	this.frame.getTextArea().append(bringToFrontCommand.toString());
	
	undoCounter++;
	redoCounter--;
	
	undoStack.push(bringToFrontCommand);
	redoStack.clear();
	
	frame.repaint();
	
}

private void handleToBack(String[] parts) {
	Shape shape = this.selectedShapesList.get(0);
	Command toBackCommand = new ToBackCommand(this.model.getShapesList(), shape);
	toBackCommand.execute();
	this.frame.getTextArea().append(toBackCommand.toString());
	
	undoCounter++;
	redoCounter--;
	
	undoStack.push(toBackCommand);
	redoStack.clear();
	
	frame.repaint();
	
}

private void handleToFront(String[] parts) {


	Shape shape = this.selectedShapesList.get(0);
	Command toFrontCommand = new ToFrontCommand(this.model.getShapesList(), shape);
	toFrontCommand.execute();
	this.frame.getTextArea().append(toFrontCommand.toString());
	
	undoCounter++;
	redoCounter--;
	
	undoStack.push(toFrontCommand);
	redoStack.clear();
	
	frame.repaint();
	
}

private void handleUndo(String[] parts) {
	 if (!undoStack.isEmpty()) {

		command = undoStack.peek();
		String shapeType = parts[2];

		String[] result = shapeType.split(" ");

		
		Shape selectedShape =null;
		if("Point".equals(result[0])) {
			int x = Integer.parseInt(result[1].substring(3, result[1].length() - 1));
		    int y = Integer.parseInt(result[2].substring(2, result[2].length() - 1));
	
		    selectedShape = new Point(x, y);
		
		}
		else if("Line".equals(result[0])) {
		   	int startX = Integer.parseInt(result[2].split(",")[0].split("=")[1]);
		   	int startY = Integer.parseInt(result[3].split(",")[0].split("=")[1].replace("]", "").replace(",", ""));
		    Point startPoint = new Point(startX, startY);
	
		    int endX = Integer.parseInt(result[5].split(",")[0].split("=")[1]);
		    int endY = Integer.parseInt(result[6].split(",")[0].split("=")[1].replace("]", "").replace(",", ""));
		    Point endPoint = new Point(endX, endY);
	
		    int color = Integer.parseInt(result[7].split("=")[1].replace("]", "").trim());
		    Color lineColor = new Color(color, true);
	
		    selectedShape = new Line(startPoint, endPoint, lineColor, false);
	
		}
		else if("Circle".equals(result[0])) {
			int startX = Integer.parseInt(result[2].split(",")[0].split("=")[1]);
		   	int startY = Integer.parseInt(result[3].split(",")[0].split("=")[1].replace("]", "").replace(",", ""));
		    Point center = new Point(startX, startY);
	
		    int radius = Integer.parseInt(result[4].split(",")[0].split("=")[1]);
	
		    int iColor = Integer.parseInt(result[6].split("=")[1].replace(",", "").trim());
		    Color innerColor = new Color(iColor, true);
	
		    int bColor = Integer.parseInt(result[8].split("=")[1].replace("]", "").trim());
		    Color borderColor = new Color(bColor, true);
	
			selectedShape = new Circle(center, radius,  borderColor, innerColor, false);
		}
		else if("Rectangle".equals(result[0])) {
			int startX = Integer.parseInt(result[2].split(",")[0].split("=")[1]);
		   	int startY = Integer.parseInt(result[3].split(",")[0].split("=")[1].replace("]", "").replace(",", ""));
		    Point upperLeftPoint = new Point(startX, startY);
		    
		    int height = Integer.parseInt(result[4].split(",")[0].split("=")[1]);
		    
		    int width = Integer.parseInt(result[5].split(",")[0].split("=")[1]);
		    
		    int iColor = Integer.parseInt(result[7].split("=")[1].replace(",", "").trim());
		    Color innerColor = new Color(iColor, true);
		    
		    int bColor = Integer.parseInt(result[9].split("=")[1].replace("]", "").trim());
		    Color borderColor = new Color(bColor, true);
			
		    selectedShape = new Rectangle(upperLeftPoint, height, width,  borderColor, innerColor, false);
		}
		else if("Donut".equals(result[0])) {
			int startX = Integer.parseInt(result[2].split(",")[0].split("=")[1]);
		   	int startY = Integer.parseInt(result[3].split(",")[0].split("=")[1].replace("]", "").replace(",", ""));
		    Point center = new Point(startX, startY);
		    
		    int radius = Integer.parseInt(result[4].split(",")[0].split("=")[1]);
		    
		    int innerRadius = Integer.parseInt(result[5].split(",")[0].split("=")[1]);
		    
		    int iColor = Integer.parseInt(result[7].split("=")[1].replace(",", "").trim());
		    Color innerColor = new Color(iColor, true);
		    
		    int bColor = Integer.parseInt(result[9].split("=")[1].replace("]", "").trim());
		    Color borderColor = new Color(bColor, true);
		
		    selectedShape = new Donut(center, radius, innerRadius, borderColor, innerColor, false);
		    
		}
		else if("Hexagon".equals(result[0])) {
			int startX = Integer.parseInt(result[2].split(",")[0].split("=")[1]);
		   	int startY = Integer.parseInt(result[3].split(",")[0].split("=")[1].replace("]", "").replace(",", ""));
		    Point center = new Point(startX, startY);
		    
		    int radius = Integer.parseInt(result[4].split(",")[0].split("=")[1]);
		    
		    int iColor = Integer.parseInt(result[6].split("=")[1].replace(",", "").trim());
		    Color innerColor = new Color(iColor, true);
		    
		    int bColor = Integer.parseInt(result[8].split("=")[1].replace("]", "").trim());
		    Color borderColor = new Color(bColor, true);
		
		    selectedShape = new HexagonAdapter(center, radius, false,  borderColor, innerColor);
		}
		
	

	        if(command instanceof SelectShapeCommand) {

				for (int i = 0; i < model.getShapesList().size(); i++) {
					if ((model.getShapesList().get(i).toString()).equals(selectedShape.toString())) {
						
						model.getShapesList().get(i).setSelected(false);						
						
						for (int j = 0; j < this.getSelectedShapesList().size(); j++) {
							if ((this.getSelectedShapesList().get(j).toString().equals(model.getShapesList().get(i).toString()))) {
								this.getSelectedShapesList().remove(j);
							}
						}
						
						DeselectShapeCommand deselect = new DeselectShapeCommand(frame.getController(), selectedShape);
						deselect.execute();
						
				        this.frame.getTextArea().append("Undo : " + undoStack.peek().toString());
						
						undoCounter--;
						redoCounter++;
						
						undoStack.pop();
						redoStack.push(deselect);
						
						frame.repaint();
						
						}
					}
	        }
	        else if(command instanceof DeselectShapeCommand) {
	        	
				for (int i = 0; i < model.getShapesList().size(); i++) {
					if ((model.getShapesList().get(i).toString()).equals(selectedShape.toString())) {
						
						model.getShapesList().get(i).setSelected(true);
						
						SelectShapeCommand  select = new SelectShapeCommand(frame.getController(), selectedShape);
						select.execute();
						
				        this.frame.getTextArea().append("Undo : " + undoStack.peek().toString());
						
						undoCounter--;
						redoCounter++;
						
						undoStack.pop();
						redoStack.push(select);
						
						frame.repaint();
						
						}
					}
	        }
	        else {
	        	if(command instanceof RemoveShapeCommand) {

	        			command.unexecute();
	        			this.redoShapesList.add(this.undoShapesList.get(this.undoShapesList.size() - 1));
	        			this.selectedShapesList.add(this.undoShapesList.get(this.undoShapesList.size()-1));
	        			this.undoShapesList.remove(this.undoShapesList.size()-1);
	        			this.frame.getTextArea().append("Undo : " + undoStack.peek().toString());
	        			saveLogList.add(command.toString());
	        			redoCounter++;
	        			undoCounter--;
	        			undoStack.pop();
	        			redoStack.push(command);
	        			command = undoStack.peek();
	        			

	        	}else {
	        		command.unexecute();
	        		this.frame.getTextArea().append("Undo : " + undoStack.peek().toString());
	        		saveLogList.add(command.toString());
	        		redoCounter++;
	        		undoCounter--;
	        		undoStack.pop();
	        		redoStack.push(command);
	        	}
	        	frame.repaint();

		redoStack.push(command);
				
	        }
	        
	 }
	
}
private void handleRedo(String[] parts) {
	
	if (!redoStack.isEmpty()) {
	System.out.println("Redo " + redoStack.peek());
	command = redoStack.peek();
	String shapeType = parts[2];

	String[] result = shapeType.split(" ");
	
	Shape selectedShape =null;
	if("Point".equals(result[0])) {
		int x = Integer.parseInt(result[1].substring(3, result[1].length() - 1));
	    int y = Integer.parseInt(result[2].substring(2, result[2].length() - 1));

	    selectedShape = new Point(x, y);
	
	}
	else if("Line".equals(result[0])) {
	   	int startX = Integer.parseInt(result[2].split(",")[0].split("=")[1]);
	   	int startY = Integer.parseInt(result[3].split(",")[0].split("=")[1].replace("]", "").replace(",", ""));
	    Point startPoint = new Point(startX, startY);

	    int endX = Integer.parseInt(result[5].split(",")[0].split("=")[1]);
	    int endY = Integer.parseInt(result[6].split(",")[0].split("=")[1].replace("]", "").replace(",", ""));
	    Point endPoint = new Point(endX, endY);

	    int color = Integer.parseInt(result[7].split("=")[1].replace("]", "").trim());
	    Color lineColor = new Color(color, true);

	    selectedShape = new Line(startPoint, endPoint, lineColor, false);

	}
	else if("Circle".equals(result[0])) {
		int startX = Integer.parseInt(result[2].split(",")[0].split("=")[1]);
	   	int startY = Integer.parseInt(result[3].split(",")[0].split("=")[1].replace("]", "").replace(",", ""));
	    Point center = new Point(startX, startY);

	    int radius = Integer.parseInt(result[4].split(",")[0].split("=")[1]);

	    int iColor = Integer.parseInt(result[6].split("=")[1].replace(",", "").trim());
	    Color innerColor = new Color(iColor, true);

	    int bColor = Integer.parseInt(result[8].split("=")[1].replace("]", "").trim());
	    Color borderColor = new Color(bColor, true);

		selectedShape = new Circle(center, radius,  borderColor, innerColor, false);
	}
	else if("Rectangle".equals(result[0])) {
		int startX = Integer.parseInt(result[2].split(",")[0].split("=")[1]);
	   	int startY = Integer.parseInt(result[3].split(",")[0].split("=")[1].replace("]", "").replace(",", ""));
	    Point upperLeftPoint = new Point(startX, startY);
	    
	    int height = Integer.parseInt(result[4].split(",")[0].split("=")[1]);
	    
	    int width = Integer.parseInt(result[5].split(",")[0].split("=")[1]);
	    
	    int iColor = Integer.parseInt(result[7].split("=")[1].replace(",", "").trim());
	    Color innerColor = new Color(iColor, true);
	    
	    int bColor = Integer.parseInt(result[9].split("=")[1].replace("]", "").trim());
	    Color borderColor = new Color(bColor, true);
		
	    selectedShape = new Rectangle(upperLeftPoint, height, width,  borderColor, innerColor, false);
	}
	else if("Donut".equals(result[0])) {
		int startX = Integer.parseInt(result[2].split(",")[0].split("=")[1]);
	   	int startY = Integer.parseInt(result[3].split(",")[0].split("=")[1].replace("]", "").replace(",", ""));
	    Point center = new Point(startX, startY);
	    
	    int radius = Integer.parseInt(result[4].split(",")[0].split("=")[1]);
	    
	    int innerRadius = Integer.parseInt(result[5].split(",")[0].split("=")[1]);
	    
	    int iColor = Integer.parseInt(result[7].split("=")[1].replace(",", "").trim());
	    Color innerColor = new Color(iColor, true);
	    
	    int bColor = Integer.parseInt(result[9].split("=")[1].replace("]", "").trim());
	    Color borderColor = new Color(bColor, true);
	
	    selectedShape = new Donut(center, radius, innerRadius, borderColor, innerColor, false);
	    
	}
	else if("Hexagon".equals(result[0])) {
		int startX = Integer.parseInt(result[2].split(",")[0].split("=")[1]);
	   	int startY = Integer.parseInt(result[3].split(",")[0].split("=")[1].replace("]", "").replace(",", ""));
	    Point center = new Point(startX, startY);
	    
	    int radius = Integer.parseInt(result[4].split(",")[0].split("=")[1]);
	    
	    int iColor = Integer.parseInt(result[6].split("=")[1].replace(",", "").trim());
	    Color innerColor = new Color(iColor, true);
	    
	    int bColor = Integer.parseInt(result[8].split("=")[1].replace("]", "").trim());
	    Color borderColor = new Color(bColor, true);
	
	    selectedShape = new HexagonAdapter(center, radius, false,  borderColor, innerColor);
	}
	
	if(command instanceof SelectShapeCommand) {
		for (int i = 0; i < model.getShapesList().size(); i++) {
			if ((model.getShapesList().get(i).toString()).equals(selectedShape.toString())) {
				
				DeselectShapeCommand  deselect = new DeselectShapeCommand(frame.getController(), selectedShape);
				deselect.execute();
				
				for (int j = 0; j < this.getSelectedShapesList().size(); j++) {
					if ((this.getSelectedShapesList().get(j).toString().equals(model.getShapesList().get(i).toString()))) {
						this.getSelectedShapesList().remove(j);
					}
				}
				
				model.getShapesList().get(i).setSelected(false);
				
				this.frame.getTextArea().append("Redo : " + redoStack.peek().toString());
				
				undoCounter++;
				redoCounter--;
				
				redoStack.pop();
				undoStack.push(deselect);	
				
				frame.repaint();
				
				}
			}
	}
	else if(command instanceof DeselectShapeCommand) {
		for (int i = 0; i < model.getShapesList().size(); i++) {
			if ((model.getShapesList().get(i).toString()).equals(selectedShape.toString())) {
				
				model.getShapesList().get(i).setSelected(true);
				
				SelectShapeCommand  select = new SelectShapeCommand(frame.getController(), selectedShape);
				select.execute();
				
				this.frame.getTextArea().append("Redo : " + redoStack.peek().toString());

				undoCounter++;
				redoCounter--;
				
				redoStack.pop();
				undoStack.push(select);	
				
				frame.repaint();
				
				}
			}
		
	}
	else {
		if(command instanceof RemoveShapeCommand) {

    			command.execute();
    			this.undoShapesList.add(this.redoShapesList.get(this.redoShapesList.size()-1));
    			this.selectedShapesList.remove(this.redoShapesList.get(this.redoShapesList.size()-1));
    			this.redoShapesList.remove(this.redoShapesList.size()-1);
    			this.frame.getTextArea().append("Redo : " + redoStack.peek().toString());
    			saveLogList.add(command.toString());
    			undoCounter++;
    			redoCounter--;
    			redoStack.pop();
    			undoStack.push(command);
    			if(!redoStack.isEmpty()) {
    				command = redoStack.peek();
    			}else {
    				command = null;
    			}

    		
    	}
    	else {
    		command.execute();
    		this.frame.getTextArea().append("Redo : " + redoStack.peek().toString());
    		saveLogList.add(command.toString());
    		undoCounter++;
    		redoCounter--;
    		redoStack.pop();
    		undoStack.push(command);
    	}
    	frame.repaint();

		
	}
	
	}
	else {
		System.out.println("Stek je prazan");
	}

	
}
	


private void handleDeselection(String[] parts) {
	Shape selectedShape = parseShape(parts);
	for (int i = 0; i < model.getShapesList().size(); i++) {
		if ((model.getShapesList().get(i).toString()).equals(selectedShape.toString())) {
			
			DeselectShapeCommand  deselect = new DeselectShapeCommand(frame.getController(), selectedShape);
			deselect.execute();

			for (int j = 0; j < this.getSelectedShapesList().size(); j++) {
				if ((this.getSelectedShapesList().get(j).toString().equals(model.getShapesList().get(i).toString()))) {
					this.getSelectedShapesList().remove(j);
				}
			}
			model.getShapesList().get(i).setSelected(false);
			
			this.frame.getTextArea().append(deselect.toString());
			
			undoCounter++;
			redoCounter--;
			
			undoStack.push(deselect);
			redoStack.clear();
			
			frame.repaint();
			
			}
		}
	
}

private void handleUpdate(String[] parts) {

	String shapes = parts[1];
	String[] result = shapes.split(" -> ");
	System.out.println(result[0]);
	System.out.println(result[1]);
	String[] shapeName = result[0].split(" ");
	
	
	
	Shape newShape = parseShape(result);
	System.out.println(newShape);
	
	if("Point".equals(shapeName[0])) {
			
			UpdatePointCommand updatePointCmd = new UpdatePointCommand((Point)this.getSelectedShapesList().get(0), (Point)newShape);
			updatePointCmd.execute();
			
			this.frame.getTextArea().append(updatePointCmd.toString());
			
			undoCounter++;
			redoCounter--;
			
			undoStack.push(updatePointCmd);
			redoStack.clear();

			frame.repaint();	
		
	} 
	else if("Line".equals(shapeName[0])) {
		
		UpdateLineCommand updateLineCmd = new UpdateLineCommand((Line)this.getSelectedShapesList().get(0), (Line)newShape);
		updateLineCmd.execute();

		this.frame.getTextArea().append(updateLineCmd.toString());
		
		undoCounter++;
		redoCounter--;
		
		undoStack.push(updateLineCmd);
		redoStack.clear();
		
		frame.repaint();
	}
	else if("Circle".equals(shapeName[0])) {
		
		UpdateCircleCommand updateCircleCmd = new UpdateCircleCommand((Circle)this.getSelectedShapesList().get(0), (Circle)newShape);
		updateCircleCmd.execute();
		
		this.frame.getTextArea().append(updateCircleCmd.toString());
		
		undoCounter++;
		redoCounter--;
		
		undoStack.push(updateCircleCmd);
		redoStack.clear();

		frame.repaint();	
	}
	else if("Rectangle".equals(shapeName[0])) {
		
		UpdateRectangleCommand updateRectangleCmd = new UpdateRectangleCommand((Rectangle)this.getSelectedShapesList().get(0), (Rectangle)newShape);
		updateRectangleCmd.execute();
		
		this.frame.getTextArea().append(updateRectangleCmd.toString());
		
		undoCounter++;
		redoCounter--;
		
		undoStack.push(updateRectangleCmd);
		redoStack.clear();

		frame.repaint();
	}
	else if("Donut".equals(shapeName[0])) {
		
		UpdateDonutCommand updateDonutCmd = new UpdateDonutCommand((Donut)this.getSelectedShapesList().get(0), (Donut)newShape);
		updateDonutCmd.execute();
		
		this.frame.getTextArea().append(updateDonutCmd.toString());
		
		undoCounter++;
		redoCounter--;
		
		undoStack.push(updateDonutCmd);
		redoStack.clear();

		frame.repaint();
	}
	else if("Hexagon".equals(shapeName[0])) {
		
		UpdateHexagonCommand updateHexagonCmd = new UpdateHexagonCommand((HexagonAdapter)this.getSelectedShapesList().get(0), (HexagonAdapter)newShape);
		updateHexagonCmd.execute();

		this.frame.getTextArea().append(updateHexagonCmd.toString());
		
		undoCounter++;
		redoCounter--;
		
		undoStack.push(updateHexagonCmd);
		redoStack.clear();
		
		frame.repaint();
	}
}
	 
	


private void handleSelection(String[] parts) {

	Shape selectedShape = parseShape(parts);
	
	for (int i = 0; i < model.getShapesList().size(); i++) {
		
		if ((model.getShapesList().get(i).toString()).equals(selectedShape.toString())) {
			
			SelectShapeCommand select = new SelectShapeCommand(frame.getController(), selectedShape);
			
			model.getShapesList().get(i).setSelected(true);
			this.getSelectedShapesList().add(this.getSelectedShapesList().size(), model.getShapesList().get(i));

			this.frame.getTextArea().append(select.toString());
			
			undoCounter++;
			redoCounter--;
			
			undoStack.push(select);
			redoStack.clear();
									
			frame.repaint();
			}
		}
	
}

public ArrayList<Shape> getSelectedShapesList() {
	return selectedShapesList;
}

public void setSelectedShapesList(ArrayList<Shape> selectedShapesList) {
	this.selectedShapesList = selectedShapesList;
}


	
	
}

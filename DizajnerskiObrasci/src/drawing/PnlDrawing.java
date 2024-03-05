package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import shapes.Circle;
import shapes.Donut;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;

public class PnlDrawing extends JPanel{ /*
	private ArrayList<Shape> alstShape = new ArrayList<>();
	private ArrayList<Point> alstPoint = new ArrayList<>();
	
	boolean isInit;
	int indexOfShape;
	
	public PnlDrawing() {
		isInit = true;
		indexOfShape = -1;
		setBackground(Color.WHITE);
		addMouseListener(this);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if (isInit) { isInit = false; return; }
		
		Iterator<Shape> it = alstShape.iterator();
		
		while (it.hasNext()) {
			it.next().draw(g);
		}
	}

	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		indexOfShape = -1;
		Iterator<Shape> it = alstShape.iterator();
		
		while (it.hasNext()) {
			it.next().setSelected(false);
		}
		
		if (FrmDrawing.isPoint) {
					
					alstPoint.clear();
					alstShape.add(new Point(e.getX(), e.getY(), Color.BLACK, false));
					repaint();
					
		} else if (FrmDrawing.isLine) {

				alstPoint.add(new Point(e.getX(), e.getY()));
				
				if (alstPoint.size() == 2) {
				
				Point startPoint = new Point(alstPoint.get(0).getX(), alstPoint.get(0).getY());
				Point endPoint = new Point(alstPoint.get(1).getX(), alstPoint.get(1).getY());
				
				alstShape.add(new Line(startPoint, endPoint, Color.BLACK, false));
				alstPoint.clear();
				repaint();
				
				}
				
		} else if (FrmDrawing.isRectangle) {
					Point upperLeftPoint = new Point(e.getX(), e.getY());
					DlgRectangle dlgRectangle = new DlgRectangle();
					dlgRectangle.setVisible(true);
			
			if (dlgRectangle.isOK) {
					int height = Integer.parseInt(dlgRectangle.getTxtHeight().getText());
					int width = Integer.parseInt(dlgRectangle.getTxtWidth().getText());
					Color color = dlgRectangle.getTxtColor().getBackground();
					Color innerColor = dlgRectangle.getTxtInnerColor().getBackground();
				
					alstShape.add(new Rectangle(upperLeftPoint, height, width, color, innerColor, false));
					repaint();
			}
		} else if (FrmDrawing.isCircle) {
					Point circleCenter = new Point(e.getX(), e.getY());
					DlgCircle dlgCircle = new DlgCircle();
			
					dlgCircle.setVisible(true);
			
			if (dlgCircle.isOK) {
					int radius = Integer.parseInt(dlgCircle.getTxtRadius().getText());
					Color color = dlgCircle.getTxtColor().getBackground();
					Color innerColor = dlgCircle.getTxtColor1().getBackground();
				
					alstShape.add(new Circle(circleCenter, radius, color, innerColor, false));
					repaint();
			}
		} else if (FrmDrawing.isDonut) {
					Point donutCenter = new Point(e.getX(), e.getY());
					DlgDonut dlgDonut = new DlgDonut();
			
					dlgDonut.setVisible(true);
			
			if (dlgDonut.isOk) {
					int radius = Integer.parseInt(dlgDonut.getTxtRadius().getText());
					int innerRadius = Integer.parseInt(dlgDonut.getTxtInnerRadius().getText());
					Color color = dlgDonut.getTxtColor().getBackground();
					Color innerColor = dlgDonut.getTxtInnerColor().getBackground();
				
					alstShape.add(new Donut(donutCenter, radius, innerRadius, color, innerColor, false));
					repaint();
			}
		} else if (FrmDrawing.isSelectMode) {
					ListIterator<Shape> sShape = alstShape.listIterator(alstShape.size());
			
			while (sShape.hasPrevious()) {
				Shape tempShape = sShape.previous();
				
				if (tempShape.contains(e.getX(), e.getY())) {
					indexOfShape = alstShape.indexOf(tempShape);
					
					alstShape.get(indexOfShape).setSelected(true);
					repaint();
					
					break;
				}
			}	
			repaint();
			
		} else if (FrmDrawing.isModifyMode) {
			Iterator<Shape> mShape = alstShape.iterator();
			
			while (mShape.hasNext()) {
				mShape.next().setSelected(false);
			}
			
			repaint();
			
		} else if (FrmDrawing.isRemoveMode) {
			ListIterator<Shape> rShape = alstShape.listIterator(alstShape.size());
			
			while (rShape.hasPrevious()) {
				Shape tempShape = rShape.previous();
				
				if (tempShape.contains(e.getX(), e.getY())) {
					alstShape.get(alstShape.indexOf(tempShape)).setSelected(true);
					
					repaint();
					
					int ch = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete", JOptionPane.YES_NO_OPTION);
					if (ch == JOptionPane.YES_OPTION) {
						alstShape.remove(alstShape.indexOf(tempShape));
					}
					
					break;
				}
			}
			
			repaint();
		}
		}
	
	

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void modifyShape() {

		if (indexOfShape >= 0) {
			Shape mShape = alstShape.get(indexOfShape);
			
			if (mShape instanceof Point) {
				DlgModPoint dlgModPoint = new DlgModPoint();
				dlgModPoint.getTxtX().setText(Integer.toString(((Point) mShape).getX()));
				dlgModPoint.getTxtY().setText(Integer.toString(((Point) mShape).getY()));
				dlgModPoint.setVisible(true);
							
				if (dlgModPoint.isOk) {
					Point newPoint = new Point();
					newPoint.setX(Integer.parseInt(dlgModPoint.getTxtX().getText()));
					newPoint.setY(Integer.parseInt(dlgModPoint.getTxtY().getText()));
					newPoint.setSelected(true);
					alstShape.set(alstShape.indexOf(mShape), newPoint);
					repaint();
					}

			} else if (mShape instanceof Line) { 
				DlgModLine dlgModLine = new DlgModLine();
				dlgModLine.getTxtX().setText(Integer.toString(((Line) mShape).startPoint.getX()));
				dlgModLine.getTxtY().setText(Integer.toString(((Line) mShape).startPoint.getY()));
				dlgModLine.getTxtX1().setText(Integer.toString(((Line) mShape).endPoint.getX()));
				dlgModLine.getTxtY1().setText(Integer.toString(((Line) mShape).endPoint.getY()));
				dlgModLine.setVisible(true);
							
				if (dlgModLine.isOk) {
					Point sPoint = new Point();
					Point ePoint = new Point();
					sPoint.setX(Integer.parseInt(dlgModLine.getTxtX().getText()));
					sPoint.setY(Integer.parseInt(dlgModLine.getTxtY().getText()));
					ePoint.setX(Integer.parseInt(dlgModLine.getTxtX1().getText()));
					ePoint.setY(Integer.parseInt(dlgModLine.getTxtY1().getText()));
					Line line = new Line(sPoint, ePoint, Color.BLACK, false);
					line.setSelected(true);
					
					alstShape.set(alstShape.indexOf(mShape), line);
					repaint();
					}
			} else if (mShape instanceof Rectangle) {
				DlgModRectangle dlgModRectangle = new DlgModRectangle();
				
				dlgModRectangle.getTxtX().setText(Integer.toString(((Rectangle) mShape).upperLeftPoint.getX()));
				dlgModRectangle.getTxtY().setText(Integer.toString(((Rectangle) mShape).upperLeftPoint.getY()));
				dlgModRectangle.getTextField().setText(Integer.toString(((Rectangle) mShape).getWidth()));
				dlgModRectangle.getTextField_1().setText(Integer.toString(((Rectangle) mShape).getHeight()));
				dlgModRectangle.getTxtColor().setBackground(mShape.getColor());
				dlgModRectangle.getTxtColor1().setBackground(((Rectangle) mShape).getInnerColor());
				
				dlgModRectangle.setVisible(true);
				
				if (dlgModRectangle.isOK) {
					Rectangle newRectangle = new Rectangle();
					
					Point upperLeftPoint = new Point();
					upperLeftPoint.setX(Integer.parseInt(dlgModRectangle.getTxtX().getText()));
					upperLeftPoint.setY(Integer.parseInt(dlgModRectangle.getTxtY().getText()));
					
					newRectangle.setUpperLeftPoint(upperLeftPoint);
					newRectangle.setHeight(Integer.parseInt(dlgModRectangle.getTextField().getText()));
					newRectangle.setWidth(Integer.parseInt(dlgModRectangle.getTextField_1().getText()));
					newRectangle.setColor(dlgModRectangle.getTxtColor().getBackground());
					newRectangle.setInnerColor(dlgModRectangle.getTxtColor1().getBackground());
					newRectangle.setSelected(true);
					
					alstShape.set(alstShape.indexOf(mShape), newRectangle);
				}
			} else if (mShape instanceof Donut) {
				DlgModDonut dlgModDonut = new DlgModDonut();
				
				dlgModDonut.getTxtX().setText(Integer.toString(((Donut) mShape).center.getX()));
				dlgModDonut.getTxtY().setText(Integer.toString(((Donut) mShape).center.getY()));	
				dlgModDonut.getTxtRadius().setText(Integer.toString(((Donut) mShape).getRadius()));
				dlgModDonut.getTxtInnerRadius().setText(Integer.toString(((Donut) mShape).getInnerRadius()));
				dlgModDonut.getTxtColor().setBackground(mShape.getColor());
				dlgModDonut.getTxtColor1().setBackground(((Donut) mShape).getInnerColor());
					
				dlgModDonut.setVisible(true);	
				
				if (dlgModDonut.isOk) {
					Donut newDonut = new Donut();
					
					Point center = new Point();
					center.setX(Integer.parseInt(dlgModDonut.getTxtX().getText()));
					center.setY(Integer.parseInt(dlgModDonut.getTxtY().getText()));
					
					newDonut.setCenter(center);
					newDonut.setRadius(Integer.parseInt(dlgModDonut.getTxtRadius().getText()));
					newDonut.setInnerRadius(Integer.parseInt(dlgModDonut.getTxtInnerRadius().getText()));
					newDonut.setColor(dlgModDonut.getTxtColor().getBackground());
					newDonut.setInnerColor(dlgModDonut.getTxtColor1().getBackground());
					newDonut.setSelected(true);
					
					alstShape.set(alstShape.indexOf(mShape), newDonut);
				}
			} else if (mShape instanceof Circle) {
				DlgModCircle dlgModCircle = new DlgModCircle();
				
				
				dlgModCircle.getTxtX().setText(Integer.toString(((Circle) mShape).getCenter().getX()));
				dlgModCircle.getTxtY().setText(Integer.toString(((Circle) mShape).getCenter().getY()));
				dlgModCircle.getTxtRadius().setText(Integer.toString(((Circle) mShape).getRadius()));
				dlgModCircle.getTxtColor().setBackground(mShape.getColor());
				dlgModCircle.getTxtInnerColor().setBackground(((Circle) mShape).getInnerColor());
				
				dlgModCircle.setVisible(true);
				
				if (dlgModCircle.isOk) {
					Circle newCircle = new Circle();
					
					Point newPoint = new Point();
					newPoint.setX(Integer.parseInt(dlgModCircle.getTxtX().getText()));
					newPoint.setY(Integer.parseInt(dlgModCircle.getTxtY().getText()));
					newPoint.setSelected(true);
					
					newCircle.setCenter(newPoint);
					newCircle.setRadius(Integer.parseInt(dlgModCircle.getTxtRadius().getText()));
					newCircle.setColor(dlgModCircle.getTxtColor().getBackground());
					newCircle.setInnerColor(dlgModCircle.getTxtInnerColor().getBackground());
					newCircle.setSelected(true);
					
					alstShape.set(alstShape.indexOf(mShape), newCircle);
				}
			}
			
			repaint();
		}
	}*/

}

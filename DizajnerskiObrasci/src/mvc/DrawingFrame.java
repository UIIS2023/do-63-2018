package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class DrawingFrame extends JFrame {
	
	private DrawingView view = new DrawingView();
	private DrawingController controller;

	
	static boolean isPoint;
	static boolean isLine;
	static boolean isCircle;
	static boolean isDonut;
	static boolean isRectangle;
	static boolean isSelectMode;
	static boolean isModifyMode;
	static boolean isRemoveMode;
	private JPanel pnlDrawing;
	private JPanel contentPane;
	private String select;
	private JButton btnBorderColor;
	private JButton btnInnerColor;
	public Color currentBorderColor = Color.black;
	public Color currentInnerColor = Color.white;
	public JTextArea textArea;
	public JButton btnRedo;
	public JButton btnUndo;
	public JButton btnToFront;
	public JButton btnToBack;
	public JButton btnBringToFront;
	public JButton btnBringToBack;
	public JButton btnModify;
	public JButton btnRemove;
	public JButton loadNext;
	
	public DrawingFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Jovana Karamarkovic IT63-2018");
		setBounds(100, 100, 1000, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Open drawing");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.openDrawing();
				
				}
				catch (ClassNotFoundException | IOException exc) {
					exc.printStackTrace();
					
				}
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Open log");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.openLog();
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Save drawing");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.saveDrawing();
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Save log");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.saveLog();
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		
		getContentPane().setLayout(new BorderLayout(0,0));

		view.setBackground(Color.white);
		contentPane.setBackground(new Color(255, 255, 255));
		
		JPanel pnlShape = new JPanel();
		getContentPane().add(pnlShape, BorderLayout.NORTH);
		
	
		
		JLabel lblShape = new JLabel("Click a shape for drawing:");
		pnlShape.add(lblShape);
		
		JButton btnPoint = new JButton("Point");
//		btnPoint.setBackground(Color.darkGray);
//		btnPoint.setForeground(Color.white);
		btnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select = "Point";
			}
		});
		pnlShape.add(btnPoint);
		
		JButton btnLine = new JButton("Line");
		btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select = "Line";
			}
		});
		pnlShape.add(btnLine);
		
		JButton btnCircle = new JButton("Circle");
		btnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select = "Circle";
			}
		});
		pnlShape.add(btnCircle);
		
		JButton btnRectangle = new JButton("Rectangle");
		btnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select = "Rectangle";
			}
		});
		pnlShape.add(btnRectangle);
		
		JButton btnDonut = new JButton("Donut");
		btnDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select = "Donut";
			}
		});
		pnlShape.add(btnDonut);
		
		JButton btnHexagon = new JButton("Hexagon");
		btnHexagon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select = "Hexagon";
			}
		});
		pnlShape.add(btnHexagon);
		
		
		
		btnBorderColor = new JButton("Border Color");
		btnBorderColor.setForeground(new Color(255, 255, 255));
			btnBorderColor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						currentBorderColor = JColorChooser.showDialog(null,"Chose a color.", currentBorderColor);
						btnBorderColor.setBackground(currentBorderColor);
					}
				});
		pnlShape.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));		
		btnBorderColor.setBackground(this.currentBorderColor);
		pnlShape.add(btnBorderColor);
				
		btnInnerColor = new JButton("Inner Color");
		btnInnerColor.setBackground(currentInnerColor);
			btnInnerColor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						currentInnerColor = JColorChooser.showDialog(null,"Chose a color", currentInnerColor);
						btnInnerColor.setBackground(currentInnerColor);
					}
				});
		pnlShape.add(btnInnerColor);
		
		
		JPanel pnlOptions = new JPanel();
		getContentPane().add(pnlOptions, BorderLayout.EAST);
		pnlOptions.setPreferredSize(new Dimension(300, 700));
		
		JLabel lblOptions = new JLabel("Options:");
		pnlOptions.add(lblOptions);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				select = "Select";
			}
		});
		pnlOptions.add(btnSelect);
		
		btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modifyShape();
				
				//((PnlDrawing) pnlDrawing).modifyShape();
			}
		});
		pnlOptions.add(btnModify);
		btnModify.setEnabled(false);
		
		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this shape?", "Remove", JOptionPane.YES_NO_OPTION);
				if(confirmation == JOptionPane.YES_OPTION) {
					controller.deleteShape(true);
				} 
				else {
					JOptionPane.showMessageDialog(null, "The shape was not removed");
				}
//				controller.deleteShape(true);
			}
		});
		pnlOptions.add(btnRemove);
		btnRemove.setEnabled(false);
		

		btnRedo = new JButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.redoFunc();
			}
		});
		pnlOptions.add(btnRedo);
		btnRedo.setEnabled(false);
		
		btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.undoFunc();
			}
		});
		pnlOptions.add(btnUndo);
		btnUndo.setEnabled(false);
		
		btnToFront = new JButton("To Front");
		btnToFront.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
				
			}
		});
		pnlOptions.add(btnToFront);
		btnToFront.setEnabled(false);
		
		btnToBack = new JButton("To Back");
		btnToBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
				
			}
		});
		pnlOptions.add(btnToBack);
		btnToBack.setEnabled(false);
		
		btnBringToFront = new JButton("Bring To Front");
		btnBringToFront.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
				
			}
		});
		pnlOptions.add(btnBringToFront);
		btnBringToFront.setEnabled(false);
		
		btnBringToBack = new JButton("Bring To Back");
		btnBringToBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
				
			}
		});
		pnlOptions.add(btnBringToBack);
		btnBringToBack.setEnabled(false);
		
		
		JPanel panelSouth = new JPanel();
		panelSouth.setPreferredSize(new Dimension(250, 200));
		panelSouth.setLayout(null);
		getContentPane().add(panelSouth, BorderLayout.WEST);
				
		panelSouth.setBackground(new Color(0xC0C0C0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 36, 195, 300);
		panelSouth.add(scrollPane);
		
				textArea = new JTextArea();
				textArea.setBackground(new Color(255, 255, 255));
				textArea.setForeground(new Color(0, 0, 0));
				scrollPane.setViewportView(textArea);
				
				JLabel lblText = new JLabel("Commands:");
				scrollPane.setColumnHeaderView(lblText);
				
				loadNext= new JButton("Load next");
				loadNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.readLogLine();
					}
				});
				loadNext.setBounds(63, 352, 115, 23);
				loadNext.setEnabled(false);
				panelSouth.add(loadNext);
		
		getContentPane().add(view, BorderLayout.CENTER);
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		
	}
	
	
	public JPanel getPnlDrawing() {
		return pnlDrawing;
	}

	public void setPnlDrawing(JPanel pnlDrawing) {
		this.pnlDrawing = pnlDrawing;
	}
	
	public DrawingView getView() {
		return this.view;
	}
	
	public void setController(DrawingController controller)  {
		this.controller = controller;
	}


	public String getSelect() {
		return select;
	}


	public void setSelect(String select) {
		this.select = select;
	}


	public Color getCurrentBorderColor() {
		return currentBorderColor;
	}


	public void setCurrentBorderColor(Color currentBorderColor) {
		this.currentBorderColor = currentBorderColor;
	}


	public Color getCurrentInnerColor() {
		return currentInnerColor;
	}


	public void setCurrentInnerColor(Color currentInnerColor) {
		this.currentInnerColor = currentInnerColor;
	}


	public JTextArea getTextArea() {
		return textArea;
	}


	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}


	public JButton getBtnRedo() {
		return btnRedo;
	}


	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}


	public JButton getBtnUndo() {
		return btnUndo;
	}


	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}


	public JButton getBtnToFront() {
		return btnToFront;
	}

	

	public void setBtnToFront(JButton btnToFront) {
		this.btnToFront = btnToFront;
	}


	public JButton getBtnToBack() {
		return btnToBack;
	}


	public void setBtnToBack(JButton btnToBack) {
		this.btnToBack = btnToBack;
	}


	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}


	public void setBtnBringToFront(JButton btnBringToFront) {
		this.btnBringToFront = btnBringToFront;
	}


	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}


	public void setBtnBringToBack(JButton btnBringToBack) {
		this.btnBringToBack = btnBringToBack;
	}


	public JButton getBtnModify() {
		return btnModify;
	}


	public void setBtnModify(JButton btnModify) {
		this.btnModify = btnModify;
	}


	public JButton getBtnRemove() {
		return btnRemove;
	}


	public void setBtnRemove(JButton btnRemove) {
		this.btnRemove = btnRemove;
	}


	public DrawingController getController() {
		return controller;
	}


	public JButton getLoadNext() {
		return loadNext;
	}


	public void setLoadNext(JButton loadNext) {
		this.loadNext = loadNext;
	}
	
	
	
	
	
}


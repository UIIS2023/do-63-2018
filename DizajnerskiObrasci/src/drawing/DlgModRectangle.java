package drawing;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import shapes.Rectangle;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgModRectangle extends JDialog{
	
	
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtWidth1;
	private JTextField txtHeight1;
	private JTextField txtColor;
	private JTextField txtColor1;
	
	public boolean isOK;
	boolean isReadOnly;
	Rectangle currentRectangle;
	
	public static void main(String[] args) {
		try {
			DlgModRectangle dlgModRectangle = new DlgModRectangle();
			dlgModRectangle.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dlgModRectangle.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DlgModRectangle() {
		setResizable(false);
		setTitle("Rectangle modify");
		setModal(true);
		setBounds(200,200,500,400);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JLabel lblUpperLeftPoint = new JLabel("Upper left Point:");
		
		JLabel lblX = new JLabel("Coordinate of X:");
		
		JLabel lblY = new JLabel("Coordinate of Y:");
		
		txtX = new JTextField();
		txtX.setColumns(10);
		
		txtY = new JTextField();
		txtY.setColumns(10);
		
		JLabel lblWidth = new JLabel("Width:");
		
		JLabel lblHeight = new JLabel("Height:");
		
		JLabel lblColor = new JLabel("Color:");
		
		JLabel lblInnerColor = new JLabel("Inner Color:");
		
		txtWidth1 = new JTextField();
		txtWidth1.setColumns(10);
		
		txtHeight1 = new JTextField();
		txtHeight1.setColumns(10);
		
		JButton btnColor = new JButton("Color");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color rectangleColor = JColorChooser.showDialog(getContentPane(), "Choose a color", Color.BLACK);
				
				if (rectangleColor != null) {
					txtColor.setBackground(rectangleColor);
				}
			}
		});
		
		JButton btnColor_1 = new JButton("Color");
		btnColor_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color rectangleColor = JColorChooser.showDialog(getContentPane(), "Choose a color", Color.BLACK);
				
				if (rectangleColor != null) {
					txtColor1.setBackground(rectangleColor);
				}
			}
		});
		
		txtColor = new JTextField();
		txtColor.setBackground(Color.BLACK);
		txtColor.setColumns(10);
		
		txtColor1 = new JTextField();
		txtColor1.setBackground(Color.WHITE);
		txtColor1.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isOK()) {
					isOK = false;
					
					setVisible(true);
					JOptionPane.showMessageDialog(null, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (!isNumeric(txtWidth1.getText().trim()) || 
						   !isNumeric(txtHeight1.getText().trim())) {
					isOK = false;
					
					setVisible(true);
					JOptionPane.showMessageDialog(null, "All fields must be numeric!", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					isOK = true;
					dispose();
				}
				
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(302, Short.MAX_VALUE)
					.addComponent(btnCancel)
					.addGap(77))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblUpperLeftPoint))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(35)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblX)
										.addComponent(lblY)
										.addComponent(lblWidth)
										.addComponent(lblHeight)
										.addComponent(lblColor))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(31)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(txtHeight1)
												.addComponent(txtWidth1)
												.addComponent(txtY)
												.addComponent(txtX, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(5)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(btnColor_1)
													.addGap(18)
													.addComponent(txtColor1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(btnColor)
													.addGap(18)
													.addComponent(txtColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addComponent(btnOk, Alignment.TRAILING)))))
								.addComponent(lblInnerColor))))
					.addContainerGap(164, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(lblUpperLeftPoint)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblX)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblY)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWidth)
						.addComponent(txtWidth1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHeight)
						.addComponent(txtHeight1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblColor)
						.addComponent(btnColor)
						.addComponent(txtColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInnerColor)
						.addComponent(btnColor_1)
						.addComponent(txtColor1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(67, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(295, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnOk))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public JTextField getTextField() {
		return txtWidth1;
	}

	public void setTextField(JTextField textField) {
		this.txtWidth1 = textField;
	}

	public JTextField getTextField_1() {
		return txtHeight1;
	}

	public void setTextField_1(JTextField textField_1) {
		this.txtHeight1 = textField_1;
	}

	public JTextField getTxtColor() {
		return txtColor;
	}

	public void setTxtColor(JTextField txtColor) {
		this.txtColor = txtColor;
	}

	public JTextField getTxtColor1() {
		return txtColor1;
	}

	public void setTxtColor1(JTextField txtColor1) {
		this.txtColor1 = txtColor1;
	}

	public boolean isOK() {
		return txtHeight1.getText().trim().isEmpty() || txtWidth1.getText().trim().isEmpty();
	}
	
	public boolean isNumeric(String number) {
		return (number != null && number.matches("[0-9]+"));
	}

	public boolean isReadOnly() {
		return isReadOnly;
	}

	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

	public Rectangle getCurrentRectangle() {
		return currentRectangle;
	}

	public void setCurrentRectangle(Rectangle currentRectangle) {
		this.currentRectangle = currentRectangle;
	}
	

	
}

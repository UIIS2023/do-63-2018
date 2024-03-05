package drawing;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import shapes.Rectangle;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgRectangle extends JDialog{
	private JTextField txtWidth;
	private JTextField txtHeight;
	private JTextField txtColor;
	private JTextField txtInnerColor;
	
	public boolean isOK;
	boolean isReadOnly;
	Rectangle currentRectangle;
	
	public static void main(String[] args) {
		try {
			DlgRectangle dlgRectangle = new DlgRectangle();
			dlgRectangle.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dlgRectangle.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DlgRectangle() {
		setTitle("Add Rectangle");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(200,200,350,300);
		
		JLabel lblWidth = new JLabel("Width:");
		
		JLabel lblHeight = new JLabel("Height:");
		
		JLabel lblColor = new JLabel("Color:");
		
		JLabel lblInnerColor = new JLabel("Inner Color:");
		
		txtWidth = new JTextField();
		txtWidth.setColumns(10);
		
		txtHeight = new JTextField();
		txtHeight.setColumns(10);
		
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
					txtInnerColor.setBackground(rectangleColor);
				}
			}
		});
		
		txtColor = new JTextField();
		txtColor.setBackground(Color.BLACK);
		txtColor.setColumns(10);
		
		txtInnerColor = new JTextField();
		txtInnerColor.setBackground(Color.WHITE);
		txtInnerColor.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isOK()) {
					isOK = false;
					
					setVisible(true);
					JOptionPane.showMessageDialog(null, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (!isNumeric(txtWidth.getText().trim()) || 
						   !isNumeric(txtHeight.getText().trim())) {
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
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblWidth)
								.addComponent(lblHeight)
								.addComponent(lblColor)
								.addComponent(lblInnerColor))
							.addGap(37)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnColor_1)
									.addGap(18)
									.addComponent(txtInnerColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnColor)
									.addGap(18)
									.addComponent(txtColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(txtHeight)
									.addComponent(txtWidth, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(73)
							.addComponent(btnOk)
							.addGap(18)
							.addComponent(btnCancel)))
					.addContainerGap(170, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWidth)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHeight)
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblColor)
						.addComponent(btnColor)
						.addComponent(txtColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInnerColor)
						.addComponent(btnColor_1)
						.addComponent(txtInnerColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOk)
						.addComponent(btnCancel))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public JTextField getTxtColor() {
		return txtColor;
	}

	public void setTxtColor(JTextField txtColor) {
		this.txtColor = txtColor;
	}

	public JTextField getTxtInnerColor() {
		return txtInnerColor;
	}

	public void setTxtInnerColor(JTextField txtInnerColor) {
		this.txtInnerColor = txtInnerColor;
	}

	public boolean isOK() {
		return txtHeight.getText().trim().isEmpty() || txtWidth.getText().trim().isEmpty();
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

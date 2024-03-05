package drawing;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgModCircle extends JDialog{
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private JTextField txtColor;
	private JTextField txtInnerColor;
	
	public boolean isOk;
	
	public static void main(String[] args) {
		try {
			DlgModCircle dlgModCircle = new DlgModCircle();
			dlgModCircle.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dlgModCircle.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public DlgModCircle() {
		setTitle("Circle modify");
		setResizable(false);
		setModal(true);
		setBounds(200,200,400,400);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JLabel lblX = new JLabel("X Coordinate of Center:");
		
		JLabel lblY = new JLabel("Y Coordinate of Center:");
		
		JLabel lblRadius = new JLabel("Radius:");
		
		JLabel lblColor = new JLabel("Color:");
		
		txtX = new JTextField();
		txtX.setColumns(10);
		
		JLabel lblInnerColor = new JLabel("Inner Color:");
		
		txtY = new JTextField();
		txtY.setColumns(10);
		
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		
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
					isOk = false;
					
					setVisible(true);
					JOptionPane.showMessageDialog(null, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (!isNumeric(txtRadius.getText().trim())) {
					isOk = false;
					
					setVisible(true);
					JOptionPane.showMessageDialog(null, "All fields must be numeric!", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					isOk = true;
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
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblX)
								.addComponent(lblY)
								.addComponent(lblRadius))
							.addGap(41)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtRadius)
								.addComponent(txtY)
								.addComponent(txtX, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(lblInnerColor)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnColor_1))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(lblColor)
									.addGap(63)
									.addComponent(btnColor))
								.addComponent(btnOk))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtInnerColor, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtColor)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(btnCancel)))))
					.addContainerGap(63, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblX)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblY)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRadius)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOk)
						.addComponent(btnCancel))
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

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
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
		return txtRadius.getText().trim().isEmpty();
	}
	
	public boolean isNumeric(String number) {
		return (number != null && number.matches("[0-9]+"));
	}

	
}

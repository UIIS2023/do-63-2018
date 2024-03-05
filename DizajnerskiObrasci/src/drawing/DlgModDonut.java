package drawing;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgModDonut extends JDialog  {
	
	
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private JTextField txtInnerRadius;
	private JTextField txtColor;
	private JTextField txtColor1;
	
	public boolean isOk;
	public static void main(String[] args) {
		try {
			DlgModDonut dlgModDonut= new DlgModDonut();
			dlgModDonut.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dlgModDonut.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DlgModDonut() {
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Donut modify");
		setBounds(100, 100, 300, 500);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JLabel lblX = new JLabel("X coordinate of the Center:");
		
		JLabel lblY = new JLabel("Y coordinate of the Center:");
		
		JLabel lblRadius = new JLabel("Radius:");
		
		JLabel lblInnerRadius = new JLabel("Inner Radius:");
		
		JLabel lblColor = new JLabel("Color:");
		
		JLabel lblInnerColor = new JLabel("Inner Color:");
		
		txtX = new JTextField();
		txtX.setColumns(10);
		
		txtY = new JTextField();
		txtY.setColumns(10);
		
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		
		txtInnerRadius = new JTextField();
		txtInnerRadius.setColumns(10);
		
		txtColor = new JTextField();
		txtColor.setBackground(Color.BLACK);
		txtColor.setColumns(10);
		
		txtColor1 = new JTextField();
		txtColor1.setBackground(Color.WHITE);
		txtColor1.setColumns(10);
		
		JButton btnColor = new JButton("Color");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					Color donutColor = JColorChooser.showDialog(getContentPane(), "Choose a color", Color.BLACK);
					
					if (donutColor != null) {
						txtColor.setBackground(donutColor);
					}
				
			}
		});
		
		JButton btnColor_1 = new JButton("Color");
		btnColor_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color donutColor = JColorChooser.showDialog(getContentPane(), "Choose a color", Color.BLACK);
				
				if (donutColor != null) {
					txtColor1.setBackground(donutColor);
				}
			}
		});
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isOK()) {
					isOk = false;
					
					setVisible(true);
					JOptionPane.showMessageDialog(null, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (!isNumeric(txtRadius.getText().trim()) || 
						   !isNumeric(txtInnerRadius.getText().trim())) {
					isOk = false;
					
					setVisible(true);
					JOptionPane.showMessageDialog(null, "All fields must be numeric!", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (Integer.parseInt(getTxtRadius().getText()) < Integer.parseInt(getTxtInnerRadius().getText()))  {
					isOk = false;
					
					setVisible(true);
					JOptionPane.showMessageDialog(null, "The inner radius mustn't be greater than the radius.", "Error", JOptionPane.ERROR_MESSAGE);
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
							.addComponent(lblColor)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnColor)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblX)
								.addComponent(lblY)
								.addComponent(lblRadius)
								.addComponent(lblInnerRadius))
							.addGap(26)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtInnerRadius)
								.addComponent(txtRadius)
								.addComponent(txtY)
								.addComponent(txtX, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnOk)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblInnerColor)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnColor_1)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnCancel)
								.addComponent(txtColor1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(43, Short.MAX_VALUE))
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblRadius)
							.addGap(18)
							.addComponent(lblInnerRadius)
							.addGap(18)
							.addComponent(lblColor)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblInnerColor)
								.addComponent(btnColor_1)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnColor))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtColor1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOk)
						.addComponent(btnCancel))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
	public boolean isOK() {
		return txtInnerRadius.getText().trim().isEmpty() || txtRadius.getText().trim().isEmpty();
	}
	
	public boolean isNumeric(String number) {
		return (number != null && number.matches("[0-9]+"));
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

	public JTextField getTxtInnerRadius() {
		return txtInnerRadius;
	}

	public void setTxtInnerRadius(JTextField txtInnerRadius) {
		this.txtInnerRadius = txtInnerRadius;
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

	
	

	

}

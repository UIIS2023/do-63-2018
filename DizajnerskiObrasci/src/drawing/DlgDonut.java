package drawing;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgDonut extends JDialog {
	
	
	private JTextField txtRadius;
	private JTextField txtInnerRadius;
	private JTextField txtColor;
	private JTextField txtInnerColor;
	
	public boolean isOk;
	
	public static void main(String[] args) {
		try {
			DlgDonut dlgDonut = new DlgDonut();
			dlgDonut.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dlgDonut.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public DlgDonut() {
		setResizable(false);
		setBounds(100, 100, 400, 300);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Add Donut");
		
		JLabel lblRadius = new JLabel("Radius:");
		
		JLabel lblInnerRadius = new JLabel("Inner Radius: ");
		
		JLabel lblColor = new JLabel("Color");
		
		JLabel lblInnerColor = new JLabel("Inner Color:");
		
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		
		txtInnerRadius = new JTextField();
		txtInnerRadius.setColumns(10);
		
		JButton btnColor = new JButton("Color");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
					txtInnerColor.setBackground(donutColor);
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRadius)
								.addComponent(lblInnerRadius)
								.addComponent(lblColor)
								.addComponent(lblInnerColor))
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnColor_1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtInnerColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnColor)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(txtInnerRadius)
									.addComponent(txtRadius, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(76)
							.addComponent(btnOk)
							.addGap(18)
							.addComponent(btnCancel)))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRadius)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInnerRadius)
						.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblColor)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblInnerColor)
								.addComponent(btnColor_1)
								.addComponent(txtInnerColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnColor)
							.addComponent(txtColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnOk))
					.addGap(34))
		);
		getContentPane().setLayout(groupLayout);
	}

	public boolean isOK() {
		return txtInnerRadius.getText().trim().isEmpty() || txtRadius.getText().trim().isEmpty();
	}
	
	public boolean isNumeric(String number) {
		return (number != null && number.matches("[0-9]+"));
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


	public JTextField getTxtInnerColor() {
		return txtInnerColor;
	}


	public void setTxtInnerColor(JTextField txtInnerColor) {
		this.txtInnerColor = txtInnerColor;
	}
}

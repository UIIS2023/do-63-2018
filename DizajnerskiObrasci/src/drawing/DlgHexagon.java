package drawing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DlgHexagon extends JDialog{

	private JTextField txtRadius;
	private JTextField txtColor;
	private JTextField txtInnerColor;
	
	 public boolean isOK;
	

	public static void main(String[] args) {
		try {
			DlgHexagon dlgHexagon = new DlgHexagon();
			dlgHexagon.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dlgHexagon.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DlgHexagon() {
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Add Hexagon");
		setBounds(100, 100, 350, 300);
		
		JLabel lblRadius = new JLabel("Radius:");
		
		JLabel lblColor = new JLabel("Color:");
		
		JLabel lblInnerColor = new JLabel("Inner Color: ");
		
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		
		JButton btnColor = new JButton("Color");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color rectangleColor = JColorChooser.showDialog(getContentPane(), "Choose a color", Color.BLACK);
				
				if (rectangleColor != null) {
					txtColor.setBackground(rectangleColor);
				}
			}
		});
		
		JButton btnColor1 = new JButton("Color");
		btnColor1.addActionListener(new ActionListener() {
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
		txtInnerColor.setColumns(10);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isOK()) {
					isOK = false;
					
					setVisible(true);
					JOptionPane.showMessageDialog(null, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (isNumeric(txtRadius.getText().trim())) {
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
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRadius)
								.addComponent(lblColor)
								.addComponent(lblInnerColor))
							.addGap(26)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnColor)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnColor1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtInnerColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtRadius)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(80)
							.addComponent(btnOK)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancel)))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRadius)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblColor)
						.addComponent(btnColor)
						.addComponent(txtColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(33)
							.addComponent(lblInnerColor))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnColor1)
								.addComponent(txtInnerColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOK)
						.addComponent(btnCancel))
					.addGap(52))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	public JTextField getTxtRadius() {
		return txtRadius;
	}
	public void setTxtRadius(JTextField txtHeight) {
		this.txtRadius = txtHeight;
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
		return (number != null && number.matches("[0-9]") );
	}
}

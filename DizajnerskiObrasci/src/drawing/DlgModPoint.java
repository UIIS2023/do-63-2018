package drawing;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgModPoint extends JDialog {
	
	private JTextField txtX;
	private JTextField txtY;
	
	public boolean isOk;
	
	public static void main(String[] args) {
		try {
			DlgModPoint dlgModPoint = new DlgModPoint();
			dlgModPoint.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dlgModPoint.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public DlgModPoint() {
		setTitle("Point modify");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100,100,200,200);
		setModal(true);
		
		JLabel lblX = new JLabel("Coordinate of X:");
		
		JLabel lblY = new JLabel("Coordinate of Y:");
		
		txtX = new JTextField();
		txtX.setColumns(10);
		
		txtY = new JTextField();
		txtY.setText("");
		txtY.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isOK()) {
					isOk = false;
					
					setVisible(true);
					JOptionPane.showMessageDialog(null, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (!isNumeric(txtX.getText().trim()) || 
						   !isNumeric(txtY.getText().trim())) {
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblX)
							.addGap(18)
							.addComponent(txtX, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblY)
							.addGap(18)
							.addComponent(txtY)))
					.addContainerGap(66, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(66)
					.addComponent(btnOk)
					.addGap(18)
					.addComponent(btnCancel)
					.addContainerGap(89, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblX)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblY)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnOk))
					.addContainerGap(52, Short.MAX_VALUE))
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




	public boolean isOK() {
		return txtY.getText().trim().isEmpty() || txtX.getText().trim().isEmpty();
	}
	
	public boolean isNumeric(String number) {
		return (number != null && number.matches("[0-9]+"));
	}

	
}

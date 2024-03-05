package drawing;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgModLine extends JDialog {
	
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtX1;
	private JTextField txtY1;
	
	public boolean isOk;
	
	public static void main(String[] args) {
		try {
			DlgModLine dlgModLine= new DlgModLine();
			dlgModLine.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dlgModLine.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public DlgModLine() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Line modify");
		setBounds(100, 100, 300, 300);
		setModal(true);
		
		JLabel lblStartPoint = new JLabel("Start Point: ");
		
		JLabel lblX = new JLabel("Coordinate of X:");
		
		JLabel lblY = new JLabel("Coordinate of Y:");
		
		JLabel lblEndPoint = new JLabel("End Point:");
		
		JLabel lblX1 = new JLabel("Coordinate of X:");
		
		JLabel lblfY1 = new JLabel("Coordinate of Y:");
		
		txtX = new JTextField();
		txtX.setColumns(10);
		
		txtY = new JTextField();
		txtY.setText("");
		txtY.setColumns(10);
		
		txtX1 = new JTextField();
		txtX1.setColumns(10);
		
		txtY1 = new JTextField();
		txtY1.setText("");
		txtY1.setColumns(10);
		
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
					isOk= true;
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
							.addComponent(lblStartPoint))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblEndPoint))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(btnOk)
								.addGap(18)
								.addComponent(btnCancel))
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(40)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(lblX)
											.addComponent(lblY)))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(47)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(lblfY1)
											.addComponent(lblX1))))
								.addGap(24)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(txtY1)
									.addComponent(txtX1)
									.addComponent(txtY)
									.addComponent(txtX, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)))))
					.addContainerGap(82, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblStartPoint)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblX)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblY)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addComponent(lblEndPoint)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblX1)
						.addComponent(txtX1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblfY1)
						.addComponent(txtY1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnOk))
					.addGap(19))
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

	public JTextField getTxtX1() {
		return txtX1;
	}

	public void setTxtX1(JTextField txtX1) {
		this.txtX1 = txtX1;
	}

	public JTextField getTxtY1() {
		return txtY1;
	}

	public void setTxtY1(JTextField txtY1) {
		this.txtY1 = txtY1;
	}

	public boolean isOK() {
		return txtY.getText().trim().isEmpty() || txtX.getText().trim().isEmpty();
	}
	
	public boolean isNumeric(String number) {
		return (number != null && number.matches("[0-9]+"));
	}


	
}

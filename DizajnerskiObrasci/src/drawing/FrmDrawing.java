package drawing;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmDrawing extends JFrame {
	
	static boolean isPoint;
	static boolean isLine;
	static boolean isCircle;
	static boolean isDonut;
	static boolean isRectangle;
	static boolean isSelectMode;
	static boolean isModifyMode;
	static boolean isRemoveMode;
	private JPanel pnlDrawing;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					FrmDrawing frmDrawing = new FrmDrawing();
					frmDrawing.setPnlDrawing(new PnlDrawing());
					
					frmDrawing.add(frmDrawing.getPnlDrawing(), BorderLayout.CENTER);
					frmDrawing.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public FrmDrawing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Jovana Karamarkovic IT63-2018");
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 1000, 500);
		
		JPanel pnlShape = new JPanel();
		getContentPane().add(pnlShape, BorderLayout.NORTH);
		
		JLabel lblShape = new JLabel("Click a shape for drawing:");
		pnlShape.add(lblShape);
		
		JButton btnPoint = new JButton("Point");
		btnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPoint = true;
				isLine = false;
				isCircle = false;
				isDonut = false;
				isRectangle = false;
			}
		});
		pnlShape.add(btnPoint);
		
		JButton btnLine = new JButton("Line");
		btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPoint = false;
				isLine = true;
				isCircle = false;
				isDonut = false;
				isRectangle = false;
			}
		});
		pnlShape.add(btnLine);
		
		JButton btnCircle = new JButton("Circle");
		btnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPoint = false;
				isLine = false;
				isCircle = true;
				isDonut = false;
				isRectangle = false;
			}
		});
		pnlShape.add(btnCircle);
		
		JButton btnDonut = new JButton("Donut");
		btnDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPoint = false;
				isLine = false;
				isCircle = false;
				isDonut = true;
				isRectangle = false;
			}
		});
		pnlShape.add(btnDonut);
		
		JButton btnRectangle = new JButton("Rectangle");
		btnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPoint = false;
				isLine = false;
				isCircle = false;
				isDonut = false;
				isRectangle = true;
			}
		});
		pnlShape.add(btnRectangle);
		
		JPanel pnlOptions = new JPanel();
		getContentPane().add(pnlOptions, BorderLayout.SOUTH);
		
		JLabel lblOptions = new JLabel("Options:");
		pnlOptions.add(lblOptions);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isPoint = false;
				isLine = false;	
				isRectangle = false;
				isCircle = false;
				isDonut = false;	
				isSelectMode = true;
				isModifyMode = false;
				isRemoveMode = false;
			}
		});
		pnlOptions.add(btnSelect);
		
		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPoint = false;
				isLine = false;	
				isRectangle = false;
				isCircle = false;
				isDonut = false;	
				isSelectMode = false;
				isModifyMode = true;
				isRemoveMode = false;
				
//				((PnlDrawing) pnlDrawing).modifyShape();
			}
		});
		pnlOptions.add(btnModify);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPoint = false;
				isLine = false;	
				isRectangle = false;
				isCircle = false;
				isDonut = false;	
				isSelectMode = false;
				isModifyMode = false;
				isRemoveMode = true;
			}
		});
		pnlOptions.add(btnRemove);
	}
	public JPanel getPnlDrawing() {
		return pnlDrawing;
	}

	public void setPnlDrawing(JPanel pnlDrawing) {
		this.pnlDrawing = pnlDrawing;
	}
}

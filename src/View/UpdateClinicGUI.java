package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Clinic;
import Model.HeadDoctor;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateClinicGUI extends JFrame {

	static HeadDoctor headdoctor = new HeadDoctor();
	
	private JPanel contentPane;
	private JTextField fld_updateClinicName;
	private static Clinic clinic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateClinicGUI(Clinic clinic) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 150);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_updateClinicName = new JLabel("Poliklinik Ad\u0131");
		lbl_updateClinicName.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_updateClinicName.setForeground(Color.WHITE);
		lbl_updateClinicName.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_updateClinicName.setBounds(49, 11, 113, 18);
		contentPane.add(lbl_updateClinicName);
		
		fld_updateClinicName = new JTextField();
		fld_updateClinicName.setColumns(10);
		fld_updateClinicName.setBounds(49, 40, 113, 20);
		fld_updateClinicName.setText(clinic.getClinicName());
		contentPane.add(fld_updateClinicName);
		
		JButton btn_updateClinic = new JButton("Düzenle");
		btn_updateClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					try {
						headdoctor.updateClinic(clinic.getClinicID(),fld_updateClinicName.getText());
						Helper.showMessage("success");
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		btn_updateClinic.setForeground(Color.WHITE);
		btn_updateClinic.setBackground(Color.GRAY);
		btn_updateClinic.setBounds(49, 77, 113, 23);
		contentPane.add(btn_updateClinic);
	}

}

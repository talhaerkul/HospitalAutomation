package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Patient;
import Model.User;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_tcno;
	private JPasswordField fld_password;
	private Patient patient = new Patient();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setTitle("Hastane Y\u00F6netim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(0, 139, 139));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_name = new JLabel("Ad Soyad");
		lbl_name.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_name.setForeground(Color.WHITE);
		lbl_name.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_name.setBounds(10, 11, 133, 18);
		w_pane.add(lbl_name);
		
		fld_name = new JTextField();
		fld_name.setColumns(10);
		fld_name.setBounds(10, 40, 264, 20);
		w_pane.add(fld_name);
		
		JButton btn_register = new JButton("Kayýt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_tcno.getText().length() ==0 || fld_password.getText().length() ==0 || fld_name.getText().length() ==0) {
					Helper.showMessage("fill");
				}else {
					try {
						boolean control = patient.register(fld_tcno.getText(), fld_password.getText(), fld_name.getText());
						if(control) {
							Helper.showMessage("success");
						}else {
							
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_register.setForeground(Color.WHITE);
		btn_register.setBackground(Color.GRAY);
		btn_register.setBounds(10, 203, 264, 23);
		w_pane.add(btn_register);
		
		JLabel lbl_tc = new JLabel("T.C Kimlik Numaras\u0131");
		lbl_tc.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_tc.setForeground(Color.WHITE);
		lbl_tc.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_tc.setBounds(10, 71, 133, 18);
		w_pane.add(lbl_tc);
		
		fld_tcno = new JTextField();
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(10, 100, 264, 20);
		w_pane.add(fld_tcno);
		
		JLabel lbl_password = new JLabel("Parola");
		lbl_password.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_password.setForeground(Color.WHITE);
		lbl_password.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbl_password.setBounds(10, 131, 133, 18);
		w_pane.add(lbl_password);
		
		fld_password = new JPasswordField();
		fld_password.setBounds(10, 159, 264, 20);
		w_pane.add(fld_password);
		
		JButton btn_backto = new JButton("Geri D\u00F6n");
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_backto.setForeground(Color.WHITE);
		btn_backto.setBackground(Color.GRAY);
		btn_backto.setBounds(91, 268, 101, 23);
		w_pane.add(btn_backto);
	}
}

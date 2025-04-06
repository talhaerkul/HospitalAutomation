package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import Helper.*;
import Model.HeadDoctor;
import Model.Doctor;
import Model.Patient;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JPasswordField fld_hastaPassword;
	private JTextField fld_doktorTc;
	private JPasswordField fld_doktorPassword;
	
	
	private DBConnection dbconnection = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Y\u00F6netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("mediccin.png")));	
		lbl_logo.setBounds(220, 11, 48, 45);
		w_pane.add(lbl_logo);
		
		JLabel lbl_hosgeldinMassage = new JLabel("Hastane Y\u00F6netim Sistemine Ho\u015Fgeldiniz");
		lbl_hosgeldinMassage.setForeground(new Color(255, 99, 71));
		lbl_hosgeldinMassage.setFont(new Font("Yu Gothic Medium", Font.BOLD, 15));
		lbl_hosgeldinMassage.setBounds(99, 67, 290, 34);
		w_pane.add(lbl_hosgeldinMassage);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBackground(new Color(32, 178, 170));
		w_tabpane.setBounds(20, 112, 430, 218);
		w_pane.add(w_tabpane);
		
		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setLocation(32, 25);
		w_hastaLogin.setBackground(new Color(32, 178, 170));
		w_tabpane.addTab("Hasta Giriþi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);
		
		JLabel lbl_hastaTc = new JLabel("T.C. Kimlik No  : ");
		lbl_hastaTc.setForeground(new Color(255, 255, 255));
		lbl_hastaTc.setFont(new Font("Yu Gothic Medium", Font.BOLD, 17));
		lbl_hastaTc.setBounds(10, 22, 191, 34);
		w_hastaLogin.add(lbl_hastaTc);
		
		JLabel lbl_hastaParola = new JLabel("Parola  : ");
		lbl_hastaParola.setForeground(new Color(255, 255, 255));
		lbl_hastaParola.setFont(new Font("Yu Gothic Medium", Font.BOLD, 17));
		lbl_hastaParola.setBounds(10, 62, 191, 34);
		w_hastaLogin.add(lbl_hastaParola);
		
		fld_hastaTc = new JTextField();
		fld_hastaTc.setFont(new Font("Arial Black", Font.PLAIN, 13));
		fld_hastaTc.setBounds(160, 26, 107, 20);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);
		
		fld_hastaPassword = new JPasswordField();
		fld_hastaPassword.setFont(new Font("Arial Black", Font.PLAIN, 13));
		fld_hastaPassword.setBounds(160, 67, 107, 20);
		w_hastaLogin.add(fld_hastaPassword);
		
		JButton btn_hastaLogin = new JButton("Giriþ Yap");
		btn_hastaLogin.setForeground(new Color(255, 255, 255));
		btn_hastaLogin.setBackground(new Color(128, 128, 128));
		btn_hastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_hastaTc.getText().length() == 0 || fld_hastaPassword.getText().length() == 0 ) {
					Helper.showMessage("fill");
				}else {
					boolean key = true;
					try {
						Connection connection = dbconnection.connectionDB();
						Statement statement = connection.createStatement();
						ResultSet resultset = statement.executeQuery("SELECT * FROM user");
						
						while(resultset.next()) {
							if(fld_hastaTc.getText().equals(resultset.getString("tcno_data")) && fld_hastaPassword.getText().equals(resultset.getString("password_data"))) {
								if(resultset.getString("type_data").equals("patient")) {
									Patient patient = new Patient();
									patient.setUserID(resultset.getInt("id_data"));
									patient.setUserPassword("password_data");  
									patient.setUserTC(resultset.getString("tcno_data"));
									patient.setUserName(resultset.getString("name_data"));
									patient.setUserType(resultset.getString("type_data"));
									PatientGUI patientGUI = new PatientGUI(patient);
									patientGUI.setVisible(true);
									dispose();
									key = false;
								}
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					if(key) {
						Helper.showMessage("Hasta bulunamadý.");
					}
					
				}
			}
		});
		btn_hastaLogin.setBounds(160, 107, 107, 23);
		w_hastaLogin.add(btn_hastaLogin);
		
		JButton btn_hastaUye = new JButton("Üye Ol");
		btn_hastaUye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI registerGUI = new RegisterGUI();
				registerGUI.setVisible(true);
				
			}
		});
		btn_hastaUye.setForeground(new Color(255, 255, 255));
		btn_hastaUye.setBackground(new Color(128, 128, 128));
		btn_hastaUye.setBounds(308, 107, 107, 23);
		w_hastaLogin.add(btn_hastaUye);
		
		JLabel lbl_uyeMessage = new JLabel("\u00DCye de\u011Fil misiniz? ");
		lbl_uyeMessage.setForeground(Color.WHITE);
		lbl_uyeMessage.setFont(new Font("Yu Gothic Medium", Font.BOLD, 12));
		lbl_uyeMessage.setBounds(308, 75, 107, 34);
		w_hastaLogin.add(lbl_uyeMessage);
		
		JPanel w_doktorLogin = new JPanel();
		w_doktorLogin.setBackground(new Color(32, 178, 170));
		w_tabpane.addTab("Doktor Giriþi", null, w_doktorLogin, null);
		w_doktorLogin.setLayout(null);
		
		JLabel lbl_doktorTC = new JLabel("T.C. Kimlik No  : ");
		lbl_doktorTC.setForeground(Color.WHITE);
		lbl_doktorTC.setFont(new Font("Yu Gothic Medium", Font.BOLD, 17));
		lbl_doktorTC.setBounds(10, 22, 191, 34);
		w_doktorLogin.add(lbl_doktorTC);
		
		fld_doktorTc = new JTextField();
		fld_doktorTc.setFont(new Font("Arial Black", Font.PLAIN, 13));
		fld_doktorTc.setColumns(10);
		fld_doktorTc.setBounds(160, 26, 107, 20);
		w_doktorLogin.add(fld_doktorTc);
		
		fld_doktorPassword = new JPasswordField();
		fld_doktorPassword.setFont(new Font("Arial Black", Font.PLAIN, 13));
		fld_doktorPassword.setBounds(160, 67, 107, 20);
		w_doktorLogin.add(fld_doktorPassword);
		
		JLabel lbl_doktorParola = new JLabel("Parola  : ");
		lbl_doktorParola.setForeground(Color.WHITE);
		lbl_doktorParola.setFont(new Font("Yu Gothic Medium", Font.BOLD, 17));
		lbl_doktorParola.setBounds(10, 62, 191, 34);
		w_doktorLogin.add(lbl_doktorParola);
		
		JButton btn_doktorLogin = new JButton("Giriþ Yap");
		btn_doktorLogin.setForeground(new Color(255, 255, 255));
		btn_doktorLogin.setBackground(new Color(128, 128, 128));
		btn_doktorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doktorTc.getText().length()==0 || fld_doktorPassword.getText().length()==0) {
					Helper.showMessage("fill");
				}else {
					try {
						Connection localconnection = dbconnection.connectionDB();
						Statement statement = localconnection.createStatement();
						ResultSet resultset = statement.executeQuery("SELECT * FROM user");
						while(resultset.next()) {
							if(fld_doktorTc.getText().equals(resultset.getString("tcno_data")) && fld_doktorPassword.getText().equals(resultset.getString("password_data"))) {
							if(resultset.getString("type_data").equals("headdoctor")) {
								HeadDoctor headdoctor = new HeadDoctor();
								headdoctor.setUserID(resultset.getInt("id_data"));
								headdoctor.setUserPassword("password_data");  
								headdoctor.setUserTC(resultset.getString("tcno_data"));
								headdoctor.setUserName(resultset.getString("name_data"));
								headdoctor.setUserType(resultset.getString("type_data"));
								HeadDoctorGUI headdoctorGUI = new HeadDoctorGUI(headdoctor);
								headdoctorGUI.setVisible(true);
								dispose();
							}
							if(resultset.getString("type_data").equals("doctor")) {
								Doctor doctor = new Doctor();
								doctor.setUserID(resultset.getInt("id_data"));
								doctor.setUserPassword("password_data");  
								doctor.setUserTC(resultset.getString("tcno_data"));
								doctor.setUserName(resultset.getString("name_data"));
								doctor.setUserType(resultset.getString("type_data"));
								DoctorGUI doctorGUI = new DoctorGUI(doctor);
								doctorGUI.setVisible(true);
								dispose();
							}
							
						}
					}
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
				}
			}
		});
		btn_doktorLogin.setBounds(160, 107, 107, 23);
		w_doktorLogin.add(btn_doktorLogin);
		
		JLabel lbl_parolaMessage = new JLabel("Parolan\u0131z yok mu?");
		lbl_parolaMessage.setForeground(Color.WHITE);
		lbl_parolaMessage.setFont(new Font("Yu Gothic Medium", Font.BOLD, 12));
		lbl_parolaMessage.setBounds(308, 76, 107, 34);
		w_doktorLogin.add(lbl_parolaMessage);
		
		JButton btn_doktorParola = new JButton("Parola Al");
		btn_doktorParola.setForeground(new Color(255, 255, 255));
		btn_doktorParola.setBackground(new Color(128, 128, 128));
		btn_doktorParola.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doktorTc.getText().length()==11) {
					Helper.showMessage("Parola, T.C. Numaranýz üzerine kayýtlý telefonunuza gönderildi !");
				}else {
					Helper.showMessage("Lütfen 11 haneli T.C. bölümünü doldurunuz.");
				}
			}
		});
		btn_doktorParola.setBounds(308, 107, 107, 23);
		w_doktorLogin.add(btn_doktorParola);
	}
}

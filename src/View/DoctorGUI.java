package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Model.Doctor;
import Model.WorkDate;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class DoctorGUI extends JFrame {
	
	static Doctor doctor = new Doctor();
	static WorkDate workdate = new WorkDate();
	

	private JPanel w_pane;
	private JTextField fld_date;
	private JTable table_workdate;

	private DefaultTableModel workDateModel = null;
	private Object[] workDateData = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
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
	public DoctorGUI(Doctor doctor) {
		setResizable(false);
		
		
		
		//-----------------
		workDateModel = new DefaultTableModel();
		Object[] columnworkdateTableHead = new Object[3];
		columnworkdateTableHead[0] = "ID";
		columnworkdateTableHead[1] = "TARÝH";
		columnworkdateTableHead[2] = "DURUM";
		workDateModel.setColumnIdentifiers(columnworkdateTableHead);
		workDateData = new Object[3];
		try {
			for(int i =0; i < workdate.getWorkDateList(doctor.getUserID()).size(); i++) {
				workDateData[0] = workdate.getWorkDateList(doctor.getUserID()).get(i).getWorkID();
				workDateData[1] = workdate.getWorkDateList(doctor.getUserID()).get(i).getWorkDate();
				workDateData[2] = workdate.getWorkDateList(doctor.getUserID()).get(i).getWorkStatus();
				workDateModel.addRow(workDateData);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//-------------------------
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_hosgeldinizSayýn = new JLabel("Hoþ geldiniz, Sayýn "+doctor.getUserName());
		lbl_hosgeldinizSayýn.setForeground(new Color(255, 99, 71));
		lbl_hosgeldinizSayýn.setFont(new Font("Yu Gothic Medium", Font.BOLD, 15));
		lbl_hosgeldinizSayýn.setBounds(85, 21, 289, 32);
		w_pane.add(lbl_hosgeldinizSayýn);
		
		JLabel lbl_logo2 = new JLabel((Icon) null);
		lbl_logo2.setBounds(35, 21, 40, 32);
		w_pane.add(lbl_logo2);
		
		JButton btn_cikis = new JButton("\u00C7\u0131k\u0131\u015F");
		btn_cikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setVisible(true);
				dispose();
			}
		});
		btn_cikis.setForeground(Color.WHITE);
		btn_cikis.setFont(new Font("Arial", Font.PLAIN, 13));
		btn_cikis.setBackground(Color.GRAY);
		btn_cikis.setBounds(652, 11, 72, 23);
		w_pane.add(btn_cikis);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(35, 68, 668, 349);
		w_pane.add(w_tab);
		
		JPanel w_workHour = new JPanel();
		w_workHour.setLayout(null);
		w_workHour.setBackground(new Color(0, 139, 139));
		w_tab.addTab("New tab", null, w_workHour, null);
		
		JComboBox select_time = new JComboBox();
		select_time.setModel(new DefaultComboBoxModel(new String[] {"10:00", "11:00"}));
		select_time.setBounds(180, 11, 62, 22);
		w_workHour.add(select_time);
		
		fld_date = new JTextField();
		fld_date.setBounds(82, 12, 86, 20);
		w_workHour.add(fld_date);
		fld_date.setColumns(10);
		
		JButton btn_addhour = new JButton("Ekle");
		btn_addhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String time = " "+select_time.getSelectedItem().toString();
				String date = fld_date.getText().toString();
				String randevuDate = date+time;
				
				if (date.length()==0) {
					Helper.showMessage("Lütfen geçerli bir tarih girin. ");
				}else {
					try {
						boolean control = doctor.addWorkDate(doctor.getUserID(), doctor.getUserName(),randevuDate);
						if (control) {
							Helper.showMessage("success");
							updateWorkDateModel(doctor);
						} else {
							
						}

					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				
			}
		});
		btn_addhour.setForeground(Color.WHITE);
		btn_addhour.setFont(new Font("Arial", Font.PLAIN, 13));
		btn_addhour.setBackground(Color.GRAY);
		btn_addhour.setBounds(282, 11, 72, 23);
		w_workHour.add(btn_addhour);
		
		JScrollPane w_scrollWorkDate = new JScrollPane();
		w_scrollWorkDate.setBounds(10, 41, 643, 269);
		w_workHour.add(w_scrollWorkDate);
		
		table_workdate = new JTable(workDateModel);
		w_scrollWorkDate.setViewportView(table_workdate);
		
		JButton btn_deletehour = new JButton("Sil");
		btn_deletehour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_workdate.getSelectedRow();
				if(selectedRow >= 0) {
					String selectedDate = table_workdate.getModel().getValueAt(selectedRow, 0).toString();
					int selectedDateID = Integer.parseInt(selectedDate);
					try {
						boolean control = doctor.deleteWorkDate(selectedDateID);
						if(control) {
							Helper.showMessage("success");
							updateWorkDateModel(doctor);
						}else {
							Helper.showMessage("error");
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}else {
					Helper.showMessage("Silinecek bir tarih seçiniz.");
					
				}
			}
		});
		btn_deletehour.setForeground(Color.WHITE);
		btn_deletehour.setFont(new Font("Arial", Font.PLAIN, 13));
		btn_deletehour.setBackground(Color.GRAY);
		btn_deletehour.setBounds(581, 11, 72, 23);
		w_workHour.add(btn_deletehour);
		
		
		
		JLabel lblNewLabel = new JLabel("Tarih");
		lblNewLabel.setBounds(26, 15, 46, 14);
		w_workHour.add(lblNewLabel);
	}
	
	public void updateWorkDateModel(Doctor doctor) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_workdate.getModel();
		clearModel.setRowCount(0);
		for(int i =0; i < workdate.getWorkDateList(doctor.getUserID()).size(); i++) {
			workDateData[0] = workdate.getWorkDateList(doctor.getUserID()).get(i).getWorkID();
			workDateData[1] = workdate.getWorkDateList(doctor.getUserID()).get(i).getWorkDate();
			workDateData[2] = workdate.getWorkDateList(doctor.getUserID()).get(i).getWorkStatus();
			workDateModel.addRow(workDateData);
		}
	}
	
	
}

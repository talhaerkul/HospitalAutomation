package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import Model.Clinic;
import Model.Doctor;
import Model.HeadDoctor;
import Model.Patient;
import Model.Randevu;
import Model.WorkDate;
import Model.Worker;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.Icon;
import java.awt.Font;
import java.awt.Point;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Helper.Helper;
import Helper.Item;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PatientGUI extends JFrame {

	static Patient patient = new Patient();
	static Clinic clinic = new Clinic();
	static Worker worker = new Worker();
	static WorkDate workdate = new WorkDate();
	static Randevu randevu = new Randevu();
	static HeadDoctor headdoctor = new HeadDoctor();
	
	
	private int selectedDoctorID;
	private int selectedClinicID;
	private String selectedClinicName;
	private String selectedRandevuDate;
	private String selectedDoctorName;
	
	private JPanel w_pane;
	private JTable table_randevular;
	
	private DefaultTableModel workerModel = null;
	private Object[] workerData = null;
	
	private DefaultTableModel workdateModel = null;
	private Object[] workdateData = null;
	
	private DefaultTableModel randevuModel = null;
	private Object[] randevuData = null;
	
	
	private JTable table_doctor;
	private JTable table_workdate;
	
	private JPopupMenu clinicMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientGUI frame = new PatientGUI(patient);
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
	public PatientGUI(Patient patient) {
		setResizable(false);
		
		workerModel = new DefaultTableModel();
		Object[] columnworkerTableHead = new Object[2];
		columnworkerTableHead[0] = "ID";
		columnworkerTableHead[1] = "AD SOYAD";
		workerModel.setColumnIdentifiers(columnworkerTableHead);
		workerData = new Object[2];
		
		workdateModel = new DefaultTableModel();
		Object[] columnworkdateTableHead = new Object[3];
		columnworkdateTableHead[0] = "ID";
		columnworkdateTableHead[1] = "DOKTOR";
		columnworkdateTableHead[2] = "TARÝH";
		workdateModel.setColumnIdentifiers(columnworkdateTableHead);
		workdateData = new Object[3];
		
		randevuModel = new DefaultTableModel();
		Object[] columnrandevuTableHead = new Object[3];
		columnrandevuTableHead[0] = "ID";
		columnrandevuTableHead[1] = "DOKTOR";
		columnrandevuTableHead[2] = "TARÝH";
		randevuModel.setColumnIdentifiers(columnrandevuTableHead);
		randevuData = new Object[3];
		try {
			for(int i = 0; i < randevu.getRandevuList(patient.getUserID()).size(); i++) {
				randevuData[0] = randevu.getRandevuList(patient.getUserID()).get(i).getRandevuID();
				randevuData[1] = randevu.getRandevuList(patient.getUserID()).get(i).getRandevuDoctorName();
				randevuData[2] = randevu.getRandevuList(patient.getUserID()).get(i).getRandevuDate();
				randevuModel.addRow(randevuData);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo2 = new JLabel((Icon) null);
		lbl_logo2.setBounds(21, 21, 40, 32);
		w_pane.add(lbl_logo2);
		
		JLabel lbl_hosgeldinizSayýn = new JLabel("Ho\u015F geldiniz, Say\u0131n " + patient.getUserName());
		lbl_hosgeldinizSayýn.setForeground(new Color(255, 99, 71));
		lbl_hosgeldinizSayýn.setFont(new Font("Yu Gothic Medium", Font.BOLD, 15));
		lbl_hosgeldinizSayýn.setBounds(71, 21, 289, 32);
		w_pane.add(lbl_hosgeldinizSayýn);
		
		JButton btn_cikis = new JButton("Çýkýþ");
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
		btn_cikis.setBounds(638, 11, 72, 23);
		w_pane.add(btn_cikis);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(31, 64, 668, 349);
		w_pane.add(w_tab);
		
		JPanel w_appointment = new JPanel();
		w_appointment.setLayout(null);
		w_appointment.setBackground(new Color(0, 139, 139));
		w_tab.addTab("Randevu Sistemi", null, w_appointment, null);
		
		JScrollPane w_scrolldoctorlist = new JScrollPane();
		w_scrolldoctorlist.setBounds(10, 97, 233, 166);
		w_appointment.add(w_scrolldoctorlist);
		
		table_doctor = new JTable(workerModel);
		table_doctor.getColumnModel().getColumn(0).setPreferredWidth(1);
		w_scrolldoctorlist.setViewportView(table_doctor);
		
		JLabel lbl_doctorlist = new JLabel("Doktor Listesi");
		lbl_doctorlist.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_doctorlist.setForeground(Color.WHITE);
		lbl_doctorlist.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lbl_doctorlist.setBounds(10, 71, 233, 18);
		w_appointment.add(lbl_doctorlist);
		
		JLabel lbl_cliniclist = new JLabel("Poliklinik Listesi");
		lbl_cliniclist.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_cliniclist.setForeground(Color.WHITE);
		lbl_cliniclist.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lbl_cliniclist.setBounds(10, 11, 233, 18);
		w_appointment.add(lbl_cliniclist);
		
		//SELECTLIST için gerekli kod
		JComboBox w_selectclinic = new JComboBox();
		w_selectclinic.setBounds(10, 37, 233, 23);
		w_selectclinic.addItem("--Klinik Seç--");
		for(int i=0 ; i < clinic.getClinicList().size();i++) {
			w_selectclinic.addItem(new Item(clinic.getClinicList().get(i).getClinicID() , clinic.getClinicList().get(i).getClinicName()));
		}
		w_selectclinic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(w_selectclinic.getSelectedIndex() != 0) {
				selectedClinicID = w_selectclinic.getSelectedIndex(); 	
				selectedClinicName = w_selectclinic.getSelectedItem().toString();
				JComboBox jcombobox = (JComboBox) e.getSource();
				Item item = (Item) jcombobox.getSelectedItem();
				DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
				clearModel.setRowCount(0);
				
					
						for(int i = 0; i < worker.getWorkerAtClinicList(item.getKey()).size(); i++) {
							workerData[0] = worker.getWorkerAtClinicList(item.getKey()).get(i).getUserID();
							workerData[1] = worker.getWorkerAtClinicList(item.getKey()).get(i).getUserName();
							workerModel.addRow(workerData);
							}
				
				}	
				else {
				DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
				clearModel.setRowCount(0);     
				}
				
			}
			
		});
		w_appointment.add(w_selectclinic);
		
		JScrollPane w_scrollworkdate = new JScrollPane();
		w_scrollworkdate.setBounds(262, 37, 261, 273);
		w_appointment.add(w_scrollworkdate);
		
		table_workdate = new JTable(workdateModel);
		table_workdate.getColumnModel().getColumn(0).setPreferredWidth(1);
		w_scrollworkdate.setViewportView(table_workdate);
		
		JLabel lbl_randevudatelist = new JLabel("Uygun Randevu Saatleri");
		lbl_randevudatelist.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_randevudatelist.setForeground(Color.WHITE);
		lbl_randevudatelist.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lbl_randevudatelist.setBounds(262, 11, 233, 18);
		w_appointment.add(lbl_randevudatelist);
		
		JButton btn_workerSelect = new JButton("Uygun Randevular\u0131 Listele");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedDoctor = Integer.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
				selectedDoctorID = selectedDoctor;
				selectedDoctorName = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
				if(selectedDoctor >=0 ) {
					
					try {
						for(int i =0; i< workdate.getWorkDateList(selectedDoctor).size();i++) {
							workdateData[0] = workdate.getWorkDateList(selectedDoctor).get(i).getWorkID();
							workdateData[1] = workdate.getWorkDateList(selectedDoctor).get(i).getWorkDoctorName();
							workdateData[2] = workdate.getWorkDateList(selectedDoctor).get(i).getWorkDate();
							workdateModel.addRow(workdateData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					Helper.showMessage("Lütfen bir doktor seçiniz.");
				}
			}
		});
		btn_workerSelect.setForeground(Color.WHITE);
		btn_workerSelect.setBackground(Color.GRAY);
		btn_workerSelect.setBounds(10, 274, 233, 36);
		w_appointment.add(btn_workerSelect);
		
		JButton btn_addrandevu = new JButton("Randevu Al");
		btn_addrandevu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRandevu = Integer.parseInt(table_workdate.getValueAt(table_workdate.getSelectedRow(), 0).toString());
				selectedRandevuDate = table_workdate.getValueAt(table_workdate.getSelectedRow(), 2).toString();
				System.out.println("1");
				if(selectedRandevu >= 0) {
					try {
						System.out.println("2");
						boolean control = patient.makeRandevu(selectedRandevu,selectedDoctorID,selectedDoctorName,
								patient.getUserID(),patient.getUserName(),selectedRandevuDate,selectedClinicName);
						if(control) {
							Helper.showMessage("success");
							patient.updateWorkDateList(selectedRandevu);
						}else {
							
						}
					} catch (Exception e2) {
						// TODO: handle exception
					} finally {
						try {
							DefaultTableModel clearModel = (DefaultTableModel) table_workdate.getModel();
							clearModel.setRowCount(0);
							for(int i =0; i< workdate.getWorkDateList(selectedDoctorID).size();i++) {
								workdateData[0] = workdate.getWorkDateList(selectedDoctorID).get(i).getWorkID();
								workdateData[1] = workdate.getWorkDateList(selectedDoctorID).get(i).getWorkDoctorName();
								workdateData[2] = workdate.getWorkDateList(selectedDoctorID).get(i).getWorkDate();
								workdateModel.addRow(workdateData);
								
								
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						try {
							DefaultTableModel clearModel = (DefaultTableModel) table_randevular.getModel();
							clearModel.setRowCount(0);
							for(int i = 0; i < randevu.getRandevuList(patient.getUserID()).size(); i++) {
								randevuData[0] = randevu.getRandevuList(patient.getUserID()).get(i).getRandevuID();
								randevuData[1] = randevu.getRandevuList(patient.getUserID()).get(i).getRandevuDoctorName();
								randevuData[2] = randevu.getRandevuList(patient.getUserID()).get(i).getRandevuDate();
								randevuModel.addRow(randevuData);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}else {
					Helper.showMessage("Lütfen bir randevu seçiniz.");
				}
			}
		});
		btn_addrandevu.setForeground(Color.WHITE);
		btn_addrandevu.setBackground(Color.GRAY);
		btn_addrandevu.setBounds(533, 40, 120, 33);
		w_appointment.add(btn_addrandevu);
		
		JPanel w_randevuexist = new JPanel();
		w_randevuexist.setLayout(null);
		w_randevuexist.setBackground(new Color(0, 139, 139));
		w_tab.addTab("Randevularým", null, w_randevuexist, null);
		
		JScrollPane w_scrollrandevu = new JScrollPane();
		w_scrollrandevu.setBounds(0, 22, 663, 299);
		w_randevuexist.add(w_scrollrandevu);
		
		clinicMenu = new JPopupMenu();
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(deleteMenu);
		deleteMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedID = Integer.parseInt(table_randevular.getValueAt(table_randevular.getSelectedRow(), 0).toString());
				if(Helper.confirm("sure")) {
					try {
						if(patient.deleteRandevu(selectedID)) {
							Helper.showMessage("success");
							DefaultTableModel clearModel = (DefaultTableModel) table_randevular.getModel();
							clearModel.setRowCount(0);
							try {
								for(int i = 0; i < randevu.getRandevuList(patient.getUserID()).size(); i++) {
									randevuData[0] = randevu.getRandevuList(patient.getUserID()).get(i).getRandevuID();
									randevuData[1] = randevu.getRandevuList(patient.getUserID()).get(i).getRandevuDoctorName();
									randevuData[2] = randevu.getRandevuList(patient.getUserID()).get(i).getRandevuDate();
									randevuModel.addRow(randevuData);
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}else {
							Helper.showMessage("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		
		table_randevular = new JTable(randevuModel);
		table_randevular.setComponentPopupMenu(clinicMenu);
		table_randevular.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_randevular.rowAtPoint(point);
				System.out.println("týklandý");
				table_randevular.setRowSelectionInterval(selectedRow, selectedRow);}} );
		w_scrollrandevu.setViewportView(table_randevular);
		
		JButton btnNewButton = new JButton("", null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("yenile");
				DefaultTableModel clearModel = (DefaultTableModel) table_randevular.getModel();
				clearModel.setRowCount(0);
				try {
					for(int i = 0; i < randevu.getRandevuList(patient.getUserID()).size(); i++) {
						randevuData[0] = randevu.getRandevuList(patient.getUserID()).get(i).getRandevuID();
						randevuData[1] = randevu.getRandevuList(patient.getUserID()).get(i).getRandevuDoctorName();
						randevuData[2] = randevu.getRandevuList(patient.getUserID()).get(i).getRandevuDate();
						randevuModel.addRow(randevuData);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		
		
		
		
		btnNewButton.setBounds(643, 0, 20, 20);
		w_randevuexist.add(btnNewButton);
		
		JLabel lbl_doctorlist_1 = new JLabel("Yenile");
		lbl_doctorlist_1.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_doctorlist_1.setForeground(Color.WHITE);
		lbl_doctorlist_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lbl_doctorlist_1.setBounds(596, 2, 43, 18);
		w_randevuexist.add(lbl_doctorlist_1);
	}
	
	
	
	
}

package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Clinic;
import Model.HeadDoctor;
import Model.Worker;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class HeadDoctorGUI extends JFrame {

	//Object
	static HeadDoctor headdoctor = new HeadDoctor();
	static Clinic clinic = new Clinic();
	static Worker worker = new Worker();
	//-----------------------
	
	
	private JPanel contentPane;
	
	private JTextField fld_deletedoctorID;
	private JTextField fld_doctorTc;
	private JTextField fld_doctorName;
	private JPasswordField fld_doctorPassword;
	private JTextField fld_clinicName;
	
	//Doctor
	private JTable table_doctor;
	private DefaultTableModel tabledoctorModel = null;
	private Object[] tabledoctorData = null;
	//-----------------------
	
	//Clinic-----------------
	private JTable table_cliniclist;
	private DefaultTableModel tablecliniclistModel = null;
	private Object[] tablecliniclistData = null;
	private JPopupMenu clinicMenu;
	//-----------------------
	
	//Doctor-clinic
	private JTable table_doctorListForClinic;
	private DefaultTableModel tabledoctorListForClinicModel = null;
	private Object[] tabledoctorListForClinicData = null;
	//-----------------------
	
	//Randevu
	private JTable table_randevulist;
	private DefaultTableModel tablerandevulistModel = null;
	private Object[] tablerandevulistData = null;
	//-----------------------
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HeadDoctorGUI frame = new HeadDoctorGUI(headdoctor);
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
	public HeadDoctorGUI(HeadDoctor headdoctor) {
		setResizable(false);
		
		
	// Table Model---
		
		//Doctor
		tabledoctorModel = new DefaultTableModel();
		Object[] columndoctorTableHead = new Object[4];
		columndoctorTableHead[0] = "ID";
		columndoctorTableHead[1] = "AD SOYAD";
		columndoctorTableHead[2] = "TC NO";
		columndoctorTableHead[3] = "PAROLA";
		tabledoctorModel.setColumnIdentifiers(columndoctorTableHead);
		
		tabledoctorData = new Object[4];
		for(int i = 0; i < headdoctor.getDoctorList().size(); i++) {
			tabledoctorData[0] = headdoctor.getDoctorList().get(i).getUserID();
			tabledoctorData[1] = headdoctor.getDoctorList().get(i).getUserName();
			tabledoctorData[2] = headdoctor.getDoctorList().get(i).getUserTC();
			tabledoctorData[3] = headdoctor.getDoctorList().get(i).getUserPassword();
			tabledoctorModel.addRow(tabledoctorData);
		}
		//-------------------------------------------------------------
		
		//Clinic
		tablecliniclistModel = new DefaultTableModel();
		Object[] columnclinicTableHead = new Object[2];
		columnclinicTableHead[0] = "ID";
		columnclinicTableHead[1] = "KLÝNÝK";
		tablecliniclistModel.setColumnIdentifiers(columnclinicTableHead);
		
		tablecliniclistData = new Object[2];
		for(int i =0; i < clinic.getClinicList().size(); i++) {
			tablecliniclistData[0] = clinic.getClinicList().get(i).getClinicID();
			tablecliniclistData[1] = clinic.getClinicList().get(i).getClinicName();
			tablecliniclistModel.addRow(tablecliniclistData);
		}
		//--------------------------------------------------------------------
		
		
		//tablodan klinik seçip doktoru selectmenuden seçip listeliyoruz, head kýsmý burada data kýsmý ekle butonunda ---------------------------
		tabledoctorListForClinicModel = new DefaultTableModel();
		Object[] columndoctorlistforclinicTableHead = new Object[2];
		columndoctorlistforclinicTableHead[0] = "ID";
		columndoctorlistforclinicTableHead[1] = "KLÝNÝKTEKÝ DOKTORLAR";
		tabledoctorListForClinicModel.setColumnIdentifiers(columndoctorlistforclinicTableHead);
		
		Object[] tabledoctorListForClinicData = new Object[2];
		
		//----------------------------------------
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	// ---Table Model
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_welcome = new JLabel("Hoþ geldiniz, Sayýn " + headdoctor.getUserName());
		lbl_welcome.setBounds(10, 11, 240, 25);
		contentPane.add(lbl_welcome);
		
		JButton btn_out = new JButton("\u00C7\u0131k\u0131\u015F");
		btn_out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setVisible(true);
				dispose();
			}
		});
		btn_out.setBounds(700, 12, 74, 23);
		contentPane.add(btn_out);
		
		JTabbedPane w_pane = new JTabbedPane(JTabbedPane.TOP);
		w_pane.setBounds(10, 47, 764, 503);
		contentPane.add(w_pane);
		
		JPanel w_doctorManagement = new JPanel();
		w_pane.addTab("Doktor Yönetimi", null, w_doctorManagement, null);
		w_doctorManagement.setLayout(null);
		
		JScrollPane w_scrollDoctorList = new JScrollPane();
		w_scrollDoctorList.setBounds(10, 11, 532, 453);
		w_doctorManagement.add(w_scrollDoctorList);
		
		table_doctor = new JTable(tabledoctorModel);
		w_scrollDoctorList.setViewportView(table_doctor);
		table_doctor.getColumnModel().getColumn(0).setPreferredWidth(1);
		
		
		JLabel lbl_doctorname = new JLabel("Ad Soyad");
		lbl_doctorname.setBounds(622, 12, 46, 14);
		w_doctorManagement.add(lbl_doctorname);
		
		JLabel lbl_doctortc = new JLabel("TC Numaras\u0131");
		lbl_doctortc.setBounds(606, 104, 73, 14);
		w_doctorManagement.add(lbl_doctortc);
		
		JLabel lbl_doctorpassword = new JLabel("Parola");
		lbl_doctorpassword.setBounds(622, 211, 46, 14);
		w_doctorManagement.add(lbl_doctorpassword);
		
		JButton btn_addDoctor = new JButton("Ekle");
		btn_addDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doctorName.getText().length() == 0 || fld_doctorTc.getText().length() == 0 || fld_doctorPassword.getText().length() == 0 ) {
					Helper.showMessage("fill");
				}else {
					try {
						boolean control = headdoctor.addDoctor(fld_doctorName.getText(), fld_doctorTc.getText() , fld_doctorPassword.getText());
						if(control) {
							Helper.showMessage("success");
							updateTableDoctor();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btn_addDoctor.setBounds(606, 290, 89, 23);
		w_doctorManagement.add(btn_addDoctor);
		
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_deletedoctorID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(),0).toString());
				} catch (Exception e2) {}
				
				}
		});
		
		table_doctor.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType() == TableModelEvent.UPDATE) {
					int selectedID = Integer.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selectedName = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selectedTCNO = table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selectedPassword = table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();
					
					try {
						headdoctor.updateDoctor(selectedID,selectedName, selectedTCNO, selectedPassword);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		JButton btn_deleteDoctor = new JButton("Sil");
		btn_deleteDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fld_deletedoctorID.getText().length() == 0) {
					Helper.showMessage("Bir doktor seçiniz.");
				}else {
					
					if(Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_deletedoctorID.getText());
						try {
							boolean control = headdoctor.deleteDoctor(selectID);
							if(control) {
								Helper.showMessage("success");
								fld_deletedoctorID.setText(null);
								updateTableDoctor();
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						} 
					}
					
				}
				
			}
		});
		btn_deleteDoctor.setBounds(606, 416, 89, 23);
		w_doctorManagement.add(btn_deleteDoctor);
		
		JLabel lbl_doctorusername = new JLabel("Kullan\u0131c\u0131 Ad\u0131");
		lbl_doctorusername.setBounds(622, 347, 57, 14);
		w_doctorManagement.add(lbl_doctorusername);
		
		fld_deletedoctorID = new JTextField();
		fld_deletedoctorID.setBounds(609, 385, 86, 20);
		w_doctorManagement.add(fld_deletedoctorID);
		fld_deletedoctorID.setColumns(10);
		
		fld_doctorTc = new JTextField();
		fld_doctorTc.setBounds(606, 148, 86, 20);
		w_doctorManagement.add(fld_doctorTc);
		fld_doctorTc.setColumns(10);
		
		fld_doctorName = new JTextField();
		fld_doctorName.setBounds(606, 52, 86, 20);
		w_doctorManagement.add(fld_doctorName);
		fld_doctorName.setColumns(10);
		
		fld_doctorPassword = new JPasswordField();
		fld_doctorPassword.setBounds(606, 238, 89, 23);
		w_doctorManagement.add(fld_doctorPassword);
		
		JPanel w_clinicManagement = new JPanel();
		w_pane.addTab("Klinik Yönetimi", null, w_clinicManagement, null);
		w_clinicManagement.setLayout(null);
		
		JScrollPane w_scrollClinicList = new JScrollPane();
		w_scrollClinicList.setBounds(10, 11, 265, 453);
		w_clinicManagement.add(w_scrollClinicList);
		
		
		
		//pop up menu-------------------------------
		
		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);
		
		updateMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedID = Integer.parseInt(table_cliniclist.getValueAt(table_cliniclist.getSelectedRow(), 0).toString());
				Clinic selectedClinic = clinic.getFetch(selectedID);
				UpdateClinicGUI updateClinicGUI = new UpdateClinicGUI(selectedClinic);
				updateClinicGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateClinicGUI.setVisible(true);
				updateClinicGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateTableClinic();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
						
			}
		});
		deleteMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedID = Integer.parseInt(table_cliniclist.getValueAt(table_cliniclist.getSelectedRow(), 0).toString());
				if(Helper.confirm("sure")) {
					try {
						if(headdoctor.deleteClinic(selectedID)) {
							Helper.showMessage("success");
							updateTableClinic();
						}else {
							Helper.showMessage("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		table_cliniclist = new JTable(tablecliniclistModel);
		table_cliniclist.setComponentPopupMenu(clinicMenu);
		table_cliniclist.getColumnModel().getColumn(0).setPreferredWidth(1);
		table_cliniclist.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_cliniclist.rowAtPoint(point);
				System.out.println("týklandý");
				table_cliniclist.setRowSelectionInterval(selectedRow, selectedRow);}} );
		
				
				
		//-----------------------------------------------
		w_scrollClinicList.setViewportView(table_cliniclist);
		
		JLabel lbl_clinicname = new JLabel("Klinik Ad\u0131");
		lbl_clinicname.setBounds(331, 27, 67, 14);
		w_clinicManagement.add(lbl_clinicname);
		
		fld_clinicName = new JTextField();
		fld_clinicName.setBounds(331, 52, 86, 20);
		w_clinicManagement.add(fld_clinicName);
		fld_clinicName.setColumns(10);
		
		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_clinicName.getText().length() == 0) {
					Helper.showMessage("fill");
				}else {
					try {
						boolean control = headdoctor.addClinic(fld_clinicName.getText());
						if(control) {
							Helper.showMessage("success");
							updateTableClinic();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		
		
		
		
		
		
		btn_addClinic.setBounds(328, 94, 89, 23);
		w_clinicManagement.add(btn_addClinic);
		
		JLabel lbl_clinicname2 = new JLabel("Klinik Ad\u0131");
		lbl_clinicname2.setBounds(331, 172, 67, 14);
		w_clinicManagement.add(lbl_clinicname2);
		
		JButton btn_pickClinic = new JButton("Se\u00E7");
		btn_pickClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_cliniclist.getSelectedRow();
				if(selectedRow >= 0) {
					String selectedClinic = table_cliniclist.getModel().getValueAt(selectedRow, 0).toString();
					int selectedClinicID = Integer.parseInt(selectedClinic);
					try {
							DefaultTableModel clearModel = (DefaultTableModel) table_doctorListForClinic.getModel();
							clearModel.setRowCount(0);
							for(int i = 0; i < worker.getWorkerAtClinicList(selectedClinicID).size(); i++){
								tabledoctorListForClinicData[0] = worker.getWorkerAtClinicList(selectedClinicID).get(i).getUserID(); 
								tabledoctorListForClinicData[1] = worker.getWorkerAtClinicList(selectedClinicID).get(i).getUserName();
								tabledoctorListForClinicModel.addRow(tabledoctorListForClinicData);
							}
							table_doctorListForClinic.setModel(tabledoctorListForClinicModel);
						} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		});
		btn_pickClinic.setBounds(328, 211, 89, 23);
		w_clinicManagement.add(btn_pickClinic);
		
		JComboBox select_doctorToClinic = new JComboBox();
		select_doctorToClinic.setBounds(331, 288, 86, 23);
		for(int i = 0 ; i < headdoctor.getDoctorList().size() ; i++) {
			select_doctorToClinic.addItem(new Item(headdoctor.getDoctorList().get(i).getUserID(),headdoctor.getDoctorList().get(i).getUserName()));
		}
		 select_doctorToClinic.addActionListener(e -> {
			JComboBox jcombobox = (JComboBox) e.getSource();
			Item item = (Item) jcombobox.getSelectedItem();
			//konsolda görmek için
			System.out.println(item.getKey() + " : "+item.getValue());
		});
		w_clinicManagement.add(select_doctorToClinic);
		
		
		
		JScrollPane w_scrollDoctorListForClinic = new JScrollPane();
		w_scrollDoctorListForClinic.setBounds(457, 11, 292, 453);
		w_clinicManagement.add(w_scrollDoctorListForClinic);
		
		table_doctorListForClinic = new JTable(tabledoctorListForClinicModel);
		w_scrollDoctorListForClinic.setViewportView(table_doctorListForClinic);
		table_doctorListForClinic.getColumnModel().getColumn(0).setPreferredWidth(1);
		
		JButton btn_addDoctorToClinic = new JButton("Ekle");
		btn_addDoctorToClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_cliniclist.getSelectedRow();
				if(selectedRow >= 0) {
					//tablodan clinic
					String selectedClinic = table_cliniclist.getModel().getValueAt(selectedRow, 0).toString();
					int selectedClinicID = Integer.parseInt(selectedClinic);
					//selectmenuden doktor
					Item doctoritem = (Item) select_doctorToClinic.getSelectedItem();
					try {
						boolean control = headdoctor.addWorker(doctoritem.getKey(), selectedClinicID);
						if(control) {
							Helper.showMessage("success");
							DefaultTableModel clearModel = (DefaultTableModel) table_doctorListForClinic.getModel();
							clearModel.setRowCount(0);
							for(int i = 0; i < worker.getWorkerAtClinicList(selectedClinicID).size(); i++){
								tabledoctorListForClinicData[0] = worker.getWorkerAtClinicList(selectedClinicID).get(i).getUserID(); 
								tabledoctorListForClinicData[1] = worker.getWorkerAtClinicList(selectedClinicID).get(i).getUserName();
								tabledoctorListForClinicModel.addRow(tabledoctorListForClinicData);
							}
							table_doctorListForClinic.setModel(tabledoctorListForClinicModel);
						}else {
							DefaultTableModel clearModel = (DefaultTableModel) table_doctorListForClinic.getModel();
							clearModel.setRowCount(0);
							for(int i = 0; i < worker.getWorkerAtClinicList(selectedClinicID).size(); i++){
								tabledoctorListForClinicData[0] = worker.getWorkerAtClinicList(selectedClinicID).get(i).getUserID(); 
								tabledoctorListForClinicData[1] = worker.getWorkerAtClinicList(selectedClinicID).get(i).getUserName();
								tabledoctorListForClinicModel.addRow(tabledoctorListForClinicData);
							}
							table_doctorListForClinic.setModel(tabledoctorListForClinicModel);
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}else {
					Helper.showMessage("Lütfen bir klinik seçin.");
				}
				
			}
		});
		btn_addDoctorToClinic.setBounds(331, 335, 89, 23);
		w_clinicManagement.add(btn_addDoctorToClinic);
		
		JPanel w_RandevuManagement = new JPanel();
		w_pane.addTab("Randevu Yönetimi", null, w_RandevuManagement, null);
		w_RandevuManagement.setLayout(null);
		
		JScrollPane w_scrollRandevuList = new JScrollPane();
		w_scrollRandevuList.setBounds(10, 11, 623, 453);
		w_RandevuManagement.add(w_scrollRandevuList);
		
		table_randevulist = new JTable();
		w_scrollRandevuList.setViewportView(table_randevulist);
		
		JButton btnNewButton_6 = new JButton("New button");
		btnNewButton_6.setBounds(643, 22, 106, 23);
		w_RandevuManagement.add(btnNewButton_6);
		
		JButton btnNewButton_6_1 = new JButton("New button");
		btnNewButton_6_1.setBounds(643, 81, 106, 23);
		w_RandevuManagement.add(btnNewButton_6_1);
		
		JButton btnNewButton_6_1_1 = new JButton("New button");
		btnNewButton_6_1_1.setBounds(643, 136, 106, 23);
		w_RandevuManagement.add(btnNewButton_6_1_1);
	}
	
	public void updateTableDoctor() throws SQLException {
		
		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);
		
		for(int i = 0; i < headdoctor.getDoctorList().size(); i++) {
			tabledoctorData[0] = headdoctor.getDoctorList().get(i).getUserID();
			tabledoctorData[1] = headdoctor.getDoctorList().get(i).getUserName();
			tabledoctorData[2] = headdoctor.getDoctorList().get(i).getUserTC();
			tabledoctorData[3] = headdoctor.getDoctorList().get(i).getUserPassword();
			tabledoctorModel.addRow(tabledoctorData);
		}
	}
	
	public void updateTableClinic() throws SQLException {
		
		DefaultTableModel clearModel = (DefaultTableModel) table_cliniclist.getModel();
		clearModel.setRowCount(0);
		
		for(int i =0; i < clinic.getClinicList().size(); i++) {
			tablecliniclistData[0] = clinic.getClinicList().get(i).getClinicID();
			tablecliniclistData[1] = clinic.getClinicList().get(i).getClinicName();
			tablecliniclistModel.addRow(tablecliniclistData);
		}
	}
}

package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import Helper.Helper;

public class HeadDoctor extends User{

	Connection localconnection = dbconnection.connectionDB();
	Statement statement = null;
	ResultSet resultset = null;
	PreparedStatement preparedStatement = null;
	
	public HeadDoctor() {}

	public HeadDoctor(int userID, String userTC, String userPassword, String userName, String userType) {
		super(userID, userTC, userPassword, userName, userType);
	}
	
	
	
	public ArrayList<User> getDoctorList() {
		
		ArrayList<User> doctorlist = new ArrayList<>();
		
		User object;
		
		
		try {
			
			statement = localconnection.createStatement();
			resultset = statement.executeQuery("SELECT * FROM user WHERE type_data='doctor'");

			while(resultset.next()) {
				object = new User(resultset.getInt("id_data"),
						resultset.getString("tcno_data"),
						resultset.getString("password_data"),
						resultset.getString("name_data"),
						resultset.getString("type_data"));
				
				doctorlist.add(object);
			
				}
		} catch (Exception e) {}
		
		return doctorlist;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean addDoctor(String comname,String comtc, String compassword) throws SQLException {
	
		boolean process = false;
		String query = "INSERT INTO user (name_data,tcno_data,password_data,type_data) VALUES (?,?,?,?)";
		try {
			
			statement = localconnection.createStatement();
			preparedStatement = localconnection.prepareStatement(query);
			preparedStatement.setString(1, comname);
			preparedStatement.setString(2, comtc);
			preparedStatement.setString(3, compassword);
			preparedStatement.setString(4, "doctor");
			preparedStatement.executeUpdate();
			process = true;
			System.out.println("Doktor Eklendi");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(process)return true;
		else return false;
	}
	
	
	public boolean deleteDoctor(int comID) throws SQLException {
	
	
		boolean process = false;
		String query = "DELETE FROM user WHERE id_data =" + comID;
		try {
			
			statement = localconnection.createStatement();
			preparedStatement = localconnection.prepareStatement(query);
			preparedStatement.executeUpdate();
			process = true;
			System.out.println("Doktor Silindi");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(process)return true;
		else return false;
	}
	
	
	public boolean updateDoctor(int comid,String comname,String comtc, String compassword) throws SQLException {
		
		boolean process = false;
		String query = "UPDATE user SET name_data = ?, tcno_data = ?, password_data = ? WHERE id_data = ?";
		try {
		
			statement = localconnection.createStatement();
			preparedStatement = localconnection.prepareStatement(query);
			preparedStatement.setString(1, comname);
			preparedStatement.setString(2, comtc);
			preparedStatement.setString(3, compassword);
			preparedStatement.setInt(4, comid);
			preparedStatement.executeUpdate();
			process = true;
			System.out.println("Doktor Güncellendi");
			
		
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		if(process) return true;
		else return false;
	}
	
	public boolean addClinic(String comname) throws SQLException {
		
		
		boolean process = false;
		String query = "INSERT INTO clinic (clinic_name_data) VALUES (?)";
		try {
			
			
			statement = localconnection.createStatement();
			preparedStatement = localconnection.prepareStatement(query);
			preparedStatement.setString(1, comname);
			
			preparedStatement.executeUpdate();
			process = true;
			System.out.println("Klinik Eklendi");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(process)return true;
		else return false;
	}
	
	
	public boolean deleteClinic(int comID) throws SQLException {
		
		boolean process = false;
		String query = "DELETE FROM clinic WHERE id_data =" + comID;
		try {
			
			
			statement = localconnection.createStatement();
			preparedStatement = localconnection.prepareStatement(query);
			preparedStatement.executeUpdate();
			process = true;
			System.out.println("Klinik Silindi");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}  
		if(process)return true;
		else return false;
	}
	
	public boolean updateClinic(int comid,String comname) throws SQLException {
		
		PreparedStatement preparedStatement = null;
		boolean process = false;
		String query = "UPDATE clinic SET clinic_name_data = ? WHERE id_data = ?";
		try {
			
			
			statement = localconnection.createStatement();
			preparedStatement = localconnection.prepareStatement(query);
			preparedStatement.setString(1, comname);
			preparedStatement.setInt(2, comid);
			preparedStatement.executeUpdate();
			process = true;
			System.out.println("Klinik Güncellendi");
			
		
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		if(process) return true;
		else return false;
	}
	
	
	public boolean addWorker(int comdoctoriddata,int comcliniciddata) throws SQLException {
		
		boolean process = false;
		int count = 0;
		String query = "INSERT INTO worker (clinic_id_data,user_id_data) VALUES (?,?)";
		try {
			
			
			statement = localconnection.createStatement();
			resultset = statement.executeQuery("SELECT * FROM worker WHERE clinic_id_data = "+ comcliniciddata + " AND user_id_data = "+ comdoctoriddata );
			while(resultset.next()) {
				count++;
			}
			if(count == 0) {
			statement = localconnection.createStatement();
			preparedStatement = localconnection.prepareStatement(query);
			preparedStatement.setInt(1, comcliniciddata);
			preparedStatement.setInt(2, comdoctoriddata);
			
			preparedStatement.executeUpdate();
			
			System.out.println("Worker Eklendi");
			process = true;
			}else {
			Helper.showMessage("Bu iþlem daha önce yapýldý.");}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(process)return true;
		else return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

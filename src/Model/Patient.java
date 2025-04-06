package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.Helper;

public class Patient extends User{

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(int userID, String userTC, String userPassword, String userName, String userType) {
		super(userID, userTC, userPassword, userName, userType);
		// TODO Auto-generated constructor stub
	}
	
	Connection localconnection = dbconnection.connectionDB();
	Statement statement = null;
	ResultSet resultset = null;
	PreparedStatement preparedStatement = null;
	
	
	
	
	
	public boolean register(String comtc, String compassword, String comname) throws SQLException {
		
		int key = 0;
		boolean dublicate = false;
		String query = "INSERT INTO user" + "(tcno_data,password_data,name_data,type_data) VALUES" + "(?,?,?,?)";
		
		try {
			System.out.println("register baðlandý");
			statement = localconnection.createStatement();
			resultset = statement.executeQuery("SELECT * FROM user WHERE tcno_data ='"+comtc+"'");

			while (resultset.next()) {
				dublicate = true;
				System.out.println("arttý");
				Helper.showMessage("Bu T.C. numarasýna kayýtlý bir üyelik bulunmaktadýr.");
				break;

			}
			if (!dublicate) {
				preparedStatement = localconnection.prepareStatement(query);
				preparedStatement.setString(1, comtc);
				preparedStatement.setString(2, compassword);
				preparedStatement.setString(3, comname);
				preparedStatement.setString(4, "patient");
				preparedStatement.executeUpdate();
				key = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (key == 1)
			return true;
		else
			return false;
	}
	
	
	public boolean makeRandevu(int comrandevuid,int comdoctorid,String comdoctorname,int compatientid,String compatientname,String comrandevudate,String comclinicname) throws SQLException {
		
		boolean process = false;
		boolean dublicate = false;
		String query = "INSERT INTO randevu (doctor_id_data,doctor_name_data,hasta_id_data,hasta_name_data,randevu_date_data,clinic_name_data) VALUES (?,?,?,?,?,?)";
		try {
			
			statement = localconnection.createStatement();
			resultset = statement.executeQuery("SELECT * FROM randevu WHERE id_data =" + comrandevuid);
			
			while (resultset.next()) {
				dublicate = true;
				Helper.showMessage("Bu randevu saati dolu.");
				break;

			}
			if (!dublicate) {
			
			
			
			statement = localconnection.createStatement();
			preparedStatement = localconnection.prepareStatement(query);
			preparedStatement.setInt(1, comdoctorid);
			preparedStatement.setString(2, comdoctorname);
			preparedStatement.setInt(3, compatientid);
			preparedStatement.setString(4, compatientname);
			preparedStatement.setString(5, comrandevudate);
			preparedStatement.setString(6, comclinicname);
			preparedStatement.executeUpdate();
			process = true;
			System.out.println("Randevu Eklendi");
			
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(process)return true;
		else return false;
	}
	
	public boolean deleteRandevu (int comrandevuid) throws SQLException {
		
		
		boolean process = false;
		String query = "DELETE FROM randevu WHERE id_data =" + comrandevuid;
		try {
			
			statement = localconnection.createStatement();
			preparedStatement = localconnection.prepareStatement(query);
			preparedStatement.executeUpdate();
			process = true;
			System.out.println("Randevu Silindi");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(process)return true;
		else return false;
	}
	
	
	
	public boolean updateWorkDateList(int comrandevuid) throws SQLException {
		
		boolean process = false;
		String query = "UPDATE workhour SET status_data = ? WHERE id_data= " + comrandevuid;
		try {
		
			statement = localconnection.createStatement();
			preparedStatement = localconnection.prepareStatement(query);
			preparedStatement.setString(1, "passive");
			preparedStatement.executeUpdate();
			process = true;
			System.out.println("Randevu Artýk Passive");
			
		
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		if(process) return true;
		else return false;
	}

}

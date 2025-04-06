package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.Helper;

public class Doctor extends User {

	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Doctor(int userID, String userTC, String userPassword, String userName, String userType) {
		super(userID, userTC, userPassword, userName, userType);
		// TODO Auto-generated constructor stub
	}

	Connection localconnection = dbconnection.connectionDB();
	Statement statement = null;
	ResultSet resultset = null;
	PreparedStatement preparedStatement = null;

	
	
	
	public boolean addWorkDate(int comdoctorid, String comdoctorname, String comdate) throws SQLException {

		int key = 0;
		int count = 0;
		String query = "INSERT INTO workhour (doctor_id_data,doctor_name_data,workdate_data) VALUES (?,?,?)";

		try {
			System.out.println("baðlandý");
			statement = localconnection.createStatement();
			resultset = statement.executeQuery("SELECT * FROM workhour WHERE status_data = 'active' AND doctor_id_data =" + comdoctorid
					+ " AND workdate_data='" + comdate + "'");

			while (resultset.next()) {
				count++;
				Helper.showMessage("Bu tarih mevcut.");
				break;

			}
			if (count == 0) {
				preparedStatement = localconnection.prepareStatement(query);
				preparedStatement.setInt(1, comdoctorid);
				preparedStatement.setString(2, comdoctorname);
				preparedStatement.setString(3, comdate);
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
	
	
	public boolean deleteWorkDate(int comdateid) throws SQLException {
	
		String query = "DELETE FROM workhour WHERE id_data = ?";
		boolean key = false;
		try {
			statement = localconnection.createStatement();
			preparedStatement = localconnection.prepareStatement(query);
			preparedStatement.setInt(1, comdateid);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		if(key) return true;
		else return false;
	}

}

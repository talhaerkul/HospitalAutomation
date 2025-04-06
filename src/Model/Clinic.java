package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Clinic {

	private int clinicID;
	String clinicName;
	
	public Clinic() {}
	
	public Clinic(int clinicID, String clinicName) {
		super();
		this.clinicID = clinicID;
		this.clinicName = clinicName;
	}

	 
	DBConnection dbconnection = new DBConnection();
	Connection localconnection = dbconnection.connectionDB();
	Statement statement = null;
	ResultSet resultset = null;
	
	
	public ArrayList<Clinic> getClinicList(){
		
		ArrayList<Clinic> cliniclist = new ArrayList<>();
		Clinic object;
		
		try {
			
			statement = localconnection.createStatement();
			resultset = statement.executeQuery("SELECT * FROM clinic");
			while(resultset.next()) {
				object = new Clinic(resultset.getInt("id_data"), resultset.getString("clinic_name_data"));
				cliniclist.add(object);
			}
		} catch (Exception e) {
			}
		return cliniclist;
	}
	
	
	public Clinic getFetch(int comid) {
		Clinic object = new Clinic();
		try {
			
			statement = localconnection.createStatement();
			resultset = statement.executeQuery("SELECT * FROM clinic WHERE id_data =" + comid); 
			while(resultset.next()) {
				object.setClinicID(resultset.getInt("id_data"));
				object.setClinicName(resultset.getString("clinic_name_data"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return object;
	}

	
	
	
	
	
	
	
	
	
	

	
	
	
	
	public int getClinicID() {
		return clinicID;
	}

	public void setClinicID(int clinicID) {
		this.clinicID = clinicID;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	
	
	
	
}































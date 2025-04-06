package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Randevu {

	
	DBConnection dbconnection = new DBConnection();
	Connection localconnection = dbconnection.connectionDB();
	Statement statement = null;
	ResultSet resultset = null;
	
	private int randevuID;
	int randevuDoctorID,randevuPatientID;
	String randevuDoctorName,randevuHastaName,randevuClinicName,randevuDate;
	
	public Randevu() {
		
	}
	public Randevu(int randevuID, int randevuDoctorID, int randevuPatientID, String randevuDoctorName,
			String randevuHastaName, String randevuClinicName, String randevuDate) {
		super();
		this.randevuID = randevuID;
		this.randevuDoctorID = randevuDoctorID;
		this.randevuPatientID = randevuPatientID;
		this.randevuDoctorName = randevuDoctorName;
		this.randevuHastaName = randevuHastaName;
		this.randevuClinicName = randevuClinicName;
		this.randevuDate = randevuDate;
	}
	
	
	public ArrayList<Randevu> getRandevuList(int compatientid) throws SQLException {
		ArrayList<Randevu> randevulist = new ArrayList<>();
		Randevu object;
		
		try {
			statement = localconnection.createStatement();
			resultset = statement.executeQuery("SELECT * FROM randevu WHERE hasta_id_data =" +compatientid);
			while (resultset.next()) {
				object = new Randevu();
				object.setRandevuID(resultset.getInt("id_data"));
				object.setRandevuDoctorID(resultset.getInt("doctor_id_data"));
				object.setRandevuHastaName(resultset.getString("hasta_name_data"));
				object.setRandevuPatientID(resultset.getInt("hasta_id_data"));
				object.setRandevuDoctorName(resultset.getString("doctor_name_data"));
				object.setRandevuClinicName(resultset.getString("clinic_name_data"));
				object.setRandevuDate(resultset.getString("randevu_date_data"));
				randevulist.add(object);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return randevulist;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public int getRandevuID() {
		return randevuID;
	}
	public void setRandevuID(int randevuID) {
		this.randevuID = randevuID;
	}
	public int getRandevuDoctorID() {
		return randevuDoctorID;
	}
	public void setRandevuDoctorID(int randevuDoctorID) {
		this.randevuDoctorID = randevuDoctorID;
	}
	public int getRandevuPatientID() {
		return randevuPatientID;
	}
	public void setRandevuPatientID(int randevuPatientID) {
		this.randevuPatientID = randevuPatientID;
	}
	public String getRandevuDoctorName() {
		return randevuDoctorName;
	}
	public void setRandevuDoctorName(String randevuDoctorName) {
		this.randevuDoctorName = randevuDoctorName;
	}
	public String getRandevuHastaName() {
		return randevuHastaName;
	}
	public void setRandevuHastaName(String randevuHastaName) {
		this.randevuHastaName = randevuHastaName;
	}
	public String getRandevuClinicName() {
		return randevuClinicName;
	}
	public void setRandevuClinicName(String randevuClinicName) {
		this.randevuClinicName = randevuClinicName;
	}
	public String getRandevuDate() {
		return randevuDate;
	}
	public void setRandevuDate(String randevuDate) {
		this.randevuDate = randevuDate;
	}
	
	
	
	
}

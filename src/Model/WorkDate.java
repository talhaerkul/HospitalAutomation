package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class WorkDate {

	DBConnection dbconnection = new DBConnection();
	Connection localconnection = dbconnection.connectionDB();
	Statement statement = null;
	ResultSet resultset = null;
	
	
	private int workID;
	int workDoctorID;
	String workDoctorName,workDate,workStatus;
	
	
	public WorkDate() {
		
	}
	
	public WorkDate(int workID, int workDoctorID, String workDoctorName, String workDate, String workStatus) {
		super();
		this.workID = workID;
		this.workDoctorID = workDoctorID;
		this.workDoctorName = workDoctorName;
		this.workDate = workDate;
		this.workStatus = workStatus;
	}
	
	
	
	public ArrayList<WorkDate> getWorkDateList(int comdoctorid) throws SQLException {
		ArrayList<WorkDate> workdatelist = new ArrayList<>();
		WorkDate object;
		
		try {
			statement = localconnection.createStatement();
			resultset = statement.executeQuery("SELECT * FROM workhour WHERE status_data = 'active' AND doctor_id_data = " + comdoctorid);
			while (resultset.next()) {
				object = new WorkDate();
				object.setWorkID(resultset.getInt("id_data"));
				object.setWorkDoctorID(resultset.getInt("doctor_id_data"));
				object.setWorkDoctorName(resultset.getString("doctor_name_data"));
				object.setWorkDate(resultset.getString("workdate_data"));
				object.setWorkStatus(resultset.getString("status_data"));
				workdatelist.add(object);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workdatelist;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public int getWorkID() {
		return workID;
	}

	public void setWorkID(int workID) {
		this.workID = workID;
	}

	public int getWorkDoctorID() {
		return workDoctorID;
	}

	public void setWorkDoctorID(int workDoctorID) {
		this.workDoctorID = workDoctorID;
	}

	public String getWorkDoctorName() {
		return workDoctorName;
	}

	public void setWorkDoctorName(String workDoctorName) {
		this.workDoctorName = workDoctorName;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	
	
	
}

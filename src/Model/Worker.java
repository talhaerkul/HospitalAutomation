package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;



public class Worker extends User{

	
	
	public Worker(){}
	
	
	public Worker(int userID, String userTC, String userPassword, String userName, String userType) {
		super(userID, userTC, userPassword, userName, userType);
		// TODO Auto-generated constructor stub
	}

	Connection localconnection = dbconnection.connectionDB();
	Statement statement = null;
	ResultSet resultset = null;
	PreparedStatement preparedStatement = null;
	
	
	public ArrayList<User> getWorkerAtClinicList(int comclinicid){
		
		ArrayList<User> workerlist = new ArrayList<>();
		User object;
		try {
			
			statement = localconnection.createStatement();
			resultset = statement.executeQuery("SELECT u.id_data, u.tcno_data, u.password_data, u.name_data, u.type_data FROM worker w LEFT JOIN user u ON w.user_id_data = u.id_data WHERE clinic_id_data ="+comclinicid);
			while(resultset.next()) {
				object = new User(resultset.getInt("u.id_data"),resultset.getString("u.tcno_data"),resultset.getString("u.password_data"),resultset.getString("u.name_data"),resultset.getString("u.type_data"));
				workerlist.add(object);
			}
		} catch (Exception e) {
			}
		return workerlist;
	}
	
	
	
	
	

}

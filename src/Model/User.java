package Model;


import Helper.DBConnection;



public class User {

	private int userID;
	String userTC,userPassword,userName,userType;
	
	DBConnection dbconnection = new DBConnection();
	
	public User() {}
	
	public User(int userID, String userTC, String userPassword, String userName, String userType) {
		super();
		this.userID = userID;
		this.userTC = userTC;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userType = userType;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserTC() {
		return userTC;
	}
	public void setUserTC(String userTC) {
		this.userTC = userTC;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	
	
	
	
	
	
	
	
}

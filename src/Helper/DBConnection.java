package Helper;

import java.sql.*;

public class DBConnection {

	Connection connection = null;
	
	public DBConnection() {}
	
	public Connection connectionDB() {
		
		try {
			this.connection = DriverManager.getConnection("jdbc:mariadb://localhost:3325/hastana2otomasyonu?user=root&password=a3110z");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}
	
	
}

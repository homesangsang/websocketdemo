package websocket.db;


import java.sql.*;

public class DB {
	private static final String url ="jdbc:mysql://localhost:3306/websocket?useUnicode=true&characterEncoding=UTF8";
	private static final String user = "websocket";
	private static final String password = "websocket";
	
	private static Connection connection = null;
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	public static Connection getConnection() {
		return connection;
	}

 
}

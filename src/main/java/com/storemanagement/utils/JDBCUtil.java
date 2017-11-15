package com.storemanagement.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JDBCUtil {
	private static final String 
		url = "jdbc:mysql://localhost/usarabia_store?verifyServerCertificate=false&useSSL=true",
		user = "root",
		pass = "root";
	private static Connection connection = null;
	public static Connection getCon(){
		return openCon();
	}
	private static Connection openCon(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	public static void close(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

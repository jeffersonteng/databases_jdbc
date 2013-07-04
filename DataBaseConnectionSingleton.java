package database;
import java.io.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseConnectionSingleton {	
	private static DataBaseConnectionSingleton databaseConSingleton=null;
	private static Connection conn = null;

	private DataBaseConnectionSingleton() throws ClassNotFoundException, SQLException, IOException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

		System.out.println("MySQL JDBC Driver Registered");

		//OSI DEV:
		
		String urlX="jdbc:mysql://localhost/testdb";
		String usernameX="root";
		String passwordX="root";
		
		try {
			System.out.println("Before connection");
			conn = DriverManager.getConnection(urlX, usernameX, passwordX);
			System.out.println("After getConnection");
			if (conn == null || conn.isClosed()) {
				System.out.println("good");
			} else {
				System.out.println("success");
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
	}

	public static synchronized Connection getConnection() throws ClassNotFoundException, SQLException, IOException {   
		if (databaseConSingleton==null || conn==null || conn.isClosed()) { //get new conn
			System.out.println("Before singleton");
			databaseConSingleton = new DataBaseConnectionSingleton();
			System.out.println("After singleton");
		}
		return conn;
	}

	public static void main(String[] args) {
		try {
			Connection conn1 = DataBaseConnectionSingleton.getConnection();
			System.out.println("conn1.isClosed()=" + conn1.isClosed());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}

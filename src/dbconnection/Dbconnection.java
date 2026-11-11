package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {

	String url="jdbc:mysql://localhost:3306/productdatabase";
	String username="root";
	String password="admin";
	
	public Connection dbconnection() {
		Connection conn= null;
		try {
			conn=DriverManager.getConnection(url, username, password);// key =>query
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
//	public static void main(String[] args) {
//		Dbconnection dbconnection= new Dbconnection();
//		System.out.println(dbconnection.dbconnection());
//	}
}

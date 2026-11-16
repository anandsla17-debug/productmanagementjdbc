package storedprocedures;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import dbconnection.Dbconnection;

public class Storedprocedures {
	
	String createprocedure="create procedure insertdisplay(in productids int,"
			+ "in productnames varchar(150),"
			+ " in productprices double,in qtys int,in dat date)"
			+ "begin "
			+ "insert into productdetails(productid,productname,productprice,qty,dates)"
			+ " values(productids,productnames,productprices,qtys,dat);"
			+ "select * from productdetails;"
			+ "end";
	Dbconnection dbconnection=new Dbconnection();
Connection conn=	dbconnection.dbconnection();// key =>username,password,url
	public void createprocedurelogic() {
		try {
			Statement st=conn.createStatement();
			st.executeUpdate(createprocedure);
			System.out.println("procedure is created");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
	String call="{call insertdisplay(?,?,?,?,?)}";
	public void Callprocedure() {
		System.out.println("--store form--");
		Scanner sc= new Scanner(System.in);
		System.out.println("enter product id");
		int productid=sc.nextInt();
		sc.nextLine();
		System.out.println("enter product name");
		String productname=sc.nextLine();
		System.out.println("Enter product price");
		double productprice=sc.nextDouble();
		System.out.println("Enter your qty");
		int qty=sc.nextInt();
		sc.nextLine();
		System.out.println("enter your date");
		String dates=sc.nextLine();
	try {
		CallableStatement cs= conn.prepareCall(call);
		cs.setInt(1, productid);
		cs.setString(2, productname);
		cs.setDouble(3, productprice);
		cs.setInt(4, qty);
		cs.setString(5, dates);
	   boolean output=cs.execute();
		if(output) {
			      ResultSet rs  =cs.getResultSet();
			      while(rs.next()) {
			    	  System.out.println("product id:"+rs.getInt("productid")+"product name:"+
			      rs.getString("productname")+",price:"+rs.getDouble("productprice")
			      +"qty:"+rs.getInt("qty")+",date:"+rs.getString("dates"));
			      }
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	}
	}
	
	public static void main(String[] args) {
		Storedprocedures storedprocedures= new Storedprocedures();
		storedprocedures.createprocedurelogic();
		storedprocedures.Callprocedure();
	}

}

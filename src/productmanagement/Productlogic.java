package productmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import dbconnection.Dbconnection;

public class Productlogic {
String create="create table productdetails("
		+ "productid int primary key,"
		+ "productname varchar(150),"+
		"productprice double,"+
		"qty int,"
		+ "dates date"
		+ ")";
Dbconnection call= new Dbconnection();
Connection conn=call.dbconnection();// key=>database
Scanner sc = new Scanner(System.in);
public void createtable() {
	try {
		Statement st=conn.createStatement();
		st.executeUpdate(create);
		System.out.println("table is created");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
	System.out.println("error:"+e.getMessage());
	}
}

String insert="insert into productdetails(productid,productname,productprice,qty,dates) values(?,?,?,?,?)";
public void inserttable() {
	
	System.out.println("---Add Product details---");
	System.out.println("Enter product id");
	int productid=sc.nextInt();
	sc.nextLine();
	System.out.println("Enter product name");
	String productname=sc.nextLine();
	System.out.println("Enter product price");
	double productprice=sc.nextDouble();
	System.out.println("Enter your qty");
	int qty=sc.nextInt();
	sc.nextLine();
	System.out.println("Enter dates");
	String date=sc.nextLine();
	try {
		PreparedStatement ps=conn.prepareStatement(insert);
		ps.setInt(1, productid);
		ps.setString(2, productname);
		ps.setDouble(3, productprice);
		ps.setInt(4, qty);
		ps.setString(5, date);
		int row=ps.executeUpdate();// 1 
		if(row==1) {
			System.out.println(row+" product added");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	}
	
}

String displayall="select * from productdetails";
public void viewalldetails() {
	try {
		Statement st= conn.createStatement();
	  ResultSet rs	=st.executeQuery(displayall);
	  while(rs.next()) // true or false
	  {
		  System.out.println("product id:"+rs.getInt("productid")
		  +",product name:"+rs.getString("productname")+
		  ",product price:"+rs.getDouble("productprice")
		  +",qty:"+rs.getDouble("qty")+",order date:"+rs.getString("dates"));
	  }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
System.out.println(e.getMessage());
	}
	
	
	
}

String displaybyid="select * from productdetails where productid=?";

public void viewdetails() {
	System.out.println("Enter your productid");
	int productid=sc.nextInt();
	
	try {
		PreparedStatement ps= conn.prepareStatement(displaybyid);
		ps.setInt(1, productid);
	ResultSet rs	=ps.executeQuery();
	
	if(rs.next()) {
		
	
			System.out.println("product id:"+rs.getInt("productid")
			  +",product name:"+rs.getString("productname")+
			  ",product price:"+rs.getDouble("productprice")
			  +",qty:"+rs.getDouble("qty")+",order date:"+rs.getString("dates"));
		
	}else {
		System.out.println("not present product");
	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	}
	
}



}

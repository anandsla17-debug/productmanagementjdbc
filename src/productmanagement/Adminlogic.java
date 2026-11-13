package productmanagement;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import dbconnection.Dbconnection;

public class Adminlogic {

	String create="create table admin("
			+ "adminid int primary key,"
			+ "name varchar(50),"+
			"password varchar(50)"
			+ ")";
Dbconnection dbconnection= new Dbconnection();
Connection conn=dbconnection.dbconnection();
Scanner sc= new Scanner(System.in);
	public  void Createtable() {
		
		try {
			Statement st=conn.createStatement();
			st.executeUpdate(create);
			System.out.println("admin table created");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		System.out.println(e.getMessage());
		}
		
	}
	
	String insert="insert into admin(adminid,name,password) values(?,?,?)";
	public void insertadmin() {
		System.out.println("---admin Register form---");
		System.out.println("Enter your id");
		int adminid=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter your Name");
		String name=sc.nextLine();
		System.out.println("Enter your password");
		String password=sc.nextLine();
		
		try {
			PreparedStatement ps=conn.prepareStatement(insert);
			ps.setInt(1, adminid);
			ps.setString(2, name);
			ps.setString(3, password);
			int row=ps.executeUpdate();
			if(row==1) {
				System.out.println("admin register successfull");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		System.out.println(e.getMessage());
		}
	}
	String login="select * from admin where adminid=? and password=?";
	public void loginlogic() {
		System.out.println("--admin login Form--");
		System.out.println("Enter your Admin id");
		int adminid=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter your password");
		String password=sc.nextLine();
		try {
			PreparedStatement ps= conn.prepareStatement(login);
			ps.setInt(1, adminid);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				System.out.println("login successfull");
				System.out.println("!welcome "+rs.getString("name"));
				Productlogic productlogic= new Productlogic();



				int choice=0;
				Scanner sc= new Scanner(System.in);
				

				do {
					System.out.println("---Product management System---");
					System.out.println("1.create table");
					System.out.println("2.insert product details");
					System.out.println("3.view all product details");
					System.out.println("4.view product details by id");
					System.out.println("5.update product details by id");
					System.out.println("6.delete product details by id");
					System.out.println("7.view all admin data");
					System.out.println("0.exit");
					System.out.print("Enter your option:");
					choice=sc.nextInt();
					switch(choice) {
					case 1:
						productlogic.createtable();
						break;
					case 2:
						productlogic.inserttable();
						break;
					case 3:

				productlogic.viewalldetails();
				break;
					case 4:
						productlogic.viewdetails();
						break;
					case 5:
						productlogic.updatedetials();
						break;
					case 6:
						productlogic.deleteproduct();
						break;
					case 7:
						displayadmin();
					case 0:
						System.out.println("exit....");
						break;
						default:
							System.out.println("incorrect option");
					}
					
				}while(choice!=0);
			}else {
				System.out.println("login failed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	String display="select * from admin";
	
	public void displayadmin() {
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(display);
			while(rs.next()) {
				System.out.println("admin id:"+rs.getInt("adminid")+",adnin name:"+rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

	
	
}

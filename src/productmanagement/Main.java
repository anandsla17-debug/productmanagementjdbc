package productmanagement;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Adminlogic adminlogic= new Adminlogic();
		
		int choice=0;
		Scanner sc=new Scanner(System.in);
		do {
			System.out.println("--Admin portal--");
			System.out.println("1.Create admin table");
			System.out.println("2.Admin register");
			System.out.println("3.Admin Login");
			System.out.println("0.exit");
			System.out.print("Enter your Option:");
			choice =sc.nextInt();
			switch(choice) {
			case 1:
				adminlogic.Createtable();
				break;
			case 2:
				adminlogic.insertadmin();
				break;
			case 3:
				adminlogic.loginlogic();
				break;
			case 0:
				System.out.println("admin exit ...");
				break;
				default:
					System.out.println("incorrect option");
			
			}
			
			
		}while(choice!=0);

		
	

		
		

	}

}

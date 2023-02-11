
    import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in); 
			System.out.print("Enter first name: ");
			String firstName = sc.nextLine();
			
			System.out.print("Enter last name: ");
			String lastName = sc.nextLine();
			
			System.out.print("Enter social security number: ");
			String socialSecurityNumber = sc.nextLine();

			System.out.print("Enter number of hour worked: ");
			double hours = sc.nextDouble();
			
			System.out.print("Enter hourly wage: ");
			double wage = sc.nextDouble();

			System.out.print("Enter gross sales: ");
			double grossSales = sc.nextDouble();
			
			System.out.print("Enter commission rate: ");
			double commissionRate = sc.nextDouble();
		
		 	HourlyEmployee employee = new HourlyEmployee(firstName, lastName, socialSecurityNumber, wage, hours);

			System.out.println("\n" + employee.toString());
			System.out.println("Earnings: $" + employee.earnings());
				

	/* 		CommissionEmployee employee = new CommissionEmployee(firstName, lastName, socialSecurityNumber, grossSales, commissionRate);
		
			System.out.printf("Earnings: $%.2f%n", employee.earnings());
			System.out.println(employee.toString());
*/
	 
	}
}


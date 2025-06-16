import java.util.Scanner; // Includes the scanner class from the java.util package

public class LabActivity1EmployeeInformationSystem { // Class Declaration
    public static void main(String[] args) { // Main Method
        Scanner scanner = new Scanner(System.in); // Creating a Scanner object to read user input

        System.out.print("Enter your first name: "); // Prompting user input for first name and storing it in firstName
        String firstName = scanner.nextLine(); // Declaring the variable

        System.out.print("Enter your last name: "); // Prompting user input for last name and storing it in lastName
        String lastName = scanner.nextLine(); // Declaring the variable

        System.out.print("Enter your age: "); // Prompting user input for age and storing it in employeeAge
        int employeeAge = scanner.nextInt(); // Declaring the variable

        System.out.print("Enter hours worked: "); // Prompting user input for hours worked and storing it in hoursWorked
        double hoursWorked = scanner.nextDouble(); // Declaring the variable

        System.out.print("Enter hourly wage: "); // Prompting user input for hourly wage and storing it in hourlyWage
        double hourlyWage = scanner.nextDouble(); // Declaring the variable

        double dailySalary = hoursWorked * hourlyWage; // Calculating the employee's daily salary by performing arithmetic operation (Multiplication)

        System.out.println("\nEmployee Information"); // Prints text to the console
        System.out.println("---------------------------"); // Prints text to the console
        System.out.println("Full Name  : " + firstName + " " + lastName); // Concatenating Strings for Ouput
        System.out.println("Age        : " + employeeAge); // Concatenating Strings for Ouput
        System.out.printf("Daily Salary:PHP %.2f\n", dailySalary); // Formats decimal output
        
        scanner.close();  // Closing the Scanner object
    }
}

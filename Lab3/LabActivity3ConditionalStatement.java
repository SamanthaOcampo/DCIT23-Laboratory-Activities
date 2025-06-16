import java.util.Scanner; // Includes the scanner class from the java.util package
import java.math.RoundingMode; // Includes rounding off
import java.text.DecimalFormat; // Includes formatting decimal places

public class LabActivity3ConditionalStatement { // Declaring public class
    public static void main(String[] args) { // Main Method
        Scanner scanner = new Scanner(System.in); // Creating a Scanner object to read user input

        System.out.print("Enter your first name: "); // Prompting user input for first name and storing it in firstName
        String firstName = scanner.nextLine(); // Declaring the variable

        System.out.print("Enter your last name: "); // Prompting user input for last name and storing it in lastName
        String lastName = scanner.nextLine(); // Declaring the variable

        System.out.print("Enter your age: "); // Prompting user input for age and storing it in employeeAge
        int employeeAge = scanner.nextInt(); // Declaring the variable
        if (employeeAge < 18) { // Checks if the user is a minor
            System.out.println("Minors are not allowed"); // Prints the message if user is a minor
            return; // Terminates the program
        } else if (employeeAge >= 65) { // Checks if the user ia a senior citizen
            System.out.println("Senior Citizens are not allowed"); // Prints the message if user is a senior citizen
            return; // Terminates the program
        }

        System.out.print("Enter hours worked: "); // Prompting user input for hours worked and storing it in hoursWorked
        int hoursWorked = scanner.nextInt(); // Declaring the variable
        if (hoursWorked > 24) { // Checks if hours worked is more than 24
            System.out.println("Number of hours worked cannot exceed 24 hours"); // Prints the message if hours worked is more than 24
            return; // Terminates the program
        } else if (hoursWorked == 0) { // Checks if hours worked is 0
            System.out.println("Wrong input on daily work hours"); // Prints the message if hours worked is equal to zero
            return; // Terminates the program
        }

        System.out.print("Enter hourly wage: "); // Prompting user input for hourly wage and storing it in hourlyWage
        double hourlyWage = scanner.nextDouble(); // Declaring the variable

        String fullName = (lastName + "," + " "+ firstName).toUpperCase(); // Concatenating the last and first name in uppercase
        
        System.out.print("Enter role code (1-Manager, 2-Supervisor, 3-Staff, 4-Intern): "); // Prompting user input for employee role and storing it in role code
        int roleCode = scanner.nextInt(); // Reads the role code

        String role;
        switch (roleCode) { // Determines the role based on the role code
            case 1:
                role = "Manager";
                break;
            case 2:
                role = "Supervisor";
                break;
            case 3:
                role = "Staff";
                break;
            case 4:
                role = "Intern";
                break;
            default:
                role = "Undefined";
                break;
        }

        double dailySalary = hoursWorked * hourlyWage; // Calculating the daily salary by multiplying hours worked by hourly wage
        long finalDailySalary = Math.round(dailySalary); // Rounding off the daily salary to the nearest whole number
        double weeklySalary = finalDailySalary * 5; // Calculating the weekly salary by multiplying the final daily salary by 5
        double monthlySalary = weeklySalary * 4; // Calculating the monthly salary by multiplying the weekly salary by 4
        double grossYearlySalary = monthlySalary * 12; // Calculating the gross yearly salary by multiplying the monthly salary by 12
        double netYearlySalary; 
        if (grossYearlySalary > 250000) { // Checks if the gross yearly salary is greater than 250,000
            netYearlySalary = grossYearlySalary - (grossYearlySalary * 0.32) - 1500;  // Applies 32% tax deduction and 1,500 government-mandated benefits
        } else {
            netYearlySalary = grossYearlySalary - 1500; // Deducts only the government-mandated benefits
        }

        int yearsToRetirement = 65 - employeeAge; // Calculates the number of years left to retirement

        DecimalFormat df = new DecimalFormat("#.00"); // Creating a DecimalFormat instance to format the output to two decimal places
        df.setRoundingMode(RoundingMode.HALF_UP); // Set the rounding mode to HALF_UP for the DecimalFormat instance


        System.out.println("\nEmployee Information"); // Printing the header "Employee Information" with a newline character for formatting
        System.out.println("---------------------------------"); // Printing the separator line
        System.out.println("Full Name: " + fullName); // Printing the full name of the employee, stored in the variable fullName
        System.out.println("Age: " + employeeAge + " " + "years old"); // Printing the age of the employee with the suffix "years old"
        System.out.println("Position: " + role); // Prints the role
        System.out.println("Years to Retirement: " + yearsToRetirement + " " + "years"); // Printing the number of years left to retirement with the suffix "years"
        System.out.println("Daily Salary: Php " + df.format(finalDailySalary)); // Printing the daily salary, formatted to two decimal places
        System.out.println("Weekly Salary: Php " + df.format(weeklySalary)); // Printing the weekly salary of the employee in Php, formatted to two decimal places
        System.out.println("Monthly Salary: Php " + df.format(monthlySalary)); // Printing the monthly salary of the employee in Php, formatted to two decimal places
        System.out.println("Gross Yearly Salary: Php " + df.format(grossYearlySalary)); // Printing the gross yearly salary of the employee in Php, formatted to two decimal places
        System.out.println("Net Yearly Salary: Php " + df.format(netYearlySalary)); // Printing the net yearly salary of the employee in Php, formatted to two decimal places


        scanner.close(); // Closing the Scanner object
    }
}

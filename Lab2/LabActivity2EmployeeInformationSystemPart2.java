import java.util.Scanner; // Includes the scanner class from the java.util package
import java.math.RoundingMode; // Includes rounding off
import java.text.DecimalFormat; // Includes formatting decimal places

public class LabActivity2EmployeeInformationSystemPart2 { // Class Declaration
    public static void main(String[] args) { // Main Method
        Scanner scanner = new Scanner(System.in); // Creating a Scanner object to read user input

        System.out.print("Enter your first name: "); // Prompting user input for first name and storing it in firstName
        String firstName = scanner.nextLine(); // Declaring the variable

        System.out.print("Enter your last name: "); // Prompting user input for last name and storing it in lastName
        String lastName = scanner.nextLine();

        System.out.print("Enter your age: "); // Prompting user input for age and storing it in employeeAge
        int employeeAge = scanner.nextInt();

        System.out.print("Enter hours worked: "); // Prompting user input for hours worked and storing it in hoursWorked
        double hoursWorked = scanner.nextDouble();

        System.out.print("Enter hourly wage: "); // Prompting user input for hourly wage and storing it in hourlyWage
        double hourlyWage = scanner.nextDouble();

        String fullName = (lastName + "," + " "+ firstName).toUpperCase(); // Concatenating the last and first name in uppercase
        int ageOfRetirement = 65; // Setting the retirement age
        int yearsToRetirement = Math.abs(ageOfRetirement - employeeAge); // Calculating the years left to retirement using the function Math.abs to ensure the result is positive

        double dailySalary = hoursWorked * hourlyWage; // Calculating the daily salary by multiplying hours worked by hourly wage
        long finalDailySalary = Math.round(dailySalary); // Rounding off the daily salary to the nearest whole number
        double weeklySalary = finalDailySalary * 5; // Calculating the weekly salary by multiplying the final daily salary by 5
        double monthlySalary = weeklySalary * 4; // Calculating the monthly salary by multiplying the weekly salary by 4
        double grossYearlySalary = monthlySalary * 12; // Calculating the gross yearly salary by multiplying the monthly salary by 12
        double netYearlySalary = grossYearlySalary - (0.32 * grossYearlySalary) - 1500; // Calculating the net yearly salary by deducting 32% tax and 1500 Php government-mandated benefits from the gross yearly salary
        
        DecimalFormat df = new DecimalFormat("#.00"); // Creating a DecimalFormat instance to format the output to two decimal places
        df.setRoundingMode(RoundingMode.HALF_UP); // Set the rounding mode to HALF_UP for the DecimalFormat instance

        System.out.println("\nEmployee Information"); // Printing the header "Employee Information" with a newline character for formatting
        System.out.println("---------------------------------"); // Printing the separator line
        System.out.println("Full Name: " + fullName); // Printing the full name of the employee, stored in the variable fullName
        System.out.println("Age: " + employeeAge + " " + "years old"); // Printing the age of the employee with the suffix "years old"
        System.out.println("Years to Retirement: " + yearsToRetirement + " " + "years"); // Printing the number of years left to retirement with the suffix "years"
        System.out.println("Daily Salary: Php " + df.format(finalDailySalary)); // Printing the daily salary, formatted to two decimal places
        System.out.println("Weekly Salary: Php " + df.format(weeklySalary)); // Printing the weekly salary of the employee in Php, formatted to two decimal places
        System.out.println("Monthly Salary: Php " + df.format(monthlySalary)); // Printing the monthly salary of the employee in Php, formatted to two decimal places
        System.out.println("Gross Yearly Salary: Php " + df.format(grossYearlySalary)); // Printing the gross yearly salary of the employee in Php, formatted to two decimal places
        System.out.println("Net Yearly Salary: Php " + df.format(netYearlySalary)); // Printing the net yearly salary of the employee in Php, formatted to two decimal places

        scanner.close(); // Closing the Scanner object
    }
}

import java.util.Scanner; // Imports the Scanner class for taking user input.

class Ticket { // Defines the Ticket class to represent a single ticket with a description, urgency level, and status.
    String description; // Declares a variable to hold the issue description.
    String urgency; // Declares a variable to represent the urgency level (Low, Medium, High).
    String status; // Declares a variable for the ticket's current status (e.g., Pending, In Progress, Resolved).

    public Ticket(String description, String urgency) { // Constructor initializes the ticket description, urgency level, and sets the status to "Pending" by default.
        this.description = description;
        this.urgency = urgency;
        this.status = "Pending"; // Default status of the ticket.
    }

    @Override // Overrides the toString() method to provide a human-readable representation of the ticket.
    public String toString() {
        return "Description: " + description + ", Urgency: " + urgency + ", Status: " + status;
    }
}

public class TicketManagementSystem { // Defines the main class that contains the application's core logic and handles ticket management.
    private static final int MAX_TICKETS = 5; // Sets the maximum number of tickets allowed in the system.
    private static Ticket[] tickets = new Ticket[MAX_TICKETS]; // Initializes an array to store the tickets.
    private static int ticketCount = 0; // Keeps track of the total number of tickets added.

    public static void main(String[] args) { // Entry point for the program where user interaction and main logic execution occur.
        Scanner scanner = new Scanner(System.in); // Initializes the Scanner for reading user input.
        int choice; // Variable to store the user's menu selection.

        do { // A loop that displays the menu and keeps the program running until the user selects the "Exit" option (choice = 5).
            displayMenu(); // Calls the method to show the menu options to the user.
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) { // Handles different menu options based on the user’s input.


                case 1:
                    addTicket(scanner);
                    break;
                case 2:
                    updateTicketStatus(scanner);
                    break;
                case 3:
                    showTickets();
                    break;
                case 4:
                    generateReport();
                    break;
                case 5:
                    System.out.println("Exiting... Thank You!");
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        } while (choice != 5);

        scanner.close(); // Closes the Scanner to free up system resources.
    }

    private static void displayMenu() { // Displays the main menu to guide the user on available actions.
        System.out.println("\n--- IT Ticket System ---");
        System.out.println("1. Add Ticket");
        System.out.println("2. Update Ticket Status");
        System.out.println("3. Show All Tickets");
        System.out.println("4. Generate Report");
        System.out.println("5. Exit");
        System.out.print("Select an option: ");
    }

    private static void addTicket(Scanner scanner) { // Creates a new Ticket object and adds it to the array.
        if (ticketCount >= MAX_TICKETS) {
            System.out.println("Maximum number of tickets reached.");
            return;
        }

        System.out.print("Enter issue description: ");
        String description = scanner.nextLine();
        System.out.print("Enter urgency level (Low, Medium, High): ");
        String urgency = scanner.nextLine();

        tickets[ticketCount] = new Ticket(description, urgency);
        ticketCount++;
        System.out.println("Ticket successfully added!");
    }

    private static void updateTicketStatus(Scanner scanner) { // Prompts the user for a new status (either "In Progress" or "Resolved") and updates the ticket if valid.
        System.out.print("Enter ticket number to update: ");
        int ticketNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (ticketNumber < 1 || ticketNumber > ticketCount) {
            System.out.println("Invalid ticket number.");
            return;
        }

        Ticket ticket = tickets[ticketNumber - 1];
        if (ticket.status.equals("Resolved")) {
            System.out.println("Cannot update a resolved ticket.");
            return;
        }

        System.out.print("Enter new status: ");
        String newStatus = scanner.nextLine();

        if (newStatus.equals("In Progress") || newStatus.equals("Resolved")) {
            ticket.status = newStatus;
            System.out.println("Ticket status updated.");
        } else {
            System.out.println("Enter new status (In Progress or Resolved): ");
        }
    }

    private static void showTickets() { // Iterates through the ticket array and displays all tickets with their details.
        if (ticketCount == 0) {
            System.out.println("No tickets available.");
            return;
        }

        System.out.println("\n--- All Tickets ---");
        for (int i = 0; i < ticketCount; i++) {
            System.out.println((i + 1) + ". " + tickets[i]);
        }
    }

    private static void generateReport() { // Counts the total number of tickets, pending tickets, and resolved tickets.
        int pendingCount = 0;
        int resolvedCount = 0;

        for (int i = 0; i < ticketCount; i++) {
            if (tickets[i].status.equals("Resolved")) {
                resolvedCount++;
            } else {
                pendingCount++;
            }
        }

        System.out.println("\n--- Ticket Report ---");
        System.out.println("Total Tickets: " + ticketCount);
        System.out.println("Pending Tickets: " + pendingCount);
        System.out.println("Resolved Tickets: " + resolvedCount);
    }
}

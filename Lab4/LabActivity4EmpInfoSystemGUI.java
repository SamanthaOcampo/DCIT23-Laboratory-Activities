// Import necessary Java Swing and AWT classes for GUI components
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Define the main class that extends JFrame for GUI functionality
public class  LabActivity4EmpInfoSystemGUI extends JFrame {
    private JTextField firstNameField, lastNameField, ageField, hoursWorkedField, hourlyRateField; // Declare text fields for user input
    private JTextArea outputArea; // Declare a text area for displaying output

    // Constructor to initialize and set up the GUI
    public LabActivity4EmpInfoSystemGUI() {
        setTitle("Laboratory Activity 4"); // Set the window title
        setLayout(new GridLayout(7, 2)); // Use a grid layout with 7 rows and 2 columns

        // Initialize text fields for user input
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        ageField = new JTextField();
        hoursWorkedField = new JTextField();
        hourlyRateField = new JTextField();

        // Create submit button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitAction()); // Add an action listener to handle button clicks

        // Create a text area for output with specified size
        outputArea = new JTextArea(3, 20);
        outputArea.setEditable(false); // Make the output area non-editable

        // Add labels and input fields to the frame
        add(new JLabel("First Name:"));
        add(firstNameField);
        add(new JLabel("Last Name:"));
        add(lastNameField);
        add(new JLabel("Age:"));
        add(ageField);
        add(new JLabel("Hours Worked:"));
        add(hoursWorkedField);
        add(new JLabel("Hourly Rate:"));
        add(hourlyRateField);
        add(submitButton);  // Add the submit button
        add(new JScrollPane(outputArea)); // Add a scrollable output area

        setSize(400, 300); // Set window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Specify behavior for closing the window
        setVisible(true); // Make the window visible
    }

    // Define the action listener class for handling form submission
    private class SubmitAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve and trim user input from text fields
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String ageStr = ageField.getText().trim();
            String hoursWorkedStr = hoursWorkedField.getText().trim();
            String hourlyRateStr = hourlyRateField.getText().trim();

            // Validate that all fields are filled out
            if (firstName.isEmpty() || lastName.isEmpty() || ageStr.isEmpty() || hoursWorkedStr.isEmpty() || hourlyRateStr.isEmpty()) {
                outputArea.setText("Error: All fields must be filled out.");
                return;
            }

            int age;
            double hoursWorked;
            double hourlyRate;

            // Validate that age is an integer
            try {
                age = Integer.parseInt(ageStr);
            } catch (NumberFormatException ex) {
                outputArea.setText("Error: Age must be a valid integer.");
                return;
            }

            // Validate that hours worked and hourly rate are numbers
            try {
                hoursWorked = Double.parseDouble(hoursWorkedStr);
                hourlyRate = Double.parseDouble(hourlyRateStr);
            } catch (NumberFormatException ex) {
                outputArea.setText("Error: Hours worked and hourly rate must be valid numbers.");
                return;
            }

            // Calculate daily salary
            double dailySalary = hoursWorked * hourlyRate;

            // Format and display output
            String result = String.format("Full Name: %s %s\nAge: %d years old\nDaily Salary: PHP %.2f",
                                           firstName, lastName, age, dailySalary);
            outputArea.setText(result);
        }
    }

    public static void main(String[] args) { // Main method to launch the application
        SwingUtilities.invokeLater(LabActivity4EmpInfoSystemGUI::new); // Ensure GUI runs on the Event Dispatch Thread
    }
}

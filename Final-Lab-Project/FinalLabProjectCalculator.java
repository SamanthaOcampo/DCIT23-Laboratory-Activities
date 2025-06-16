// Import necessary Java Swing and AWT libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

// Main calculator class extending JFrame and implementing ActionListener
public class FinalLabProjectCalculator extends JFrame implements ActionListener {

    // UI components and styling variables
    private JTextField display;  // Text field for calculator display
    private JPanel buttonPanel;  // Panel to hold calculator buttons
    private Font buttonFont = new Font("Serif", Font.BOLD, 22);  // Font for buttons
    private Font displayFont = new Font("Serif", Font.PLAIN, 26);  // Font for display
    private Color backgroundColor = new Color(204, 179, 153);  // Earth tone background
    private Color buttonColor = new Color(153, 128, 102);  // Default button color
    private Color buttonHoverColor = new Color(180, 150, 110);  // Button hover color
    private Color displayColor = new Color(238, 221, 198);  // Display background color

    // Calculator constructor
    public FinalLabProjectCalculator() {
        // Set up the main window
        setTitle("GUI Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(backgroundColor);

        // Initialize and configure the display text field
        display = new JTextField();
        display.setFont(displayFont);
        display.setHorizontalAlignment(JTextField.RIGHT);  // Right-align text
        display.setEditable(false);  // Prevent direct editing
        display.setBackground(displayColor);
        display.setForeground(new Color(51, 25, 0));  // Dark brown text
        add(display, BorderLayout.NORTH);  // Add display to top of frame

        // Create panel for calculator buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 10, 10));  // 6x4 grid with spacing
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Add padding

        // Array of button labels
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "Del", "^", "√",
                "(", ")", "%", "Ans"
        };

        // Create and configure buttons
        for (String label : buttonLabels) {
            SquareButton button = new SquareButton(label);  // Custom square button
            button.setFont(buttonFont);
            button.setBackground(buttonColor);
            button.setForeground(new Color(255, 255, 204));  // Light yellow text
            button.setFocusPainted(false);  // Remove focus border
            button.setContentAreaFilled(true);
            button.addActionListener(this);  // Add action listener

            // Add hover effects to buttons
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(buttonHoverColor);  // Change color on hover
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(buttonColor);  // Revert color when mouse leaves
                }
            });

            buttonPanel.add(button);  // Add button to panel
        }

        add(buttonPanel, BorderLayout.CENTER);  // Add button panel to center

        setVisible(true);  // Make window visible
    }

    // Handle button click events
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();  // Get button text

        switch (command) {
            // Number buttons (0-9) and decimal point
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case ".":
            case "(":
            case ")":
                display.setText(display.getText() + command);  // Append to display
                break;
            // Operator buttons
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
            case "^":
                display.setText(display.getText() + command);  // Append to display
                break;
            // Equals button - evaluate expression
            case "=":
                try {
                    String expression = display.getText();
                    double result = evaluateExpression(expression);  // Evaluate
                    display.setText(formatResult(result));  // Format and display result
                } catch (Exception ex) {
                    display.setText("Error");  // Show error if evaluation fails
                }
                break;
            // Clear button
            case "C":
                display.setText("");  // Clear display
                break;
            // Delete button (backspace)
            case "Del":
                String currentText = display.getText();
                if (!currentText.isEmpty()) {
                    // Remove last character
                    display.setText(currentText.substring(0, currentText.length() - 1));
                }
                break;
            // Square root button
            case "√":
                try {
                    double value = Double.parseDouble(display.getText());
                    if (value >= 0) {
                        display.setText(formatResult(Math.sqrt(value)));  // Calculate and format square root
                    } else {
                        display.setText("Error");  // Error for negative numbers
                    }
                } catch (NumberFormatException ex) {
                    display.setText("Error");  // Error for invalid input
                }
                break;
            // Answer button (placeholder)
            case "Ans":
                // Future functionality to recall last answer
                break;
        }
    }

    // Evaluate mathematical expression using shunting yard algorithm
    private double evaluateExpression(String expression) {
        try {
            return shuntingYard(expression);  // Delegate to shunting yard implementation
        } catch (Exception e) {
            throw new RuntimeException("Invalid Expression");  // Throw error if evaluation fails
        }
    }

    // Shunting Yard Algorithm implementation for parsing mathematical expressions
    private double shuntingYard(String expression) {
        Stack<Double> numbers = new Stack<>();  // Stack for numbers
        Stack<Character> operators = new Stack<>();  // Stack for operators

        // Process each character in the expression
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // Handle numbers and decimal points
            if (Character.isDigit(c) || c == '.') {
                StringBuilder num = new StringBuilder();
                // Collect all consecutive digits and decimal points
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    num.append(expression.charAt(i));
                    i++;
                }
                i--;  // Adjust index after loop
                numbers.push(Double.parseDouble(num.toString()));  // Push number to stack
            } 
            // Handle opening parenthesis
            else if (c == '(') {
                operators.push(c);
            } 
            // Handle closing parenthesis
            else if (c == ')') {
                // Process all operators until matching '(' is found
                while (!operators.isEmpty() && operators.peek() != '(') {
                    numbers.push(applyOperator(operators.pop(), numbers.pop(), numbers.pop()));
                }
                if (!operators.isEmpty() && operators.peek() == '(') {
                    operators.pop();  // Remove the '('
                } else {
                    throw new IllegalArgumentException("Mismatched parentheses");  // Error if no matching '('
                }
            } 
            // Handle operators
            else if (isOperator(c)) {
                // Process higher or equal precedence operators first
                while (!operators.isEmpty() && precedence(c) <= precedence(operators.peek())) {
                    numbers.push(applyOperator(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.push(c);  // Push current operator to stack
            }
        }

        // Process any remaining operators
        if (!operators.isEmpty()) {
            while (!operators.isEmpty()) {
                if (operators.peek() == '(') {
                    throw new IllegalArgumentException("Mismatched parentheses");  // Error for unmatched '('
                }
                numbers.push(applyOperator(operators.pop(), numbers.pop(), numbers.pop()));
            }
        }

        return numbers.pop();  // Final result
    }

    // Check if character is a supported operator
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '%';
    }

    // Define operator precedence
    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;  // Lowest precedence
            case '*':
            case '/':
            case '%':
                return 2;  // Medium precedence
            case '^':
                return 3;  // Highest precedence
            default:
                return -1;  // Unknown operator
        }
    }

    // Apply an operator to two operands
    private double applyOperator(char operator, double b, double a) {
        switch (operator) {
            case '+':
                return a + b;  // Addition
            case '-':
                return a - b;  // Subtraction
            case '*':
                return a * b;  // Multiplication
            case '/':
                if (b == 0) throw new ArithmeticException("Division by zero");  // Error for division by zero
                return a / b;  // Division
            case '^':
                return Math.pow(a, b);  // Exponentiation
            case '%':
                return a % b;  // Modulo
            default:
                return 0;  // Default case (shouldn't occur)
        }
    }

    // Format result to display integers without decimal places
    private String formatResult(double result) {
        if (result == (int) result) {
            return String.valueOf((int) result);  // Display as integer if no decimal
        } else {
            return String.valueOf(result);  // Otherwise display as double
        }
    }

    // Custom square button class that maintains square aspect ratio
    static class SquareButton extends JButton {
        public SquareButton(String text) {
            super(text);  // Call parent constructor
        }

        // Override size methods to maintain square shape
        @Override
        public Dimension getPreferredSize() {
            Dimension size = super.getPreferredSize();
            int max = Math.max(size.width, size.height);  // Use largest dimension
            return new Dimension(max, max);  // Square dimensions
        }

        @Override
        public Dimension getMinimumSize() {
            return getPreferredSize();  // Same as preferred size
        }

        @Override
        public Dimension getMaximumSize() {
            return getPreferredSize();  // Same as preferred size
        }
    }

    // Main method to launch the calculator
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FinalLabProjectCalculator::new);  // Create calculator on Event Dispatch Thread
    }
}

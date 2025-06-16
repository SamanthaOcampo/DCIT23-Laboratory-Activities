import java.awt.*;
import java.awt.event.*;

import javax.swing.JOptionPane;

public class LabActivity5QuizAppAWT extends Frame implements ActionListener {
    private Label questionLabel;
    private CheckboxGroup answersGroup;
    private Checkbox[] answerOptions;
    private Button nextButton;
    private String[] questions;
    private String[][] options;
    private int[] correctAnswers;
    private int currentQuestion;
    private int score;

    public LabActivity5QuizAppAWT() {
        // Quiz questions and options
        questions = new String[]{
            "What does CPU stand for in Computer Science?",
            "Which of the following is an example of an input device?",
            "What is the primary purpose of an operating system?"
        };

        options = new String[][]{
            {"A. Central Processing Unit", "B. Computer Processing Unit", "C. Central Programming Unit", "D. Computer Programming Unit"},
            {"A. Monitor", "B. Keyboard", "C. Printer", "D. Speaker"},
            {"A. To manage hardware and software resources", "B. To create websites", "C. To design computer hardware", "D. To write computer programs"}
        };
        
        correctAnswers = new int[]{0, 1, 0}; // Index of the correct answers

        currentQuestion = 0;
        score = 0;

        // Set up the Frame
        setTitle("Quiz App");
        setSize(400, 300);
        setLayout(new FlowLayout());

        // Create question label
        questionLabel = new Label(questions[currentQuestion]);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(questionLabel);

        // Create radio buttons
        answersGroup = new CheckboxGroup();
        answerOptions = new Checkbox[4];
        for (int i = 0; i < options[currentQuestion].length; i++) {
            answerOptions[i] = new Checkbox(options[currentQuestion][i], answersGroup, false);
            answerOptions[i].setFont(new Font("Arial", Font.PLAIN, 14));
            add(answerOptions[i]);
        }

        // Next button
        nextButton = new Button("Next");
        nextButton.addActionListener(this);
        add(nextButton);

        // Close button
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }
@Override
    public void actionPerformed(ActionEvent e) {
        // Check the answer
        Checkbox selectedAnswer = answersGroup.getSelectedCheckbox();
        if (selectedAnswer != null) {
            // Find the index of the selected answer
            char letter = selectedAnswer.getLabel().charAt(0);
            int answerIndex = letter - 'A'; // Convert 'A', 'B', 'C', 'D' to 0, 1, 2, 3
            
            // int answerIndex = Integer.parseInt(selectedAnswer.getLabel().substring(0, 1)) - 1; // Get the answer index
            if (answerIndex == correctAnswers[currentQuestion]) {
                score++;
            }
        }

        // Move to the next question
        currentQuestion++;
        if (currentQuestion < questions.length) {
            updateQuestion();
        } else {
            displayScore();
        }
    }

    private void updateQuestion() {
        // Update question label and options
        questionLabel.setText(questions[currentQuestion]);
        for (int i = 0; i < answerOptions.length; i++) {
            if (i < options[currentQuestion].length) {
                answerOptions[i].setLabel(options[currentQuestion][i]);
            } else {
                answerOptions[i].setVisible(false);
            }
            answerOptions[i].setState(false); // Reset selection
        }
    }

    private void displayScore() {
        // Show final score
        questionLabel.setText("Quiz Completed! Your Score: " + score + " out of " + questions.length);
        for (Checkbox cb : answerOptions) {
            cb.setVisible(false); // Hide answer options
        }
        nextButton.setVisible(false); // Hide Next button
    }

    public static void main(String[] args) {
        new LabActivity5QuizAppAWT();
    }
}

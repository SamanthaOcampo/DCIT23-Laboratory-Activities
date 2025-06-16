import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LabActvity6SwingToDoList {
    private JFrame viewerFrame;
    private JTable taskTable;
    private DefaultTableModel tableModel;
    private JButton addTaskButton;
    private ToDoForm toDoForm;

    public LabActvity6SwingToDoList() {
        initializeViewer();
    }

    private void initializeViewer() {
        viewerFrame = new JFrame("To-Do List Viewer");
        viewerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewerFrame.setSize(500, 400);
        
        // Table Model
        tableModel = new DefaultTableModel(new Object[]{"Task Name", "Task Description", "Status"}, 0);
        taskTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(taskTable);
        
        // Add Task Button
        addTaskButton = new JButton("Add Task");
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (toDoForm == null) {
                    toDoForm = new ToDoForm(LabActvity6SwingToDoList.this);
                }
            }
        });
        
        // Layout
        viewerFrame.setLayout(new BorderLayout());
        viewerFrame.add(scrollPane, BorderLayout.CENTER);
        viewerFrame.add(addTaskButton, BorderLayout.NORTH);
        
        viewerFrame.setVisible(true);
    }

    public void addTask(String taskName, String taskDescription, String status) {
        tableModel.addRow(new Object[]{taskName, taskDescription, status});
        toDoForm = null; // Reset the form reference
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LabActvity6SwingToDoList::new);
    }
}

class ToDoForm {
    private JFrame formFrame;
    private JTextField taskNameField;
    private JTextArea taskDescriptionArea;
    private JComboBox<String> statusComboBox;
    private LabActvity6SwingToDoList app;

    public ToDoForm(LabActvity6SwingToDoList app) {
        this.app = app;
        initializeForm();
    }

    private void initializeForm() {
        formFrame = new JFrame("Add New Task");
        formFrame.setSize(300, 300);
        formFrame.setLayout(new GridLayout(5, 1));
        
        // Task Name
        taskNameField = new JTextField();
        formFrame.add(new JLabel("Task Name:"));
        formFrame.add(taskNameField);
        
        // Task Description
        taskDescriptionArea = new JTextArea();
        formFrame.add(new JLabel("Task Description:"));
        formFrame.add(new JScrollPane(taskDescriptionArea));
        
        // Status Dropdown
        String[] statuses = {"Not Started", "Ongoing", "Completed"};
        statusComboBox = new JComboBox<>(statuses);
        formFrame.add(new JLabel("Status:"));
        formFrame.add(statusComboBox);
        
        // Save Button
        JButton saveButton = new JButton("Save Task");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName = taskNameField.getText().trim();
                String taskDescription = taskDescriptionArea.getText().trim();
                String status = (String) statusComboBox.getSelectedItem();

                if (taskName.isEmpty() || status == null || status.isEmpty()) {
                    JOptionPane.showMessageDialog(formFrame, "Please fill in Task Name and Status.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    app.addTask(taskName, taskDescription, status);
                    formFrame.dispose();
                }
            }
        });
        
        formFrame.add(saveButton);
        formFrame.setVisible(true);
    }
}

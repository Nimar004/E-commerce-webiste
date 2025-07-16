import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ToDoList extends JFrame {

    private JTextField taskField;
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JButton addButton;
    private JButton removeButton;
    private JButton clearButton;

    public ToDoList() {
        super("To-Do List");
        setLayout(new BorderLayout());

        // Create task field
        taskField = new JTextField();
        taskField.setFont(new Font("Arial", Font.PLAIN, 18));
        add(taskField, BorderLayout.NORTH);

        // Create task list
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setFont(new Font("Arial", Font.PLAIN, 18));
        add(new JScrollPane(taskList), BorderLayout.CENTER);

        // Create buttons
        addButton = new JButton("Add");
        addButton.setFont(new Font("Arial", Font.BOLD, 18));
        addButton.setBackground(Color.decode("#4CAF50"));
        addButton.setForeground(Color.WHITE);

        removeButton = new JButton("Remove");
        removeButton.setFont(new Font("Arial", Font.BOLD, 18));
        removeButton.setBackground(Color.decode("#e74c3c"));
        removeButton.setForeground(Color.WHITE);

        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 18));
        clearButton.setBackground(Color.decode("#4CAF50"));
        clearButton.setForeground(Color.WHITE);

        // Add action listeners to buttons
        addButton.addActionListener(new AddButtonListener());
        removeButton.addActionListener(new RemoveButtonListener());
        clearButton.addActionListener(new ClearButtonListener());

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set up window
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String task = taskField.getText();
            if (!task.isEmpty()) {
                listModel.addElement(task);
                taskField.setText("");
            }
        }
    }

    private class RemoveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                listModel.remove(selectedIndex);
            }
        }
    }

    private class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listModel.clear();
        }
    }

    public static void main(String[] args) {
        new ToDoList();
    }
}
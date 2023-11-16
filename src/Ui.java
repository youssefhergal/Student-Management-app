import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;

public class Ui {
    private JFrame frame;
    private Controller controller;
    private JTable studentTable;
    private DefaultTableModel tableModel;

    public Ui(Controller controller) {
        this.controller = controller;
        initializeUI();
        showStudentList();
    }

    private void initializeUI() {
        frame = new JFrame("Student Management System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Email", "Note"}, 0);
        studentTable = new JTable(tableModel);

        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.getTableHeader().setReorderingAllowed(false);
        studentTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        studentTable.getColumnModel().getColumn(2).setPreferredWidth(180);

        Font tableFont = new Font("Arial", Font.PLAIN, 12);
        studentTable.setFont(tableFont);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel navBarPanel = new JPanel();
        JButton addBtn = new JButton("Add Student");
        JButton deleteBtn = new JButton("Delete Student");
        JButton updateBtn = new JButton("Update Student");

        addBtn.addActionListener(e -> showAddStudentDialog());
        deleteBtn.addActionListener(e -> deleteSelectedStudent());
        updateBtn.addActionListener(e -> showUpdateStudentDialog());

        navBarPanel.add(addBtn);
        navBarPanel.add(deleteBtn);
        navBarPanel.add(updateBtn);

        JPanel searchPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchBtn = new JButton("Search");
        searchBtn.addActionListener(e -> searchStudentsByName(searchField.getText()));

        searchPanel.add(new JLabel("Search by Name:"));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);

        mainPanel.add(navBarPanel, BorderLayout.NORTH);
        mainPanel.add(searchPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }






    private void showStudentList() {
        List<Student> students = controller.getAllStudents();

        tableModel.setRowCount(0);


        for (Student student : students) {
            tableModel.addRow(new Object[]{student.getId(), student.getName(), student.getEmail(), student.getNote()});
        }
    }

    private void searchStudentsByName(String name) {
        List<Student> searchResults = controller.searchStudentByName(name);
        tableModel.setRowCount(0);

        for (Student student : searchResults) {
            tableModel.addRow(new Object[]{student.getId(), student.getName(), student.getEmail(), student.getNote()});
        }
    }

    private void refreshStudentList() {
        tableModel.setRowCount(0);
        List<Student> students = controller.getAllStudents();
        for (Student student : students) {
            tableModel.addRow(new Object[]{student.getId(), student.getName(), student.getEmail(), student.getNote()});
        }
    }

    private void showAddStudentDialog() {
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField noteField = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Note:"));
        panel.add(noteField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Student", JOptionPane.OK_CANCEL_OPTION);

        // Process user input
        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                String email = emailField.getText();
                int note = Integer.parseInt(noteField.getText());

                controller.addStudent(name, email, note);

                refreshStudentList();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Note must be a number.");
            }
        }
    }

    private void showUpdateStudentDialog() {
        int selectedRow = studentTable.getSelectedRow();

        if (selectedRow != -1) {
            int studentId = (int) studentTable.getValueAt(selectedRow, 0);
            Student student = controller.getStudentById(studentId);

            JTextField nameField = new JTextField(student.getName());
            JTextField emailField = new JTextField(student.getEmail());
            JTextField noteField = new JTextField(String.valueOf(student.getNote()));

            JPanel panel = new JPanel(new GridLayout(5, 3));
            panel.add(new JLabel("Name:"));
            panel.add(nameField);
            panel.add(new JLabel("Email:"));
            panel.add(emailField);
            panel.add(new JLabel("Note:"));
            panel.add(noteField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Update Student", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    String name = nameField.getText();
                    String email = emailField.getText();
                    int note = Integer.parseInt(noteField.getText());

                    controller.updateStudent(studentId, name, email, note);

                    refreshStudentList();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Note must be a number.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a student to update.");
        }
    }


    private void deleteSelectedStudent() {
        int selectedRow = studentTable.getSelectedRow();

        if (selectedRow != -1) {
            int option = JOptionPane.showConfirmDialog(frame,
                    "Are you sure you want to delete this student?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                int studentId = (int) studentTable.getValueAt(selectedRow, 0);
                controller.deleteStudent(studentId);
                refreshStudentList();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a student to delete.");
        }
    }


    // Additional methods for handling dialog boxes for add/update operations can be added here
}

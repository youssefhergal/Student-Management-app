import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Test {
    private JFrame managementFrame;
    private JTextField chercherField;
    private JComboBox<String> chercherBox;
    private JComboBox<String> trierBox;
    private DefaultTableModel tableModel;
    private JTable studentTable ;

    private Controller controller;


    public Test(Controller controller) {
        this.controller = controller;
        initialize();
        showStudentList();
        studentTable.clearSelection();
        managementFrame.setVisible(true);
    }

    private void initialize() {
        managementFrame = new JFrame();
        managementFrame.setBounds(100, 100, 860, 540);
        managementFrame.setResizable(false);
        managementFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        managementFrame.getContentPane().setLayout(null);

        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(10, 10, 820, 35);
        headerPanel.setBackground(new Color(0x072D37));
        JLabel headerLabel = new JLabel("Student Management");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        headerPanel.add(headerLabel);
        managementFrame.getContentPane().add(headerPanel);

        JPanel tablePanel = new JPanel();
        tablePanel.setBackground(new Color(0x3B749E));
        tablePanel.setBorder(new LineBorder(new Color(96, 134, 165), 4));
        tablePanel.setBounds(260, 50, 575, 355);
        managementFrame.getContentPane().add(tablePanel);
        tablePanel.setLayout(new BorderLayout());

        JLabel tableTitleLabel = new JLabel("Liste des étudiants");
        tableTitleLabel.setForeground(Color.WHITE);
        tableTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        tableTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        tablePanel.add(tableTitleLabel, BorderLayout.NORTH);


        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Email", "Note"}, 0);
        studentTable = new JTable(tableModel);
        studentTable.setBounds(50, 50, 240, 355);
        int marginSize = 20;
        EmptyBorder tableMargin = new EmptyBorder(marginSize, marginSize, marginSize, marginSize);
        JScrollPane tableScrollPane = new JScrollPane(studentTable);
        tableScrollPane.setBorder(tableMargin);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        tablePanel.setBackground(new Color(0x072D37));


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new LineBorder(new Color(133, 170, 200), 4));
        buttonsPanel.setBackground(new Color(0x072D37));
        buttonsPanel.setBounds(10, 415, 825, 80);
        managementFrame.getContentPane().add(buttonsPanel);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEADING , 80, 17));

        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton exitButton = new JButton("Exit");

        Color buttonBackgroundColor = new Color(0x8AA9BE);
        addButton.setBackground(buttonBackgroundColor);
        updateButton.setBackground(buttonBackgroundColor);
        deleteButton.setBackground(buttonBackgroundColor);
        exitButton.setBackground(buttonBackgroundColor);

        addButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        updateButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        exitButton.setFont(new Font("Tahoma", Font.PLAIN, 16));

        int buttonMarginSize = 10;
        EmptyBorder buttonMargin = new EmptyBorder(buttonMarginSize, buttonMarginSize, buttonMarginSize, buttonMarginSize);

        Dimension buttonSize = new Dimension(100, 40);

        addButton.setPreferredSize(buttonSize);
        updateButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);

        buttonsPanel.add(addButton);
        buttonsPanel.add(updateButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(exitButton);


        JPanel studentPanel = new JPanel();
        studentPanel.setBorder(new LineBorder(new Color(96, 134, 165), 4));
        studentPanel.setBounds(10, 50, 240, 355);
        managementFrame.getContentPane().add(studentPanel);
        studentPanel.setBackground(new Color(0x88B9D3));
        studentPanel.setLayout(null);

        JLabel chercherLabel = new JLabel("Chercher");
        chercherLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        chercherLabel.setBounds(10, 22, 67, 19);
        studentPanel.add(chercherLabel);

        chercherBox = new JComboBox<>();
        chercherBox.setName("chercherBox");
        chercherBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        chercherBox.setBounds(85, 23, 143, 22);
        chercherBox.addItem("ID");
        chercherBox.addItem("Name");
        chercherBox.addItem("Note");
        studentPanel.add(chercherBox);

        chercherField = new JTextField();
        chercherField.setName("chercherField");
        chercherField.setBounds(10, 130, 220, 30);
        chercherField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        studentPanel.add(chercherField);

        JButton chercherButton = new JButton("Chercher");
        chercherButton.setName("chercher");
        chercherButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        chercherButton.setBounds(10, 170, 220, 30);


        JLabel trierLabel = new JLabel("Trier");
        trierLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        trierLabel.setBounds(10, 210, 67, 19);
        studentPanel.add(trierLabel);

        trierBox = new JComboBox<>();
        trierBox.setName("trierBox");
        trierBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        trierBox.setBounds(85, 210, 143, 22);
        trierBox.addItem("Id");
        trierBox.addItem("Name");
        trierBox.addItem("Note");
        studentPanel.add(trierBox);

        JButton trierButton = new JButton("Trier");
        trierButton.setName("trier");
        trierButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        trierButton.setBounds(10, 300, 220, 30);
        studentPanel.add(trierButton);
        studentPanel.add(chercherButton);

        addButton.addActionListener(e -> showAddStudentDialog());
        updateButton.addActionListener(e -> showUpdateStudentDialog());
        deleteButton.addActionListener(e -> deleteSelectedStudent());
        exitButton.addActionListener(e -> System.exit(-1));
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
            JOptionPane.showMessageDialog(managementFrame, "Please select a student to update.");
        }
    }


    private void deleteSelectedStudent() {
        int selectedRow = studentTable.getSelectedRow();

        if (selectedRow != -1) {
            int option = JOptionPane.showConfirmDialog(managementFrame,
                    "Are you sure you want to delete this student?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                int studentId = (int) studentTable.getValueAt(selectedRow, 0);
                controller.deleteStudent(studentId);
                refreshStudentList();
            }
        } else {
            JOptionPane.showMessageDialog(managementFrame, "Please select a student to delete.");
        }
    }


}
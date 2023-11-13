import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Controller {


    public Controller() {
    }

    // Create a new student
    public void addStudent(String name, String email, int note) {
        try (PreparedStatement pstmt = new SingletonConnexion().connect().prepareStatement(
                "INSERT INTO etudiants (name, email, note) VALUES (?, ?, ?)")) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, note);
            pstmt.executeUpdate();
            System.out.println("New record added successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (PreparedStatement pstmt = new SingletonConnexion().connect().prepareStatement("SELECT * FROM etudiants");
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int note = resultSet.getInt("note");

                Student student = new Student(id, name, email, note);
                students.add(student);
            }

            System.out.println("All students retrieved successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

    // Update student information
    public void updateStudent(int id, String name, String email, int note) {
        Connection connection = new SingletonConnexion().getConnection();
        try (PreparedStatement pstmt = new SingletonConnexion().connect().prepareStatement(
                "UPDATE etudiants SET name=?, email=?, note=? WHERE id=?")) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, note);
            pstmt.setInt(4, id);

            // Execute the update statement
            pstmt.executeUpdate();
            System.out.println("Record updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete a student
    public void deleteStudent(int id) {
        Connection connection = new SingletonConnexion().getConnection();
        try (PreparedStatement pstmt = new SingletonConnexion().connect().prepareStatement(
                "DELETE FROM etudiants WHERE id=?")) {

            pstmt.setInt(1, id);

            // Execute the delete statement
            pstmt.executeUpdate();
            System.out.println("Record deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Student> searchStudentByName(String name) {
        List<Student> students = new ArrayList<>();
        try (PreparedStatement pstmt = new SingletonConnexion().connect().prepareStatement(
                "SELECT * FROM etudiants WHERE name LIKE ?")) {

            pstmt.setString(1, "%" + name + "%");
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String studentName = resultSet.getString("name");
                String email = resultSet.getString("email");
                int note = resultSet.getInt("note");

                Student student = new Student(id, studentName, email, note);
                students.add(student);
            }

            System.out.println("Search completed successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

    // Dans votre classe Controller
    public Student getStudentById(int studentId) {
        Student student = null;
        try (PreparedStatement pstmt = new SingletonConnexion().connect().prepareStatement(
                "SELECT * FROM etudiants WHERE id = ?")) {

            pstmt.setInt(1, studentId);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int note = resultSet.getInt("note");

                student = new Student(studentId, name, email, note);
            }

            System.out.println("Student retrieved by ID successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

}

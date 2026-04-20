package Registration.src.com_emp;
import java.sql.*;

public class DAO {

    public static Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/emp_db?useSSL=false&serverTimezone=UTC",
                "root",
                "root123"
            );

            System.out.println("DB Connected");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }

    public static int insert(Employee emp) {
        int status = 0;

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO employee(name, email, department) VALUES (?, ?, ?)")) {

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getDepartment());

            status = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}
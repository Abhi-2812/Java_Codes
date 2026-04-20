package Registration.src.com_emp;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Servlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String dept = request.getParameter("department");

        Employee emp = new Employee();
        emp.setName(name);
        emp.setEmail(email);
        emp.setDepartment(dept);

        int status = DAO.insert(emp);

        if (status > 0) {
            request.setAttribute("name", name);
            RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
            rd.forward(request, response);
        } else {
            response.getWriter().println("Error inserting data");
        }
    }
}
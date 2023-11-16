package com.itsc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class AddEmployeeServlet
 */
@WebServlet("/addemployee")
public class AddEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String query =
            "insert into employees(name, designation, salary)" +
            "values(?, ?, ?)";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
        response.setContentType("text/html");
        
        String name = request.getParameter("name");
        String designation = request.getParameter("designation");
        float salary;

        try {
            salary = Float.parseFloat(request.getParameter("salary"));
        } catch (NumberFormatException e) {
            pw.println("<h2>Invalid Employee Salary format.</h2>");
            return;
        }

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
            pw.println("<h1>MySQL JDBC Driver not found.</h1>");
            return;
        }

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ems", "root", "");
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, designation);
            ps.setFloat(3, salary);

            int count = ps.executeUpdate();

            if (count == 1) {
                pw.println("<h2>Employee Added successfully.</h2>");
            } else {
                pw.println("<h2>Employee Not Added Successfully.</h2>");
            }

        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>SQLException: " + se.getMessage() + "</h1>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>Exception: " + e.getMessage() + "</h1>");
        }
        pw.println("<a href='index.html'>Home</a>");
        pw.println("<br>");
        pw.println("<a href='employeeslist'>Employees List</a>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

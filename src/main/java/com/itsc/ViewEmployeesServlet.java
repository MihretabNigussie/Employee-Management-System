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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class ViewEmployeesServlet
 */
@WebServlet("/employeeslist")
public class ViewEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String query = "select id, name, designation, salary from employees";  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewEmployeesServlet() {
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

          

            ResultSet rs = ps.executeQuery();
            pw.println("<table>");
            pw.println("<tr>");
            pw.println("<th>Employee ID</th>");
            pw.println("<th>Name</th>");
            pw.println("<th>Designation</th>");
            pw.println("<th>Salary</th>");
            pw.println("</tr>");
            
            while (rs.next()) {
            	pw.println("<tr>");
            	pw.println("<td>"+ rs.getInt(1)+"</td>");
            	pw.println("<td>"+ rs.getString(2)+"</td>");
            	pw.println("<td>"+ rs.getString(3)+"</td>");
            	pw.println("<td>"+ rs.getFloat(4)+"</td>");
            	pw.println("<td><a href ='editscreen?id=" + rs.getInt(1) +"'>edit</a></td>");
            	pw.println("<td><a href ='deleteurl?id=" + rs.getInt(1) +
            			"'>delete</a></td>");
            	pw.println("</tr>");
            }
            pw.println("</table>");
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>SQLException: " + se.getMessage() + "</h1>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>Exception: " + e.getMessage() + "</h1>");
        }
        pw.println("<a href='index.html'>Home</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

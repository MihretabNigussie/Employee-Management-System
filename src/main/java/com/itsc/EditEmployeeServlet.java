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
 * Servlet implementation class EditEmployeeServlet
 */
@WebServlet("/editurl")
public class EditEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String query = "update employees set name=?,designation=?, salary=? where id= ?";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEmployeeServlet() {
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
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		String name = request.getParameter("name");
		String designation= request.getParameter("designation");
		float salary = Float.parseFloat(request.getParameter("salary"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		
		try {
			Connection conn = DriverManager.getConnection(
			 "jdbc:mysql:///ems", 
			 "root", 
			"");
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, designation);
			ps.setFloat(3, salary);
			ps.setInt(4, id);
			int count = ps.executeUpdate();
			if(count == 1) {
			pw.println("<h2>Record is edited successfully.</h2>");
			}else {
			pw.println("<h2>Record not edited.</h2>");
		}
		} catch (SQLException se) {
			se.printStackTrace();
			pw.println("<h1>" + se.getMessage() + "</h1>");
			} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>" + e.getMessage() + "</h1>");
			}
			pw.println("<a href='index.html'>Home</a>");
			pw.print("<br>");
			pw.println("<a href='employeeslist'>Employee List</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

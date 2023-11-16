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
 * Servlet implementation class DeleteEmployeeServlet
 */
@WebServlet("/deleteurl")
public class DeleteEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String query = 
			 "Delete from employees where id = ?";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		//set content type
		response.setContentType("text/html");
		// get the id of record
		int id = Integer.parseInt(request.getParameter("id"));
		//load the jdbc driver
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException cnf) {
		cnf.printStackTrace();
		}
		//generate the connection
		try {
		Connection conn = DriverManager.getConnection(
		 "jdbc:mysql:///ems", "root", "");
		PreparedStatement ps = 
		 conn.prepareStatement(query);
		ps.setInt(1, id);
		int count = ps.executeUpdate();
		if(count == 1) {
		pw.println("<h2>Record is deleted successfully.</h2>");
		}else {
		pw.println("<h2>Record not deleted.</h2>");
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

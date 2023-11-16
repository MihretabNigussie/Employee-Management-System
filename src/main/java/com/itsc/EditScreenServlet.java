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
 * Servlet implementation class EditScreenServlet
 */
@WebServlet("/editscreen")
public class EditScreenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String query = "select name, designation, salary from employees where id = ?";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditScreenServlet() {
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
		Connection conn = 
		 DriverManager.getConnection(
		 "jdbc:mysql:///ems", 
		 "root", "");
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		pw.println("<form action='editurl?id="+id+"' method = 'post'>");
		pw.println("<table>");
		pw.println("<tr>");
		pw.println("<td>name</td>");
		pw.println("<td><input type = 'text', name = 'name', value = '" + rs.getString(1)+"'</td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td>designation</td>");
		pw.println("<td><input type = 'text', name = 'designation', value = '" + rs.getString(2)+"'</td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td>salary</td>");
		pw.println("<td><input type = 'text', name = 'salary', value = '" + rs.getFloat(3)+"'</td>");
		pw.println("</tr>");
		pw.println("</table>");
		pw.println("</form>");
		} catch (SQLException se) {
		se.printStackTrace();
		pw.println("<h1>" + se.getMessage() + "</h1>");
		} catch (Exception e) {
		e.printStackTrace();
		pw.println("<h1>" + e.getMessage() + "</h1>");
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

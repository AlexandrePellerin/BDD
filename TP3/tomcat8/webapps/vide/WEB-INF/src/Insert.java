import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@WebServlet("/servlet/Insert")

public class Insert extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();

		Connection con = null;
		Statement stmt = null;

		String table = req.getParameter("table");
		if (table == null) {
			table = "produits";
		}

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

		}

		String url = "jdbc:postgresql://psqlserv/n3p1";
		String nom = "pelleria";
		String mdp = "moi";

		ResultSetMetaData resMeta;
		ResultSet rs = null;
		int nb = 0;
		String query = "";
		
		try {
			res.setContentType("text/html");
			out.println(
					"<head><title>Insert</title><link rel=\"stylesheet\" href=\"/vide/style.css\" /></head><body><center>");
			out.println("<h1>Table " + table + "</h1>");
			con = DriverManager.getConnection(url, nom, mdp);
			stmt = con.createStatement();	
			
		} catch (SQLException e) {
			out.println("<h2>Erreur de connexion.</h2>");
		}
		try{
			rs = stmt.executeQuery("select * from " + table);
		}catch (SQLException e) {
			table = "produits";
			try {
				rs = stmt.executeQuery("select * from " + table);
			} catch (SQLException e2) {
				out.println("<h2>La table n'existe pas.</h2>");
			}
		}
		try{
			resMeta = rs.getMetaData();
			nb = resMeta.getColumnCount();
			
			res.sendRedirect("Select?table="+table);
		} catch (SQLException e) {
			
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.println("</center> </body> </html>");
	}
}
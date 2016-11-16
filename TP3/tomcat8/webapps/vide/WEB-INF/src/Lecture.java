import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/servlet/Lecture")

public class Lecture extends HttpServlet {

	private static final long serialVersionUID = 7570959486331090632L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();

		Connection con = null;
		Statement stmt = null;

		String login = (String) req.getSession().getAttribute("login");
		Boolean connecte = (Boolean) req.getSession().getAttribute("connecte");
		if(connecte == null || !connecte){
			res.sendRedirect("/vide/login.html");
		}

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

		}

		String url = "jdbc:postgresql://psqlserv/n3p1";
		String nom = "pelleria";
		String mdp = "moi";

		ResultSet rs = null;

		res.setContentType("text/html");
		out.println("<html><head><title>Lecture "+ login +"</title><link rel=\"stylesheet\" "
				+ "href=\"/vide/style.css\" /></head><body><center>");
		
		try {
			con = DriverManager.getConnection(url, nom, mdp);
			stmt = con.createStatement();
		} catch (SQLException e) {
			out.println("Erreur de Connexion.");
		}

		try {
			rs = stmt.executeQuery("Select * from personnes where login='" + login + "'");
			out.println("<table><tr>");
			if (rs.next()) {
				for (int i = 1; i < 9; i++) {
					out.println("<td>" + rs.getString(i) + "</td>");
				}
			}
			out.println("</tr></table>");
		} catch (SQLException e) {
			out.println("Erreur de Requete");
		}

		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.println("</center> </body> </html>");
	}
}

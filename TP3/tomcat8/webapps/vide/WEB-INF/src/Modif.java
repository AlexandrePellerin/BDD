import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/servlet/Modif")
public class Modif extends HttpServlet {

	private static final long serialVersionUID = 1060678263437787426L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();

		Connection con = null;
		Statement stmt = null;

		String login = (String) req.getSession().getAttribute("login");

		Boolean connecte = (Boolean) req.getSession().getAttribute("connecte");
		if (connecte == null || !connecte) {
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
		ResultSetMetaData resMeta = null;

		res.setContentType("text/html");
		out.println("<html><head><title>Modif " + login + "</title><link rel=\"stylesheet\" "
				+ "href=\"/vide/style.css\" /></head><body><center>");

		try {
			con = DriverManager.getConnection(url, nom, mdp);
			stmt = con.createStatement();
		} catch (SQLException e) {
			out.println("Erreur de Connexion.");
		}

		try {
			rs = stmt.executeQuery("select * from personnes where login = '" + login + "'");
			rs.next();
			if (rs.getString(9).equals("admin")) {
				rs = stmt.executeQuery("select * from personnes");
			} else {
				rs = stmt.executeQuery("select * from personnes where login = '" + login + "'");
			}
			resMeta = rs.getMetaData();
			out.println("<table>");

			while (rs.next()) {
				out.println("<tr><form action=\"/vide/servlet/Update\" method=\"post\">");
				for (int i = 1; i < 9; i++) {
					out.println("<td><input type=\"text\" name=\"" + resMeta.getColumnName(i) + "\" value=\""
							+ rs.getString(i) + "\"></td>");
				}
				out.println("<td><input type=\"submit\" value=\"Inserer\"></td>");
				out.println("</form><tr>");
			}

			out.println("</table>");
		} catch (SQLException e) {
			e.printStackTrace();
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

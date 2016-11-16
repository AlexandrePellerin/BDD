import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/servlet/Synthese")

public class Synthese extends HttpServlet {

	private static final long serialVersionUID = 7096280221527877616L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();

		Connection con = null;
		Statement stmt = null;

		String table = "salles";

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

		}

		String url = "jdbc:postgresql://psqlserv/n3p1";
		String nom = "pelleria";
		String mdp = "moi";

		try {
			res.setContentType("text/html");
			out.println(
					"<head><title>Synthese</title><link rel=\"stylesheet\" href=\"/vide/style.css\" /></head><body>");
			out.println("<nav><ol><li><a href=\"/vide/saisie.html\">Saisie</a></li>"
					+ "<li><a href=\"/vide/servlet/Lister\">Lister</a></li>"
					+ "<li><a href=\"/vide/servlet/Synthese\">Synthese</a></li></ol></nav>");
			out.println("<center>");
			con = DriverManager.getConnection(url, nom, mdp);
			stmt = con.createStatement();

		} catch (SQLException e) {
			out.println("<h2>Erreur de connexion.</h2>");
		}
		ResultSet rs = null;

		try {
			res.setContentType("text/html");
			out.println(
					"<head><title>Synthese</title><link rel=\"stylesheet\" href=\"/vide/style.css\" /></head><body><center>");
			out.println("<h1>Table " + table + "</h1>");
			con = DriverManager.getConnection(url, nom, mdp);
			stmt = con.createStatement();
		} catch (SQLException e) {
			out.println("<h2>Erreur de connexion.</h2>");
		}
		try {
			rs = stmt.executeQuery("select ip,count(*),Min(dat),Max(dat) from " + table + " group by ip");

			out.println("<table><tr>");
			out.println("<th>" + "ip" + "</th>");
			out.println("<th>" + "Count" + "</th>");
			out.println("<th>" + "Min Dat" + "</th>");
			out.println("<th>" + "Max Dat" + "</th>");

			while (rs.next()) {

				out.println("<tr>");
				for (int i = 0; i < 4; i++) {

					out.println("<td>");
					if (i == 0) {
						out.println("<a href=http://localhost:8080/vide/servlet/Lister?ip=" + rs.getString(i + 1) + ">"
								+ rs.getString(i + 1));
					} else {
						out.println(rs.getString(i + 1));
					}
					out.println("</td>");

				}
				out.println("</tr>");
			}
			out.println("</table>");

		} catch (SQLException e) {
			out.println("<h2>Ca catch l'init</h2>");
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
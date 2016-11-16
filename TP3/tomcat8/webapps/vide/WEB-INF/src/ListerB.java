import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.ResultSetMetaData;

@WebServlet("/servlet/ListerB")

public class ListerB extends HttpServlet {

	public static final String ASC = "asc";
	public static final String DESC = "desc";

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();

		Connection con = null;
		Statement stmt = null;

		String tri = req.getParameter("tri");
		if (tri == null) {
			tri = "nom";
		}
		String sens = req.getParameter("sens");
		if (sens == null || !(sens.equals(ASC) || sens.equals(DESC))) {
			sens = ASC;
		}
		String filtre = req.getParameter("filtre");
		if (filtre == null) {
			filtre = "";
		}

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

		}

		String url = "jdbc:postgresql://psqlserv/n3p1";
		String nom = "pelleria";
		String mdp = "moi";

		out.println(
				"<head><title>ListerB</title><link rel=\"stylesheet\" href=\"/vide/style.css\" /></head><body><center>");
		out.println("<h1>Table Resultat</h1>");

		int nb = 0;
		ResultSetMetaData resMeta;
		String query = "";
		boolean triValide = false;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, nom, mdp);
			stmt = con.createStatement();

			rs = stmt.executeQuery("select * from resultats");

			resMeta = rs.getMetaData();
			nb = resMeta.getColumnCount();

			for (int i = 1; i <= nb; i++) {
				if (tri.equals(resMeta.getColumnName(i))) {
					if (filtre.equals("")) {
						query = "select * from resultats " + "ORDER BY " + tri + " " + sens;
					} else {
						query = "select * from resultats WHERE " + tri + " = "
								+ filtre +  " ORDER BY " + tri + " " + sens ;
					}
					triValide = true;
				}
			}
		} catch (SQLException e) {
			out.println("<h2>Ca catch l'init</h2>");
		}
		boolean requete = false;
		while (!requete) {
			try {
				rs = stmt.executeQuery(query);
				requete = true;
			} catch (SQLException e) {
				out.println("Filtre invalide : "+filtre);
				query = "select * from resultats " + "ORDER BY " + tri + " " + sens;
			}
		}
		try {
			resMeta = rs.getMetaData();

			out.println("<form action=\"/vide/servlet/ListerB\" method=\"get\"> <p>");
			out.println("<label>Tri</label> : <input type=\"text\" name=\"tri\" value=\"" + tri + "\">");
			out.println("<label>Sens</label> : <input type=\"text\" name=\"sens\" value=\"" + sens + "\">");
			out.println("<label>Filtre</label> : <input type=\"text\" name=\"filtre\" value=\"" + filtre + "\">");
			out.println("</p>");
			out.println("<p><input type=\"submit\"></p>");

			out.println("<table>");

			out.println("<tr>");
			for (int i = 1; i <= nb; i++) {
				if (resMeta.getColumnName(i).equals(tri)) {
					if (sens.equals(DESC)) {
						sens = ASC;
					} else {
						sens = DESC;
					}
					out.println("<th>" + "<a href=http://localhost:8080/vide/servlet/ListerB?tri="
							+ resMeta.getColumnName(i) + "&sens=" + sens + ">" + resMeta.getColumnName(i) + "</a>"
							+ "</th>");
				} else {
					out.println("<th>" + "<a href=http://localhost:8080/vide/servlet/ListerB?tri="
							+ resMeta.getColumnName(i) + "&sens=" + ASC + ">" + resMeta.getColumnName(i) + "</a>"
							+ "</th>");
				}

			}
			out.println("</tr>");

			while (rs.next()) {

				out.println("<tr>");
				for (int i = 0; i < nb; i++) {
					out.println("<td>");
					out.println(rs.getString(i + 1));
					out.println("</td>");
				}
				out.println("</tr>");
			}
			out.println("</table>");

			con.close();
		} catch (SQLException e) {
			out.println("<h2>Ca catch " + tri + "</h2>");
		}
		out.println("</center> </body>");
		out.println("</html>");
	}
}

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/servlet/Select")

public class Select extends HttpServlet {

	private static final long serialVersionUID = -5022831265943562128L;

	public static final String ASC = "asc";
	public static final String DESC = "desc";

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();

		Connection con = null;
		Statement stmt = null;

		String table = req.getParameter("table");
		if (table == null) {
			table = "produits";
		}
		String tri = req.getParameter("tri");

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

		ResultSetMetaData resMeta;
		ResultSet rs = null;
		int nb = 0;
		String query = "";
		boolean triValide = false;

		try {
			res.setContentType("text/html");
			out.println(
					"<head><title>Select</title><link rel=\"stylesheet\" href=\"/vide/style.css\" /></head><body><center>");
			out.println("<h1>Table " + table + "</h1>");
			con = DriverManager.getConnection(url, nom, mdp);
			stmt = con.createStatement();
		} catch (SQLException e) {
			out.println("<h2>Erreur de connexion.</h2>");
		}
		try {
			rs = stmt.executeQuery("select * from " + table);
		} catch (SQLException e) {
			table = "produits";
			try {
				rs = stmt.executeQuery("select * from " + table);
			} catch (SQLException e2) {
				out.println("<h2>Mauvaise Requete.</h2>");
			}
		}
		try {
			resMeta = rs.getMetaData();
			nb = resMeta.getColumnCount();

			if (tri == null) {
				tri = resMeta.getColumnName(1);
			}

			while (!triValide) {
				for (int i = 1; i <= nb; i++) {
					if (tri.equals(resMeta.getColumnName(i))) {
						if (filtre.equals("")) {
							query = "select * from " + table + " ORDER BY " + tri + " " + sens;
						} else {
							query = "select * from " + table + " WHERE " + tri + " = " + filtre + " ORDER BY " + tri
									+ " " + sens;
						}
						triValide = true;
					}
				}
				if (!triValide) {
					tri = resMeta.getColumnName(1);
				}
			}

		} catch (SQLException e) {
			out.println("<h2>Ca catch l'init</h2>");
		}

		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			out.println("Filtre invalide : " + filtre);
			query = "select * from  " + table + " ORDER BY " + tri + " " + sens;
			try {
				rs.getStatement().executeQuery(query);
			} catch (SQLException e2) {

			}
		}

		try {
			resMeta = rs.getMetaData();

			out.println("<form action=\"/vide/servlet/Select\" method=\"get\"> <p>");
			out.println("<label>Table</label> : <input type=\"text\" name=\"table\" value=\"" + table + "\">");
			out.println("<label>Tri</label> : <input type=\"text\" name=\"tri\" value=\"" + tri + "\">");
			out.println("<label>Sens</label> : <input type=\"text\" name=\"sens\" value=\"" + sens + "\">");
			out.println("<label>Filtre</label> : <input type=\"text\" name=\"filtre\" value=\"" + filtre + "\">");
			out.println("</p>");
			out.println("<p><input type=\"submit\"></p>");
			out.println("</form>");
			
			out.println("<table>");

			out.println("<tr>");
			for (int i = 1; i <= nb; i++) {
				if (resMeta.getColumnName(i).equals(tri)) {
					if (sens.equals(DESC)) {
						sens = ASC;
					} else {
						sens = DESC;
					}
					out.println(
							"<th>" + "<a href=http://localhost:8080/vide/servlet/Select?tri=" + resMeta.getColumnName(i)
									+ "&sens=" + sens + ">" + resMeta.getColumnName(i) + "</a>" + "</th>");
				} else {
					out.println("<th>" + "<a href=http://localhost:8080/vide/servlet/Select?" + "table=" + table
							+ "&tri=" + resMeta.getColumnName(i) + "&sens=" + ASC + ">" + resMeta.getColumnName(i)
							+ "</a>" + "</th>");
				}

			}
			out.println("<th>Test</th>");
			out.println("</tr>");

			while (rs.next()) {

				out.println("<tr>");
				for (int i = 0; i < nb; i++) {
					out.println("<td>");
					out.println(rs.getString(i + 1));
					out.println("</td>");
				}
				out.println("<td>Add</td>");
				out.println("</tr>");
			}

			out.println("<tr>");
			out.println("<form action=\"Insert?table="+table+"\" method=\"post\">");
			for (int i = 1; i <= nb; i++) {
				out.println("<td><input type=\"text\" name=\"" + resMeta.getColumnName(i) + "\"></td>");
			}
			out.println("<td><input type=\"submit\" value=\"Inserer\"></td>");
			out.println("</form></tr>");

			out.println("</table>");

		} catch (SQLException e) {
			out.println("<h2>Ca catch a la fin</h2>");
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
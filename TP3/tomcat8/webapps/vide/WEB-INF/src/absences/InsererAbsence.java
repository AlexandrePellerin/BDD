package absences;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;

@WebServlet("/servlet/absences/InsererAbsence")
public class InsererAbsence extends HttpServlet {

	private static final long serialVersionUID = 8781181273727762165L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();

		Connection con = null;
		Statement stmt = null;

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

		}

		String url = "jdbc:postgresql://psqlserv/n3p1";
		String nom = "pelleria";
		String mdp = "moi";

		String numetu = StringEscapeUtils.escapeHtml4(req.getParameter("numetu"));
		String dateDebut = StringEscapeUtils.escapeHtml4(req.getParameter("dateDebut"));
		String hDeb = StringEscapeUtils.escapeHtml4(req.getParameter("hDeb"));
		
		res.setContentType("text/html");
		out.println(
				"<head><title>Insert</title><link rel=\"stylesheet\" href=\"/vide/style.css\" /></head><body><center>");

		try {
			con = DriverManager.getConnection(url, nom, mdp);
			stmt = con.createStatement();
		} catch (SQLException e) {
			out.println("<h2>Erreur de connexion.</h2>");
		}

		try {
			
		} catch (SQLException e) {

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

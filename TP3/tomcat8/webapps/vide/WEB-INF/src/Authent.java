import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/Authent")

public class Authent extends HttpServlet {

	private static final long serialVersionUID = -445695039276613022L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();

		Connection con = null;
		Statement stmt = null;

		res.setContentType("text/html");
		out.println("<html><head><title>Cpt</title><link rel=\"stylesheet\" "
				+ "href=\"/vide/style.css\" /></head><body><center>");

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

		}

		String url = "jdbc:postgresql://psqlserv/n3p1";
		String nom = "pelleria";
		String mdp = "moi";

		boolean valide = true;

		String login = req.getParameter("login");
		if (login == null) {
			valide = false;
		}

		String password = req.getParameter("password");
		if (password == null) {
			valide = false;
		}

		if (!valide) {
			out.println("<h1>Un des deux champs est vide.</h1>");
		} else {
			try {
				con = DriverManager.getConnection(url, nom, mdp);
				stmt = con.createStatement();
			} catch (SQLException e) {
				out.println("Erreur de Connexion.");
			}
			ResultSet rs = null;
			try {
				rs = stmt.executeQuery("select login, mdp from personnes where login='" + login + 
						"' and mdp='" + password+"'");
				if (rs.next()) {
					out.println("<h1>Login et Mot de Passe Valide</h1>");
					valide = true;
				} else {
					out.println("<h1>Login ou mot de passe invalide</h1>");
					valide = false;
				}
			} catch (SQLException e) {
				out.println("<h1>Erreur sur la requete</h1>");
			}
		}

		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.println("</center> </body> </html>");
		if(valide){
			req.getSession().setAttribute("connecte", true);
			req.getSession().setAttribute("login", login);
			req.getSession().setMaxInactiveInterval(60);
			res.sendRedirect("/vide/servlet/Menu");
		}else{
			res.sendRedirect("/vide/login.html");
		}
	}

}

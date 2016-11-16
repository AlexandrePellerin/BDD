import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/servlet/Update")

public class Update extends HttpServlet{

	private static final long serialVersionUID = 3729842203142240698L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();

		Connection con = null;
		Statement stmt = null;

		String login = (String) req.getSession().getAttribute("login");

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

		}

		String url = "jdbc:postgresql://psqlserv/n3p1";
		String nom = "pelleria";
		String mdp = "moi";
	
		try {
			con = DriverManager.getConnection(url, nom, mdp);
			stmt = con.createStatement();
		} catch (SQLException e) {
			out.println("Erreur de Connexion.");
		}
		
		res.setContentType("text/html");
		out.println("<html><head><title>Update</title><link rel=\"stylesheet\" "
				+ "href=\"/vide/style.css\" /></head><body><center>");
		
		try{
			ResultSet rs = stmt.executeQuery("update personnes set login='"+req.getParameter("login")
			+"', mdp='"+req.getParameter("mdp")
			+"', nom='"+req.getParameter("nom")
			+"', prenom='"+req.getParameter("prenom")
			+"', adresse='"+req.getParameter("adresse")
			+"', email='"+req.getParameter("email")
			+"', datenaiss='"+req.getParameter("datenaiss")
			+"', tel='"+req.getParameter("tel")
			+"' where login='"+ login+"'"
			//ICI
			);
		}catch(SQLException e){
			out.println("Erreur de requetes.");
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		res.sendRedirect("/vide/servlet/Modif");
		
		out.println("</center> </body> </html>");
	}
}

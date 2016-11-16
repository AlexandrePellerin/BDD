import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/Inserer")

public class Inserer extends HttpServlet{
	
	private static final long serialVersionUID = 8967076427544121028L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		
		Connection con = null;
		Statement stmt = null;
		
		String table = "salles";
		
		String name = req.getParameter("nom");
		String taille = req.getParameter("taille");
		String chaises = req.getParameter("chaises");
		String portes = req.getParameter("portes");
		String fenetres = req.getParameter("fenetres");
		
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
					"<head><title>Insert</title><link rel=\"stylesheet\" href=\"/vide/style.css\" /></head><body><center>");
			out.println("<h1>Table " + table + "</h1>");
			con = DriverManager.getConnection(url, nom, mdp);
			stmt = con.createStatement();	
			
		} catch (SQLException e) {
			out.println("<h2>Erreur de connexion.</h2>");
		}
		
		try{
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SS");
			if(!(name == null || name.equals(""))){
				ResultSet rs = stmt.executeQuery("insert into " + table + " values('" + name+"','"+taille+"','"+chaises 
					+"','"+portes+"','"+fenetres+"','"+req.getRemoteAddr()+"','"+format.format(date)+"')");
			}
		}catch(SQLException e){
			
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.println("</center> </body> </html>");
		
		res.sendRedirect("/vide/saisie.html");
	}
}

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/Lister")

public class Lister extends HttpServlet {

	private static final long serialVersionUID = 6887782183237700865L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();

		Connection con = null;
		Statement stmt = null;

		String table = "salles";

		int page;
		try {
			page = Integer.parseInt(req.getParameter("page"));
		} catch (Exception e) {
			page = 1;
		}
		
		String ip = req.getParameter("ip");
		if(ip == null){
			ip = "";
		}
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

		}

		String url = "jdbc:postgresql://psqlserv/n3p1";
		String nom = "pelleria";
		String mdp = "moi";

		try {
			res.setContentType("text/html");
			out.println("<head><title>Lister</title><link rel=\"stylesheet\" href=\"/vide/style.css\" /></head><body>");
			out.println("<nav><ol><li><a href=\"/vide/saisie.html\">Saisie</a></li>"
					+ "<li><a href=\"/vide/servlet/Lister\">Lister</a></li>"
					+ "<li><a href=\"/vide/servlet/Synthese\">Synthese</a></li></ol></nav>");
			out.println("<center>");
			con = DriverManager.getConnection(url, nom, mdp);
			stmt = con.createStatement();

		} catch (SQLException e) {
			out.println("<h2>Erreur de connexion.</h2>");
		}
		ResultSetMetaData resMeta;
		ResultSet rs = null;
		int nb = 0;

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
		int lignes = 0;
		try {
			ResultSet count = stmt.executeQuery("select count(*) from " + table + " where ip like '"+ip+"%'");
			if(count.next()){
				lignes = Integer.parseInt(count.getString(1));
			}
		}catch(Exception e){
			lignes = 0;
		}
		try{
			rs = stmt.executeQuery("select * from " + table + " where ip like '"+ip+"%'");
		} catch (SQLException e) {

		}
		try {
			resMeta = rs.getMetaData();
			nb = resMeta.getColumnCount();

			out.println("<table><tr>");

			for (int i = 1; i <= nb; i++) {
				out.println("<th>" + resMeta.getColumnName(i) + "</th>");
			}
			int cpt = 0;
			while (rs.next()) {
				if (cpt < (page * 10) && cpt >= (page*10-10)) {
					out.println("<tr>");
					for (int i = 0; i < nb; i++) {
						out.println("<td>");
						out.println(rs.getString(i + 1));
						out.println("</td>");
					}
					out.println("</tr>");
				}
				cpt++;
			}
			out.println("</table>");
			
			out.println("<form action=\"/vide/servlet/Lister?page="+(1)+"&ip="+ip+"\" method=\"post\">");
			out.println("<input type=\"submit\" value=\"<<\" \" >");
			out.println("</form>");
			if(page>1){
				out.println("<form action=\"/vide/servlet/Lister?page="+(page-1)+"&ip="+ip+"\" method=\"post\">");
			}else{
				out.println("<form action=\"/vide/servlet/Lister?page="+(page)+"&ip="+ip+"\" method=\"post\">");
			}
			out.println("<input type=\"submit\" value=\"<\" \" >");
			out.println("</form>");
			if(page<((lignes+9)/10)){
				out.println("<form action=\"/vide/servlet/Lister?page="+(page+1)+"&ip="+ip+"\" method=\"post\">");
			}else{
				out.println("<form action=\"/vide/servlet/Lister?page="+(page)+"&ip="+ip+"\" method=\"post\">");
			}
			out.println("<input type=\"submit\" value=\">\" \" >");
			out.println("</form>");
			out.println("<form action=\"/vide/servlet/Lister?page="+((lignes+9)/10)+"&ip="+ip+"\" method=\"post\">")	;
			out.println("<input type=\"submit\" value=\">>\" \" >");
			out.println("</form>");
			
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
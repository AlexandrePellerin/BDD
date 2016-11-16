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

@WebServlet("/servlet/ListerA")

public class ListerA extends HttpServlet{
	
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		PrintWriter out = res.getWriter();
		
		String tri = req.getParameter("tri");
		if(tri == null){
			tri = "nom";
		}
		String sens = req.getParameter("sens");
		if( sens == null 
			|| !(sens.equals(ASC) || sens.equals(DESC)) 
		){
			sens = ASC;
		}
		
		Connection con = null;
		Statement stmt;
	
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}catch(ClassNotFoundException e){
			
		}
		
		String url = "jdbc:odbc:ACCES";
		String nom = "pelleria";
		String mdp = "moi";
		
		out.println( "<head><title>ListerA</title><link rel=\"stylesheet\" href=\"/vide/style.css\" /></head><body><center>" );
		out.println( "<h1>Table Resultat</h1>" );
		
		res.setContentType( "text/html" );
		
		try{
		con = DriverManager.getConnection(url, nom, mdp);
		stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery("select * from resultats");
		
		ResultSetMetaData resMeta = rs.getMetaData();
		int nb = resMeta.getColumnCount();
		
		String query = "";
		boolean triValide = false;
		for(int i=1;i<=nb;i++){
			if(tri.equals(resMeta.getColumnName(i))){
				query = "select * from resultats " + "ORDER BY "+tri + " " +sens;
				triValide = true;
			}
		}
		rs = stmt.executeQuery(query);
		resMeta = rs.getMetaData();
		
		
		out.println( "<table>");
		
		out.println("<tr>");
		for(int i=1;i<=nb;i++){
			if(resMeta.getColumnName(i).equals(tri)){
				if(sens.equals(DESC)){
					sens = ASC;
				}else{
					sens = DESC;
				}
				out.println("<th>"+ "<a href=http://localhost:8080/vide/servlet/ListerA?tri="
						+resMeta.getColumnName(i)+"&sens="+sens+">"+resMeta.getColumnName(i)+"</a>"+"</th>");
			}else{
				out.println("<th>"+ "<a href=http://localhost:8080/vide/servlet/ListerA?tri="
						+resMeta.getColumnName(i)+"&sens="+ASC+">"+resMeta.getColumnName(i)+"</a>"+"</th>");
			}
			
			
		}
		out.println("</tr>");
		
		while(rs.next()){
	
	out.println("<tr>");
			for(int i=0;i<nb;i++){
			out.println("<td>");
			out.println(rs.getString(i+1));
			out.println("</td>");
			}
			out.println("</tr>");
		}
		out.println("</table>");
		
		
		
		con.close();
		}catch(SQLException e){
			out.println("<h2>Ca catch " + tri + "</h2>");
		}
		out.println( "</center> </body>" );
		out.println("</html>");
	}
}
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

@WebServlet("/servlet/Liste")

public class Liste extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		PrintWriter out = res.getWriter();
		
		Connection con = null;
		Statement stmt;
		String table = "personne";
	
		try{
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e){
			
		}
		
		String url = "jdbc:postgresql://psqlserv/n3p1";
		String nom = "pelleria";
		String mdp = "moi";
		
		
		try{
		con = DriverManager.getConnection(url, nom, mdp);
		stmt = con.createStatement();
		String query = "select * from "+table;
		ResultSet rs = stmt.executeQuery(query);
		
		ResultSetMetaData resMeta = rs.getMetaData();
		int nb = resMeta.getColumnCount();
		
		res.setContentType( "text/html" );
		out.println( "<head><title>Lister</title>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">");
		out.println("</head><body><center>" );
		out.println( "<h1>Table Resultats</h1>" );
		
		out.println( "<table class=\"table-centered table-hover table-condensed\">");
		//out.println("<tr><th>Nom</th><th>Prenom</th><th>Age</th></tr>");
		
		out.println("<tr>");
		for(int i=1;i<=nb;i++){
			out.println("<th>"+resMeta.getColumnName(i)+"</th>");
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
		
		out.println( "</center> </body>" );
		
		con.close();
		}catch(SQLException e){
			
		}
	}
}

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

@WebServlet("/servlet/TPLister")

public class TPLister extends HttpServlet{
	
	private static final long serialVersionUID = -1484276312201524120L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		PrintWriter out = res.getWriter();
		
		Connection con = null;
		Statement stmt;
	
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}catch(ClassNotFoundException e){
			
		}
		
		String url = "jdbc:odbc:ACCES";
		String nom = "pelleria";
		String mdp = "moi";
		
		
		try{
		con = DriverManager.getConnection(url, nom, mdp);
		stmt = con.createStatement();
		String query = "select * from resultats";
		ResultSet rs = stmt.executeQuery(query);
		
		ResultSetMetaData resMeta = rs.getMetaData();
		int nb = resMeta.getColumnCount();
		
		res.setContentType( "text/html" );
		out.println( "<head><title>Lister</title><link rel=\"stylesheet\" href=\"/vide/style.css\" /></head><body><center>" );
		out.println( "<h1>Table Resultats</h1>" );
		
		out.println( "<table>");
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
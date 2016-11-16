import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;


@WebServlet("/servlet/Select")
public class Select extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	PrintWriter out = res.getWriter();
	res.setContentType("text/html");
	
	/* NE PAS MODIFIER (Sauf indication)*/
	out.println("<!DOCTYPE html><html lang='fr'>");
	out.println("<head><meta charset='utf-8'><meta http-equiv='X-UA-Compatible' content='IE=edge'><meta name='viewport' content='width=device-width, initial-scale=1'>");
	
		/* Titre de la page HTML */
	out.println("<title>Menu</title>");
		/* **************** */
	
	out.println("<!-- Bootstrap core CSS --><link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css' rel='stylesheet'>");
	
	out.println("</head>");
	out.println("<body>");
	
	HttpSession session = req.getSession(true);
	Personne p = (Personne)session.getAttribute("login");
	if (p==null) res.sendRedirect("Deconnect");
	
	out.println("<div class='container'>");
	
		
	
		
	
	String query;
	if (p.role.equals("admin"))
	    {
		out.println("<div class='page-header'>");
			out.println("<h1>Affichage administrateur</h1>");
		out.println("</div>");
		query="select * from users";
	    }
	else
	    {
		out.println("<div class='page-header'>");
			out.println("<h1>Vos informations</h1>");
		out.println("</div>");
		query="select * from users where login ='"+p.login+"'";
	    }
	
	out.println("<div class='row'>");
		out.println("<div class='col-xs-12'>");
	
	Connection con =null;
	try {
	    
	    // enregistrement du driver
	    Class.forName("org.postgresql.Driver");
	    
	    // connexion a la base
	    con = DriverManager.getConnection("jdbc:postgresql://localhost/template1","mathieu","moi");
	    
	    // execution de la requete
	    Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    
	    out.println("<pre>"+query+"</pre>");
	    
	    out.println("<table class='table table-bordered table-striped'>");
	    
	    out.print("<thead>");
		out.print("<tr>");
	    out.print("<th>login</th>");
	    out.print("<th>mdp</th>");
	    out.print("<th>nom</th>");
	    out.print("<th>prenom</th>");
	    out.print("<th>role</th>");
	    out.print("<th>adresse</th>");
	    out.println("</tr>");
	    out.print("</thead>");
	    
	    out.print("<tbody>");
	    
	    while(rs.next())
		{
		    out.println("<tr>");

		    out.print("<td>"+rs.getString("login")+"</td>");
		    out.print("<td>"+rs.getString("mdp")+"</td>");
		    out.print("<td>"+rs.getString("nom")+"</td>");
		    out.print("<td>"+rs.getString("prenom")+"</td>");
		    out.print("<td>"+rs.getString("role")+"</td>");
		    out.print("<td>"+rs.getString("adresse")+"</td>");
		    out.println("</tr>");
		}
	    
		out.print("</tbody>");
		
	    out.println("</table>");
	    
	}
	catch (Exception e) {			
		out.println("<div class='alert alert-warning' role='alert'>Erreur "+e.getClass()+" : "+e.getMessage()+"</div>");
	}
	finally
	    {
		try{con.close();} catch (Exception e){}
	    }
			
			out.println("</div>");
		out.println("</div>");
	out.println("</div>");

	out.println("</body></html>");
    }
}

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;


@WebServlet("/servlet/New2")
public class New2 extends HttpServlet 
{
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    {
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
	
	out.println("<div class='container'>");
	
	out.println("<div class='page-header'>");
		out.println("<h1>Inscription</h1>");
	out.println("</div>");

	out.println("<div class='row'>");
		out.println("<div class='col-xs-12'>");
	
	Connection con=null;
	try 
	    {
		
		// enregistrement du driver
		Class.forName("org.postgresql.Driver");
		
		// connexion a la base
		con = DriverManager.getConnection("jdbc:postgresql://localhost/template1","mathieu","moi");
		
		String login = req.getParameter("login");
		String mdp = req.getParameter("mdp");
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String adresse = req.getParameter("adresse");
		
		// Verification du login
		Statement stmt = con.createStatement();
		String query = "select * from users where login='"+ login + "'";
		ResultSet rs = stmt.executeQuery(query);
		
		if(rs.next()) 
		    {
			out.println("<pre>Query : "+query+"</pre>");
			out.println("<div class='alert alert-danger' role='alert'>Le login "+ login +" est déja utilisé, veuillez en choisir un autre.</div>");
		    out.println("<a href='New'><button type='button' class='btn btn-default btn-lg'>Retour</button></a>");
		    }
		else
		    {
			String query2 = "insert into users values (";
			query2 += ("'"+login+"',");
			query2 += ("'"+mdp+"',");
			query2 += ("'"+nom+"',");
			query2 += ("'"+prenom+"',");
			query2 += ("'user',");
			query2 += ("'"+adresse+"')");
			
			out.println("<pre>Query : "+query2+"</pre>");
			
			stmt.executeUpdate(query2);
			
			out.println("<div class='alert alert-success' role='alert'>Bienvenue " + prenom + ", ! Votre compte à bien été créé !</div>");
		    out.println("<a href='../login.html'><button type='button' class='btn btn-default btn-lg'>Connexion</button></a>");
		    }
	    }
	catch (Exception e) {
		out.println("<div class='alert alert-warning' role='alert'>Erreur "+e.getClass()+" : "+e.getMessage()+"</div>");
	}
	finally{try {con.close();} catch (Exception e){}}

	out.println("</div>");
	out.println("</div>");
	out.println("</div>");
	out.println("</body></html>");

    }
}

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/servlet/Maj2")
public class Maj2 extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    {
	PrintWriter out = res.getWriter();
	res.setContentType("text/html");
	
	/* NE PAS MODIFIER (Sauf indication)*/
	out.println("<!DOCTYPE html><html lang='fr'>");
	out.println("<head><meta charset='utf-8'><meta http-equiv='X-UA-Compatible' content='IE=edge'><meta name='viewport' content='width=device-width, initial-scale=1'>");
	
		/* Titre de la page HTML */
	out.println("<title>Administration</title>");
		/* **************** */
	
	out.println("<!-- Bootstrap core CSS --><link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet'>");
	
	out.println("</head>");
	out.println("<body>");
	
	// authentifie ?
	HttpSession session = req.getSession(true);
	Personne p = (Personne)session.getAttribute("login");
	if (p==null) res.sendRedirect("Deconnect");

	out.println("<div class='container'>");
	
		out.println("<div class='page-header'>");
			out.println("<h1>Administration des informations.</h1>");
		out.println("</div>");
		
		out.println("<div class='row'>");
			out.println("<div class='col-xs-12'>");
	
	Connection con=null;
	try {
	    
	    // enregistrement du driver
	    Class.forName("org.postgresql.Driver");
	    
	    // connexion a la base
	    con = DriverManager.getConnection("jdbc:postgresql://localhost/template1","mathieu","moi");
	    
	    String login = req.getParameter("login");
	    String mdp = req.getParameter("mdp");
	    String nom = req.getParameter("nom");
	    String prenom = req.getParameter("prenom");
	    String adresse = req.getParameter("adresse");
	    
	    Statement stmt = con.createStatement();
	    
	    
	    // Verification du login
	    if (!login.equals(p.login)) // il a change son login
		{
		    String query = "select * from users where login='"+ login + "'";
		    out.println("<pre>"+query+"</pre>");
		    ResultSet rs = stmt.executeQuery(query);
		    
		    if(rs.next()) 
			{
			    out.println("<div class='alert alert-danger' role='alert'>Le login "+ login +" est déja utilisé, veuillez en choisir un autre.</div>");
			    con.close();
			    out.println("<a href='Maj'><button type='button' class='btn btn-default btn-lg'>Retour</button></a>");
			}
		}
	    
	    String query2 = "update users set ";
	    query2 += ("login='"+login+"',");
	    query2 += ("mdp='"+mdp+"',");
	    query2 += ("nom='"+nom+"',");
	    query2 += ("prenom='"+prenom+"',");
	    query2 += ("adresse='"+adresse+"'");
	    query2 += " where login ='"+p.login+"'";
	    
	    out.println("<pre>"+query2+"</pre>");
	    stmt.executeUpdate(query2);
	    
	    // modif de l'objet : ici on ne peut pas changer le role
	    p.maj(login,mdp,nom,prenom,adresse);
	    
	    out.println("<div class='alert alert-success' role='alert'>"+prenom + ", vos donnees ont ete mises a jour !</div>");
	    
	    out.println("<a href='Menu'><button type='button' class='btn btn-default btn-lg'>Retour au menu</button></a>");
	    
	    con.close();
	}
	catch (Exception e) {
		out.println("<div class='alert alert-warning' role='alert'>Erreur "+e.getClass()+" : "+e.getMessage()+"</div>");
	}
	finally
	    {
		try{con.close();} catch (Exception e){}
	    }
    }
}

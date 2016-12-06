import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.annotation.*;
import java.sql.*;

import org.apache.commons.lang3.StringEscapeUtils;

@WebServlet("/servlet/Maj2")
public class Maj2 extends HttpServlet {
    
	private static final long serialVersionUID = -6336027718603268014L;

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
	    con = DriverManager.getConnection("jdbc:postgresql://psqlserv/n3p1","pelleria","moi");
	    
	    String login = StringEscapeUtils.escapeHtml4(req.getParameter("login"));
	    String mdp = StringEscapeUtils.escapeHtml4(req.getParameter("mdp"));
	    String nom = StringEscapeUtils.escapeHtml4(req.getParameter("nom"));
	    String prenom = StringEscapeUtils.escapeHtml4(req.getParameter("prenom"));
	    String adresse = StringEscapeUtils.escapeHtml4(req.getParameter("adresse"));
	    
	    Statement stmt = con.createStatement();
	    
	    
	    // Verification du login
	    if (!login.equals(p.login)) // il a change son login
		{
	    	PreparedStatement ps = con.prepareStatement("select * from users where login=?");
	    	ps.setString(1, login);
		    ResultSet rs = ps.executeQuery();
		    
		    if(rs.next()) 
			{
			    out.println("<div class='alert alert-danger' role='alert'>Le login "+ login +" est déja utilisé, veuillez en choisir un autre.</div>");
			    con.close();
			    out.println("<a href='Maj'><button type='button' class='btn btn-default btn-lg'>Retour</button></a>");
			}
		}
	    
	    PreparedStatement pres = con.prepareStatement("update users set login=?, mdp=?, nom=?,"
	    		+ " prenom=?, adresse=? where login = ?");
	    pres.setString(1, login);
	    pres.setString(2, mdp);
	    pres.setString(3, nom);
	    pres.setString(4, prenom);
	    pres.setString(5, adresse);
	    pres.setString(6, p.login);
	    
	    ResultSet resSet = pres.executeQuery();
	    
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

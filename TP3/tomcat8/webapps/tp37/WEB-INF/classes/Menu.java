import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;


@WebServlet("/servlet/Menu")
public class Menu extends HttpServlet 
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
	
	out.println("<!-- Bootstrap core CSS --><link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' rel='stylesheet'>");
	
	out.println("</head>");
	out.println("<body>");
	
	// authentifie ?
	HttpSession session = req.getSession(true);
	Personne p = (Personne)session.getAttribute("login");
	if (p==null) res.sendRedirect("Deconnect");
	else
	    {
			out.println("<div class='container'>");
			
			out.println("<div class='page-header'>");
				out.println("<h1>Bonjour "+ p.prenom + ", que voulez faire ?</h1>");
			out.println("</div>");
			
			out.println("<div class='row'");
			out.println("<div class='col-xs-6 col-xs-offset-3'");
			out.println("<nav>");
			out.println("<ul class='nav nav-pills nav-justified'>");
				out.println("<li role='presentation' class='btn btn-default btn-lg'><a href='Select'>Voir les infos</a></li>");
				out.println("<li role='presentation' class='btn btn-default btn-lg'><a href='Maj'>Modifier vos infos</a></li>");
				out.println("<li role='presentation' class='btn btn-default btn-lg'><a href='Deconnect'>Déconnexion</a></li>");
			out.println("</ul>");
			out.println("</nav>");
		    out.println("</div>");
		    out.println("</div>");
	    }
    
    
    out.println("</body></html>");

    }
}

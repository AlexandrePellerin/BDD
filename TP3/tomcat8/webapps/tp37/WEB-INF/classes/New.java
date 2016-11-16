import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;


@WebServlet("/servlet/New")
public class New extends HttpServlet 
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
	
	out.println("<div class='container'>");
	
	out.println("<div class='page-header'>");
		out.println("<h1>Inscription</h1>");
	out.println("</div>");

	out.println("<div class='row'>");
		out.println("<div class='col-xs-12'>");
		
			out.println("<form class='form-horizontal' method='get' action='New2'>");
				out.println("<div class='form-group'>");
					out.println("<label for='inputLogin' class='col-sm-2 control-label'>Login</label>");
					out.println("<div class='col-sm-10'>");
						out.println("<input type='text' class='form-control' id='inputLogin' name='login'>");
					out.println("</div>");
				out.println("</div>");
				
				out.println("<div class='form-group'>");
					out.println("<label for='inputPassword' class='col-sm-2 control-label'>Mot de passe</label>");
					out.println("<div class='col-sm-10'>");
						out.println("<input type='text' class='form-control' id='inputPassword' name='mdp'>");
						out.println("</div>");
				out.println("</div>");
				
				out.println("<div class='form-group'>");
					out.println("<label for='inputNom' class='col-sm-2 control-label'>Nom</label>");
					out.println("<div class='col-sm-10'>");
						out.println("<input type='text' class='form-control' id='inputNom' name='nom'>");
					out.println("</div>");
				out.println("</div>");
			
				out.println("<div class='form-group'>");
					out.println("<label for='inputPrenom' class='col-sm-2 control-label'>Prénom</label>");
					out.println("<div class='col-sm-10'>");
						out.println("<input type='text' class='form-control' id='inputPrenom' name='prenom'>");
					out.println("</div>");
				out.println("</div>");
		
				out.println("<div class='form-group'>");
					out.println("<label for='inputAdresse' class='col-sm-2 control-label'>Adresse</label>");
					out.println("<div class='col-sm-10'>");
						out.println("<input type='text' class='form-control' id='inputAdresse' name='adresse'>");
					out.println("</div>");
				out.println("</div>");
				
				out.println("<div class='form-group'>");
					out.println("<div class='col-sm-offset-2 col-sm-10'>");
						out.println("<button type='submit' class='btn btn-primary'>Ajouter</button>");
					out.println("</div>");
				out.println("</div>");
			out.println("</form>");
			out.println("<a href='../login.html'><button class='btn btn-default'>Connexion</button></a>");
		
		out.println("</div>");
	out.println("</div>");
out.println("</div>");

out.println("</body></html>");
    }
}

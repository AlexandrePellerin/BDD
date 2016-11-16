import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Cpt")

public class Cpt extends HttpServlet {

	private static final long serialVersionUID = 7501638548747587465L;

	private int totalConnection = 0;

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();

		res.setContentType("text/html");
		out.println(
				"<html><head><title>Cpt</title><link rel=\"stylesheet\" "
				+ "href=\"/vide/style.css\" /></head><body><center>");
		
		totalConnection ++;
		HttpSession session = req.getSession( true );
		Integer cpt = (Integer)session.getAttribute( "compteur" );
		cpt = new Integer( cpt == null ? 1 : cpt.intValue() + 1 );
		session.setAttribute( "compteur", cpt );

		out.println("<h1>Vous vous êtes connectés " + cpt + " sur " + totalConnection + " fois.</h1>");
		
		out.println("</center> </body> </html>");
	}

}

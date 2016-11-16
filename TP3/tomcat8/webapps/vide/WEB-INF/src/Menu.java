import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Menu")

public class Menu extends HttpServlet{

	private static final long serialVersionUID = -4054868060818756785L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		
		Boolean connecte = (Boolean) req.getSession().getAttribute("connecte");
		if(connecte == null || !connecte){
			res.sendRedirect("/vide/login.html");
		}
		
		res.setContentType("text/html");
		out.println("<html><head><title>Menu</title><link rel=\"stylesheet\" "
				+ "href=\"/vide/style.css\" /></head><body><center>");
		
		out.println("<li><a href=\"/vide/servlet/Lecture\">Lecture</a>"
				+"</li><li><a href=\"/vide/servlet/Modif\">Modif</a></li>");
		
		out.println("</center> </body> </html>");
	}
}

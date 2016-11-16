import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Deconnect")

public class Deconnect extends HttpServlet{

	private static final long serialVersionUID = 616865082825214146L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.getSession().invalidate();
		res.sendRedirect("/vide/login.html");
		
	}

}

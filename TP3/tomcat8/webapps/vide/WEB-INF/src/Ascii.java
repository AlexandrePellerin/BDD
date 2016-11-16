import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Ascii")

public class Ascii extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		PrintWriter out = res.getWriter();
		
		String param = req.getParameter("nbCol");
		int nbCol = 1;
		try{
			nbCol = Integer.parseInt(param);
		}catch(Exception e){
			
		}
		
		res.setContentType( "text/html" );
		out.println( "<head><title>First</title><link rel=\"stylesheet\" href=\"/vide/style.css\" /></head><body><center>" );
		out.println( "<h1>Table Ascii</h1>" );
		
		out.println( "<table>");
		for(int i=32;i<255;i++){
			if((i-32)%nbCol == 0){
				if((i-32) != 0){
					out.println("</tr>");
				}
				out.println("<tr>");
			}
			out.println("<td>"+i+"</td>");
			out.println("<td>"+(char)i+"</td>");
		}
		out.println("</table>");
		
		out.println( "</center> </body>" );
	}
}
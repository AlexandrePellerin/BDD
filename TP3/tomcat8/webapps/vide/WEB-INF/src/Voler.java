import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/servlet/Voler")
public class Voler extends HttpServlet {

	private static final long serialVersionUID = 6306627520633603406L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Connection con = null;
		try {

			// enregistrement du driver
			Class.forName("org.postgresql.Driver");

			// connexion a la base
			con = DriverManager.getConnection("jdbc:postgresql://psqlserv/n3p1?allowMultiQueries=true", "pelleria",
					"moi");
			
			Statement stmt = con.createStatement();
			stmt.executeQuery("insert into numsessions values ('"+req.getParameter("p"));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

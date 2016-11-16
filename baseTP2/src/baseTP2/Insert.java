package baseTP2;

import java.sql.*;

public class Insert {
	public static void main(String args[]) throws Exception {
		// enregitrement du driver
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

		String url = "jdbc:odbc:USER";
		String nom = "pelleria";
		String mdp = "moi";
		Connection con = DriverManager.getConnection(url, nom, mdp);

		Statement stmt = con.createStatement();
		for(int i=1;i<1000;i++){
			stmt.executeUpdate("insert into CLIENTS "+
					"values ('nom_"+i+"', 'prenom_"+i+"', "+(i%80)+")");
		}
		
		// fermeture des espaces
		con.close();
	}
	
	
}

package baseTP2;

import java.sql.*;

public class Select {

	public static void main(String args[]) throws Exception {
		Connection con = null;
		Statement stmt;

		/*Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

		String url = "jdbc:odbc:tp2";
		String nom = "pelleria";
		String mdp = "moi";*/
		
		Proprietes prop = new Proprietes();
		try {
			Class.forName(prop.getDriver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// connexion à la base
		String url = prop.getUrl();
		String nom = prop.getLogin();
		String mdp = prop.getPassword();
		
		con = DriverManager.getConnection(url, nom, mdp);
		stmt = con.createStatement();
		String query = "select NOM,PRENOM,AGE from CLIENTS";
		ResultSet rs = stmt.executeQuery(query);

		System.out.println("Liste des clients:");
		while (rs.next()) {
			String n = rs.getString(1); // nom
			String p = rs.getString(2); // prenom
			int a = rs.getInt(3); // age
			System.out.println(n + " " + p + " " + a);
		}
		con.close();
	}
}

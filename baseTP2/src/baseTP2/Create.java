package baseTP2;

import java.sql.*;

public class Create {

	public static void main(String args[]) {
		// enregistrement du driver
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
		Connection con;
		Statement stmt;
		try {
			con = DriverManager.getConnection(url, nom, mdp);
			stmt = con.createStatement();
			stmt.executeUpdate("create table CLIENTS " + "(NOM varchar(10), PRENOM varchar(20), AGE int)");
		} catch (SQLException e) {
			con = null;
		}
		

		// fermeture des espaces
		try {
			con.close();
		} catch (SQLException e) {
		} catch (NullPointerException e){
		}
	}
}

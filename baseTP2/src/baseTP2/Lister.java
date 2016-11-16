package baseTP2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Lister {

	String table;
	Properties prop;

	public Lister(String table) {
		this.table = table;
		InputStream fichier = null;
		prop = new Properties();

		try {
			fichier = new FileInputStream(new File("E:/Documents/config.properties"));
			prop.load(fichier);
			fichier.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String driver = prop.getProperty("driver");
		
		try {
			Class.forName( driver );
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = prop.getProperty("url");
		String nom = prop.getProperty("login");
		String mdp = prop.getProperty("password");
		Connection con;
		Statement stmt;
		try {
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
		} catch (SQLException e) {
			con = null;
			e.printStackTrace();
		}
		

		// fermeture des espaces
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e){
			e.printStackTrace();
		}
	}

	public static void main(String[]args){
		new Lister("CLIENTS");
	}
}

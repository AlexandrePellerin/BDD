package baseTP2;

import java.sql.*;

public class Count {

	public static void main(String args[]) throws Exception {
		Connection con = null;
		Statement stmt;

		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

		String url = "jdbc:odbc:tp2";
		String nom = "pelleria";
		String mdp = "moi";
		con = DriverManager.getConnection(url, nom, mdp);
		stmt = con.createStatement();
		String query = "select count(*) from CLIENTS";
		ResultSet rs = stmt.executeQuery(query);

		System.out.println("Liste des clients:");
		while (rs.next()) {
			String n = rs.getString(1); // nom
			System.out.println(n);
		}
		con.close();
	}
}

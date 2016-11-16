package baseTP2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Type {

	public static void main(String[]args) throws Exception
	{
		Connection con = null;
		Statement stmt;

		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

		String url = "jdbc:odbc:tp2";
		String nom = "pelleria";
		String mdp = "moi";
		con = DriverManager.getConnection(url, nom, mdp);
		stmt = con.createStatement();
		
		String query = "select * from CLIENTS";
		ResultSet rs = stmt.executeQuery(query);
		
		ResultSetMetaData res = rs.getMetaData();
		int nb = res.getColumnCount();
		for(int i=1;i<=nb;i++){
			System.out.println("Colonne "+i+": "+res.getColumnName(i)+"("+res.getColumnTypeName(i)+")");
		}
		
		con.close();
	}
}

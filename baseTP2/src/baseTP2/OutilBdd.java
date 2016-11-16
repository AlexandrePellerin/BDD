package baseTP2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class OutilBdd {

	private Connection connection = null;
	private Proprietes prop;

	public OutilBdd() {
	}

	public void connecter() {
		prop = new Proprietes();
		try {
			connection = DriverManager.getConnection(prop.getUrl(), prop.getLogin(), prop.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void liste(String table) {
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String query = "select NOM,PRENOM,AGE from CLIENTS";
			ResultSet rs = stmt.executeQuery(query);

			System.out.println("Liste des clients:");
			
			ResultSetMetaData res = rs.getMetaData();
			int nb = res.getColumnCount();
			
			while (rs.next()) {
				for(int i=1;i<=nb;i++){
					System.out.print(rs.getString(i)+" ");
				}
				System.out.println("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public void compter(String table) {
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String query = "select count(*) from " + table;
			ResultSet rs = stmt.executeQuery(query);

			System.out.println("Liste des clients:");
			while (rs.next()) {
				String n = rs.getString(1); // nom
				System.out.println(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		OutilBdd outil = new OutilBdd();
		outil.connecter();
		outil.liste("CLIENTS");
		outil.compter("CLIENTS");
	}

}

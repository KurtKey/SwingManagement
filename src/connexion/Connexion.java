package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

	private static String url = "jdbc:mysql://localhost:3306/rappel2";
	private static String user = "root";
	private static String pass = "";
	private Connection connection;
	private static Connexion instance;

	private Connexion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			System.out.println("base de donnees introuvable!!");
		} catch (SQLException e) {
			System.out.println("connexion echouee!!");
		}
	}

	public static Connexion oneConnection() {
		if (instance == null)
			instance = new Connexion();
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}

}

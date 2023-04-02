import java.sql.*;
public class BaseConnection {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:21T1020569-2.db");
		return conn;
	}
}

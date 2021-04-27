package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {

//	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/BeautyCircle";
	private static final String DB_CONNECTION = "jdbc:postgresql://ec2-184-73-232-93.compute-1.amazonaws.com:5432/db1228k40ismlh?user=amynmsmglqpyqv&password=9a7dc88596deffbb78e359eebdd5b263d9867d2ec1591226cbc76eb0a2159c8e&sslmode=require";
//	private static final String DB_USER = "postgres";
//	private static final String DB_PASSWORD = "1996";

	private static final String DB_USER = "amynmsmglqpyqv";
	private static final String DB_PASSWORD = "9a7dc88596deffbb78e359eebdd5b263d9867d2ec1591226cbc76eb0a2159c8e";

	static Connection dbConnection = null;

	public static PreparedStatement prepare(String stm) throws SQLException {
		PreparedStatement preparedStatement = null;
		try {
			Connection dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(stm);

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}
		return preparedStatement;
	}

	private static Connection getDBConnection() {

		try {
			DriverManager.registerDriver(new org.postgresql.Driver());

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			getDbConnection().setAutoCommit(false);
			return getDbConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}

		System.out.println("Connection problem");
		return null;
	}

	public static Connection getDbConnection() {
		return dbConnection;
	}

	public static void commit() throws SQLException {
		dbConnection.commit();
	}

	public static void closePreparedStatement(PreparedStatement pstmt) throws SQLException {
		pstmt.close();

	}

	public static void closeConnection(Connection conn) throws SQLException {
		conn.close();

	}

}

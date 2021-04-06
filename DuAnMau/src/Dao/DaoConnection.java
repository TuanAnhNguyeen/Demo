package Dao;
import java.sql.*;
public class DaoConnection {
	public static String url ="jdbc:sqlserver://localhost:1433;databaseName=QuanLiKhoaHoc;integratedSecurity=true";
	public static Connection connection() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, "", "");
		return con;
	}
}

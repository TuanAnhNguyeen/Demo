/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NgocPJa
 */
public class DAOHelper {
	 static DaoConnection connectinon;
	
	public static PreparedStatement getStmt(String sql, Object...args) throws SQLException, ClassNotFoundException {
		PreparedStatement stmt;
		Connection con = DaoConnection.connection();
		if(sql.trim().startsWith("{")) {
			stmt =con.prepareCall(sql);
		}else {
			stmt = con.prepareStatement(sql);
		}
		for(int i =0;i<args.length;i++) {
			stmt.setObject(i+1, args[i]);
		}
		return stmt;
	}
	public static ResultSet query(String sql,Object...args) throws SQLException, ClassNotFoundException{
		PreparedStatement stmt = DAOHelper.getStmt(sql, args);
		return stmt.executeQuery();
	}
	public static Object value(String sql , Object... args){
		try {
			ResultSet rs = DAOHelper.query(sql, args);
			if(rs.next()) {
				return rs.getObject(0);
			}
			rs.getStatement().getConnection().close();
			return null;
		} catch (Exception e) {
			throw new RuntimeException();
		}	
	}
	public static int update(String sql,Object...args) {
		try {
			PreparedStatement stmt = DAOHelper.getStmt(sql, args);
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}

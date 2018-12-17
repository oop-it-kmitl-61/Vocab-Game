package com.mygdx.game;
import java.sql.*;
public class DBConnection {
	private static String DBpath;
	public Connection getConnection() {
		Connection c = null;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			String path = DBpath+"\\core\\assets\\vocab\\Vocab.accdb";
			String url = "jdbc:ucanaccess://"+path;
			c = DriverManager.getConnection(url);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	public static void setDBPath(String path) {
		DBpath = path;
	}
}

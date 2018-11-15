package com.mygdx.game;
import java.sql.*;
public class DBConnection {
	public Connection getConnection() {
		Connection c = null;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			String path = "C:\\Project\\Vocab-Game\\core\\assets\\vocab\\Vocab.accdb";
			String url = "jdbc:ucanaccess://"+path;
			c = DriverManager.getConnection(url);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
}

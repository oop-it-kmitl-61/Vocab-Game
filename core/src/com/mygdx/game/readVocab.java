package com.mygdx.game;
import java.sql.*;
public class readVocab {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection theConn = null;
		ResultSet resultSet;
		Statement statement;
		String sql, word, meaning;
		int id;
		try {
			DBConnection MyCon = new DBConnection();
			theConn = MyCon.getConnection();
			sql = "SELECT * FROM animals";
			statement = theConn.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				id= resultSet.getInt(1);
				word = resultSet.getString(2);
				meaning = resultSet.getString(3);
				System.out.printf("%d %s %s\n", id, word, meaning);
			}
			resultSet.close();
			statement.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

}

package com.mygdx.game;
import java.sql.*;
public class readVocab {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection theConn = null;
		ResultSet resultSet;
		Statement statement;
		PreparedStatement ps;
		String sql, word, meaning;
		int id;
		try {
			DBConnection MyCon = new DBConnection();
			theConn = MyCon.getConnection();
			sql = "UPDATE score SET bodies=?";
			//Try to make your ResultSet scrollable:
			ps = theConn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1, 10);
			ps.executeUpdate();
			//			resultSet = ps.executeQuery();
//			resultSet.first();
//			int score =resultSet.getInt("animals");
//			System.out.println(score);
//			resultSet.absolute(10); 
//			while(resultSet.next()) {
//				id= resultSet.getInt(1);
//				word = resultSet.getString(2);
//				meaning = resultSet.getString(3);
//				System.out.printf("%d %s %s\n", id, word, meaning);
//			}
//			resultSet.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

}

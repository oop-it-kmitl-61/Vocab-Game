package com.mygdx.game;
import java.sql.*;
import java.util.ArrayList;

import com.mygdx.game.screens.OptionScreen;
public class ReadVocabs {
		private static Connection theConn;
		private static ResultSet resultSet;
		private static PreparedStatement ps;
        private static ArrayList<Integer> random;
        private static Vocab[] vocab;
        private static String tableName;
		
        private static boolean isConnect(String tableName) {
			ReadVocabs.tableName = tableName;
			vocab = new Vocab[OptionScreen.getWordnum()];
			random = new ArrayList<Integer>();
			try {
			DBConnection MyCon = new DBConnection();
			theConn = MyCon.getConnection();
			String sql = "SELECT * FROM ";
			sql +=tableName+" ORDER BY ID";
			//Try to make your ResultSet scrollable:
			ps = theConn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			resultSet = ps.executeQuery();
			return true;
			}catch (Exception e) {
				System.out.println("e");
				return false;
				
			}
		}
        public static Vocab[] getData(String tableName){
    		String word, meaning;
    		int id;
            if(isConnect(tableName)) {
	             	 try {
						resultSet.last();
						id= Integer.parseInt(resultSet.getString("ID"));
						//random 0-last id-row;
						randomRow(id);         
						for(int i=0;i< OptionScreen.getWordnum();i++){   
	                    	  // random row from vocab table
							resultSet.absolute(random.get(i)); 
							id= Integer.parseInt(resultSet.getString("ID"));
							word = resultSet.getString("words");
							meaning = resultSet.getString("meaning");
							     System.out.println("id: "+id +" "+word+" "+ meaning);
							vocab[i] = new Vocab(id, word, meaning);
		
	                 }
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        
	     			try {
	     				random.clear();
	     				theConn.close();
	     				ps.close();
						resultSet.close();	
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                 return vocab;
                 }
              else {
                	 return null;
                 }
           } 
         
         private static void randomRow(int lastNum){
             int n;
             do{
                  // lastNum is number of words
                   n = (int) (Math.random()*lastNum)+1;
                   if(!random.contains(n)) {
                	   random.add(n);
                   }
             }while(random.size() < OptionScreen.getWordnum());
         }
         
         
         private static void updateBestScore(int score) {
        	DBConnection MyCon = new DBConnection();
 			theConn = MyCon.getConnection();
 			String sql = "UPDATE score SET "+tableName+"=?";
 			
 			//Try to make your ResultSet scrollable:
 			PreparedStatement ps;
			try {
				ps = theConn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ps.setInt(1, score);
				ps.executeUpdate();
				
				ps.close();
				theConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
         }
         public static int getBestScore() {
        	int score = 0;
        	 try {
     			DBConnection MyCon = new DBConnection();
     			theConn = MyCon.getConnection();
     			String sql = "SELECT * FROM score";
     			//Try to make your ResultSet scrollable:
     			ps = theConn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
     			resultSet = ps.executeQuery();
     			resultSet.first();
     			score = resultSet.getInt(tableName); 
     			
     			theConn.close();
     			resultSet.close();
     			ps.close();
     			
     	}catch (Exception e) {
			e.printStackTrace();
		}
       return score;
        	 
       }
         
       public static void isUpdateBestScore(int score) {
    	   if(getBestScore()<score) {
    		   updateBestScore(score);
    	   }
       }
         
//	public static void main(String[] args) {
//       ReadVocabs.getData("bodies");
//       System.out.println(ReadVocabs.getBestScore());
//	}
}

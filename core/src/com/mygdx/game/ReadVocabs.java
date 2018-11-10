package com.mygdx.game;
import java.sql.*;
public class ReadVocabs {
		private Connection theConn = null;
		private ResultSet resultSet;
		private Statement statement;
		PreparedStatement ps;
		private String sql, word, meaning;
		private int id;
        private int[] random;
        private Vocab[] vocab;
		public ReadVocabs() {
			vocab = new Vocab[20];
			random = new int[20];
			try {
			DBConnection MyCon = new DBConnection();
			theConn = MyCon.getConnection();
			sql = "SELECT * FROM animals";
			//Try to make your ResultSet scrollable:
			ps = theConn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			resultSet = ps.executeQuery();
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
		}
        public Vocab[] getData(){
                 randomRow();
                 for(int i=0;i< 20;i++){   
                      try { 
                    	  // random row from vocab table
						resultSet.absolute(random[i]); 
						id= resultSet.getInt(1);
						word = resultSet.getString(2);
						meaning = resultSet.getString(3);
						     System.out.println("id: "+id +" "+word+" "+ meaning);
						vocab[i] = new Vocab(id, word, meaning);
					} catch (SQLException e) {
						e.printStackTrace();
					}
                 }
     			try {
     				ps.close();
					resultSet.close();	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		
                 return vocab;
           } 
         
         public void randomRow(){
             int n, index = 0;
             boolean state = false;
             do{
                  // 20 is number of words
                   n = (int) (Math.random()*20)+1;
                   
                   for(int i=0; i<random.length;i++){
                        if(random[i] == n){
                             state = true;
                             break;
                        }
                   }
                   if(state){
                        state = false;
                   }else{
                        random[index] = n;
                        System.out.println(n);
                        index++;
                   }
             }while(index <= 19);
         }
	public static void main(String[] args) {

       ReadVocabs readVocabs = new ReadVocabs();
       for(Vocab vocab: readVocabs.getData()) {
    	   System.out.println(vocab);
       }
       
	}

}

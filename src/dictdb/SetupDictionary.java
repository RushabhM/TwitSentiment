package dictdb;

import java.io.*;
import java.sql.*;

public class SetupDictionary {
	
	public static void main(String args[]) throws Exception 
	{ 
		String s; 
		int i = 0;
		String temp[], word[], pos[], polar[];
		boolean boolType, boolStem;
		
		Class.forName("org.sqlite.JDBC");
	    Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
	    Statement stat = conn.createStatement();
		
	    stat.executeUpdate("drop table if exists dictionary;");
	    stat.executeUpdate("create table dictionary (id, type, word, pos, stem, polarity);");
		
		FileReader fr = new FileReader("/home/rushabh/workspace/TwitSentiment/dictionary.txt"); 
		BufferedReader br = new BufferedReader(fr);
		
		while((s = br.readLine()) != null)
		{ 
			temp = s.split(" ");
			i++;
			
			if(temp[0].contains("weaksubj"))
				boolType = false;
			else
				boolType = true;
			
			if(temp[4].contains("n"))
				boolStem = false;
			else
				boolStem = true;
			
			word = temp[2].split("=");
			pos = temp[3].split("=");
			polar = temp[5].split("=");
			
		    PreparedStatement prep = conn.prepareStatement("insert into dictionary values (?,?,?,?,?,?);");
		    prep.setInt(1,i);
		    prep.setBoolean(2,boolType);
		    prep.setString(3,word[1]);
		    prep.setString(4,pos[1]);
		    prep.setBoolean(5,boolStem);
		    prep.setString(6,polar[1]);
		    
		    prep.addBatch();
		    conn.setAutoCommit(false);
		    prep.executeBatch();
		    conn.setAutoCommit(true);

		} 
			fr.close(); 
		
			ResultSet rs = stat.executeQuery("select word from dictionary;");
		    while (rs.next()) {
		      System.out.println(rs.getString(1));
		    }
		    rs.close();
		    conn.close();
	} 
}

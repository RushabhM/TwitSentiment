package dictdb;

import java.io.*;
import java.sql.*;

public class SetupHashTagDict {
	
	public static void main(String args[]) throws Exception 
	{ 
		String s; 
		int i = 0;
		String temp[], tag[], polar[], wt[];
		
		Class.forName("org.sqlite.JDBC");
	    Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
	    Statement stat = conn.createStatement();
		
	    stat.executeUpdate("drop table if exists hashtags;");
	    stat.executeUpdate("create table hashtags (id, weight, tag, polarity);");
		
		FileReader fr = new FileReader("/home/rushabh/workspace/TwitSentiment/hashtag.txt"); 
		BufferedReader br = new BufferedReader(fr);
		
		while((s = br.readLine()) != null)
		{ 
			temp = s.split(" ");
			i++;
			
			wt = temp[0].split("=");
			tag = temp[1].split("=");
			polar = temp[2].split("=");
			
		    PreparedStatement prep = conn.prepareStatement("insert into hashtags values (?,?,?,?);");
		    prep.setInt(1,i);
		    prep.setInt(2,Integer.parseInt(wt[1]));
		    prep.setString(3,tag[1]);
		    prep.setString(4,polar[1]);
		    
		    prep.addBatch();
		    conn.setAutoCommit(false);
		    prep.executeBatch();
		    conn.setAutoCommit(true);

		} 
			fr.close(); 
		
			ResultSet rs = stat.executeQuery("select tag from hashtags;");
		    while (rs.next()) {
		      System.out.println(rs.getString(1));
		    }
		    rs.close();
		    conn.close();
	} 
}

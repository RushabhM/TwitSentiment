package dictdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WeighHashtags {

	public static int findWtHashtags(ArrayList<String> tagsUsed) //throws ClassNotFoundException, SQLException
	{
		int hashScore = 0, tmp = 0 ;
		String currentTag;
		
		try
		{
		Class.forName("org.sqlite.JDBC");
	    Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
	    Statement stat = conn.createStatement();
	    ResultSet rs;
	    
	    while(!tagsUsed.isEmpty())
	    {	
	    	currentTag = tagsUsed.remove(0);
	    	rs  = stat.executeQuery("select weight, polarity from hashtags where tag='"+ currentTag +"' ;");
	    	while (rs.next()) 
	    	{
	    		if(rs.getString(2).equals("positive"))
	    			tmp = 1;
	    		else if(rs.getString(2).equals("negative"))
	    			tmp = -1;
	    		
	    		tmp *= Integer.parseInt(rs.getString(1));
	    		
	    		hashScore += tmp;
	    	}
	    	rs.close();
	    }
	    
	    conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    
		return hashScore;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<String> s = new ArrayList<String>();
		s.add("fail");
		s.add("gusta");
		WeighHashtags whtg = new WeighHashtags();
		System.out.println(whtg.findWtHashtags(s));
	}

}

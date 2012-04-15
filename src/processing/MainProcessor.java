package processing;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import dictdb.WeighHashtags;

import model.Sentence;
import xmlparser.SAX_Parser;

public class MainProcessor {

	public static ArrayList<Sentence> sentences;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		sentences = new ArrayList<Sentence>(); 
		try
		{
			FileInputStream fstream = new FileInputStream("/home/rushabh/workspace/TwitSentiment/fetched_tweets2.txt");
		    // Get the object of DataInputStream
		    DataInputStream in = new DataInputStream(fstream);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    String strLine;
		    int sentenceId=0;
		    //Read File Line By Line
		    while ((strLine = br.readLine()) != null)   {
		      // Print the content on the console
		      
		    	System.out.println (strLine);
		    	Sentence stn = new Sentence();
		    	stn.setIndex(sentenceId);
		    	
		    	ArrayList<String> tagsUsed = ProcessingUtility.hashtagProcessing(strLine);
		    	stn.setHashScore(WeighHashtags.findWtHashtags(tagsUsed));
		    	stn.setEmotScore(ProcessingUtility.matchSmileys(strLine));
		    	
		    	sentenceId++;
		    	sentences.add(stn);
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		SAX_Parser sax = new SAX_Parser();
		String xmlFilePath = "/home/rushabh/workspace/TwitSentiment/opmytwt.xml";
		sentences = sax.parseDocument(xmlFilePath, sentences);
		
	}

}

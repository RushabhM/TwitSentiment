package fetch;

import twitter4j.*;
import java.io.*;
import java.util.ArrayList;


import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import twitter4j.conf.*;
public class PrintTweets {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
         // The factory instance is re-useable and thread safe.
    	
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey("lA6RPbAEHf1y5CSQR8N7iw")
          .setOAuthConsumerSecret("PprZJl2CxxDCYqgHgOOiQviQCxhsqOG7zGf0rKHp8Q")
          .setOAuthAccessToken("25548689-KmxWp0XBAkE9OCUZfNlqCsXwet8JjTgRRAseq3gWl")
          .setOAuthAccessTokenSecret("f2inSSbQBCA72pVlqWENb7kJ4BstGzf6A0NWIhKdC8");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        
        
        QueryResult result;
        FileWriter write;
        PrintWriter pw;
        String message;
        ArrayList<String> corpus = new ArrayList<String>();
        
        try
        {
        	write = new FileWriter("/home/rushabh/workspace/TwitSentiment/fetched_tweets2.txt",true);
        	pw = new PrintWriter(write);
        }
        catch(Exception e)
        {	
        	System.out.println("File could not be found"); 
        	return;
        }
       
       
        Query query = new Query("to:VodafoneIN");
        try
        {
            result = twitter.search(query);
         
	        for (Tweet tweet : result.getTweets())
	        {
	            message = tweet.getFromUser() + ": " + tweet.getText();
	            System.out.println(message);
	            pw.println(message);
	            corpus.add(tweet.getText());    
	        }
        
	        try {
				MaxentTagger tagger = new MaxentTagger("home/rushabh/Projects/FYP/Implementation/stanford-postagger-2012-03-09/models/wsj-0-18-left3words.tagger");
				
				String taggedString = tagger.tagString("Here's a tagged string.");
				System.out.println(taggedString);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
        }
        catch(TwitterException te)
        {
            System.out.println("could not search!!");
        }
        
        pw.close();
}

}
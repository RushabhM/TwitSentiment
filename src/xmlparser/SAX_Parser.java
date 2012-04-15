package xmlparser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import model.Sentence;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;

import processing.ProcessingUtility;

public class SAX_Parser extends DefaultHandler {
	
	private String tempVal;
	public int wordID;
	public String pos;
	public static ArrayList<Sentence> sentences;
	public ArrayList<String> posFetch;
	public Sentence sentence = new Sentence();
	public boolean fetch = false;
	public int tempIndex;
	
	public ArrayList<Sentence> parseDocument(String path, ArrayList<Sentence> stn)
	{
		sentences = stn;
		//get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		
		posFetch = new ArrayList<String>();
		posFetch.add("JJ");
		posFetch.add("JJR");
		posFetch.add("JJS");
		posFetch.add("RB");
		posFetch.add("RBR");
		posFetch.add("RBS");
		
		try {
			
			//get a new instance of parser
			SAXParser sp = spf.newSAXParser();
			
			File file = new File(path);
			
			//parse the file and also register this class for call backs
			sp.parse(file , this);
			
			
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
		
		return sentences;
	}

	

	//Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//reset
		
		if(qName.equalsIgnoreCase("sentence")) {
		
			tempIndex = Integer.parseInt(attributes.getValue("id"));
			//sentence = new Sentence();
			//sentence.setIndex(Integer.parseInt(attributes.getValue("id")));
			System.out.println("Sentence ID #" + tempIndex);
		}
		
		else if(qName.equalsIgnoreCase("word")) {
			
			wordID = Integer.parseInt(attributes.getValue("wid"));
			pos = attributes.getValue("pos");
			if(posFetch.contains(pos))
			{
				fetch = true;
				//sentence.position.add(wid);
				System.out.print(wordID  + "-" + pos + "\t");
			}
		}
		
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase("sentence")) 
		{
			//sentences.add(sentence);
			System.out.println();
		}
		else if (qName.equalsIgnoreCase("word")) 
		{
			if(fetch)
			{
				//sentence.words.add(tempVal.toLowerCase());
				String t = ProcessingUtility.removePunctuations(tempVal);
				System.out.println(t);
				fetch = false;
			}
		}
	}
	
	public static void main(String[] args)
	{
		SAX_Parser sax = new SAX_Parser();
		String xmlFilePath = "/home/rushabh/workspace/TwitSentiment/opmytwt.xml";
		sax.parseDocument(xmlFilePath,sentences);
	}
	
}

package processing;

import java.util.ArrayList;

public class ProcessingUtility {

	public static String removePunctuations(String word)
	{
		 String regx = ",.?!-";
		    char[] ca = regx.toCharArray();
		    for (char c : ca) {
		        word = word.replace(""+c, "");
		    }
		return word;
	}
	
	public static ArrayList<String> hashtagProcessing(String line)
	{
		ArrayList<String> tags = new ArrayList<String>();
		String tag = null;
		int hashindex = 0 ;
		
		while(hashindex!=-1)
		{
			int size = line.length() -1;
			hashindex = line.indexOf("#");
			//System.out.println(hashindex);
			if(hashindex != -1)
			{
			int spaceindex = -1;
			line = line.substring(hashindex,size);
			//System.out.println(line);
			spaceindex = line.indexOf(" ");
			tag = line.substring(1,spaceindex);
			//System.out.println(tag);
			tags.add(tag);
			line = line.substring(spaceindex);
			}
		}
		
		return tags;
	}
	
	public static int matchSmileys(String line)
	{
		int emotScore=0;
		//String smiley_regex = "(\A|\s)(((>[:;=+])|[>:;=+])[,*]?[-~+o]?(\)+|\(+|\}+|\{+|\]+|\[+|\|+|\\+|/+|>+|<+|D+|[@#!OoPpXxZS$03])|>?[xX8][-~+o]?(\)+|\(+|\}+|\{+|\]+|\[+|\|+|\\+|/+|>+|<+|D+))(\Z|\s)";
		String[] regex = {":-D",":D",":)",":-)",";-)",";)",":-P",":P",":(",":-(",":-|",":-/",":@","x-("};
		for (int s=0 ; s < regex.length ; s++)
		{
			if(line.contains(regex[s]))
				//System.out.println(s);
			{
				switch(s)
				{
				case 0:
				case 1:
				case 2:
				case 3:
				case 4:
				case 5: emotScore+= 1; System.out.println("positive sentiment"); break;
				case 6:
				case 7: System.out.println("neutral sentiment"); break;
				case 8:
				case 9:
				case 10:
				case 11: emotScore-= 1; System.out.println("negative sentiment"); break;
				case 12:
				case 13: emotScore-= 2; System.out.println("negative sentiment"); break;
				}
			}
		}
		return emotScore;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(ProcessingUtility.hashtagProcessing("I #like the way you #move it :P"));
	}

}

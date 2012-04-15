package model;

import java.util.ArrayList;

public class Sentence {

	/**
	 * @param args
	 */
	
	public ArrayList<String> words;
	public ArrayList<Integer> position;
	public ArrayList<Float> polarity;
	public float sentiIndex;
	int index; 
	int HashScore=0;
	int EmotScore=0;
	
	
	

	public int getHashScore() {
		return HashScore;
	}



	public void setHashScore(int hashScore) {
		HashScore = hashScore;
	}



	public int getEmotScore() {
		return EmotScore;
	}



	public void setEmotScore(int emotScore) {
		EmotScore = emotScore;
	}



	public int getIndex() {
		return index;
	}



	public void setIndex(int index) {
		this.index = index;
	}



	public ArrayList<String> getWords() {
		return words;
	}



	public void setWords(ArrayList<String> words) {
		this.words = words;
	}



	public ArrayList<Integer> getPosition() {
		return position;
	}



	public void setPosition(ArrayList<Integer> position) {
		this.position = position;
	}



	public ArrayList<Float> getPolarity() {
		return polarity;
	}



	public void setPolarity(ArrayList<Float> polarity) {
		this.polarity = polarity;
	}



	public float getSentiIndex() {
		return sentiIndex;
	}



	public void setSentiIndex(float sentiIndex) {
		this.sentiIndex = sentiIndex;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

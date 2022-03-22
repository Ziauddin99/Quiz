package com.games;

public class GameCard {
	
	private String question;
	private String answer;
	public GameCard(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
	}
	String getQuestion()
	{
		return question;
	}
	String getAnswer()
	{
		return answer;
	}
}

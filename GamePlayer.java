package com.games;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.*;

public class GamePlayer {
 JFrame frame;
 JTextArea question;
 JButton answerButton;
 JMenuBar menuBar;
 JPanel panel;
 JMenuItem load;
 boolean show=false;
 JLabel label;
 ArrayList<GameCard> cardlist;
 GameCard currentCard;
 JScrollPane scroller;
 int currentCardIndex;
 int noOfCards=0;
 void create()
 {
	 frame=new JFrame("Game Player");
	 panel=new JPanel();
	 question=new JTextArea();
	 question.setLineWrap(true);
	 label=new JLabel("WELCOME TO GAME QUIZ");
	 label.setVisible(true);
	 panel.add(label);
	 scroller =new JScrollPane(question);
	 scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	 scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);	 
	 scroller.setVisible(false);
	 panel.add(scroller);
	 panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	 answerButton=new JButton("Check Button");
	 answerButton.setVisible(false);
	 panel.add(answerButton);
	 panel.setEnabled(false);
	 answerButton.addActionListener(new AnswerButton());
	 JMenuBar bar=new JMenuBar();
	 JMenu menu=new JMenu("file");
	 load=new JMenuItem("load");
	 load.addActionListener(new loadListener());
	 menu.add(load);
	 bar.add(menu);
	 frame.setJMenuBar(bar);
	 
	 frame.getContentPane().add(panel);
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 frame.setSize(200,200);
	 frame.setVisible(true);
	 
 }
 private class AnswerButton implements ActionListener
 {

	@Override
	public void actionPerformed(ActionEvent e) {
		if(show)
		{
			scroller.setVisible(true);
			label.setText("ANSWER");
			question.setText(currentCard.getAnswer());
			answerButton.setText("Next Card");
			show=false;
		}
		else
		{
			if(currentCardIndex<cardlist.size())
			{
				showNext();
			}
			else
			{
				label.setText("FINISHED");
				question.setText("That was the last card");
				scroller.setEnabled(false);
				scroller.setFocusable(false);
				answerButton.setEnabled(false);
				
			}
		}
	}
	 
 }
 class loadListener implements ActionListener
 {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		JFileChooser chooser=new JFileChooser();
		chooser.showOpenDialog(frame);
		loadFile(chooser.getSelectedFile());
	}
	 
 }
void loadFile(File file)
 {
	//scroller.setEnabled(true);
	
	answerButton.setVisible(true);
	answerButton.setText("Click to get a card");
	 answerButton.setEnabled(true);
	 cardlist=new ArrayList<GameCard>();
	 try {

		 BufferedReader br=new BufferedReader(new FileReader(file));
		 String line=null;
		 while((line=br.readLine())!=null)
		 {
			 makeCard(line);
		 }
		 br.close();
	} catch (Exception e) {
		System.out.println("error in loading file");
		e.printStackTrace();
	}
 }
void makeCard(String str)
{
	
	String[] res=str.split("/");
//	for(String s:res)
//	{
//		System.out.println(s);
//	}
	GameCard card=new GameCard(res[0], res[1]);
	cardlist.add(card);
	noOfCards++;
	System.out.println("made "+ noOfCards+ " cards");
}
 private void showNext()
 {
	 currentCard=cardlist.get(currentCardIndex);
	 currentCardIndex++;
	 question.setText(currentCard.getQuestion());
	 label.setText("QUESTION");
	 scroller.setVisible(true);
	 answerButton.setText("get answer");
	 show=true;
 }
}

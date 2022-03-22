package com.games;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class GameCardBuilder {
JFrame frame;
JPanel mainPanel;
JTextArea question;
JTextArea answer;
JButton nextButton;
JMenuBar menuBar;
JMenuItem newItem;
JMenuItem saveItem;
JMenuItem nextItem;
private ArrayList<GameCard> cardList;
void outline()
{
	cardList =new ArrayList<GameCard>();
	frame=new JFrame("GameCard Frame");
	mainPanel=new JPanel();
	
	Font bigFont=new Font("Times New Roman", Font.BOLD, 32);
	JLabel questionLabel=new JLabel("QUESTION:");
	questionLabel.setFont(bigFont);
	
	question=new JTextArea();
	question.setLineWrap(true);
	
	question.setFont(bigFont);
	question.requestFocus();
	
	JScrollPane qscroller=new JScrollPane(question);
	qscroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	qscroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	
	JLabel answerLabel=new JLabel("ANSWER:");
	answerLabel.setFont(bigFont);
	
	answer=new JTextArea();
	answer.setLineWrap(true);
	answer.setFont(bigFont);
	
	JScrollPane ascroller=new JScrollPane(answer);
	ascroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	ascroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	
	nextButton=new JButton("NEXT");
	nextButton.addActionListener(new ButtonListener());
	
	menuBar=new JMenuBar();
	JMenu fileMenu=new JMenu("File");
	newItem=new JMenuItem("new");
	saveItem=new JMenuItem("save");
	nextItem=new JMenuItem("next");
	newItem.addActionListener(new NewListener());
	saveItem.addActionListener(new SaveListener());
	nextItem.addActionListener(new NextListener());
	fileMenu.add(newItem);
	fileMenu.add(nextItem);
	fileMenu.add(saveItem);
	menuBar.add(fileMenu);
	
	mainPanel.add(questionLabel);
	mainPanel.add(qscroller);
	mainPanel.add(answerLabel);
	mainPanel.add(ascroller);
	mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS) );
	mainPanel.add(nextButton);
	
	frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
	
	frame.setJMenuBar(menuBar);
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(500,500);
	frame.setVisible(true);
	
}
private class ButtonListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) {
		nextButton.setBackground(Color.red);
	}
	
}
private class NextListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) {

		GameCard card=new GameCard(question.getText(), answer.getText());
		cardList.add(card);
		clearCard();
	}
	
}
private class NewListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) {

		cardList.clear();
		clearCard();
	}
	
}
private class SaveListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) {
GameCard card=new GameCard(question.getText(), answer.getText());
cardList.add(card);
JFileChooser fileSave=new JFileChooser();
fileSave.showSaveDialog(frame);
saveFile(fileSave.getSelectedFile());

}
	
}
void saveFile(File file)
{
	try {
		BufferedWriter writer=new BufferedWriter(new FileWriter(file));
		for(GameCard g:cardList)
		{
			writer.write(g.getQuestion()+"/");
			writer.write(g.getAnswer()+"\r\n");
		}
		writer.close();
		
	} catch (Exception e) {
		System.out.println("cannot write into file");
		e.printStackTrace();
	}
}
void clearCard()
{
	question.setText("");
	answer.setText("");
	question.requestFocus();
}
}

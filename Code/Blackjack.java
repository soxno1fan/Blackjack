import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Blackjack extends JFrame
{
	public static final int WIDTH = 545;
	public static final int HEIGHT = 425;
	private JPanel topPanel, midPanel, lowMid, bottomPanel;
	private JTextArea topOutput, lowerOutput;
	private JButton bottomButton, hitButton, standButton;
	private Player[] players;
	private Dealer dealer;
	private Deck deck;
	private int playerNumber;
	
    //listener for the main button at bottom of screen
	private class BottomButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getActionCommand().equals("Begin!"));
			{ //Creates and displays five buttons for player selection
				topOutput.setText("How many players this round?");
				bottomButton.setText("New Game");
				
				JButton oneButton = new JButton("1");
				oneButton.addActionListener(new PlayerNumberListener());
				midPanel.add(oneButton);
				oneButton.setBackground(Color.RED);
				
				JButton twoButton = new JButton("2");
				twoButton.addActionListener(new PlayerNumberListener());
				midPanel.add(twoButton);
				
				JButton threeButton = new JButton("3");
				threeButton.addActionListener(new PlayerNumberListener());
				midPanel.add(threeButton);
				
				JButton fourButton = new JButton("4");
				fourButton.addActionListener(new PlayerNumberListener());
				midPanel.add(fourButton);
				
				JButton fiveButton = new JButton("5");
				fiveButton.addActionListener(new PlayerNumberListener());
				midPanel.add(fiveButton);
			}
			if (e.getActionCommand().equals("New Game"))
			{ //Resets the program in effect
				topOutput.setText("How many players this round?");
				for (int i = 9; i > 4; i--)
					midPanel.remove(i);
				midPanel.setVisible(true);
				lowMid.setVisible(false);
			}
		}
	}
	
    //listener for the player selection buttons, gets the game started
	private class PlayerNumberListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			midPanel.setVisible(false);
			lowMid.setVisible(true);
			int players = Integer.parseInt(e.getActionCommand().trim());
			newRound(players);
		}
	}
	
    //listener to control the main user action of the game: hitting/standing
	private class HitStandListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (playerNumber < players.length)
			{}
			if (e.getActionCommand().equals("Hit"))
			{
				Card draw = deck.drawCard();
				players[playerNumber].addCard(draw);
				
				lowerOutput.setText("You were hit the "+draw);
                if (players[playerNumber].isBusted())
                {
                	lowerOutput.append(", you busted!");
                }
                
                if (players[playerNumber].isBusted() && playerNumber < players.length)
                {
                	playerNumber++;
                	lowerOutput.append("  Player "+(playerNumber+1)+":");
                }
                if (playerNumber >= players.length)
                	dealerTurn();
			}
			else if (e.getActionCommand().equals("Stand"))
			{
				playerNumber++;
				if (playerNumber < players.length)
				{					
					lowerOutput.setText("Player "+(playerNumber+1)+":");
				}
				if (playerNumber >= players.length)
                	dealerTurn();
			}
		}
	}
	
	public Blackjack() //describes the GUI JFrame and basic components
	{
		super("Blackjack v. II");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(140, 181, 223));
		setLayout(new GridLayout(4, 1));
		
		topPanel = new JPanel();
		topOutput = new JTextArea("Welcome to Mordechai's BlackJack game! Please click \"begin\"", 5, 33);
		topOutput.setEditable(false);
		topPanel.add(topOutput);
		add(topPanel);
		
		midPanel = new JPanel();
		add(midPanel);
		
		lowMid = new JPanel();
		add(lowMid);
		lowerOutput = new JTextArea(2, 40);
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2, 1));
		bottomButton = new JButton("Begin!");
		bottomButton.addActionListener(new BottomButtonListener());
		bottomButton.setBackground(Color.RED);
		JLabel blank = new JLabel("");
		bottomPanel.add(blank);
		bottomPanel.add(bottomButton);
		add(bottomPanel);
	}
	
	public static void main(String[] args)
	{
		Blackjack firstTry = new Blackjack();
		firstTry.setVisible(true);
	}
	
    //sets up everything for the game but stops at the first players first
    //option to hit or stand, then passes off to the HitStand action listener
	public void newRound(int a)
	{		
		playerNumber = 0;
		
		dealer = new Dealer();
		deck = new Deck();
		players = new Player[a];
		
		dealer.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
        dealer.flipFirstCard();
        String s = ""+dealer;        
        
        for (int i = 0; i < players.length; i++)
        {    
            players[i] = new Player();
            players[i].addCard(deck.drawCard());
            players[i].addCard(deck.drawCard());
            s += "\nPlayer "+(i+1)+players[i];
        }
        topOutput.setText(s);

        
        lowMid.add(lowerOutput);
        hitButton = new JButton("Hit");
        hitButton.addActionListener(new HitStandListener());
        standButton = new JButton("Stand");
        standButton.addActionListener(new HitStandListener());
        lowMid.add(hitButton);
        lowMid.add(standButton);
        
        lowerOutput.setText("Player "+(playerNumber+1)+":");        
	}
	
    //takes the blackjack card game turn of the dealer, which does not rely
    //upon user input/decisions
	public void dealerTurn()
	{
		hitButton.setVisible(false);
		standButton.setVisible(false);
		lowMid.remove(hitButton);
		lowMid.remove(standButton);
		
		while (dealer.willHit())
            dealer.addCard(deck.drawCard());
		dealer.flipFirstCard();
		
		lowerOutput.setText("Dealer's turn...Game over!  Winner(s): "+winners());
		
		topOutput.setText(printHands());
		
	}
	
    //creates the output declaring the game winner(s)
	public String winners()
	{
		String s = "";
		for (int i = 0; i < players.length; i++)
		{
			if (dealer.isBusted())
			{
				if (!players[i].isBusted())
				{
					s += " Player "+(i+1);
				}
			}
			else if (players[i].getHandValue() > dealer.getHandValue() && !players[i].isBusted())
			{
				s += " Player "+(i+1);
			}
		}
		if (s.equals(""))
			s += "You all lose!";
		return s;
	}
	
	public String printHands()
	{
		String s = ""+dealer+", "+dealer.getHandValue();
		for (int i = 0; i < players.length; i++)
			s += "\nPlayer "+(i+1)+players[i]+", "+players[i].getHandValue();
        
		return s;
	}
}
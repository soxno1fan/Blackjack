import java.util.Scanner;
/**
 * Creates Player type for game of Blackjack.
 * 
 * @author Mordechai Sadowsky
 * @version Honors Project Due 12/12/12
 */
public class Player
{
    private Hand playerHand;
    
    public Player()
    {
        playerHand = new Hand();
    }
    
    public boolean willHit()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Hit or stand? ");
        char response = keyboard.nextLine().toLowerCase().charAt(0);
        return response == 'h';
    }
    
    public boolean isBusted()
    {
        return playerHand.isBusted();
    }
    
    public void addCard (Card a)
    {
        playerHand.addCard(a);
    }

    public String toString()
    {
        return " hand: "+playerHand;
    }
    
    public int getHandValue()
    {
        return playerHand.getHandValue();
    }
}

/**
 * Creates the data type of a hand of playing cards.
 * 
 * @author Mordechai Sadowsky
 * @version Honors Project Due 12/12/12
 */
public class Hand
{
    private Card[] hand = new Card[15];
    private int slots;
    
    public Hand(Card first, Card second)
    {
       hand[0] = first;
       hand[1] = second;
       slots=2;
    }
    
    public Hand()
    {
    }
    
    public int getHandValue()
    {
       int total=0, ace=0;
       for(int i = 0; i < hand.length; i++)
           if (hand[i] != null)
               total += hand[i].getValue();
       for (int i = 0; i < hand.length; i++)
           if (hand[i] != null)
               if (hand[i].getRank() == 0 && total <= 11)
                   ace = 10;
       return total+ace;
    }
    
    public String toString()
    {
        String display = "";
        for (int i = 0; i < slots; i++)
        {
        	if (i < slots-1)
            {
        		if (hand[i].getDirection())
        			display += hand[i]+", ";
	            else 
	                display += "XXXXXX ";
            }
        	else
        		display += hand[i]+"";
        }
        return display;
    }
    
    public boolean isBusted()
    {
       return getHandValue() > 21;
    }
   
    public void addCard (Card newCard)
    {
        hand[slots] = newCard;
        slots++;
    }
    
    public void flipFirstCard() //used to hide or show the first card in the hand
    {
        hand[0].flipCard();
    }
    
    public void clearHand()
    {
        for (int i = 0; i < hand.length; i++)
            hand[i] = null;
        slots = 0;
    }
}
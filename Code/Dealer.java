
/**
 * Describes Dealer type for game of Blackjack.
 * 
 * @author Mordechai Sadowsky
 * @version Honors Project Due 12/12/12
 */
public class Dealer
{
    private Hand dealerHand;
    
    public Dealer()
    {
        dealerHand = new Hand();
    }
    
    public boolean willHit() //Blackjack convention that dealer hits with under 17
    {
        return dealerHand.getHandValue() < 17;
    }
    
    public boolean isBusted()
    {
        return dealerHand.isBusted();
    }
    
    public String toString()
    {
        return "Dealer's hand: "+dealerHand;
    }
    
    public void addCard (Card a)
    {
        dealerHand.addCard(a);
    }
    
    public int getHandValue()
    {
        return dealerHand.getHandValue();
    }
    
    public void flipFirstCard()//used to hide and show Dealer's first card
    {
        dealerHand.flipFirstCard();
    }
    
    public void clearHand()
    {
        dealerHand.clearHand();
    }
}
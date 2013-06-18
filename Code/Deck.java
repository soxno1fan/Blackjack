import java.util.Random;
/**
 * Creates the data type of a deck of cards.
 * 
 * @author Mordechai Sadowsky
 * @version Honors Project COSC 211, Fall 2012
 */
public class Deck
{
    private Card[] cards;
    private int cardsRemaining;
    
    public Deck()//fills deck with standard 52 cards
    {
        cards = new Card[52];
        for(int rank = 1, index = 0; rank <= 13; rank++)
            for(int suit = 0; suit < 4; suit++, index++)
                cards[index] = new Card(rank,suit);
        cardsRemaining = 52;
    }
    
    public Card returnCard(int index)
    {
        return cards[index];
    }
    
    public Card drawCard()//removes a random card from the deck and fills gap
    {
        Random generator = new Random();
        int index = 0;
        index = generator.nextInt(cardsRemaining);
        Card a = cards[index];
        cards[index] = cards[cardsRemaining-1];
        cards[cardsRemaining-1] = null;
        cardsRemaining--;
        return a;
    }
    
    public int getCardsRemaining()
    {
        return cardsRemaining;
    }
}
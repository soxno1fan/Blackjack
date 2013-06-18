
/**
 * This class creates the data type representing a single playing card.
 * 
 * @author Mordechai Sadowsky
 * @version Honors Project due 12/12/12
 */
public class Card implements SuitsAndRanks
{
    private int rank, suit;
    private boolean directionFacing; //false = down, true = face up
    
    public Card()
    {
        
    }
                              
    public Card(int rank, int suit)
    {
        this.rank = rank-1;
        this.suit = suit;
        directionFacing = true;
    }
    
    public boolean getDirection()
    {
        return directionFacing;
    }
    
    public int getRank()
    {
        return rank;
    }
    
    public int getValue()
    {
        switch (rank)
        {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return rank+1;
            case 9:
            case 10:
            case 11:
            case 12:
                return 10;
            default:
                return 0;
        }
    }
    
    public int getSuitNumber()
    {
        return suit;
    }
    
    public String getSuitString()
    {
        return SUITS[suit];
    }
    
    public void flipCard()
    {
        directionFacing = !directionFacing;
    }
    
    public String toString()
    {
        return RANKS[rank] + " of " + SUITS[suit];
    }
}
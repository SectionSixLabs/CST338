/**
 * Deck Of Cards
 * CST338
 * Module3
 * @author Sergiy Zarubin, Lacey Sikes, Rocky Moreno, Danny Tran
 *
 */


public class Assignment3
{

   public static void main(String[] args)
   {
      // Test of Card Class
      Card card1 = new Card();
      System.out.println(card1);
      
      Card card2 = new Card('2', Card.Suit.HEARTS);
      System.out.println(card2);
      
      Card card3 = new Card('X', Card.Suit.CLUBS);
      System.out.println(card3);
      
   }

}

/**
 * Phase 1: The Card Class
 * 
 *
 */

class Card
{
  
	//A Public enum Type 
   public enum Suit {HEARTS, CLUBS, SPADES, DIAMONDS};
   
   //Private Member Data
   private char value;
   private Suit suit;
   private boolean errorFlag;
   
   
   /*The constructor should call the proper mutator(s).  
    * Overload this to cope with a client that wants to instantiate 
    * without parameters and use 'A' and 'spades' as the default value 
    * and suit when not supplied.  Provide at least two constructors -- 
    * no parameters and all parameters -- or more if you wish.  
    * Because we have the errorFlag member, the constuctor (via the mutator),
    *  can set that member when it gets bad data; 
    *  it does not have to assign default values upon receipt of bad data.  
    *  This is a new technique for us.  Again, 
    *  default card (no parameters passed) is the ('A', spades).*/
   public Card()
   {
      setValue('A');
      setSuit(Suit.SPADES);
      errorFlag = false;
   }
   
   
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }
   
   
   /*a stringizer that the client can use prior to displaying the card.  
    * It provides a clean representation of the card.  If errorFlag == true, it should return correspondingly reasonable reflection of this fact (something like "[ invalid ]" rather than a suit and value).(non-Javadoc)
    * @see java.lang.Object#toString()
    */
   public String toString()
   {
      if(isValid(value, suit))
      {
         return(Character.toString(value)+" of "+suit.toString());
      }
      else
      {
         return("** illegal **");
      }
   }
   
   /*a mutator that accepts the legal values established in the earlier section. 
    *  When bad values are passed, errorFlag is set to true and other values can be 
    *  left in any state (even partially set). If good values are passed, they are 
    *  stored and errorFlag is set to false.  
    *  Make use of the private helper, listed below.*/
   public boolean set(char value, Suit suit)
   {
      if(isValid(value, suit))
      {
         errorFlag = false;
         this.value = value;
         this.suit = suit;
         return true;
      }
      else 
      {
         errorFlag = true;
         return false;
      }
   }
   
   public void setValue(char value)
   {
      this.value = value;
   }
   
   public char getValue()
   {
      return this.value;
   }
   
   public void setSuit(Suit suit)
   {
      this.suit = suit;
   }
   
   public Suit getSuit()
   {
      return suit;
   }
   
   public boolean getErrorFlag()
   {
      return errorFlag;
   }
   
   public boolean equals(Card card)
   {
      return(suit.equals(card.suit) && value == card.value);
   }
   
   private boolean isValid(char valueLocal, Suit suitLocal)
   {
     
      //Using Strings for Value
      String values = "A23456789TJQK";
      String stringValue = Character.toString(valueLocal);
      
      //See if value is in the String if not return false
      // No need to look farther
      if (! values.contains(stringValue))
      {
    	  return false;
      }
      //check suit
      switch (suitLocal) 
      {
      // case statements
      // values must be of same type of expression
      case HEARTS:
      case SPADES:
      case CLUBS:
      case DIAMONDS:  return true;

      default: return false;
      }
      /* we could use switch statement instead as we already use enumerators 
      if(suit == Suit.HEARTS)
      {
         validSuit = true;
      }
      else if(suit == Suit.SPADES)
      {
         validSuit = true;
      }
      else if(suit == Suit.CLUBS)
      {
         validSuit = true;
      }
      else if(suit == Suit.DIAMONDS)
      {
         validSuit = true;
      }
      else 
      {
         validSuit = false;
      }
      */
     // System.out.println(validValue);
     // System.out.println(validSuit);
     // System.out.println(validValue && validSuit);
     // return(validValue && validSuit);
   }
   
}

/**
 * Phase 2
 */

//TODO Code for Phase 2
class Hand {
	public int MAX_CARDS  = 50; 
	
	private  Card[] myCards;
	private  int numCards;

	// a default constructor.
	public Hand()
	   {

	   }
	
	//remove all cards from the hand (in the simplest way).
	public void resetHand() {

	}
	
	/*adds a card to the next available position in the myCards array.  
	 * This is an object copy, not a reference copy, since the source 
	 * of the Card might destroy or change its data after our Hand gets it
	 *  -- we want our local data to be exactly as it was when we received it.*/
	public boolean takeCard(Card card) 
	{
		return false;
	}
	//returns and removes the card in the top occupied position of the array.
	public Card playCard() {
		return null; 
	}
	
	//a stringizer that the client can use prior to displaying the entire hand.
	public String toString() 
	{
		return null; 
	}
	
	//Accessor for numCards.
	public int numCards() 
	{
		return 0;
	}
	
	//Accessor for an individual card.  Returns a card with errorFlag = true if k is bad.
	public Card inspectCard(int k) 
	{
		return null; 
	}
}

/**
 * Phase 3
 */

//TODO Code for Phase 3


/**
 * Phase 4
 */

//TODO Code for Phase 4
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
  public enum Suit {HEARTS, CLUBS, SPADES, DIAMONDS};
   
   private char value;
   private Suit suit;
   private boolean errorFlag;
   
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
   
   
   public String toString()
   {
	   boolean isValidCard = isValid(value, suit);
      if(isValidCard)
      {
         return(Character.toString(value)+" of "+suit.toString());
      }
      else
      {
         return("** illegal **");
      }
   }
   
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


/**
 * Phase 3
 */

//TODO Code for Phase 3


/**
 * Phase 4
 */

//TODO Code for Phase 4
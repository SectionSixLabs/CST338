/**
 * Deck Of Cards
 * CST338
 * Module3
 * @author Sergiy Zarubin, Lacey Sikes, Rockey Moreno, Danny Tran
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
   boolean errorFlag;
   
   public Card()
   {
      setValue('J');
      setSuit(Suit.SPADES);
      errorFlag = false;
   }
   
   public Card(char value, Card.Suit suit)
   {
      set(value, suit);
   }
   
   
   public String toString()
   {
      if(isValid(value, suit))
      {
         return(value+" of "+suit);
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
      return value;
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
   
   private boolean isValid(char value, Suit suit)
   {
      boolean validValue;
      boolean validSuit;
      
      //Using Strings for Value
      String values = "A23456789TJQK";
      String stringValue = Character.toString(value);
      
      //See if value is in the String 
      validValue = values.contains(stringValue);
        
      //check suit
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
      
      return(validValue && validSuit);
   }
   
}

/**
 * Phase 2
 */

//Code for Phase 2


/**
 * Phase 3
 */

//Code for Phase 3


/**
 * Phase 4
 */

//Code for Phase 4
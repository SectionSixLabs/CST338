/**
 * GUI Cards
 * CST338
 * Module5
 * @author Sergiy Zarubin, Lacey Sikes, Rocky Moreno, Danny Tran
 *
 */

//TODO Please submit one file for each phase (3 .txt files, UML .pdf file)

//TODO Checklist
//Phase 1 Done. (Comment out tests when submitting.)
//TODO Phase 2
//TODO Phase 3
//TODO Phase 4

//Phase1
import javax.swing.*;

import java.awt.*;

import java.io.File;
import java.util.Random;

public class Assignment5
{


   //Phase1
   static final int NUM_CARD_IMAGES = 57; // 52 + 4 jokers + 1 back-of-card image
   static Icon[] icon = new ImageIcon[NUM_CARD_IMAGES];

   static void loadCardIcons()
   {
      // build the file names ("AC.gif", "2C.gif", "3C.gif", "TC.gif", etc.)
      // in a SHORT loop.  For each file name, read it in and use it to
      // instantiate each of the 57 Icons in the icon[] array.

      //Get your current working directory (generic)
      String myDir = System.getProperty("user.dir");
      File path = new File(myDir+"/Assignment5/images");
      File[] files = path.listFiles();
      
      if(files.length == NUM_CARD_IMAGES)
      {
         System.out.println("There are 57 cards.");
         
         for (int i = 0; i < files.length; i++)
         {
            if (files[i].isFile())
            { 
               System.out.println(files[i]); //TODO Comment this out when finalizing
               icon[i] = new ImageIcon(files[i].toString());
            }
         }
      }
      else
      {
         System.out.println("Problem! There is not 57 card files in folder.");
      }
   }

   // turns 0 - 13 into "A", "2", "3", ... "Q", "K", "X"
   static String turnIntIntoCardValue(int k)
   {
      //FIXME Look at the Cards class, what cards do we have there? 
      // an idea for a helper method (do it differently if you wish) 
      String cardNum = "A23456789TJQKX"; //Note there is Joker I guess as X
      String myCardValue = cardNum.substring(k, k+1);
      
      return myCardValue;
   }

   // turns 0 - 3 into "C", "D", "H", "S"
   static String turnIntIntoCardSuit(int j)
   {
      //XXX You can use Enum for this
      // an idea for another helper method (do it differently if you wish)
      String cardSuit = "CDHS"; // Clubs Diamonds Hearts Spades 
      String myCardSuit = cardSuit.substring(j, j+1);
      
      return myCardSuit;
   }

   // a simple main to throw all the JLabels out there for the world to see
   public static void main(String[] args)
   {
      int k;

      // prepare the image icon array
      loadCardIcons();

      // establish main frame in which program will run
      JFrame frmMyWindow = new JFrame("Card Room");
      frmMyWindow.setSize(1150, 650);
      frmMyWindow.setLocationRelativeTo(null);
      frmMyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // set up layout which will control placement of buttons, etc.
      FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 5, 20);   
      frmMyWindow.setLayout(layout);

      // prepare the image label array
      JLabel[] labels = new JLabel[NUM_CARD_IMAGES];
      for (k = 0; k < NUM_CARD_IMAGES; k++)
         labels[k] = new JLabel(icon[k]);

      // place your 3 controls into frame
      for (k = 0; k < NUM_CARD_IMAGES; k++)
         frmMyWindow.add(labels[k]);

      // show everything to the user
      frmMyWindow.setVisible(true);

      //Testing methods Area
      
      //A23456789TJQKX
      //Testing method: turnIntIntoCardValue
      System.out.println("Testing method: turnIntIntoCardValue");
      for(int i=0; i<14; i++)
      {
         System.out.println("Int "+i+" is " +turnIntIntoCardValue(i));
         if (i<Card.cardValues.length()) {
         System.out.println("Using Card Class: "+i+" is " +Card.cardValues.toCharArray()[i]);
         }
      }
      
      //Testing method: turnIntIntoCardSuit
      System.out.println("Testing method: turnIntoIntoCardSuit");
      for(int i=0; i<4; i++)
      {
         System.out.println("Int "+i+" is " +turnIntIntoCardSuit(i));
         System.out.println("Using enum:"+Card.Suit.values()[i].name());
      }

      
      //End of main
      System.out.println("End of main.");
   }

}


class Card
{

   //A Public enum Type 
   public enum Suit {HEARTS, CLUBS, SPADES, DIAMONDS};
   //One addition to be used externally in Deck class
   public final static String cardValues = "A23456789TJQK";

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
      this.value = 'A';
      this.suit= Suit.SPADES;
      errorFlag = false;
   }


   public Card(char value, Suit suit)
   {
      set(value, suit);
   }


   /*a stringizer that the client can use prior to displaying the card.  
    * It provides a clean representation of the card.  If errorFlag == true, it
    *  should return correspondingly reasonable reflection of this fact 
    *  (something like "[ invalid ]" rather than a suit and value).(non-Javadoc)
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
    *  When bad values are passed, errorFlag is set to true and other values can
    *  be left in any state (even partially set). If good values are passed, 
    *  they are stored and errorFlag is set to false.  
    *  Make use of the private helper, listed below.*/
   public boolean set(char value, Suit suit)
   {
      this.value = value;
      this.suit = suit;
      if(isValid(value, suit))
      {
         errorFlag = false;
         return true;
      }
      else 
      {
         this.errorFlag = true;
         return false;
      }
   }

   /*public void setValue(char value)
   {
      this.value = value;
   }*/

   //Accessors for  value
   public char getValue()
   {
      return this.value;
   }

  /* public void setSuit(Suit suit)
   {
      this.suit = suit;
   }*/

   //Accessors for suit 
   public Suit getSuit()
   {
      return suit;
   }

   //Accessor for errorFlag.
   public boolean getErrorFlag()
   {
      return errorFlag;
   }

   //returns true if all the fields (members) are identical and false,otherwise.
   public boolean equals(Card card)
   {
      return(suit.equals(card.suit) && value == card.value);
   }

   private boolean isValid(char valueLocal, Suit suitLocal)
   {

      //Using Strings for Value
      //String values = "A23456789TJQK";
      String stringValue = Character.toString(valueLocal);

      //See if value is in the String if not return false
      // No need to look farther
      if (! cardValues.contains(stringValue))
      {
         return false;
      }
      //check suit
      switch (suitLocal) 
      {
      // case statements
      case HEARTS:
      case SPADES:
      case CLUBS:
      case DIAMONDS:  return true;

      default: return false;
      }
   }

}

/**
 * Phase 2
 */
//TODO Code for Phase 2
class Hand 
{
   //public int value like MAX_CARDS and set it to something like 50 or 100 so
   //a runaway program can't try to create a monster array
   public static final int MAX_CARDS  = 100; 

   private  Card[] myCards; 
   private  int numCards;

   // a default constructor.
   public Hand()
   {
      myCards =new Card [MAX_CARDS];
      numCards= 0;
   }

   //remove all cards from the hand (in the simplest way).
   public void resetHand() 
   {
      this.myCards =new Card [MAX_CARDS];
      this.numCards = 0;
   }

   /*adds a card to the next available position in the myCards array.  
    * This is an object copy, not a reference copy, since the source 
    * of the Card might destroy or change its data after our Hand gets it
    *  -- we want our local data to be exactly as it was when we received it.*/
   public boolean takeCard(Card myCard) 
   {
      if (this.numCards <= MAX_CARDS) 
      {
      this.myCards[this.numCards] = new Card(myCard.getValue(), myCard.getSuit()); 
      numCards +=1;
      return true; 
      } else return false;
   }
   
   //returns and removes the card in the top occupied position of the array.
   public Card playCard() 
   {
      if (this.numCards>0) 
      {
         Card cardLocal = this.myCards[this.numCards-1]; 
         this.myCards[this.numCards-1] = null; 
         this.numCards-=1;
         return cardLocal; 
      } else return null; 
   } //If hand is empty return NULL

   //a stringizer that the client can use prior to displaying the entire hand.
   public String toString() 
   {
      String strLocal = ""; 
      for (int i = 0; i<this.numCards;i++) 
      {
         strLocal+= this.myCards[i].toString()+", ";
      }
      if (strLocal.length()>2) return strLocal.substring(0,strLocal.length()-2);
      else return strLocal; 
   }

   //Accessor for numCards.
   public int numCards() 
   {
      return this.numCards;
   }

   //Accessor for an individual card.  
   //Returns a card with errorFlag = true if k is bad.
   public Card inspectCard(int k) 
   {
      Card localCard = new Card('0',Card.Suit.HEARTS);
      if (k<=this.numCards && k>0) localCard=this.myCards[k-1];
      return localCard; 
   }
}

/**
 * Phase 3
 */

//TODO Code for Phase 3

class Deck 
{
   //initialize it to allow a maximum of six packs

   private static int MAX_PACKS = 6;
   private static int PACK_SIZE = 52;
   //It is more efficient to use number of packs and pack size separately
   //So we are not doing calculations inside of the deck class
   
   //We might use it outside of the class
   public static final int MAX_CARDS = MAX_PACKS*PACK_SIZE;     
   /*This is a private static Card array, masterPack[], 
    * containing exactly 52 card references, which point to all 
    * the standard cards.   It will enable us to avoid capriciously and 
    * repeatedly declaring the same 52 cards which are needed 
    * as the game proceeds.  In other words, once we have, say, 
    * a ('6', spades) Card constructed and stored (inside this masterPack[]),
    * we use that same instance whenever we need it as a source to copy in 
    * various places, notably during a re-initialization of the Deck object;
    * it will always be in the masterPack[] array for us to copy.*/
   private static Card[] masterPack  = new Card[PACK_SIZE];
   
   private Card[] cards; 
   private int topCard;
   private int numPacks=1;//default value for number of packs
   
   /*a constructor that populates the arrays and assigns initial 
    * values to members.  Overload so that if no parameters are passed, 
    * 1 pack is assumed.*/
   public Deck() 
   {
      allocateMasterPack();
      init(this.numPacks);
   }
   
   //Overload Deck() with one parameter 
   public Deck(int numPacks) 
   {
      allocateMasterPack();
      if(numPacks>MAX_PACKS) init(MAX_PACKS); else init(numPacks);
   }
   
   /*re-populate cards[] with the standard 52 × numPacks cards.  
    * We should not repopulate the static array,masterPack[], since that was 
    * done once, in the (first-invoked) constructor and  never changes. */
   public void init(int numPacks) 
   {
      cards = new Card[PACK_SIZE*numPacks]; 
      for (int i=0; i<numPacks;i++) 
      {
        System.arraycopy(masterPack, 0, cards, i*masterPack.length, 
              masterPack.length);
      }
      topCard = cards.length-1;
   }
   
/*mixes up the cards with the help of the standard random number generator.*/
   public void shuffle() 
   {
      int localDeckSize =this.cards.length; 
      Card[] localDeck = new Card[localDeckSize]; 
      Random rgen = new Random();
      //While there cards in the deck deal card and assign it to the rendom 
      //place in the new array
      while (this.topCard>=0) 
      {
         int randomIndex = -1;
         //Make sure that space is empty before assignment
         do {
            randomIndex=rgen.nextInt(localDeckSize);
            //System.out.println(""+randomIndex);
         } while (localDeck[randomIndex]!=null); 
         localDeck[randomIndex] = dealCard();
      }
      
      System.arraycopy(localDeck, 0, this.cards,0,localDeckSize);
      this.topCard = this.cards.length-1;
   }
   
   /*returns and removes the card in the top occupied position of cards[].*/
   public Card dealCard() 
   {
      Card localCard = cards[topCard()];
      this.topCard--; 
      return localCard; 
   }
   
   /*An accessor for the int, topCard (no mutator.)*/
   public int topCard() 
   {
      return this.topCard; 
   }
   
   /*Accessor for an individual card.  Returns a card with errorFlag = true 
    *if k is bad.
    *We are not useing it anywhere though*/
   public Card inspectCard(int k) 
   {
      Card localCard = new Card('0',Card.Suit.CLUBS); 
      if (k<cards.length) localCard = cards[k]; 
      return localCard;
   }
   
   /* this is a private method that will be called by the constructor.  
    * However, it has to be done with a very simple twist:  
    * even if many Deck objects are constructed in a given program, 
    * this static method will not allow itself to be executed more than once. 
    * ince masterPack[] is a static, unchanging, entity, it need not be built
    * every time a new Deck is instantiated.  So this method needs to be able
    * to ask itself, "Have I been here before?", and if the answer is "yes", 
    * it will immediately return without doing anything;  it has already built 
    * masterPack[] in a previous invocation.*/
   private static void allocateMasterPack() 
   {
      if (masterPack[0]!=null) return; 
      int i = 0;
      for(Card.Suit suit : Card.Suit.values()) 
      {
         for (char value : Card.cardValues.toCharArray()) 
         {
            Card localCard = new Card(value,suit);
            masterPack[i]=localCard; 
            i++; 
         }
      }
      
   }
   
} 


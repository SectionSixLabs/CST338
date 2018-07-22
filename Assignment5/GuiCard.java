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

import javax.swing.*;


//import Card.Suit;
//Confused on whats in default package? Is Cards class in it since we don't need to import Card

import java.util.Random;

public class GuiCard
{
   //TODO Implement Module 3 Class with modification for this Class
   
   private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K + joker
   private static Icon iconBack;
   static boolean iconsLoaded = false;
   
   static void loadCardIcons()
   {
      //Hint:  Call this method any time you might need an Icon, but make 
      //sure that it loads the entire array the first time it is called, 
      //and does nothing any later time.
      
      if(!iconsLoaded) //if not iconsLoaded
      {
         //Then load icons
         String myDir = System.getProperty("user.dir");
         String path = myDir+"/Assignment5/images/";
         
         String cardValues[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9",
                                 "T", "J", "Q", "K", "X"};
         //Lets keep it consistent with Card Class
         String cardSuites[] = {"H", "C", "S", "D"};
         
         for(int suit=0; suit<4; suit++)
         {
            for(int value=0; value<14; value++)
            {
               String file = path+cardValues[value]+cardSuites[suit]+".gif";
//               System.out.println(file); //test filepath
               iconCards[value][suit] = new ImageIcon(file);
            }
         }
         //Loading the face down card separately
         String backOfCard = path+"BK.gif";
         System.out.println(backOfCard);
        iconBack = new ImageIcon(backOfCard);
      }
      iconsLoaded = true; //After icons loaded true, don't need to load again
      
   }
   
   static public Icon getIcon(Card card)
   {  
      if(iconsLoaded && !card.getErrorFlag())
      {

         //Get index based of suit and value of card
         int intValueIndex = Card.cardValues.lastIndexOf(card.getValue());
         int intSuitIndex = card.getSuit().ordinal();

//         //Testing ~comment out
//         System.out.println("Using Class: "+card.getValue()+" Index: "+intValueIndex); 
//         System.out.println("Using Class: "+card.getSuit()+" Index: "+intSuitIndex); 
//
//         JFrame myJframe = new JFrame("test window");
//         myJframe.setSize(400, 500);
//         myJframe.setVisible(true);
//         myJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         JLabel CardLabel = new JLabel();
//         CardLabel.setIcon(iconCards[intValueIndex][intSuitIndex]);
//         myJframe.add(CardLabel);

         return iconCards[intValueIndex][intSuitIndex];
      }
      else
      {
         return null;
      }

   }
   
   static public Icon getBackCardIcon()
   {
      if(iconsLoaded)
      {
         return iconBack;
      }
      else
      {
         return null;
      }
      
   }
   //main as testing only ~remove after
   public static void main(String[] args)
   {
      loadCardIcons();
      Card myCard = new Card('4',Card.Suit.HEARTS);
     // getIcon(myCard);
      
      //Test for back of card icon
      JFrame myJframe = new JFrame("test window");
      myJframe.setSize(400, 500);
      myJframe.setVisible(true);
      myJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      JLabel backCardLabel = new JLabel();
      //backCardLabel.setIcon(getBackCardIcon());
            
      myJframe.add(backCardLabel);
   }
   

}


class Card
{

   //A Public enum Type 
   public enum Suit {HEARTS, CLUBS, SPADES, DIAMONDS};
   //One addition to be used externally in Deck class
   public final static String cardValues = "A23456789TJQKX";
   public static char[] valuRanks = {'2', '3', '4', '5', '6', '7', '8', '9',
         'T', 'J', 'Q', 'K','A','X'};

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
      return(suit.equals(card.suit) && value == card.value 
            && card.errorFlag == false);
   }
   
   //Sort incoming card array
   static void arraySort(Card[] cards, int arraySize) {
      int n = arraySize;
      for (int i = 0; i < n-1; i++)
          for (int j = 0; j < n-i-1; j++)
              if (getRank(cards[j]) >getRank( cards[j+1]))
              {
                  // swap temp and cards[i]
                 Card temp = cards[j];
                  cards[j] = cards[j+1];
                  cards[j+1] = temp;
              }
   }
   
   //Gets rank of the card, could have used String methods though 
   static int getRank(Card card) {
      for (int i = 0; i<valuRanks.length; i++)
      {
         if (card.getValue() ==valuRanks[i]) return i; 
      }
      return 0;
      
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
   public Card playCard(int cardIndex) 
   {
      if (this.numCards>0) 
      {
         Card cardLocal = this.myCards[cardIndex]; 
         this.myCards[cardIndex] = null; 
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
   
   void sort() {
      Card.arraySort(this.myCards, this.myCards.length);
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
   private static int PACK_SIZE = 56;
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
      Card localCard = new Card('0',Card.Suit.CLUBS); 
      if (topCard>=0) {
         localCard = cards[topCard()];
         this.topCard--; 
      } 
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
   /*TODO make sure that there are not too many instances of the card in the deck 
   if you add it.  Return false if there will be too many.  It should put the 
   card on the top of the deck.
   */
   public boolean addCard (Card card) {
      for (Card lcard : cards) {
         if (lcard.equals(card) && card.getValue()!='X') {
            return false; 
         }
      }
      cards[cards.length] = card; 
      return true; 
   }
   
   /*you are looking to remove a specific card from the deck.  Put the current 
    *top card into its place.  Be sure the card you need is actually still in 
    *the deck, if not return false.*/
   public boolean removeCard(Card card) {
      if(cards.length<MAX_CARDS) {
         
         for (int i = 0; i< cards.length; i++) {
            if (cards[i].equals(card)) {
                              
               cards[i] = cards[topCard];
               cards[topCard] = null; 
               topCard --; 
               return true;
            }
         }
      }
      return false;
   }
   
   /*put all of the cards in the deck back into the right order according to 
    *their values.  Is there another method somewhere that already does this 
    *that you could refer to?*/
   public void sort() {
      Card.arraySort(this.cards, this.cards.length);
      
   }
   //return the number of cards remaining in the deck.
   public int getNumCards() {
     
      return this.cards.length;
      
   }
   
} 

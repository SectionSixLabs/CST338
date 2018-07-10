import java.util.*;

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
      Card[] testCards = new Card[5]; 
      testCards[0] = new Card(); 
      testCards[1] = new Card('X', Card.Suit.CLUBS);
      testCards[2] = new Card('2', Card.Suit.HEARTS);
      testCards[3] = new Card('4', Card.Suit.DIAMONDS);
      testCards[4] = new Card('5', Card.Suit.SPADES);
      showCards(testCards);
      
      pauseUntillKeyPress();
      
      testCards[0].set('0',Card.Suit.CLUBS);
      testCards[1].set('2', Card.Suit.CLUBS);

      showCards(testCards);
      
      pauseUntillKeyPress();
      
      // Test of Hand Class
      testCards[0].set('A',Card.Suit.SPADES);
      

      Hand hand1 = new Hand(); 
      testHand(hand1,testCards);       

      
      System.out.println("After deal");
      System.out.println("Hand =  ( "+hand1.toString()+" )");      

      showRandomCardFromHand(hand1,10);
      playAllCards(hand1); 
      
      pauseUntillKeyPress();
      
      // Test of Deck Class
      //two packs of cards
      Deck myDeck = new Deck(2); 
      dealAllCards(myDeck);
      
      pauseUntillKeyPress();
      
      System.out.println("Re-Initialising Deck with 2 Packs");
      myDeck.init(2);
      
      System.out.println("Evryday I'm shuffeling:");
      myDeck.shuffle();
      
      dealAllCards(myDeck);
      
      pauseUntillKeyPress();
      
      //one packs of cards
      System.out.println("Re-Initialising Deck:1 Pack");
      myDeck.init(1);
      
      dealAllCards(myDeck);
      
      pauseUntillKeyPress();
      
      System.out.println("Re-Initialising Deck:1 Pack");
      myDeck.init(1);
      
      System.out.println("Evryday I'm shuffeling:");
      myDeck.shuffle();
      
      dealAllCards(myDeck);
      
      pauseUntillKeyPress();
      
      /**
       * Phase 4
       */

      //TODO Code for Phase 4
      /*Consider creating an array for Hands dealt.*/
      System.out.println("Everything above this is testing phase stuff");
      System.out.println("Things below are phase 4 stuff\n");

      
      int numHands = getNumberOfHands();
      
      //Creating hand Objects
      Hand[] handArr = new Hand[numHands];
      for(int i=0; i<numHands; i++)
      {
         handArr[i] = new Hand();
      }
      
      //Reinitializing deck to 1 pack not shuffled 
      myDeck.init(1);

      distributeCards(handArr,myDeck,numHands); 
      showHands(handArr);
      
      pauseUntillKeyPress();
      
      myDeck.init(1);
      resetAllHands(handArr);
      System.out.println("Evryday I'm shuffeling:");
      myDeck.shuffle();

      
      distributeCards(handArr,myDeck,numHands); 
      showHands(handArr);
      
      pauseUntillKeyPress();

   }

   //Function to distribute 
   public static void distributeCards(Hand[] handArr, Deck myDeck, int numHands)
   {
      System.out.println("Distributing cards:");
      int playerNum = 0;
      while(myDeck.topCard()>=0) {
         //We need to deal with MAX card number mismatch between hand and deck
         if(handArr[playerNum].numCards()<handArr[playerNum].MAX_CARDS) {
            handArr[playerNum].takeCard(myDeck.dealCard());
            playerNum++; 
            if(playerNum == numHands) playerNum = 0;
         } else {
            //We will be dealing out to table cards that exceed the limit
            System.out.println("Dealing "+myDeck.dealCard().toString());
         }; 
        
      }
   }
   
   //Function to display all hands 
   public static void showHands(Hand[] handArr) {
      System.out.println("Here are our "+handArr.length+" hands:");
      for (Hand playerHand : handArr) {

         System.out.println("Hand = ("+" "+playerHand.toString() +" )");
         
      }
   }
   
   //Function to ask user to enter number of hands.
   @SuppressWarnings("resource")
   public static int getNumberOfHands() {
      Scanner scannerObject = new Scanner(System.in);
      //boolean repeatLoop = true;
      int numHands=0;
      do {
         System.out.print("How many hands? (1 - 10, please): ");
         numHands = scannerObject.nextInt();

      }while(!(numHands >=1 && numHands <=10));

      System.out.println("Number of hands is: "+numHands);
      return numHands; 
   }
   
   //
   public static void pauseUntillKeyPress() {

      System.out.print("Press Enter key to continue . . .");
      try {System.in.read();}
      catch(Exception e){}
   }
   
   //helper test function to display all cards in given array
   public static void showCards (Card[] cardsArray) {
      for (Card localCard :cardsArray) {
      System.out.println("Showing: "+ localCard.toString());
      }
   }
   
   //Helper Test function to test Hand functionality
   public static void testHand(Hand myHand, Card[] cardsArray) {
      while (myHand.numCards()<myHand.MAX_CARDS) 
      {
         boolean isBreak = false; 
         for (Card localCard: cardsArray) {
            isBreak = !myHand.takeCard(localCard);
            if (isBreak) break;
         }
         if (isBreak) break; 
      }
      System.out.println("Hand full");
   }
   
   //Helper Test function to test Inspect Card Method
   public static void showRandomCardFromHand(Hand myHand, int n) {
      System.out.println("Testing inspectCard()");
      Random rgen = new Random();
      System.out.println(myHand.inspectCard(1));
      for (int i=0;i<n;i++) {
      System.out.println(myHand.inspectCard(rgen.nextInt(myHand.MAX_CARDS+3)));
      }
      System.out.println(myHand.inspectCard(52));
      System.out.println(myHand.inspectCard(100));
   }
   
   //Helper test function to polay all cards in given hand to screen
   public static void playAllCards (Hand myHand) {
      while(myHand.numCards()>0) {
         System.out.println("Playing "+myHand.playCard().toString());
      }
      System.out.println("After playing all cards");
      System.out.println("Hand =  ( "+myHand.toString()+" )");  
   }
   
   //Helper test function to deal all cards in the Deck to screen
   public static void dealAllCards (Deck myDeck) {
      System.out.println("Dealing Cards:");
      while(myDeck.topCard()>=0) {
         System.out.println("Dealing "+myDeck.dealCard().toString());
      }
   }
   
   //Helper function to reset all active hands to initial state
   public static void resetAllHands(Hand[] handArr) {
      for (int i=0; i<handArr.length;i++) {
         handArr[i].resetHand();
      }
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
class Hand {
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
   public void resetHand() {
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
      if (this.numCards>0) {
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

class Deck {
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
   public Deck() {
      allocateMasterPack();
      init(this.numPacks);
   }
   
   //Overload Deck() with one parameter 
   public Deck(int numPacks) {
      allocateMasterPack();
      if(numPacks>MAX_PACKS) init(MAX_PACKS); else init(numPacks);
   }
   
   /*re-populate cards[] with the standard 52 � numPacks cards.  
    * We should not repopulate the static array,masterPack[], since that was 
    * done once, in the (first-invoked) constructor and  never changes. */
   public void init(int numPacks) {
      cards = new Card[PACK_SIZE*numPacks]; 
      for (int i=0; i<numPacks;i++) {
        System.arraycopy(masterPack, 0, cards, i*masterPack.length, 
              masterPack.length);
      }
      topCard = cards.length-1;
   }
   
/*mixes up the cards with the help of the standard random number generator.*/
   public void shuffle() {
      int localDeckSize =this.cards.length; 
      Card[] localDeck = new Card[localDeckSize]; 
      Random rgen = new Random();
      //While there cards in the deck deal card and assign it to the rendom 
      //place in the new array
      while (this.topCard>=0) {
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
   public Card dealCard() {
      Card localCard = cards[topCard()];
      this.topCard--; 
      return localCard; 
   }
   
   /*An accessor for the int, topCard (no mutator.)*/
   public int topCard() {
      return this.topCard; 
   }
   
   /*Accessor for an individual card.  Returns a card with errorFlag = true 
    *if k is bad.
    *We are not useing it anywhere though*/
   public Card inspectCard(int k) {
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
   private static void allocateMasterPack() {
      if (masterPack[0]!=null) return; 
      int i = 0;
      for(Card.Suit suit : Card.Suit.values()) {
         for (char value : Card.cardValues.toCharArray()) {
            Card localCard = new Card(value,suit);
            masterPack[i]=localCard; 
            i++; 
         }
      }
      
   }
   
   } 


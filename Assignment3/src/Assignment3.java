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
      while(myDeck.topCard()>=0) 
      {
         //We need to deal with MAX card number mismatch between hand and deck
         if(handArr[playerNum].numCards()<handArr[playerNum].MAX_CARDS) 
         {
            handArr[playerNum].takeCard(myDeck.dealCard());
            playerNum++; 
            if(playerNum == numHands) playerNum = 0;
         } 
         else 
         {
            //We will be dealing out to table cards that exceed the limit
            System.out.println("Dealing "+myDeck.dealCard().toString());
         }; 
        
      }
   }
   
   //Function to display all hands 
   public static void showHands(Hand[] handArr) 
   {
      System.out.println("Here are our "+handArr.length+" hands:");
      for (Hand playerHand : handArr) 
      {
         System.out.println("Hand = ("+" "+playerHand.toString() +" )");  
      }
   }
   
   //Function to ask user to enter number of hands.
   @SuppressWarnings("resource")
   public static int getNumberOfHands() 
   {
      Scanner scannerObject = new Scanner(System.in);
      
      int numHands=0;
      do {
         System.out.print("How many hands? (1 - 10, please): ");
         numHands = scannerObject.nextInt();

      }while(!(numHands >=1 && numHands <=10));

      System.out.println("Number of hands is: "+numHands);
      return numHands; 
   }
   
   //
   public static void pauseUntillKeyPress() 
   {

      System.out.print("Press Enter key to continue . . .");
      try {System.in.read();}
      catch(Exception e){}
   }
   
   //helper test function to display all cards in given array
   public static void showCards (Card[] cardsArray) 
   {
      for (Card localCard :cardsArray) 
      {
         System.out.println("Showing: "+ localCard.toString());
      }
   }
   
   //Helper Test function to test Hand functionality
   public static void testHand(Hand myHand, Card[] cardsArray) 
   {
      while (myHand.numCards()<myHand.MAX_CARDS) 
      {
         boolean isBreak = false; 
         for (Card localCard: cardsArray) 
         {
            isBreak = !myHand.takeCard(localCard);
            if (isBreak) break;
         }
         if (isBreak) break; 
      }
      System.out.println("Hand full");
   }
   
   //Helper Test function to test Inspect Card Method
   public static void showRandomCardFromHand(Hand myHand, int n) 
   {
      System.out.println("Testing inspectCard()");
      Random rgen = new Random();
      System.out.println(myHand.inspectCard(1));
      for (int i=0;i<n;i++) 
      {
         System.out.println(myHand.inspectCard(rgen.nextInt(myHand.MAX_CARDS+3)));
      }
      System.out.println(myHand.inspectCard(52));
      System.out.println(myHand.inspectCard(100));
   }
   
   //Helper test function to polay all cards in given hand to screen
   public static void playAllCards (Hand myHand) 
   {
      while(myHand.numCards()>0) 
      {
         System.out.println("Playing "+myHand.playCard().toString());
      }
      System.out.println("After playing all cards");
      System.out.println("Hand =  ( "+myHand.toString()+" )");  
   }
   
   //Helper test function to deal all cards in the Deck to screen
   public static void dealAllCards (Deck myDeck) 
   {
      System.out.println("Dealing Cards:");
      while(myDeck.topCard()>=0) 
      {
         System.out.println("Dealing "+myDeck.dealCard().toString());
      }
   }
   
   //Helper function to reset all active hands to initial state
   public static void resetAllHands(Hand[] handArr) 
   {
      for (int i=0; i<handArr.length;i++) 
      {
         handArr[i].resetHand();
      }
   } 
   
}

/**
 * Phase 1: The Card Class
 * 
 *
 */



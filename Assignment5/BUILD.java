/**
 * GUI Cards
 * CST338
 * Module5
 * @author Sergiy Zarubin, Lacey Sikes, Rocky Moreno, Danny Tran
 *
 */

//TODO Please submit one file for each phase (3 .txt files, UML .pdf file)

//Phase 1 Done. (Comment out tests when submitting.)

//Phase1
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.*;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javax.swing.JButton;
import javax.swing.JFrame;


//okayBranch has in tact the original Assignment5
public class BUILD
{

   static enum piles {One,Two};
   static enum players {CPU, One}; 
   static int NUM_CARDS_PER_HAND = 7;
   static int  NUM_PLAYERS = 2;
   static EndingListener[] humanListner =new EndingListener[NUM_CARDS_PER_HAND]; 

   public static void main(String[] args)
   {
      // going to add something
      int numPacksPerDeck = 1;
      int numJokersPerPack = 0;
      int numUnusedCardsPerPack = 0;
      Card[] unusedCardsPerPack = null;
      CardGameFramework highCardGame = new CardGameFramework( 
            numPacksPerDeck, numJokersPerPack,  
            numUnusedCardsPerPack, unusedCardsPerPack, 
            NUM_PLAYERS, NUM_CARDS_PER_HAND);
      GuiCard.loadCardIcons();
      highCardGame.deal(); 
      boolean c2 = true;
      CardTable myCardTable = 
            new CardTable("my card table", NUM_CARDS_PER_HAND, NUM_PLAYERS, c2);
      myCardTable.setVisible(true);
      //Add 
     GameController.init(highCardGame, NUM_PLAYERS,NUM_CARDS_PER_HAND); 
     GameView.init(highCardGame, myCardTable,NUM_PLAYERS,NUM_CARDS_PER_HAND);
      //GameController
     GameController.dealCards2table();
     GameView.drawCPUHand();
     GameView.drawPlayerHand();
     GameView.updateScore(0, 0);

      //Time Panel
      //manually make gui bigger to see clock, 
      TimeClock myTimeClock = new TimeClock();
      GameView.drawTimer(myTimeClock);


      
      //Testing Area
      System.out.println("Hi from end of Assingment5");
      System.out.println("How mnay cards are left in deck");
      int cardLeft = highCardGame.getNumCardsRemainingInDeck();
      System.out.println(cardLeft);

      System.out.println("Testing Area");
      System.out.println("Whose turn?");

   }



}

class EndingListener implements ActionListener
{
   
   public void actionPerformed(ActionEvent e)
   {

      String actionCommand = e.getActionCommand();
      
      if(actionCommand.equals("I cannot play")) //this is human button not cpu
      {
         boolean playerCantPlay = true;  
         GameController.setCannotPlay(playerCantPlay,GameController.player.One);
         GameController.cannotPlay(GameController.player.One) ; 
         GameController.computerPlay();
         
         boolean pcCannotPlay = 
               GameController.getCannotPlay(GameController.player.CPU); 
         if (playerCantPlay&&pcCannotPlay) {
            if( GameController.getNumCardsInDeck()== 0)
                  GameController.EndGame();

            GameController.dealCards2table();
         }
      }
      else
      {
         int cardIndex = Integer.valueOf(e.getActionCommand()); 
         System.out.println("Card Index: "+cardIndex);

         
         System.out.println("human Playing ");
         GameController.humenPlay(cardIndex);

      } //end of else (action performed)
   } //ending of action performed
  
}

class EndListener implements ActionListener
{
   public void actionPerformed(ActionEvent e)
   {
      System.out.println("Closing Program. Goodbye!");
      System.exit(0);
   }
}

class GameController
{
   static enum turn {CPU, One};
   static enum player {CPU, One}; 
   static int NUM_CARDS_PER_HAND;
   static int  NUM_PLAYERS;
   static CardGameFramework highCardGame;
   static Card[] cardsInPlay; 
   static JLabel[] computerFaces;
   private static int playNum = 0;
   private static int scorePC = 0;
   private static int scoreHum = 0;
   private static  boolean[] cannotPlay;
   
   //Accessors + Mutators
   public int getPlayNum()
   {
      return playNum;
   }
   

   public void setPlayNum(int num)
   {
      playNum = num;
   }
   
   public int getScorePC()
   {
      return scorePC;
   }
   
   public void setScorePC(int num)
   {
      scorePC = num;
   }
   
   public int getScoreHum()
   {
      return scoreHum;
   }
   
   public void setScoreHum(int num)
   {
      scoreHum = num;
   }
 
   public static int getNumCardsInDeck() 
   {
      return highCardGame.getNumCardsRemainingInDeck(); 
   }
   
   public static void setCardsInPlay(Card[] cards) {
      cardsInPlay = cards; 
      GameView.drawPlayAria(cards);
   } 

   //Retrieve reference to game data
   static void  init (CardGameFramework game, int nP, int nCPH) {
      highCardGame = game; 
      NUM_CARDS_PER_HAND = nCPH;
      NUM_PLAYERS = nP; 
      cardsInPlay = new Card[NUM_PLAYERS];
      cannotPlay  = new boolean[NUM_PLAYERS];
   }

   public static boolean cardPlay(int cardIndex, player playerIndex)
   {

      //test see if actionCommand for card is valid to play 
      //(one higher or lower than card in table)
      Card card2Inspect = highCardGame.getHand(playerIndex.ordinal()).
            inspectCard(cardIndex+1); 
      System.out.println("cardInPlay: "+card2Inspect);
      player stuckIndex = validPlay(card2Inspect); 
      if(stuckIndex!=null)

        {
         Card cardInPlay =
               highCardGame.getHand(playerIndex.ordinal()).playCard(cardIndex);
         cardsInPlay[0] = cardsInPlay[stuckIndex.ordinal()];
         cardsInPlay[1]=cardInPlay; 
         highCardGame.takeCard(playerIndex.ordinal());
         GameView.drawPlayAria(cardsInPlay);
         if (playerIndex==player.One)
            GameView.drawPlayerHand();
         else 
            GameView.drawCPUHand();
            return true; 
        }
            return false; 
   }


   public static void humenPlay(int cardIndex )
   {
      System.out.println("Neo playing");
      
      if (cardPlay(cardIndex,player.One)) {
         GameController.computerPlay();
         cannotPlay[player.One.ordinal()] = false; 
      } 


   }

   
   public static void computerPlay()
   {
      System.out.println("CPU playing");
      // TODO choose a card from the hand 
      highCardGame.getHand(player.CPU.ordinal()).sort();
      
      Card[] myHand = highCardGame.getHand(player.CPU.ordinal()).getCards();

     //Set default to no cards to play
     boolean cardPlayed = false; 
     
     //Check if we can play any of the cards
      for (int i=0; i< myHand.length;i++) {
         cardPlayed=cardPlay(i,player.CPU); 
         //if card was played exit
         if (cardPlayed) {
            cannotPlay[player.CPU.ordinal()] = false; 
            break;
            } 
      }
      if (!cardPlayed) {
      cannotPlay[player.CPU.ordinal()] = true;
      cannotPlay(player.CPU);
      }

   }

   //Method to see if card is one below or above card in pile for play
   public static player validPlay(Card card)
   {
      //Card to check rank
      int cardRank = Card.getRank(card);
      System.out.println("Cardrank: "+cardRank);
      int cardRankAbove = cardRank + 1;
      int cardRankBelow = cardRank - 1;

      //Get the card pile rank
      int pile1Rank = Card.getRank(cardsInPlay[0]);
      System.out.println("pile1Rank: "+pile1Rank);
      int pile2Rank = Card.getRank(cardsInPlay[1]);
      System.out.println("pile2Rank: "+pile2Rank);


      //valid if is there is a lower card by 1
      if(cardRankBelow == pile1Rank || cardRankAbove == pile1Rank)
      {
         return player.One;
      } //Valid if there is a higher card by 1
      else if (cardRankBelow == pile2Rank || cardRankAbove == pile2Rank)
      {
         return player.CPU;
      }
      else 
      {
         return null;
      }
   }


   public static void EndGame() 
   {

      String messageText = "";
      if(scoreHum > scorePC)
      {
         messageText = "You win!";
      }
      else if(scoreHum < scorePC)
      {
         messageText = "You lose.";
      }
      else
      {
         messageText = "Tied Game!";
      }
      GameView.drawEndGame(messageText);

   }
   
   public static void cannotPlay( player p) {

      if (p ==player.One) scoreHum++;
      else scorePC++; 
      GameView.updateScore(scorePC, scoreHum);

   }


   public static boolean getCannotPlay(player index)
   {
      return cannotPlay[index.ordinal()];
   }


   public static void setCannotPlay(boolean canPlay, player index)
   {
      cannotPlay[index.ordinal()] = canPlay;
   }
   
   public static void dealCards2table(){
      for (int i= 0 ;i<NUM_PLAYERS;i++) {
         cardsInPlay[i] = highCardGame.getCardFromDeck();
      }
      GameView.drawPlayAria(cardsInPlay);
   }

}

class GameView{
   static enum player {CPU, One}; 
   static int NUM_CARDS_PER_HAND;
   static int  NUM_PLAYERS;
   static JLabel[] computerLabels;
   static JLabel[] computerFaces ;
   static JLabel[] humanLabels;
   static JButton[] humanButtons ;
   static JLabel[] playedCardLabels ; 
   static JLabel[] playLabelText ;
   static CardTable myCardTable; 
   static CardGameFramework highCardGame ;
   public static void init(
         CardGameFramework highCG, CardTable myCT, 
         int nUM_PLAYERS2, int nUM_CARDS_PER_HAND2)
   {
     
      NUM_PLAYERS= nUM_PLAYERS2;
      NUM_CARDS_PER_HAND= nUM_CARDS_PER_HAND2;
      computerLabels = new JLabel[NUM_CARDS_PER_HAND];
      computerFaces= new JLabel[NUM_CARDS_PER_HAND];
      humanLabels = new JLabel[NUM_CARDS_PER_HAND];
      humanButtons= new JButton [NUM_CARDS_PER_HAND];
      playedCardLabels = new JLabel[NUM_PLAYERS];
      playLabelText = new JLabel[NUM_PLAYERS];
      myCardTable = myCT; 
      highCardGame = highCG; 
      
      
   }
   
   public static void drawCPUHand() {
      //Clear panel
      myCardTable.pnlComputerHand.removeAll();
      
      for (int i = 0; i < 
            highCardGame.getHand(player.CPU.ordinal()).numCards(); i++)
      {
         Icon tempIcon = GuiCard.getBackCardIcon();
         JLabel temlLabel = new JLabel(); 
         temlLabel.setIcon(tempIcon); 
         Card card = highCardGame.getHand(player.CPU.ordinal()).
               inspectCard(i+1); 
         if (card.isValidCard()) 
            computerLabels[i]=temlLabel;

      }
      for (int i = 0; i <
            highCardGame.getHand(player.CPU.ordinal()).numCards(); i++)
         {
            myCardTable.pnlComputerHand.add(computerLabels[i]);
         }

      refresh();
   }
   public static void drawPlayerHand() {
      // ADDING Labels TO HUMAN HAND
      myCardTable.pnlHumanHand.removeAll();
      
      for (int i = 0; i < myCardTable.getNumCardsPerHand(); i++)
      {
         Icon tempIcon = 
               GuiCard.getIcon(highCardGame.getHand(player.One.ordinal()).
                     inspectCard(i+1));
         JLabel temlLabel = new JLabel(); 
         JButton tempButton = 
               new JButton();

         EndingListener tempListner = new EndingListener();
         tempButton.addActionListener(tempListner);
         tempButton.setIcon(tempIcon);
         tempButton.setActionCommand((i)+"");
         tempButton.setBorder(null);
         temlLabel.setIcon(tempIcon);
         humanLabels[i]=temlLabel;
         humanButtons[i] = tempButton; 
      }
      for (int i = 0; i < myCardTable.getNumCardsPerHand(); i++)
      {
         myCardTable.pnlHumanHand.add(humanButtons[i]);
      }
      
      refresh();
      
   }
   
   public static void updateScore(int scorePC, int scoreHum ) {
      //Reassign Score Panel Labels
      myCardTable.pnlScore.removeAll();

      JLabel compScore = 
            new JLabel("CPU: "+scorePC, SwingConstants.CENTER);
      JLabel humScore = 
            new JLabel("Player: "+scoreHum+"", SwingConstants.CENTER);

      JLabel gameScore = new JLabel("SCORE");
      
      JLabel cards = new JLabel(
            highCardGame.getNumCardsRemainingInDeck()+" cards left"); 
      myCardTable.pnlButton.add(cards,BorderLayout.EAST);

      myCardTable.pnlScore.add(compScore);
      myCardTable.pnlScore.add(gameScore);
      myCardTable.pnlScore.add(humScore);
      myCardTable.pnlScore.add(cards);
      
      refresh();
   }
   
   public static void drawPlayAria(Card[] cards) {
      //Initialize Game by flipping two cards from deck into pnlPlayArea
      myCardTable.pnlPlayArea.removeAll();
      
      
      //display the two cards in card playing field
      Icon tempIconCPU = 
            GuiCard.getIcon(cards[0]);
      JLabel tempLabelCPU = new JLabel(); 
      tempLabelCPU.setIcon(tempIconCPU);
      
      playedCardLabels[player.CPU.ordinal()] = tempLabelCPU;
      
      Icon tempIconOne = 
            GuiCard.getIcon(cards[1]);
      JLabel tempLabelOne = new JLabel();
      tempLabelOne.setIcon(tempIconOne);
      
      playedCardLabels[player.One.ordinal()] = tempLabelOne;
      
      //Setting JLabel configs
      playedCardLabels[
                    player.CPU.ordinal()].setHorizontalAlignment(JLabel.CENTER);
      playedCardLabels[player.CPU.ordinal()].setBorder(null);
      
      playedCardLabels[
                    player.One.ordinal()].setHorizontalAlignment(JLabel.CENTER);
      playedCardLabels[player.One.ordinal()].setBorder(null);
      

      myCardTable.pnlPlayArea.add(playedCardLabels[player.CPU.ordinal()]);
      myCardTable.pnlPlayArea.add(playedCardLabels[player.One.ordinal()]);

      refresh();
   }
   
   public static void drawTimer(TimeClock myTimeClock ){
      myCardTable.pnlButton.add(myTimeClock.getContentPane(),BorderLayout.EAST);
      JButton noPlayButton = new JButton("I cannot play");
      EndingListener noPlayListener = new EndingListener();
      noPlayButton.addActionListener(noPlayListener);
      myCardTable.pnlButton.add(noPlayButton);
      refresh();
      
   }
   public static void drawEndGame(String messageText)  {
      //XXX We are working here
      int WIDTH = 600;
      int HEIGHT = 400;

      JFrame endWindow = new JFrame();
      endWindow.setSize(WIDTH, HEIGHT);

      endWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JButton endButton = new JButton(messageText);
      EndListener buttonEar = new EndListener();
      endButton.addActionListener(buttonEar);
      endWindow.add(endButton);
      //If we cannot push it back we will shrink it. 
      myCardTable.setSize(0,0);
      myCardTable.setVisible(false);
      myCardTable.dispose();
      endWindow.setVisible(true); 
      endWindow.repaint();
   }
   
   private static void refresh() {
      myCardTable.setVisible(true);
      myCardTable.repaint();
   }
   

}
   




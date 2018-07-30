/**
 * GUI Cards
 * CST338
 * Module5
 * @author Sergiy Zarubin, Lacey Sikes, Rocky Moreno, Danny Tran
 *
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.*;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFrame;


public class TimedHighCardGame
{
   static enum player {CPU, One}; 
   static int NUM_CARDS_PER_HAND = 7;
   static int  NUM_PLAYERS = 2;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] computerFaces = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JButton[] humanButtons = new JButton [NUM_CARDS_PER_HAND];
   static HightListener[] humanListner =new HightListener[NUM_CARDS_PER_HAND]; 
   static JLabel[] playedCardLabels  = new JLabel[NUM_PLAYERS]; 
   static JLabel[] playLabelText  = new JLabel[NUM_PLAYERS]; 
   static Card[] cardsInPlay = new Card[NUM_PLAYERS];
   // a simple main to throw all the JLabels out there for the world to see
   
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
      HightController.init(highCardGame, NUM_PLAYERS, NUM_CARDS_PER_HAND);
      GuiCard.loadCardIcons();

      highCardGame.deal(); 

      boolean c2 = true;
      CardTable myCardTable = 
            new CardTable("my card table", NUM_CARDS_PER_HAND, NUM_PLAYERS, c2);
      myCardTable.setVisible(true);
      HightView.init(highCardGame, myCardTable, NUM_PLAYERS, NUM_CARDS_PER_HAND);
      HightView.drawCPUHand();
      HightView.drawPlayerHand();
      HightView.drawPlayAria();
      HightView.updateScore(0, 0);
      //Time Panel
      //manually make gui bigger to see clock, 
      TimeClock myTimeClock = new TimeClock();
      HightView.drawTimer(myTimeClock);

      

   }

   public static void hi()
   {
      System.out.println("hi from buttonpress");
   }

}

class HightListener implements ActionListener
{




   public void actionPerformed(ActionEvent e)
   {
      String actionCommand = e.getActionCommand();
      if(actionCommand.equals("Exit"))
      {
         System.out.println("Closing Program. Goodbye!");
         System.exit(0);
      }
      else
      {
         int cardIndex = Integer.valueOf(e.getActionCommand()); 
         System.out.println(actionCommand.toString());
         HightController.playerTurn( cardIndex);
         //pcTurn(); 
         HightController.pcTurn(cardIndex); //matching card# human chooses

         HightController.updateScore();
         
      }
   }
}

class HiEndListener implements ActionListener
{
   public void actionPerformed(ActionEvent e)
   {
      System.out.println("Closing Program. Goodbye!");
      System.exit(0);
   }
}



class HightView{
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

         HightListener tempListner = new HightListener();
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
   
   
   public static void drawPlayAria()
   {
      //Initialize Game by flipping two cards from deck into pnlPlayArea
      myCardTable.pnlPlayArea.removeAll();
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
      playedCardLabels[player.CPU.ordinal()].setHorizontalAlignment(JLabel.CENTER);
      playedCardLabels[player.CPU.ordinal()].setBorder(null);
      
      playedCardLabels[player.One.ordinal()].setHorizontalAlignment(JLabel.CENTER);
      playedCardLabels[player.One.ordinal()].setBorder(null);
      

      myCardTable.pnlPlayArea.add(playedCardLabels[player.CPU.ordinal()]);
      myCardTable.pnlPlayArea.add(playedCardLabels[player.One.ordinal()]);

      refresh();
   }
   
   public static void drawTimer(TimeClock myTimeClock ){
      myCardTable.pnlButton.add(myTimeClock.getContentPane(),BorderLayout.EAST);
      refresh();
      
   }
   public static void drawEndGame(String messageText) throws Exception {
      
      EndWindow myEnd = new EndWindow(messageText,
            HightController.getScorePC(),HightController.getScoreHum()); 
      myCardTable.removeAll();
      myCardTable.setTitle(messageText);
      refresh(); 
      Thread.sleep(4000);
      System.exit(0); 
   }
   
   private static void refresh() {
      myCardTable.setVisible(true);
      myCardTable.repaint();
   }
   

}

class HightController
{
   static enum turn {CPU, One};
   static enum player {CPU, One}; 
   static int NUM_CARDS_PER_HAND;
   static int  NUM_PLAYERS;
   static CardGameFramework highCardGame;
   static Card[] cardsInPlay; 
   static JLabel[] computerFaces;
   private static int scorePC = 0;
   private static int scoreHum = 0;

   
   //Accessors + Mutators
  
   public static int getScorePC()
   {
      return scorePC;
   }
   
   public static void setScorePC(int num)
   {
      scorePC = num;
   }
   
   public static int getScoreHum()
   {
      return scoreHum;
   }
   
   public static void setScoreHum(int num)
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

   }

   public static void EndGame() throws Exception
   {

      String messageText = "Frack";
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
      HightView.drawEndGame(messageText);

   }
   
   public static void pcTurn(int cardIndex) {

      cardsInPlay[player.CPU.ordinal()]=
            highCardGame.playCard(player.CPU.ordinal(), cardIndex); 

      System.out.println("Smith Playing: "
            +cardsInPlay[player.CPU.ordinal()].toString());
      HightView.drawCPUHand();
      HightView.drawPlayAria(cardsInPlay);
      if (highCardGame.getHand(player.CPU.ordinal()).numCards()==0) {
         try
         {
            EndGame();
         } catch (Exception e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         } 
      }
   } 

   public static void playerTurn(int cardIndex) {
      //human action first

      //System.out.println("Unexpected Error.");
      // making sure we have a card ref
      cardsInPlay[player.One.ordinal()]=
            highCardGame.playCard(player.One.ordinal(), cardIndex); 
      System.out.println("Neo Playing: "
            +cardsInPlay[player.One.ordinal()].toString());      
            HightView.drawPlayerHand();
   } 
   public static void updateScore()
   {
      
      if (
            Card.getRank(cardsInPlay[player.One.ordinal()])
            >
            Card.getRank(cardsInPlay[player.CPU.ordinal()]))
         scoreHum++;   
      else if (
            Card.getRank(cardsInPlay[player.One.ordinal()])
            <
            Card.getRank(cardsInPlay[player.CPU.ordinal()])
            ) scorePC++; 
      HightView.updateScore(scorePC, scoreHum);
      
   }


}




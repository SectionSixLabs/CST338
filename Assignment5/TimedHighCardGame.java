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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.*;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFrame;

//Deleted Assignment6 and using old Assignmnet5 becuase of similar default package class overlap giving issues
//okayBranch has in tact the original TimedHighCardGame
public class TimedHighCardGame
{
   static enum player {CPU, One}; 
   static int NUM_CARDS_PER_HAND = 7;
   static int  NUM_PLAYERS = 2;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] computerFaces = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JButton[] humanButtons = new JButton [NUM_CARDS_PER_HAND];
   static EndingListener[] humanListner =new EndingListener[NUM_CARDS_PER_HAND]; 
   static JLabel[] playedCardLabels  = new JLabel[NUM_PLAYERS]; 
   static JLabel[] playLabelText  = new JLabel[NUM_PLAYERS]; 
   static Card[] cardsInPlay = new Card[NUM_PLAYERS];
   static int playNum = 0;
   static int oldIndex = 0;
   static int scorePC = 0;
   static int scoreHum = 0;
   static int numTurns = 0;
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



      GuiCard.loadCardIcons();

      highCardGame.deal(); 

      boolean c2 = true;
      CardTable myCardTable = 
            new CardTable("my card table", NUM_CARDS_PER_HAND, NUM_PLAYERS, c2);
      myCardTable.setVisible(true);

      GameController.initListener(highCardGame, myCardTable, 
            humanButtons,humanLabels,computerLabels,playedCardLabels,
            playLabelText,cardsInPlay,NUM_PLAYERS, NUM_CARDS_PER_HAND, 
            computerFaces); 

      // ADDING Labels TO COMPUTER HAND
      for (int i = 0; i < myCardTable.getNumCardsPerHand(); i++)
      {
         Icon tempIcon = GuiCard.getBackCardIcon();
         JLabel temlLabel = new JLabel(); 
         temlLabel.setIcon(tempIcon); 
         computerLabels[i]=temlLabel;

         //computerFaces
         Icon tempIconFace = GuiCard.getIcon(highCardGame.getHand(0).
               inspectCard(i+1));
         computerFaces[i] = new JLabel(tempIconFace);

      }

      // ADDING Labels TO HUMAN HAND
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
         humanListner[i] = tempListner; 
      }

      // ADD LABELS TO PANELS -----------------------------------------
      
      for (int i = 0; i < myCardTable.getNumCardsPerHand(); i++)
      {
         myCardTable.pnlComputerHand.add(computerLabels[i]);
         //myCardTable.pnlHumanHand.add(humanLabels[i]);
         myCardTable.pnlHumanHand.add(humanButtons[i]);
      }

      myCardTable.setVisible(true);


      JLabel instruction = new JLabel("Play:", SwingConstants.CENTER);
      myCardTable.pnlButton.add(instruction);

      JButton noPlayButton = new JButton("I cannot play");
      EndingListener noPlayListener = new EndingListener();
      noPlayButton.addActionListener(noPlayListener);
      myCardTable.pnlButton.add(noPlayButton);

      myCardTable.setVisible(true);

      //Score Panel
      JLabel compScore = new JLabel(scorePC+"", SwingConstants.CENTER);
      JLabel gameScore = new JLabel("SCORE");
      JLabel humScore = new JLabel(scoreHum+"", SwingConstants.CENTER);

      myCardTable.pnlScore.add(compScore);
      myCardTable.pnlScore.add(gameScore);
      myCardTable.pnlScore.add(humScore);
      
      
      //Initialize Game by flipping two cards from deck into pnlPlayArea
      cardsInPlay[player.CPU.ordinal()] = highCardGame.getCardFromDeck();
      cardsInPlay[player.One.ordinal()] = highCardGame.getCardFromDeck();
      
      //Testing card drawn from deck
      System.out.println("The card for cpu pile:");
      System.out.println(cardsInPlay[player.CPU.ordinal()]);
      System.out.println("The card for hum pile:");
      System.out.println(cardsInPlay[player.One.ordinal()]);
      
      //display the two cards in card playing field
      Icon tempIconCPU = 
            GuiCard.getIcon(cardsInPlay[player.CPU.ordinal()]);
      JLabel tempLabelCPU = new JLabel(); 
      tempLabelCPU.setIcon(tempIconCPU);
      
      playedCardLabels[player.CPU.ordinal()] = tempLabelCPU;
      
      Icon tempIconOne = 
            GuiCard.getIcon(cardsInPlay[player.One.ordinal()]);
      JLabel tempLabelOne = new JLabel();
      tempLabelOne.setIcon(tempIconOne);
      
      playedCardLabels[player.One.ordinal()] = tempLabelOne;
      
      //Setting JLabel configs
      playedCardLabels[player.CPU.ordinal()].setHorizontalAlignment(JLabel.CENTER);
      playedCardLabels[player.CPU.ordinal()].setBorder(null);
      
      playedCardLabels[player.One.ordinal()].setHorizontalAlignment(JLabel.CENTER);
      playedCardLabels[player.One.ordinal()].setBorder(null);
      
      //clear cards if any but there shouldn't be atm bc initilizing phase
      if (playedCardLabels[player.CPU.ordinal()]!=null) {
         myCardTable.pnlPlayArea.remove(playedCardLabels[player.CPU.ordinal()]);          
      }
      
      if (playedCardLabels[player.One.ordinal()]!=null) {
         myCardTable.pnlPlayArea.remove(playedCardLabels[player.One.ordinal()]);          
      }

      myCardTable.pnlPlayArea.add(playedCardLabels[player.CPU.ordinal()]);
      myCardTable.pnlPlayArea.add(playedCardLabels[player.One.ordinal()]);
      myCardTable.setVisible(true);
      myCardTable.repaint();
      
      
      
      //Testing Area
      System.out.println("Hi from end of Assingment5");
      System.out.println("How mnay cards are left in deck");
      int cardLeft = highCardGame.getNumCardsRemainingInDeck();
      System.out.println(cardLeft);

      System.out.println("Testing Area");

   }



}

class EndingListener implements ActionListener
{


   public void actionPerformed(ActionEvent e)
   {
      //when "I cannot play" button gets pressed increment the tally underscore
      //higher score is losing, kinda like golf

      String actionCommand = e.getActionCommand();
      
      if(actionCommand.equals("I cannot play")) //this is human button not cpu
      {
         GameController.playerCannotPlay(); 
      }
      else
      {
         int cardIndex = Integer.valueOf(e.getActionCommand()); 
         System.out.println(actionCommand.toString());
         
      
         /* The method calls from prior assignment //Comment out
         playerTurn( cardIndex);
         pcTurn2(cardIndex); //matching card# human chooses
         updateScore();
         */
         
         GameController.humanPlay(cardIndex);
         GameController.computerPlay();
         
         TimedHighCardGame.numTurns += 1;
         //End game when no more cards in deck
         if(GameController.highCardGame.getNumCardsRemainingInDeck() == 0)
         {
            GameController.EndGame();
         }
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

class GameController {
   
   static enum player {CPU, One}; 
   static int NUM_CARDS_PER_HAND;
   static int  NUM_PLAYERS;
   static CardGameFramework highCardGame;
   static CardTable myCardTable; 
   static JButton[] humanButtons; 
   static JLabel[] humanLabels;
   static JLabel[] computerLabels;
   static JLabel[] playedCardLabels; 
   static JLabel[] playLabelText; 
   static Card[] cardsInPlay; 
   static JLabel[] computerFaces;

   //Retrieve reference to game data
   //XXX Listner Init
   static void  initListener (CardGameFramework game, CardTable table, 
         JButton[] hB, JLabel[] hL, JLabel[] cL, JLabel[] pCL,JLabel[] pLtxt, 
         Card[] cIP, int nP, int nCPH, JLabel[] cF) {
      highCardGame = game; 
      myCardTable = table; 
      humanButtons = hB; 
      humanLabels = hL; 
      computerLabels = cL; 
      playedCardLabels = pCL;
      playLabelText = pLtxt;
      cardsInPlay = cIP; 
      NUM_CARDS_PER_HAND = nCPH;
      NUM_PLAYERS = nP; 
      computerFaces = cF;

   }
   public static void playerCannotPlay()
   {
      TimedHighCardGame.scoreHum += 1;

      //Reassign Score Panel Labels
      myCardTable.pnlScore.removeAll();

      JLabel compScore = 
            new JLabel("CPU: "+TimedHighCardGame.scorePC, SwingConstants.CENTER);
      JLabel humScore = 
            new JLabel("Player: "+TimedHighCardGame.scoreHum+"", SwingConstants.CENTER);

      JLabel gameScore = new JLabel("SCORE");

      myCardTable.pnlScore.add(compScore);
      myCardTable.pnlScore.add(gameScore);
      myCardTable.pnlScore.add(humScore);

      myCardTable.setVisible(true);
      myCardTable.repaint();
   }
   
   public static void humanPlay(int cardIndex)
   {
    //indicator of which pile to use
      boolean usePile1 = false;
      boolean usePile2 = false;
      
      //TODO fix issue of card be able to be placed in either pile if it is a valid play
      
      //test see if actionCommand for card is valid to play (one higher or lower than card in table)
      if(validPlay(highCardGame.getHand(player.One.ordinal()).inspectCard(cardIndex)))
      {
            
         //Because validPlay is true then one of the piles must be valid for play by the cardIndex
         
         //Check range for first pile
         int pile1Rank = Card.getRank(cardsInPlay[player.CPU.ordinal()]);
         
         //if pile1 (cardsInPlay[player.CPU.ordinal()] is one off (+/- 1) then use this pile
         int pile1RankBelow = pile1Rank-1;
         int pile1RankAbove = pile1Rank+1;
         
         //if player selected card is valid +/- 1 from pile1 use this pile for playing
         if(pile1RankBelow == Card.getRank(highCardGame.getHand(player.One.ordinal()).inspectCard(cardIndex))
               || pile1RankAbove == Card.getRank(highCardGame.getHand(player.One.ordinal()).inspectCard(cardIndex))  );
         {
            //pile1
            cardsInPlay[player.CPU.ordinal()] = 
                  highCardGame.playCard(player.One.ordinal(), cardIndex);
            //Testing
            System.out.println("Neo Playing: "
                  +cardsInPlay[player.CPU.ordinal()].toString());
            
            usePile1 = true;
         }  
         
         
         int pile2Rank = Card.getRank(cardsInPlay[player.One.ordinal()]);
         
         int pile2RankBelow = pile2Rank-1;
         int pile2RankAbove = pile2Rank+1;
         
         if((pile2RankBelow == Card.getRank(highCardGame.getHand(player.One.ordinal()).inspectCard(cardIndex))
               || pile2RankAbove == Card.getRank(highCardGame.getHand(player.One.ordinal()).inspectCard(cardIndex))) 
               && //negation of the before if statement to ensure not to put in pile1
               pile1RankBelow != Card.getRank(highCardGame.getHand(player.One.ordinal()).inspectCard(cardIndex))
               || pile1RankAbove != Card.getRank(highCardGame.getHand(player.One.ordinal()).inspectCard(cardIndex))   )
         { //pile2
            cardsInPlay[player.One.ordinal()]=
                  highCardGame.playCard(player.One.ordinal(), cardIndex); 
            System.out.println("Cifer Playing: "
                  +cardsInPlay[player.One.ordinal()].toString());
            
            usePile2 = true;
         }
         
         humanLabels[cardIndex].setHorizontalAlignment(JLabel.CENTER);
         humanLabels[cardIndex].setBorder(null);
         
         
         //Decide which pile to add image label to
         
         if(usePile1)
         {
            if (playedCardLabels[player.CPU.ordinal()]!=null) 
            { //clear pile1 image
               myCardTable.pnlPlayArea.remove(playedCardLabels[player.CPU.ordinal()]);   
               
               Icon tempIconCPU = 
                     GuiCard.getIcon(cardsInPlay[player.CPU.ordinal()]);
               JLabel tempLabelCPU = new JLabel(); 
               tempLabelCPU.setIcon(tempIconCPU);
               
               myCardTable.pnlPlayArea.add(playedCardLabels[player.CPU.ordinal()]);
               myCardTable.setVisible(true);
               myCardTable.repaint();
            }
         } 
         else if(usePile2)
         {
            if (playedCardLabels[player.One.ordinal()]!=null)
            { //clear pile2 image
               myCardTable.pnlPlayArea.remove(playedCardLabels[player.One.ordinal()]);
               
               Icon tempIconOne = 
                     GuiCard.getIcon(cardsInPlay[player.One.ordinal()]);
               JLabel tempLabelOne = new JLabel();
               tempLabelOne.setIcon(tempIconOne);
               
               myCardTable.pnlPlayArea.add(playedCardLabels[player.One.ordinal()]);
               myCardTable.setVisible(true);
               myCardTable.repaint();
            }
         }
         
         
         myCardTable.pnlHumanHand.remove(humanButtons[cardIndex]);
         myCardTable.setVisible(true);
         myCardTable.repaint();
      } //end of inner if of else clause
   }
   
   public static  void computerPlay()
   {
      //Trying to hash out humanPlay first
   }

   //Method to see if card is one below or above card in pile for play
   public static boolean validPlay(Card card)
   {
      //Card to check rank
      int cardRank = Card.getRank(card);
      int cardRankAbove = cardRank + 1;
      int cardRankBelow = cardRank - 1;

      //Get the card pile rank
      int pile1Rank = Card.getRank(cardsInPlay[player.CPU.ordinal()]);
      int pile2Rank = Card.getRank(cardsInPlay[player.One.ordinal()]);


      //valid if is there is a lower card by 1
      if(cardRankBelow == pile1Rank || cardRankBelow == pile2Rank)
      {
         return true;
      } //Valid if there is a higher card by 1
      else if (cardRankAbove == pile1Rank || cardRankAbove == pile2Rank)
      {
         return true;
      }
      else 
      {
         return false;
      }

      //Need to check for cases when card is lowest or highest rank ie A or X?

   }

   public static void updateScore()
   {
      //Testing the card values

      int pcRank = Card.getRank(cardsInPlay[player.CPU.ordinal()]);
      int humRank = Card.getRank(cardsInPlay[player.One.ordinal()]);

      System.out.println("rankHumRank: "+humRank);

      System.out.println("rankPCrank: "+pcRank);
      //need to check out the card.getRank

      if(humRank > pcRank)
      {
         TimedHighCardGame.scoreHum += 1;
      }
      else if (humRank < pcRank)
      {
         TimedHighCardGame.scorePC += 1;
      }
      else 
      {
         //Do update to score
      }
      //Reassign Score Panel Labels
      myCardTable.pnlScore.removeAll();

      JLabel compScore = 
            new JLabel("CPU: "+TimedHighCardGame.scorePC, SwingConstants.CENTER);
      JLabel humScore = 
            new JLabel("Player: "+TimedHighCardGame.scoreHum+"", SwingConstants.CENTER);

      JLabel gameScore = new JLabel("SCORE");

      myCardTable.pnlScore.add(compScore);
      myCardTable.pnlScore.add(gameScore);
      myCardTable.pnlScore.add(humScore);

      myCardTable.setVisible(true);
      myCardTable.repaint();

   }


   public static void pcTurn2(int cardIndex) {

      if(TimedHighCardGame.playNum == 0)
      {
         myCardTable.pnlPlayArea.add(computerFaces[cardIndex]);
         myCardTable.pnlPlayArea.remove(computerLabels[cardIndex]);
         TimedHighCardGame.oldIndex = cardIndex;
      }
      else if(TimedHighCardGame.playNum > 0)
      {
         myCardTable.pnlPlayArea.remove(computerFaces[TimedHighCardGame.oldIndex]);

         TimedHighCardGame.oldIndex = cardIndex;
         myCardTable.pnlPlayArea.add(computerFaces[cardIndex]);

         //computerFaces = array with card faces
         //computerLabels = back of card

      }
      cardsInPlay[player.CPU.ordinal()]=
            highCardGame.playCard(player.CPU.ordinal(), cardIndex); 

      System.out.println("Smith Playing: "
            +cardsInPlay[player.CPU.ordinal()].toString());
      TimedHighCardGame.playNum++;

      myCardTable.pnlComputerHand.remove(computerLabels[cardIndex]);

      myCardTable.setVisible(true);
      myCardTable.repaint();
   } 

   private static void playerTurn(int cardIndex) {
      //human action first

      //System.out.println("Unexpected Error.");
      // making sure we have a card ref
      cardsInPlay[player.One.ordinal()]=
            highCardGame.playCard(player.One.ordinal(), cardIndex); 
      System.out.println("Neo Playing: "
            +cardsInPlay[player.One.ordinal()].toString());


      humanLabels[cardIndex].setHorizontalAlignment(JLabel.CENTER);
      humanLabels[cardIndex].setBorder(null);

      if (playedCardLabels[player.One.ordinal()]!=null) {
         myCardTable.pnlPlayArea.remove(playedCardLabels[player.One.ordinal()]);          
      }

      myCardTable.pnlPlayArea.add(humanLabels[cardIndex]);
      playedCardLabels[player.One.ordinal()] = humanLabels[cardIndex]; 
      myCardTable.pnlHumanHand.remove(humanButtons[cardIndex]);
      myCardTable.setVisible(true);
      myCardTable.repaint();
   } 

   public static void EndGame()
   {
      int WIDTH = 300;
      int HEIGHT = 200;

      JFrame endWindow = new JFrame();
      endWindow.setSize(WIDTH, HEIGHT);

      endWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      String messageText = "";
      if(TimedHighCardGame.scoreHum > TimedHighCardGame.scorePC)
      {
         messageText = "You win!";
      }
      else if(TimedHighCardGame.scoreHum < TimedHighCardGame.scorePC)
      {
         messageText = "You lose.";
      }
      else
      {
         messageText = "Tied Game!";
      }
      JButton endButton = new JButton(messageText);
      EndListener buttonEar = new EndListener();
      endButton.addActionListener(buttonEar);
      endWindow.add(endButton);
      endWindow.setVisible(true);  
   }
}




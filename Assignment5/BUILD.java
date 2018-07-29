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

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFrame;

//Deleted Assignment6 and using old Assignmnet5 becuase of similar default package class overlap giving issues

//okayBranch has in tact the original Assignment5
public class BUILD
{
   //Adding a turn enum
   static enum turn{CPU, One};
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
      
      //Add 
      GameController.initListener(highCardGame, myCardTable, 
            humanButtons,humanLabels,computerLabels,playedCardLabels,
            playLabelText,cardsInPlay,NUM_PLAYERS, NUM_CARDS_PER_HAND, 
            computerFaces); 
      
      //GameController
      
      

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


      //JLabel instruction = new JLabel("Play:", SwingConstants.CENTER);
      //myCardTable.pnlButton.add(instruction);

    
      //Time Panel
      //manually make gui bigger to see clock, 
      //TODO fix border / size to see 
      TimeClock myTimeClock = new TimeClock();
      myCardTable.pnlButton.add(myTimeClock.getContentPane(), BorderLayout.EAST);
      
      JButton noPlayButton = new JButton("I cannot play");
      GameController noPlayListener = new GameController();
      //EndingListener noPlayListener = new EndingListener();
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
      //System.out.println("Whose turn?");
      //turn playerTurn = turn.CPU;
      //System.out.println(playerTurn);
   }



}

class EndingListener implements ActionListener
{
   

   //Retrieve reference to game data
   //XXX Listner Init
 
   public void actionPerformed(ActionEvent e)
   {
      //when "I cannot play" button gets pressed increment the tally underscore
      //higher score is losing, kinda like golf

      String actionCommand = e.getActionCommand();
      
      if(actionCommand.equals("I cannot play")) //this is human button not cpu
      {

         
         //switch to turns to opponent
         //TODO
         GameController.cannotPlay() ; 
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
         
         //TODO implement turn based
         GameController.humanPlay(cardIndex);
         GameController.computerPlay();
         
         GameController.getNumTurns() ;
         //End game when no more cards in deck
         if( GameController.getNumCardsInDeck()== 0)
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

class GameController implements ActionListener
{
   static enum turn {CPU, One};
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
   private static int playNum = 0;
   private static int oldIndex = 0;
   private static int scorePC = 0;
   private static int scoreHum = 0;
   private static  int numTurns = 0;
   
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
   
   public static int getNumTurns()
   {
      return numTurns;
   }
   
   public void setNumTurns(int num)
   {
      numTurns = num;
   }
   public static int getNumCardsInDeck() 
   {
      return highCardGame.getNumCardsRemainingInDeck(); 
   }
   

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

   public void actionPerformed(ActionEvent e)
   {
      //when "I cannot play" button gets pressed increment the tally underscore
      //higher score is losing, kinda like golf

      String actionCommand = e.getActionCommand();
      
      if(actionCommand.equals("I cannot play")) //this is human button not cpu
      {
         scoreHum += 1;
         
         //Test
         System.out.println(scoreHum);

         //Reassign Score Panel Labels
         myCardTable.pnlScore.removeAll();

         JLabel compScore = 
               new JLabel("CPU: "+scorePC, SwingConstants.CENTER);
         JLabel humScore = 
               new JLabel("Player: "+scoreHum+"", SwingConstants.CENTER);

         JLabel gameScore = new JLabel("SCORE");

         myCardTable.pnlScore.add(compScore);
         myCardTable.pnlScore.add(gameScore);
         myCardTable.pnlScore.add(humScore);

         myCardTable.setVisible(true);
         myCardTable.repaint();
         
         //switch to turns to opponent
         //TODO
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
         
         //TODO implement turn based
         humanPlay(cardIndex);
         computerPlay();
         
        numTurns += 1;
         //End game when no more cards in deck
         if(highCardGame.getNumCardsRemainingInDeck() == 0)
         {
            EndGame();
         }
      } //end of else (action performed)
   } //ending of action performed
   
   public static void humanPlay(int cardIndex)
   { //start bracket

      
      player playerIndex = validPlay(highCardGame.getHand(player.One.ordinal()).inspectCard(cardIndex));
    
      
      //test see if actionCommand for card is valid to play (one higher or lower than card in table)
      if(playerIndex != null)
      {
           
         //humanLabels[cardIndex].setHorizontalAlignment(JLabel.CENTER);
         //humanLabels[cardIndex].setBorder(null);
         
         
         //Decide which pile to add image label to
         //TODO issue with wrong card bbeing played
         //TODO draw card after playing
         
 
            //cards disappear
            if (playedCardLabels[playerIndex.ordinal()]!=null)
            { //clear pile image
               myCardTable.pnlPlayArea.remove(playedCardLabels[playerIndex.ordinal()]);
               
               Icon tempIconOne = 
                     GuiCard.getIcon(cardsInPlay[playerIndex.ordinal()]);
               JLabel tempLabelOne = new JLabel();
               tempLabelOne.setIcon(tempIconOne);
               
               myCardTable.pnlPlayArea.add(playedCardLabels[playerIndex.ordinal()]);
               myCardTable.setVisible(true);
               myCardTable.repaint();
               
               //testing cards in play
               System.out.println("Card pile test");
               System.out.println("Pile1");
               
               System.out.println(cardsInPlay[player.CPU.ordinal()]);
               
               System.out.println("Pile2:");
               System.out.println(cardsInPlay[player.One.ordinal()]);
               
               System.out.println("Reaches here!");
               
            }
            else 
            { //seems like its dead code and doesn't reach 
               System.out.println("Cannot play a card!!!");
            }
  
         
         
         myCardTable.pnlHumanHand.remove(humanButtons[cardIndex]);
         myCardTable.setVisible(true);
         myCardTable.repaint();
      } //end of inner if of else clause
   }//end method bracket
   
   public static void computerPlay()
   {
      //TODO Trying to hash out humanPlay first
      
      //Pseuo
    //Check range for pile on play area
      int pile1Rank = Card.getRank(cardsInPlay[player.CPU.ordinal()]);
      
      int pile2Rank = Card.getRank(cardsInPlay[player.One.ordinal()]);
      
      //check for cards in hand
      //if card in computer hand is playable in any of the piles 
      
      //play the card into pile
      //TODO decide what kind of algorithm to use? Maybe just pick the first card that works? to discuss with team
      
      //display JLabel / icon for card
      
      //Switch player turn
      
      //else if cannt play
      //hit the "i cannot play" button
      
      //switch turn to other player
      
      
      
      
   }

   //Method to see if card is one below or above card in pile for play
   //chnage to return player based on pile to play
   public static player validPlay(Card card)
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
         return player.CPU; //enum index (int)
      } //Valid if there is a higher card by 1
      else if (cardRankAbove == pile1Rank || cardRankAbove == pile2Rank)
      {
         return player.One; //enum index (int)
      }
      else 
      {
         return null;
      }
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
         scoreHum += 1;
      }
      else if (humRank < pcRank)
      {
         scorePC += 1;
      }
      else 
      {
         //Do update to score
      }
      //Reassign Score Panel Labels
      myCardTable.pnlScore.removeAll();

      JLabel compScore = 
            new JLabel("CPU: "+scorePC, SwingConstants.CENTER);
      JLabel humScore = 
            new JLabel("Player: "+scoreHum+"", SwingConstants.CENTER);

      JLabel gameScore = new JLabel("SCORE");

      myCardTable.pnlScore.add(compScore);
      myCardTable.pnlScore.add(gameScore);
      myCardTable.pnlScore.add(humScore);

      myCardTable.setVisible(true);
      myCardTable.repaint();

   }


   public static void pcTurn2(int cardIndex) {

      if(playNum == 0)
      {
         myCardTable.pnlPlayArea.add(computerFaces[cardIndex]);
         myCardTable.pnlPlayArea.remove(computerLabels[cardIndex]);
         oldIndex = cardIndex;
      }
      else if(playNum > 0)
      {
         myCardTable.pnlPlayArea.remove(computerFaces[oldIndex]);

         oldIndex = cardIndex;
         myCardTable.pnlPlayArea.add(computerFaces[cardIndex]);

         //computerFaces = array with card faces
         //computerLabels = back of card

      }
      cardsInPlay[player.CPU.ordinal()]=
            highCardGame.playCard(player.CPU.ordinal(), cardIndex); 

      System.out.println("Smith Playing: "
            +cardsInPlay[player.CPU.ordinal()].toString());
      playNum++;

      myCardTable.pnlComputerHand.remove(computerLabels[cardIndex]);

      myCardTable.setVisible(true);
      myCardTable.repaint();
   } 

   public static void playerTurn(int cardIndex) {
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
      //Remember current score is how mnay times "I cannot play" is called
      //lower score is better
      if(scoreHum < scorePC)
      {
         messageText = "You win!";
      }
      else if(scoreHum > scorePC)
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
   
   public static void cannotPlay() {


      //Reassign Score Panel Labels
      myCardTable.pnlScore.removeAll();

      JLabel compScore = 
            new JLabel("CPU: "+scorePC, SwingConstants.CENTER);
      JLabel humScore = 
            new JLabel("Player: "+scoreHum+"", SwingConstants.CENTER);

      JLabel gameScore = new JLabel("SCORE");

      myCardTable.pnlScore.add(compScore);
      myCardTable.pnlScore.add(gameScore);
      myCardTable.pnlScore.add(humScore);

      myCardTable.setVisible(true);
      myCardTable.repaint();
   }
}
   




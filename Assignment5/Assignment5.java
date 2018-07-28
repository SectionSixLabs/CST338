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


public class Assignment5
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

      EndingListener.initListener(highCardGame, myCardTable, 
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
      //TODO code goes here ...
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
      
      //TODO
      //Initialize Game by flipping two cards from deck into pnlPlayArea
      cardsInPlay[player.CPU.ordinal()] = highCardGame.getCardFromDeck();
      cardsInPlay[player.One.ordinal()] = highCardGame.getCardFromDeck();
      
      //Testing
      System.out.println("The card for cpu pile:");
      System.out.println(cardsInPlay[player.CPU.ordinal()]);
      System.out.println("The card for hum pile:");
      System.out.println(cardsInPlay[player.One.ordinal()]);
      
      //TODO display card in field
      
      
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

   public void actionPerformed(ActionEvent e)
   {
      //when "I cannot play" button gets pressed increment the tally underscore
      //higher score is losing, kinda like golf

      String actionCommand = e.getActionCommand();
      if(actionCommand.equals("I cannot play")) //this is human button not cpu
      {
         Assignment5.scoreHum += 1;

         //Reassign Score Panel Labels
         myCardTable.pnlScore.removeAll();

         JLabel compScore = 
               new JLabel("CPU: "+Assignment5.scorePC, SwingConstants.CENTER);
         JLabel humScore = 
               new JLabel("Player: "+Assignment5.scoreHum+"", SwingConstants.CENTER);

         JLabel gameScore = new JLabel("SCORE");

         myCardTable.pnlScore.add(compScore);
         myCardTable.pnlScore.add(gameScore);
         myCardTable.pnlScore.add(humScore);

         myCardTable.setVisible(true);
         myCardTable.repaint();
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
         
         //test see if actionCommand for card is valid to play (one higher or lower than card in table)
         if(validPlay(highCardGame.getHand(player.One.ordinal()).inspectCard(cardIndex)))
         { //TODO fix so plays to appropirate pile not just player pile
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
         
         Assignment5.numTurns += 1;
         //End game when no more cards in deck
         if(highCardGame.getNumCardsRemainingInDeck() == 0)
         {
            EndGame();
         }
      }
   }

   //Method to see if card is one below or above card in pile for play
   private boolean validPlay(Card card)
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

   private void updateScore()
   {
      //Testing the card values

      int pcRank = Card.getRank(cardsInPlay[player.CPU.ordinal()]);
      int humRank = Card.getRank(cardsInPlay[player.One.ordinal()]);

      System.out.println("rankHumRank: "+humRank);

      System.out.println("rankPCrank: "+pcRank);
      //need to check out the card.getRank

      if(humRank > pcRank)
      {
         Assignment5.scoreHum += 1;
      }
      else if (humRank < pcRank)
      {
         Assignment5.scorePC += 1;
      }
      else 
      {
         //Do update to score
      }
      //Reassign Score Panel Labels
      myCardTable.pnlScore.removeAll();

      JLabel compScore = 
            new JLabel("CPU: "+Assignment5.scorePC, SwingConstants.CENTER);
      JLabel humScore = 
            new JLabel("Player: "+Assignment5.scoreHum+"", SwingConstants.CENTER);

      JLabel gameScore = new JLabel("SCORE");

      myCardTable.pnlScore.add(compScore);
      myCardTable.pnlScore.add(gameScore);
      myCardTable.pnlScore.add(humScore);

      myCardTable.setVisible(true);
      myCardTable.repaint();

   }


   private void pcTurn2(int cardIndex) {

      if(Assignment5.playNum == 0)
      {
         myCardTable.pnlPlayArea.add(computerFaces[cardIndex]);
         myCardTable.pnlPlayArea.remove(computerLabels[cardIndex]);
         Assignment5.oldIndex = cardIndex;
      }
      else if(Assignment5.playNum > 0)
      {
         myCardTable.pnlPlayArea.remove(computerFaces[Assignment5.oldIndex]);

         Assignment5.oldIndex = cardIndex;
         myCardTable.pnlPlayArea.add(computerFaces[cardIndex]);

         //computerFaces = array with card faces
         //computerLabels = back of card

      }
      cardsInPlay[player.CPU.ordinal()]=
            highCardGame.playCard(player.CPU.ordinal(), cardIndex); 

      System.out.println("Smith Playing: "
            +cardsInPlay[player.CPU.ordinal()].toString());
      Assignment5.playNum++;

      myCardTable.pnlComputerHand.remove(computerLabels[cardIndex]);

      myCardTable.setVisible(true);
      myCardTable.repaint();
   } 

   private void playerTurn(int cardIndex) {
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

   private void EndGame()
   {
      int WIDTH = 300;
      int HEIGHT = 200;

      JFrame endWindow = new JFrame();
      endWindow.setSize(WIDTH, HEIGHT);

      endWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      String messageText = "";
      if(Assignment5.scoreHum > Assignment5.scorePC)
      {
         messageText = "You win!";
      }
      else if(Assignment5.scoreHum < Assignment5.scorePC)
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

class EndListener implements ActionListener
{
   public void actionPerformed(ActionEvent e)
   {
      System.out.println("Closing Program. Goodbye!");
      System.exit(0);
   }
}




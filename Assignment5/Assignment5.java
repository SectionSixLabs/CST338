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
import java.util.*;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;


public class Assignment5
{
   static enum player {CPU, One}; 
   static int NUM_CARDS_PER_HAND = 7;
   static int  NUM_PLAYERS = 2;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JButton[] humanButtons = new JButton [NUM_CARDS_PER_HAND];
   static EndingListener[] humanListner = new EndingListener[NUM_CARDS_PER_HAND]; 
   static JLabel[] playedCardLabels  = new JLabel[NUM_PLAYERS]; 
   static JLabel[] playLabelText  = new JLabel[NUM_PLAYERS]; 
   static Card[] cardsInPlay = new Card[NUM_PLAYERS];
   static int playNum = 0;
   static int oldIndex = 0;
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
            playLabelText,cardsInPlay,NUM_PLAYERS, NUM_CARDS_PER_HAND ); 
      
      // ADDING Labels TO COMPUTER HAND
      for (int i = 0; i < myCardTable.getNumCardsPerHand(); i++)
      {
         Icon tempIcon = GuiCard.getBackCardIcon();
         JLabel temlLabel = new JLabel(); 
         temlLabel.setIcon(tempIcon); 
         computerLabels[i]=temlLabel;
      }
      
      // ADDING Labels TO HUMAN HAND
      for (int i = 0; i < myCardTable.getNumCardsPerHand(); i++)
      {
         Icon tempIcon = 
               GuiCard.getIcon(highCardGame.getHand(player.One.ordinal()).inspectCard(i+1));
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
      
      //  //TODO Means of selection card from Human Hand
      //Maybe have a button for each card to play
      //[Play Card1][Play Card2][Play Card3]..etc..
      
      //an example here, however we will need to add a new panel on the frame layout
      
      //Adding Buttons
      //Extra close Button (an example)
      
      //TODO discussion on what we want to implement with team 
            
      JLabel instruction = new JLabel("Play:", SwingConstants.CENTER);
      myCardTable.pnlButton.add(instruction);
      
      //Socre Panel
      JLabel compScore = new JLabel("0", SwingConstants.CENTER);
      JLabel gameScore = new JLabel("SCORE");
      JLabel humScore = new JLabel("0", SwingConstants.CENTER);
      
      myCardTable.pnlScore.add(compScore);
      myCardTable.pnlScore.add(gameScore);
      myCardTable.pnlScore.add(humScore);
      
      
      //TODO Create a Game Loop
      while (true) {
         
         break; 
      }
      
      
      //Testing Area
      System.out.println("Hi from end of Assingment5");
      System.out.println("How mnay cards are left in deck");
      int cardLeft = highCardGame.getNumCardsRemainingInDeck();
      System.out.println(cardLeft);
      
   }

   public static void hi()
   {
      System.out.println("hi from buttonpress");
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
   
   //Retrieve reference to game data
   //XXX Listner Init
   static void  initListener (CardGameFramework game, CardTable table, 
         JButton[] hB, JLabel[] hL, JLabel[] cL, JLabel[] pCL,JLabel[] pLtxt, 
         Card[] cIP, int nP, int nCPH) {
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
      
   }
   
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
         playerTurn( cardIndex);
         //pcTurn(); 
         pcTurn2(cardIndex); //matching card# human chooses
         
      }
   }
   
   private void pcTurn() {
      System.out.println("Before Sort: "+highCardGame.getHand(player.CPU.ordinal()).toString());
      highCardGame.getHand(player.CPU.ordinal()).sort(); 
      System.out.println("After Sort: "+highCardGame.getHand(player.CPU.ordinal()).toString());
      int playerCardRank =0;
      int tempCardRank = 0; 
      int cardIndex = 1; 
      for (int i =0; i<highCardGame.getHand(player.CPU.ordinal()).numCards(); 
            i++) {
          playerCardRank = Card.getRank(cardsInPlay[player.One.ordinal()]); 
          tempCardRank  = Card.getRank(highCardGame.getHand(player.CPU.ordinal()).
                     inspectCard(i));
         //TODO make into a function 
         if (playerCardRank<tempCardRank) {

            cardIndex = i; 
            break; 
         }
         
      }
      cardsInPlay[player.CPU.ordinal()]=
            highCardGame.playCard(player.CPU.ordinal(), cardIndex); 
      System.out.println("CPU Playing: "+cardsInPlay[player.CPU.ordinal()].toString());
      computerLabels[cardIndex].setBorder(null);
      computerLabels[cardIndex].setIcon(GuiCard.getIcon(cardsInPlay[player.CPU.ordinal()]));
      if (playedCardLabels[player.CPU.ordinal()]!=null) {
         myCardTable.pnlPlayArea.remove(playedCardLabels[player.CPU.ordinal()]);          
      }
      myCardTable.pnlPlayArea.add(computerLabels[cardIndex],1,1);
      playedCardLabels[player.CPU.ordinal()] = computerLabels[cardIndex]; 
      myCardTable.pnlComputerHand.remove(computerLabels[cardIndex]);
      myCardTable.setVisible(true);
      myCardTable.repaint();
      
   } 
   
   private void pcTurn2(int x) {
      /* Testing Cards in Computer Hands
      for(int x=0; x<NUM_CARDS_PER_HAND;x++)
      {
         System.out.println("PC card "+"x"+": "+highCardGame.getHand(0).inspectCard(x+1));
      } 
      
        Icon tempIcon = GuiCard.getIcon(highCardGame.getHand(0).inspectCard(x));
        computerLabels[x].setHorizontalAlignment(JLabel.CENTER);
        computerLabels[x].setBorder(null);
        computerLabels[x] = new JLabel(tempIcon);
        myCardTable.pnlPlayArea.add(computerLabels[x]);
        System.out.println("playNum: "+ Assignment5.playNum);
      */
      
      if(Assignment5.playNum == 0)
      {
         myCardTable.pnlPlayArea.add(computerLabels[x]);
         Assignment5.oldIndex = x;
      }
      else if(Assignment5.playNum > 0)
      {
         myCardTable.pnlPlayArea.remove(computerLabels[Assignment5.oldIndex]);
         Assignment5.oldIndex = x;
         myCardTable.pnlPlayArea.add(computerLabels[x]);
      }
      
      Assignment5.playNum++;
      
      myCardTable.pnlPlayArea.add(computerLabels[x]);
      myCardTable.setVisible(true);
      myCardTable.repaint();
   } 
   
   private void playerTurn(int cardIndex) {
      //human action first

      //System.out.println("Unexpected Error.");
      // making sure we have a card ref
      cardsInPlay[player.One.ordinal()]=highCardGame.playCard(player.One.ordinal(), cardIndex); 
      System.out.println("Playing: "+cardsInPlay[player.One.ordinal()].toString());

      
      //humanLabels[cardIndex].setHorizontalAlignment(JLabel.CENTER);
      humanLabels[cardIndex].setBorder(null);
      
      if (playedCardLabels[player.One.ordinal()]!=null) {
         myCardTable.pnlPlayArea.remove(playedCardLabels[player.One.ordinal()]);          
      }
      
      myCardTable.pnlPlayArea.add(humanLabels[cardIndex],0,1);
      playedCardLabels[player.One.ordinal()] = humanLabels[cardIndex]; 
      myCardTable.pnlHumanHand.remove(humanButtons[cardIndex]);
      myCardTable.setVisible(true);
      myCardTable.repaint();
   } 

}




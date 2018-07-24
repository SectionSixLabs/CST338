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
   static int NUM_CARDS_PER_HAND = 7;
   static int  NUM_PLAYERS = 2;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JButton[] humanButtons = new JButton [NUM_CARDS_PER_HAND];
   static EndingListener[] humanListner = new EndingListener[NUM_CARDS_PER_HAND]; 
   static JLabel[] playedCardLabels  = new JLabel[NUM_PLAYERS]; 
   static JLabel[] playLabelText  = new JLabel[NUM_PLAYERS]; 


   // a simple main to throw all the JLabels out there for the world to see
   public static void main(String[] args)
   {
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
            playLabelText,NUM_PLAYERS, NUM_CARDS_PER_HAND ); 
      
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
               GuiCard.getIcon(highCardGame.getHand(1).inspectCard(i+1));
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
   static int NUM_CARDS_PER_HAND;
   static int  NUM_PLAYERS;
   static CardGameFramework highCardGame;
   static CardTable myCardTable; 
   static JButton[] humanButtons; 
   static JLabel[] humanLabels;
   static JLabel[] computerLabels;
   static JLabel[] playedCardLabels; 
   static JLabel[] playLabelText; 
   
   //Retrieve reference to game data
   //XXX Listner Init
   static void  initListener (CardGameFramework game, CardTable table, 
         JButton[] hB, JLabel[] hL, JLabel[] cL, JLabel[] pCL,JLabel[] pLtxt, 
         int nP, int nCPH) {
      highCardGame = game; 
      myCardTable = table; 
      humanButtons = hB; 
      humanLabels = hL; 
      computerLabels = cL; 
      playedCardLabels = pCL;
      playLabelText = pLtxt;
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
         //System.out.println("Unexpected Error.");
         System.out.println(actionCommand.toString());
         System.out.println("Playing: "+highCardGame.playCard(1, cardIndex).toString());
         humanLabels[cardIndex].setHorizontalAlignment(JLabel.CENTER);
         humanLabels[cardIndex].setBorder(null);
         if (playedCardLabels[1]!=null) {
            myCardTable.pnlPlayArea.remove(playedCardLabels[1]);          
         }
         
         myCardTable.pnlPlayArea.add(humanLabels[cardIndex]);
         playedCardLabels[1] = humanLabels[cardIndex]; 
         myCardTable.pnlHumanHand.remove(humanButtons[cardIndex]);
         myCardTable.setVisible(true);
         myCardTable.repaint();

      }
   }
   
   private void pcTurn() {
      
   } 

}




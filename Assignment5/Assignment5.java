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
         temlLabel.setIcon(tempIcon);
         humanLabels[i]=temlLabel;
      }

        // ADD LABELS TO PANELS -----------------------------------------
        //TODO code goes here ...
      for (int i = 0; i < myCardTable.getNumCardsPerHand(); i++)
      {
         myCardTable.pnlComputerHand.add(computerLabels[i]);
         myCardTable.pnlHumanHand.add(humanLabels[i]);
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
      
      JButton c1Button = new JButton("Card1");
      EndingListener c1Ear = new EndingListener();
      c1Button.addActionListener(c1Ear);
      myCardTable.pnlButton.add(c1Button);
      
      JButton c2Button = new JButton("Card2");
      EndingListener c2Ear = new EndingListener();
      c2Button.addActionListener(c2Ear);
      myCardTable.pnlButton.add(c2Button);
      
      JButton c3Button = new JButton("Card3");
      EndingListener c3Ear = new EndingListener();
      c3Button.addActionListener(c3Ear);
      myCardTable.pnlButton.add(c3Button);
      
      JButton c4Button = new JButton("Card4");
      EndingListener c4Ear = new EndingListener();
      c4Button.addActionListener(c4Ear);
      myCardTable.pnlButton.add(c4Button);
      
      JButton c5Button = new JButton("Card5");
      EndingListener c5Ear = new EndingListener();
      c5Button.addActionListener(c5Ear);
      myCardTable.pnlButton.add(c5Button);
      
      JButton c6Button = new JButton("Card6");
      EndingListener c6Ear = new EndingListener();
      c6Button.addActionListener(c6Ear);
      myCardTable.pnlButton.add(c6Button);
      
      JButton c7Button = new JButton("Card7");
      EndingListener c7Ear = new EndingListener();
      c7Button.addActionListener(c7Ear);
      myCardTable.pnlButton.add(c7Button);
      
      JButton endButton = new JButton("Exit");
      EndingListener buttonEar = new EndingListener();
      endButton.addActionListener(buttonEar);
      myCardTable.pnlButton.add(endButton);
      
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
   public void actionPerformed(ActionEvent e)
   {
      String actionCommand = e.getActionCommand();
      if(actionCommand.equals("Exit"))
      {
         System.out.println("Closing Program. Goodbye!");
         System.exit(0);
      }
      else if(actionCommand.equals("Card1"))
      {
         Assignment5.hi();
      }
      else if(actionCommand.equals("Card2"))
      {
         
      }
      else if(actionCommand.equals("Card3"))
      {
         
      }
      else if(actionCommand.equals("Card4"))
      {
         
      }
      else if(actionCommand.equals("Card5"))
      {
         
      }
      else if(actionCommand.equals("Card6"))
      {
         
      }
      else if(actionCommand.equals("Card7"))
      {
         
      }
      else
      {
         System.out.println("Unexpected Error.");
      }
   }
}




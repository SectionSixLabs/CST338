
import java.awt.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;
public class Phase2
{
   static int NUM_CARDS_PER_HAND = 7;
   static int  NUM_PLAYERS = 2;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];  
   static JLabel[] playedCardLabels  = new JLabel[NUM_PLAYERS]; 
   static JLabel[] playLabelText  = new JLabel[NUM_PLAYERS]; 
   
   public static void main(String[] args)
   {
      int k;
//      Icon tempIcon;
      GuiCard gui = new  GuiCard();
      GuiCard.loadCardIcons();
      
      Deck deck = new Deck(); 

      
      
      // establish main frame in which program will run
      CardTable myCardTable 
         = new CardTable("CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
      myCardTable.setSize(800, 600);
      myCardTable.setLocationRelativeTo(null);
      myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // show everything to the user
      myCardTable.setVisible(true);

      // CREATE LABELS ----------------------------------------------------
      //TODO code goes here ...

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
       
       Icon tempIcon = GuiCard.getIcon(getRandomCard(deck));
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
      // and two random cards in the play region (simulating a computer/hum ply)
      myCardTable.setVisible(true);
      //TODO code goes here ...
 
      Icon tempIcon1 = GuiCard.getIcon(getRandomCard(deck));
      Icon tempIcon2 = GuiCard.getIcon(getRandomCard(deck));
      JLabel temlLabel1 = new JLabel(); 
      JLabel temlLabel2 = new JLabel(); 
      temlLabel1.setHorizontalAlignment(JLabel.CENTER);
      temlLabel2.setHorizontalAlignment(JLabel.CENTER);
      temlLabel1.setIcon(tempIcon1);
      temlLabel2.setIcon(tempIcon2);
      
      playedCardLabels[0] = temlLabel1; 
      playedCardLabels[1] = temlLabel2; 
      
      myCardTable.pnlPlayArea.add(playedCardLabels[0]);
      myCardTable.pnlPlayArea.add(playedCardLabels[1]);
      
      // show everything to the user
      myCardTable.setVisible(true);
   }
   
   private static Card getRandomCard(Deck deck) {
      
      Card card = new Card();
      Random rgen = new Random();
      int randomIndex = 0; 
      randomIndex=rgen.nextInt(deck.topCard());
      card = deck.inspectCard(randomIndex); 
      deck.removeCard(card); 
      return card;
      
   }
}
/**
 * GUI Cards
 * CST338
 * Module5
 * @author Sergiy Zarubin, Lacey Sikes, Rocky Moreno, Danny Tran
 *
 */

//TODO Please submit one file for each phase (3 .txt files, UML .pdf file)

//TODO Checklist
//Phase 1 Done. (Comment out tests when submitting.)
//TODO Phase 2
//TODO Phase 3
//TODO Phase 4

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CardTable extends JFrame
{
   static int MAX_CARDS_PER_HAND = 56;
   static int MAX_PLAYERS = 2;  // for now, we only allow 2 person games
   
   private int numCardsPerHand;
   private int numPlayers;

   public JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea;
   
   public CardTable(String title, int numCardsPerHand, int numPlayers)
   {
      //Constructor filters input, adds any panels to the JFrame
      //Also establishes layouts according to the general description following:
      
      //We will use three Public Jpanels: one for each hand (player-bottom
      //and computer-top) and a middle "playing" JPanel
      
      //The client (below) will generate the human's cards at random and will 
      //be visible in the bottom JPanel, while the computer's cards will be
      //chosen (again, by the client) to be all back-of-card images in the top
      //JPanel.  The middle JPanel will display cards that are "played" by the
      //computer and human during the conflict.  Let's assume that each player 
      //plays one card per round, so for a 2-person game (computer+human) there 
      //will be exactly two cards played in the central region per round of
      //battle. My client chose a joker for the two central cards, just so we 
      //have something to see in the playing region.
      
      //TODO
   }
   
   //Accessors for the two instance members
  public int getNumCardsPerHand()
  {
     return this.numCardsPerHand;
  }
  
  public int getNumPlayers()
  {
     return this.numPlayers;
  }
}

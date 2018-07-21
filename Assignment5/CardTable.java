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
import javax.swing.JLabel;

import java.awt.event.AdjustmentListener;
import java.awt.event.ActionEvent;

import java.awt.Color;
import java.awt.FlowLayout; 
import java.awt.BorderLayout;

public class CardTable extends JFrame
{
   static int MAX_CARDS_PER_HAND = 56;
   static int MAX_PLAYERS = 2;  // for now, we only allow 2 person games
   
   private int numCardsPerHand;
   private int numPlayers;

   public JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea;
   
   //Will we need theses?
   public static final int WIDTH = 1150; 
   public static final int HEIGHT = 650;
   
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
      
      
      
      //TODO Finish CardTable. Run to test the GUI. 

      super();
      
      setSize(WIDTH, HEIGHT);
      setTitle("Card Table");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      
      //Color panes? getContentPane().setBackground(Color.PINK);
      
      //Layout Manager? setLayout(new BorderLayout()); 
      setLayout(new BorderLayout());
      
      //Labeling the 3 public Jpanels
      
      //Computer Label Top 
      JLabel topLabel = new JLabel("Computer Hand");
      add(topLabel, BorderLayout.NORTH);
      
      //Cards Played Middle 
      JLabel middleLabel = new JLabel("Play Area");
      add(middleLabel, BorderLayout.CENTER);
      
      //Player Label Bottom
      JLabel bottomLabel = new JLabel("Player Hand");
      add(bottomLabel, BorderLayout.SOUTH);
      
      this.numCardsPerHand = numCardsPerHand;
      this.numPlayers = numPlayers;
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
  
  //Testing CardTable comment / remove after
  //Should not have a main here. Only for testing CardTable. 
  public static void main(String[] args)
  {
     CardTable gui = new CardTable("TestGui", 5, 2);
     gui.setVisible(true);
  }
  
}

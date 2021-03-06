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
import javax.swing.JLabel;

import java.awt.GridLayout;

import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;



//TODO add the Clock Timer which extends Thread

public class CardTable extends JFrame
{
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   static int MAX_CARDS_PER_HAND = 56;
   static int MAX_PLAYERS = 2;  // for now, we only allow 2 person games
   
   private int numCardsPerHand;
   private int numPlayers;

   public JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea, pnlButton,pnlScore;
   
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
      
      super(title);
      this.numCardsPerHand = numCardsPerHand;
      this.numPlayers = numPlayers;
      
      setSize(800, 500);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBackground(Color.GREEN);
      setLayout(new BorderLayout());
      
      
      // COMPUTER HAND  
      TitledBorder computerHandBorder = new TitledBorder("Computer Hand");
      computerHandBorder.setTitleJustification(TitledBorder.LEFT);
      computerHandBorder.setTitlePosition(TitledBorder.TOP);

      pnlComputerHand = new JPanel();
      pnlComputerHand.setBorder(computerHandBorder);
      pnlComputerHand.setLayout(new FlowLayout());
      add(pnlComputerHand,BorderLayout.NORTH);
    
      TitledBorder playAreaBorder = new TitledBorder("Play Area");
      playAreaBorder.setTitleJustification(TitledBorder.LEFT);
      playAreaBorder.setTitlePosition(TitledBorder.TOP);
      
      pnlPlayArea = new JPanel();
      pnlPlayArea.setBorder(playAreaBorder);
      pnlPlayArea.setLayout(new GridLayout(2,2));
      add(pnlPlayArea,BorderLayout.CENTER);
  
      JLabel computerPlayArea = new JLabel("Computer", JLabel.CENTER);
      pnlPlayArea.add(computerPlayArea);
      
      JLabel youPlayArea = new JLabel("You", JLabel.CENTER);
      pnlPlayArea.add(youPlayArea);

      // HUMAN HAND
      TitledBorder humanHandBorder = new TitledBorder("Your Hand");
      humanHandBorder.setTitleJustification(TitledBorder.LEFT);
      humanHandBorder.setTitlePosition(TitledBorder.TOP);
      
      pnlHumanHand = new JPanel();
      pnlHumanHand.setBorder(humanHandBorder);
      pnlHumanHand.setLayout(new FlowLayout());
      add(pnlHumanHand,BorderLayout.SOUTH);
   }
   

   public CardTable(String title, int numCardsPerHand, int numPlayers, 
                    boolean x)
   {
      super(title);
      this.numCardsPerHand = numCardsPerHand;
      this.numPlayers = numPlayers;
      
      setSize(1000, 800);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBackground(Color.GREEN);
      setLayout(new BorderLayout());
      
      
      // COMPUTER HAND  
      TitledBorder computerHandBorder = new TitledBorder("Computer Hand");
      computerHandBorder.setTitleJustification(TitledBorder.LEFT);
      computerHandBorder.setTitlePosition(TitledBorder.TOP);
      
      
      pnlComputerHand = new JPanel();
      pnlComputerHand.setBorder(computerHandBorder);
      pnlComputerHand.setLayout(new FlowLayout());
      add(pnlComputerHand,BorderLayout.NORTH);
      
      TitledBorder playAreaBorder = new TitledBorder("Play Area");
      playAreaBorder.setTitleJustification(TitledBorder.LEFT);
      playAreaBorder.setTitlePosition(TitledBorder.TOP);
      
      pnlPlayArea = new JPanel();
      pnlPlayArea.setBorder(playAreaBorder);
      pnlPlayArea.setLayout(new GridLayout(2,2));
      add(pnlPlayArea,BorderLayout.CENTER);

      JLabel youPlayArea = new JLabel("You", JLabel.CENTER);
      pnlPlayArea.add(youPlayArea);
           
      JLabel computerPlayArea = new JLabel("Computer", JLabel.CENTER);
      pnlPlayArea.add(computerPlayArea);
      
     
      
      // HUMAN HAND
      TitledBorder humanHandBorder = new TitledBorder("Your Hand");
      humanHandBorder.setTitleJustification(TitledBorder.LEFT);
      humanHandBorder.setTitlePosition(TitledBorder.TOP);
      
      pnlHumanHand = new JPanel();
      pnlHumanHand.setBorder(humanHandBorder);
      pnlHumanHand.setLayout(new FlowLayout());
      add(pnlHumanHand,BorderLayout.SOUTH);

      //BUTTON Panel
      pnlButton = new JPanel();
      pnlButton.setLayout(new GridLayout(9,1));
      add(pnlButton,BorderLayout.EAST);
      
      //Score Panel
      pnlScore = new JPanel();
      pnlScore.setLayout(new GridLayout(3, 1));
      add(pnlScore,BorderLayout.WEST);
   
   } //End of 2nd Constructor

  public int getNumCardsPerHand()
  {
     return this.numCardsPerHand;
  }
  
  public int getNumPlayers()
  {
     return this.numPlayers;
  }
  
  
  public static void main(String[] args)
  {
     CardTable myCardTable= new CardTable("CardTable", 5, 2);
     myCardTable.setSize(800, 600);
     myCardTable.setLocationRelativeTo(null);
     myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     myCardTable.setVisible(true);
     
  }
}


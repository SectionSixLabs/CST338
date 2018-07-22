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
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

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
      
      super(title);
      this.numCardsPerHand = numCardsPerHand;
      this.numPlayers = numPlayers;
      
      String myDir = System.getProperty("user.dir");
      
      
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
      
      // ADDING CARDS TO COMPUTER HAND
      int i;
      for (i = 0; i <= numCardsPerHand; i++)
      {
         JLabel computerCardsLabel = new JLabel();
         ImageIcon computerCardsIcon = new ImageIcon(myDir+"/Assignment5/images/BK.gif");
         computerCardsLabel.setIcon(computerCardsIcon);
         pnlComputerHand.add(computerCardsLabel);  
      }
      
      
      
      // Play Area
      
      /*But we also need to some text below each of the two center icons 
       * to we know who played which card ( "Computer" or "You", so, we'll 
       * really need four labels in this central play JPanel : two for card images
       *  and two for text "Computer" and "You". Since we want the text 
       *  directly below the icon, one way to do this is to make your 
       *  central playing panel a 2x2 Grid Layout, where the top two positions
       *   will be images and the bottom two will be text that describe the 
       *   images. Hint: to center text in a label, use

            myLabel = new JLabel( "My Text", JLabel.CENTER );
      */
      
      
      TitledBorder playAreaBorder = new TitledBorder("Play Area");
      playAreaBorder.setTitleJustification(TitledBorder.LEFT);
      playAreaBorder.setTitlePosition(TitledBorder.TOP);
      
      JPanel playArea = new JPanel();
      playArea.setBorder(playAreaBorder);
      playArea.setLayout(new GridLayout(2,2));
      add(playArea,BorderLayout.CENTER);
      
      JLabel playedCardLabel1, playedCardLabel2;
      
      playedCardLabel1 = new JLabel();
      playedCardLabel1.setHorizontalAlignment(JLabel.CENTER);
      ImageIcon playedCard1Icon = new ImageIcon(myDir+"/Assignment5/images/3D.gif");
      playedCardLabel1.setIcon(playedCard1Icon);
      playArea.add(playedCardLabel1);
      
      playedCardLabel2 = new JLabel();
      playedCardLabel2.setHorizontalAlignment(JLabel.CENTER);
      ImageIcon playedCard2Icon = new ImageIcon(myDir+"/Assignment5/images/4H.gif");
      playedCardLabel2.setIcon(playedCard2Icon);
      playArea.add(playedCardLabel2);
      
      
      JLabel computerPlayArea = new JLabel("Computer", JLabel.CENTER);
      playArea.add(computerPlayArea);
      
      JLabel youPlayArea = new JLabel("You", JLabel.CENTER);
      playArea.add(youPlayArea);
      
      
      
      // HUMAN HAND
      TitledBorder humanHandBorder = new TitledBorder("Your Hand");
      humanHandBorder.setTitleJustification(TitledBorder.LEFT);
      humanHandBorder.setTitlePosition(TitledBorder.TOP);
      
      pnlHumanHand = new JPanel();
      pnlHumanHand.setBorder(humanHandBorder);
      pnlHumanHand.setLayout(new FlowLayout());
      add(pnlHumanHand,BorderLayout.SOUTH);
      
      // ADDING CARDS TO HUMAN HAND
      for (i = 0; i <= numCardsPerHand; i++)
      {
         JLabel humanCardsLabel = new JLabel();
         ImageIcon humanCardsIcon = new ImageIcon(myDir+"/Assignment5/images/BK.gif");
         humanCardsLabel.setIcon(humanCardsIcon);
         pnlHumanHand.add(humanCardsLabel);  
      }
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
  
  
  public static void main(String[] args)
  {
     CardTable myCardTable= new CardTable("CardTable", 5, 2);
     myCardTable.setSize(800, 600);
     myCardTable.setLocationRelativeTo(null);
     myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     myCardTable.setVisible(true);
  }
}

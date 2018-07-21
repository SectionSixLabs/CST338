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

//Phase1
import javax.swing.*;
import java.awt.*;

import java.io.File;

public class Assignment5
{


   //Phase1
   static final int NUM_CARD_IMAGES = 57; // 52 + 4 jokers + 1 back-of-card image
   static Icon[] icon = new ImageIcon[NUM_CARD_IMAGES];

   static void loadCardIcons()
   {
      // build the file names ("AC.gif", "2C.gif", "3C.gif", "TC.gif", etc.)
      // in a SHORT loop.  For each file name, read it in and use it to
      // instantiate each of the 57 Icons in the icon[] array.

      //Get your current working directory (generic)
      String myDir = System.getProperty("user.dir");
      File path = new File(myDir+"/Assignment5/images");
      File[] files = path.listFiles();
      
      if(files.length == NUM_CARD_IMAGES)
      {
         System.out.println("There are 57 cards.");
         
         for (int i = 0; i < files.length; i++)
         {
            if (files[i].isFile())
            { 
               System.out.println(files[i]); //TODO Comment this out when finalizing
               icon[i] = new ImageIcon(files[i].toString());
            }
         }
      }
      else
      {
         System.out.println("Problem! There is not 57 card files in folder.");
      }
   }

   // turns 0 - 13 into "A", "2", "3", ... "Q", "K", "X"
   static String turnIntIntoCardValue(int k)
   {
      //FIXME Look at the Cards class, what cards do we have there? 
      // an idea for a helper method (do it differently if you wish) 
      String cardNum = "A23456789TJQKX"; //Note there is Joker I guess as X
      String myCardValue = cardNum.substring(k, k+1);
      
      return myCardValue;
   }

   // turns 0 - 3 into "C", "D", "H", "S"
   static String turnIntIntoCardSuit(int j)
   {
      //XXX You can use Enum for this
      // an idea for another helper method (do it differently if you wish)
      String cardSuit = "CDHS"; // Clubs Diamonds Hearts Spades 
      String myCardSuit = cardSuit.substring(j, j+1);
      
      return myCardSuit;
   }

   // a simple main to throw all the JLabels out there for the world to see
   public static void main(String[] args)
   {
      int k;

      // prepare the image icon array
      loadCardIcons();

      // establish main frame in which program will run
      JFrame frmMyWindow = new JFrame("Card Room");
      frmMyWindow.setSize(1150, 650);
      frmMyWindow.setLocationRelativeTo(null);
      frmMyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // set up layout which will control placement of buttons, etc.
      FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 5, 20);   
      frmMyWindow.setLayout(layout);

      // prepare the image label array
      JLabel[] labels = new JLabel[NUM_CARD_IMAGES];
      for (k = 0; k < NUM_CARD_IMAGES; k++)
         labels[k] = new JLabel(icon[k]);

      // place your 3 controls into frame
      for (k = 0; k < NUM_CARD_IMAGES; k++)
         frmMyWindow.add(labels[k]);

      // show everything to the user
      frmMyWindow.setVisible(true);

      //Testing methods Area
      
      //A23456789TJQKX
      //Testing method: turnIntIntoCardValue
      System.out.println("Testing method: turnIntIntoCardValue");
      for(int i=0; i<14; i++)
      {
         System.out.println("Int "+i+" is " +turnIntIntoCardValue(i));
      }
      
      //Testing method: turnIntIntoCardSuit
      System.out.println("Testing method: turnIntoIntoCardSuit");
      for(int i=0; i<4; i++)
      {
         System.out.println("Int "+i+" is " +turnIntIntoCardSuit(i));
      }
      
      //End of main
      System.out.println("End of main.");
   }

}

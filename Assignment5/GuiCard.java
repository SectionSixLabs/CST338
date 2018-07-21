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

import javax.swing.*;

//import Card.Suit;

import java.awt.*;
import java.io.File;

public class GuiCard
{
   //TODO Implement Module 3 Class with modification for this Class
   
   private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K + joker
   private static Icon iconBack;
   static boolean iconsLoaded = false;
   
   static void loadCardIcons()
   {
      //Hint:  Call this method any time you might need an Icon, but make 
      //sure that it loads the entire array the first time it is called, 
      //and does nothing any later time.
      
   }
   
   static public Icon getIcon(Card card)
   {
      
   }
   
   static public Icon getBackCardIcon()
   {
      
   }
   

}



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
//Confused on whats in default package? Is Cards class in it since we don't need to import Card


import java.awt.*;
import java.io.File;
import java.util.Arrays;

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
      
      if(!iconsLoaded) //if not iconsLoaded
      {
         //Then load icons
         String myDir = System.getProperty("user.dir");
         String path = myDir+"/Assignment5/images/";
         
         String cardValues[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9",
                                 "T", "J", "Q", "K", "X"};
         String cardSuites[] = {"C", "D", "H", "S"};
         
         for(int suit=0; suit<4; suit++)
         {
            for(int value=0; value<14; value++)
            {
               String file = path+cardValues[value]+cardSuites[suit]+".gif";
               System.out.println(file); //test filepath
               iconCards[value][suit] = new ImageIcon(file);
            }
         }
         //Loading the face down card separately
         String backOfCard = path+"BK.gif";
         System.out.println(backOfCard);
        iconBack = new ImageIcon(backOfCard);
      }
      iconsLoaded = true; //After icons loaded true, don't need to load again
      
   }
   
   static public Icon getIcon(Card card)
   {  
      if(iconsLoaded)
      {
         //Keeping consistency same as loadCardIcons maybe to make this public to class later
         String cardValues[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9",
               "T", "J", "Q", "K", "X"};
         String cardSuites[] = {"C", "D", "H", "S"};

         //Remember Card has a char for Value and enum for Suit

         //get card attributes value and suit to find their index in Array

         String suitString;
         String valueString;

         switch(card.getSuit())
         {
         case CLUBS:
            suitString = "C";
            break;
         case HEARTS:
            suitString = "H";
            break;
         case DIAMONDS:
            suitString = "D";
            break;
         case SPADES:
            suitString = "S";
            break;
         default:
            suitString = "Error";
            break;

         }

         valueString = String.valueOf(card.getValue());

         //Testing ~comment out
         System.out.println(valueString);
         System.out.println(suitString);

         //Get index based of suit and value of card
         int intValueIndex = Arrays.asList(cardValues).indexOf(valueString);
         int intSuitIndex = Arrays.asList(cardSuites).indexOf(suitString);

         return iconCards[intValueIndex][intSuitIndex];
      }
      else
      {
         return null;
      }

   }
   
   static public Icon getBackCardIcon()
   {
      if(iconsLoaded)
      {
         return iconBack;
      }
      else
      {
         return null;
      }
      
   }
   //main as testing only ~remove after
   public static void main(String[] args)
   {
      loadCardIcons();
      Card myCard = new Card();
      getIcon(myCard);
   }
   

}



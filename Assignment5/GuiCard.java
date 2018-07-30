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
         //Lets keep it consistent with Card Class
         String cardSuites[] = {"H", "C", "S", "D"};
         
         for(int suit=0; suit<4; suit++)
         {
            for(int value=0; value<14; value++)
            {
               String file = path+cardValues[value]+cardSuites[suit]+".gif";
//               System.out.println(file); //test filepath
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
      if(iconsLoaded && !card.getErrorFlag())
      {

         //Get index based of suit and value of card
         int intValueIndex = Card.cardValues.lastIndexOf(card.getValue());
         int intSuitIndex = card.getSuit().ordinal();

//         //Testing ~comment out
//         System.out.println("Using Class: "+card.getValue()+" Index: "+intValueIndex); 
//         System.out.println("Using Class: "+card.getSuit()+" Index: "+intSuitIndex); 
//
//         JFrame myJframe = new JFrame("test window");
//         myJframe.setSize(400, 500);
//         myJframe.setVisible(true);
//         myJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         JLabel CardLabel = new JLabel();
//         CardLabel.setIcon(iconCards[intValueIndex][intSuitIndex]);
//         myJframe.add(CardLabel);

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
     // Card myCard = new Card('4',Card.Suit.HEARTS);
     // getIcon(myCard);
      
      //Test for back of card icon
      JFrame myJframe = new JFrame("test window");
      myJframe.setSize(400, 500);
      myJframe.setVisible(true);
      myJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      JLabel backCardLabel = new JLabel();
      //backCardLabel.setIcon(getBackCardIcon());
            
      myJframe.add(backCardLabel);
   }
   

}






/**
 * Optical Barcode Readers and Writers
 * CST338
 * Module4
 * @author Sergiy Zarubin, Lacey Sikes, Rocky Moreno, Danny Tran
 *
 */

//TODO NOTE! Even though you learned to do multi-file projects, 
//TODO continue to submit only one file with a full, working program and run. 

public class Assignment4
{

   public static void main(String[] args)
   {
      // TODO Auto-generated method stub
      
      System.out.print("End of main.");
   }

}


//TODO class BarcodeImage

class BarcodeImage implements Cloneable
{
   public static final int MAX_HEIGHT = 30;    
   public static final int MAX_WIDTH = 65;
   private boolean[][] image_data;
   
   //TODO class Methods
}


//TODO class DataMatrix
class DataMatrix implements BarcodeIO
{
   private String text;
   
   public boolean scan(BarcodeImage bc)
   {
      
   }
   
   public boolean readText(String text)
   {
      if(text != null)
      {
         this.text = text;
         return true;
      }
      return false;
   }
   
   public boolean generateImageFromText()
   {
      
   }
   
   public boolean translateImageToText()
   {
      
   }
   
   public void displayTextToConsole()
   {
      
   }
   
   public void displayImageToConsole()
   {
      
   }
}
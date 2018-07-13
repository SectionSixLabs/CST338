/**
 * Optical Barcode Readers and Writers
 * CST338
 * Module4
 * @author Sergiy Zarubin, Lacey Sikes, Rocky Moreno, Danny Tran
 *
 */

//TODO NOTE! Even though you learned to do multi-file projects, 
//TODO continue to submit only one file with a full, working program and run. 
//TODO Code Documentation

public class Assignment4
{

   public static void main(String[] args)
   {
      // TODO Auto-generated method stub
      
      
      
      
      String[] sImageIn_2 =
         {
               "                                          ",
               "                                          ",
               "* * * * * * * * * * * * * * * * * * *     ",
               "*                                    *    ",
               "**** *** **   ***** ****   *********      ",
               "* ************ ************ **********    ",
               "** *      *    *  * * *         * *       ",
               "***   *  *           * **    *      **    ",
               "* ** * *  *   * * * **  *   ***   ***     ",
               "* *           **    *****  *   **   **    ",
               "****  *  * *  * **  ** *   ** *  * *      ",
               "**************************************    ",
               "                                          ",
               "                                          ",
               "                                          ",
               "                                          "

         };
        
         BarcodeImage bc = new BarcodeImage(sImageIn_2);
      
      
      
      
      
      
      
      
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
   
   /*
    * Default Constructor
    */
   public BarcodeImage()
   {
      image_data = new boolean[MAX_HEIGHT][MAX_WIDTH];
      
      int row, column;
      for(row=0; row<image_data.length; row++)
      {
         for(column=0; column<image_data[row].length; column++)
         {
            image_data[row][column]=false;
         }
      }
   }
   
   /*
    * Constructor -takes a 1D array of Strings and converts it to 
    * the internal 2D array of booleans. 
    */
   public BarcodeImage(String[] str_data)
   {
      //TODO - see HINT
      /*
      String[] sImageIn_2 =
         {
               "                                          ",
               "                                          ",
               "* * * * * * * * * * * * * * * * * * *     ",
               "*                                    *    ",
               "**** *** **   ***** ****   *********      ",
               "* ************ ************ **********    ",
               "** *      *    *  * * *         * *       ",
               "***   *  *           * **    *      **    ",
               "* ** * *  *   * * * **  *   ***   ***     ",
               "* *           **    *****  *   **   **    ",
               "****  *  * *  * **  ** *   ** *  * *      ",
               "**************************************    ",
               "                                          ",
               "                                          ",
               "                                          ",
               "                                          "

         };
      */
      
      
      image_data = new boolean[MAX_HEIGHT][MAX_WIDTH];
            
      int row;
      int column;
      //int charIndex;
      
      
      for(row=0; row<image_data.length; row++)
      {
         for(column=0; column<image_data[row].length; column++)
         {
            image_data[row][column] = false;
         }
      }
      
      
      
      
      //int str_dataLength = 0;
      
      for(row=0; row<str_data.length; row++)
      {
         for(column=0; column<str_data[row].length(); column++)
         {
            if(str_data[row].length() < MAX_WIDTH )
            {
               if (str_data[row].charAt(column) == ' ')
               {
                  image_data[row][column]=false; 
               }
               else if(str_data[row].charAt(column) == '*')
               {
                  image_data[row][column] = true;
               }              
            }
            
            // TEST
            //System.out.println( "cell" + "[" + row + "]" + "[" + column + "]" + " " + image_data[row][column]);
         }
      }
      
      
      String booleanPrint = "";
      String setIndex = "";
      // TEST PRINT OUT
      for(row=0; row<image_data.length; row++)
      {
         for(column=0; column<image_data[row].length; column++)
         {
            if (image_data[row][column] == false)
            {
               setIndex = " ";
            }
            else
            {
               setIndex = "*";
            }
            booleanPrint += setIndex;
            
         }
         
         System.out.println(booleanPrint);
         booleanPrint = "";
         System.out.println();
      }
      
      
      
   }
   
   //Acessor / mutators
   
   boolean getPixel(int row, int col)
   {  //checking if parameter doesn't exceed max.
      if((row > MAX_HEIGHT || row < 0) || (col > MAX_WIDTH || col < 0))
      {
         return false;
      }
      else
      {
         return image_data[row][col];
      }
   }
   
   boolean setPixel(int row, int col, boolean value)
   {
      if((row > MAX_HEIGHT || row < 0) || (col > MAX_WIDTH || col < 0))
      {
         return false;
      }
      else
      {
         image_data[row][col] = value;
         return true;
      }
   }
   
   public BarcodeImage clone()
   {
      //TODO can implement with: return new BarcodeImage(this) but would need copy constructor
   }
   
   //TODO Optional Methods but good for utility / debugging
   //checkSize(String[] data)
   //displayToConsole()
   
}


//TODO class DataMatrix
class DataMatrix implements BarcodeIO
{
   //Data
   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ' ';
   
   /*
    * private Barcode image -
    * a single internal copy of any image scanned-in OR passed-into 
    * the constructor OR created by BarcodeIO's generateImageFromText()
    */
   private BarcodeImage image;
   
   /*
    * private String text - 
    * a single internal copy of any text read-in OR passed-into 
    * the constructor OR created by BarcodeIO's translateImageToText()
    */
   private String text;
   
   /*
    * these ints that are typically less than BarcodeImage.MAX_WIDTH 
    * and BarcodeImage.MAX_HEIGHT which represent the actual portion of
    * the BarcodeImage that has the real signal
    */
   private int actualWidth;
   private int actualHeight;
   
   //Methods
   /*
    * Default constructor
    */
   
   public DataMatrix()
   {
      
      
      
   }
   
   public DataMatrix(BarcodeImage image)
   {
      
   }
   
   
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
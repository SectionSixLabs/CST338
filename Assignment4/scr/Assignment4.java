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
   //TODO BarcodeImage()
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
   //TODO BarcodeImage(String[] str_data)
  
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
      
      // Create and initialize 2D array
      image_data = new boolean[MAX_HEIGHT][MAX_WIDTH];
            
      int row;
      int column;
      
      for(row=0; row<image_data.length; row++)
      {
         for(column=0; column<image_data[row].length; column++)
         {
            image_data[row][column] = false;
         }
      }
      
      
      
      // Copy string parameter into 2D array
      int image_dataRow = MAX_HEIGHT - str_data.length; 
      
      for(row=0; row<str_data.length; row++)
      {
         for(column=0; column<str_data[row].length(); column++)
         {
            if(str_data[row].length() < MAX_WIDTH )
            {
               if (str_data[row].charAt(column) == ' ')
               {
                  image_data[image_dataRow][column]=false; 
               }
               else if(str_data[row].charAt(column) == '*')
               {
                  image_data[image_dataRow][column] = true;
               } 
            }
            
            // TEST gives cell number and value
            //System.out.println( "cell" + "[" + row + "]" + "[" + column + "]" + " " + image_data[row][column]);
         }
         image_dataRow++;
      }
      
      //TEST PRINT OUT 2D ARRAY
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
            booleanPrint += "[" + setIndex + "]";  
            
         }         
         System.out.println(booleanPrint);
         booleanPrint = "";
      }
   }
   
   //Acessor / mutators
   
   //TODO getPixel(int row, int col)
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
   
   //TODO setPixel(int row, int col, boolean value)
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
   
   /*
    * Copy constructor
    */
   //TODO BarcodeImage(BarcodeImage other)
   public BarcodeImage(BarcodeImage other)
   {
     
      this.image_data = new boolean[other.image_data.length][other.image_data[0].length];
      
      int row, column;
      for(row=0; row<this.image_data.length; row++)
      {
         for(column=0; column<other.image_data[0].length; column++)
         {
            this.image_data[row][column]= other.image_data[row][column];
         }
      }
      
   }
   
   /*
    * (non-Javadoc)
    * @see java.lang.Object#clone()
    */
   //TODO BarcodeImage clone()
   public BarcodeImage clone()
   {
      //can implement with: return new BarcodeImage(this) but would need copy constructor
      return new BarcodeImage(this);
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
   
 //TODO  DataMatrix()
   public DataMatrix()
   {
      
      
      
   }
   
   

   public DataMatrix(BarcodeImage image)
   {
      
   }
   
   

   public boolean scan(BarcodeImage bc)
   {
      return false;
      
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
      return false;
      
   }
   
   public boolean translateImageToText()
   {
      return false;
      
   }
   
   public void displayTextToConsole()
   {
      
   }
   
   public void displayImageToConsole()
   {
      
   }
}
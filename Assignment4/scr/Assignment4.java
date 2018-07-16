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
               "**************************************    "

         };
        
        // BarcodeImage bc = new BarcodeImage(sImageIn);
         
         
         String[] sImageIn =
            {
               "                                               ",
               "                                               ",
               "                                               ",
               "     * * * * * * * * * * * * * * * * * * * * * ",
               "     *                                       * ",
               "     ****** **** ****** ******* ** *** *****   ",
               "     *     *    ****************************** ",
               "     * **    * *        **  *    * * *   *     ",
               "     *   *    *  *****    *   * *   *  **  *** ",
               "     *  **     * *** **   **  *    **  ***  *  ",
               "     ***  * **   **  *   ****    *  *  ** * ** ",
               "     *****  ***  *  * *   ** ** **  *   * *    ",
               "     ***************************************** ",
               "                                               ",
               "                                               ",
               "                                               "

            };
         
         BarcodeImage bc = new BarcodeImage(sImageIn_2);
         
         //Testing some Data Matrix Methods
         //DataMatrix myDataMatrix = new DataMatrix();
         //myDataMatrix.displayTextToConsole();
      DataMatrix myData = new DataMatrix();
      
     
      
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
      
           
      // Copy string parameter into 2D array and put in lower left
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
   public BarcodeImage clone() throws CloneNotSupportedException
   {
      //can implement with: return new BarcodeImage(this) but would need copy constructor
      return new BarcodeImage(this);
   }
   
   //TODO Optional Methods but good for utility / debugging
   //checkSize(String[] data)
   
   
   public void displayToConsole()
   {
      int row, column;
      String booleanPrint = "";
      String setIndex = "";
      
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
            booleanPrint += setIndex + "";             
         }         
         System.out.println("\"" + booleanPrint + "\"");
         booleanPrint = "";
      }
   }
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
   /*
    * Default constructor - construct an empty, but non-null image and text
    */
   public DataMatrix()
   {
      image = new BarcodeImage();
      text = "undefined"; //change to "" later "undefined" for testing purpose
      actualWidth = 0;
      actualHeight = 0;
      
   }
   
   /*
    * Sets the image but leaves text at its default value. Calls scan(). 
    */
   public DataMatrix(BarcodeImage image)
   {
      this.image = image;
   }
   
   /*
    * Sets the text but leaves image at its default value. Calls readText().
    */
   public DataMatrix(String text)
   {
      if (text == null)
      {
         System.out.println("Error: null DataMatrix constructor String Text");
         System.exit(0);
      }
      readText(text);
   }

   /*
    * A mutator for image 
    */
   public boolean scan(BarcodeImage bc)
   {
      //TODO try-catch
      try
      {
        
      }
      catch(CloneNotSupportedException e) 
      {
         e.printStackTrace();
      }
      return false;
   }
   
   
   /*
    * Access for actualWidth
    */
   
   public int getAcutalWidth()
   {
      return actualWidth;
   }
   
   /*
    * Access for actualHeight
    */
   public int getAcutalHeight()
   {
      return actualHeight;
   }
   
   /*
    * A mutator for text 
    */
   public boolean readText(String text)
   {
      if(text != null)
      {
         this.text = text;
         return true;
      }
      return false;
   }
   
   /*
    * Looks at internal text and creates a companion BarcodeImage
    */
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
      System.out.println(text);
   }
   
   //Private methods
   private void cleanImage()
   {
      // TODO need to change back to private
      int row, col;
      String booleanPrint = "";
      String setIndex = "";
      
      // TESTING PRINT OUT BY GET PIXEL
      /*
      System.out.println("From clean mage before cleaned " );
      for(row=0; row<BarcodeImage.MAX_HEIGHT; row++)
      {
         for(col=0; col<BarcodeImage.MAX_WIDTH; col++)
         {
            if (image.getPixel(row, col) == false)
            {
               setIndex = " ";
            }
            else
            {
               setIndex = "*";
            }
            booleanPrint += setIndex + "";             
         }         
         System.out.println("|" + booleanPrint + "|");
         booleanPrint = "";
      }     
      */
      
      // SEARCH FOR START OF BAR CODE AND END OF BAR CODE
      // SET DIMENSIONS

      booleanPrint = "";
      setIndex = "";
      boolean topLeftFound = false;
      int topLeftRow = 0;
      int topLeftCol = 0; 
      
      boolean bottomLeftFound = false;
      int bottomLeftRow = 0;
      int bottomLeftCol = 0;
      
      boolean bottomRightFound = false;
      int bottomRightRow = 0;
      int bottomRightCol = 0;
         
      for(row=0; row<BarcodeImage.MAX_HEIGHT; row++)
      {
         for(col=0; col<BarcodeImage.MAX_WIDTH; col++)
         {
            if (image.getPixel(row, col) == false)
            {
               setIndex = " ";
            }
            else
            {
               setIndex = "*";
               if (topLeftFound == false)
               {
                  topLeftFound = true;
                  topLeftRow = row;
                  topLeftCol = col;
               }
               
               if(row == BarcodeImage.MAX_HEIGHT -1 || image.getPixel(row + 1, topLeftCol) == false)
               {
                  bottomLeftFound = true;
                  bottomLeftRow = row;
                  bottomLeftCol = topLeftCol;
                  
                  int searchCol = bottomLeftCol;
                  while(bottomRightFound == false)
                  {
                     if(image.getPixel(row, searchCol) == false || searchCol == BarcodeImage.MAX_WIDTH -1)
                     {
                        bottomRightFound = true;
                        bottomRightRow = row;
                        bottomRightCol = searchCol;
                     }
                     searchCol++;
                  }
               }                          
            }
            //booleanPrint += setIndex + "";             
         }         
         //System.out.println("|" + booleanPrint + "|");
        // booleanPrint = "";
      }
      
      int barcodeWidth = bottomRightCol - topLeftCol;
      actualWidth = barcodeWidth;
      
      int barcodeHeight = bottomRightRow - topLeftRow;
      actualHeight = barcodeHeight;
      
      int shiftLeftBy = topLeftCol;
      int shiftDownBy = BarcodeImage.MAX_HEIGHT - bottomLeftRow - 1;
      
      // TEST PRINT OUTS FOR WIDTH AND HEIGHT
      /*
      System.out.println(" this is the start of code: row " + topLeftRow + " col " + topLeftCol);
      System.out.println(" this is the bottom left of code: row " + bottomLeftRow + " col " + bottomLeftCol);
      System.out.println(" this is the bottom right of code: row " + bottomRightRow + " col " + bottomRightCol);
      System.out.println("the barCode dims are " + barcodeWidth + " width " + barcodeHeight + " height");
      */
      
      //TESTING
      //System.out.println("Cleaned image");
      //System.out.println("shift down by " + shiftDownBy);
      
      // RE-POSITION IMAGE TO 2D ARRAY
      for(row=BarcodeImage.MAX_HEIGHT - 1; row > 0; row--)
      {
         for(col=0; col<BarcodeImage.MAX_WIDTH; col++)
         {
            if (image.getPixel(row, col) == true)
            {
               image.setPixel(row, col, false);
               image.setPixel(row + shiftDownBy, col - shiftLeftBy, true);
            }            
         }         
      } 
   }
}
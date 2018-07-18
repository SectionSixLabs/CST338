/**
 * Optical Barcode Readers and Writers
 * CST338
 * Module4
 * @author Sergiy Zarubin, Lacey Sikes, Rocky Moreno, Danny Tran
 *
 */

// NOTE! Even though you learned to do multi-file projects, 
// continue to submit only one file with a full, working program and run. 
// Code Documentation

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
         
         BarcodeImage bc = new BarcodeImage(sImageIn);
         DataMatrix dm = new DataMatrix(bc);
        
         // First secret message
         dm.translateImageToText();
         dm.displayTextToConsole();
         dm.displayImageToConsole();
         
         // second secret message
         bc = new BarcodeImage(sImageIn_2);
         bc.displayToConsole();
         dm.scan(bc);
         dm.translateImageToText();
         dm.displayTextToConsole();
         dm.displayImageToConsole();
//         
//         // create your own message
//         dm.readText("What a great resume builder this is!");
//         dm.generateImageFromText();
//         dm.displayTextToConsole();
//         dm.displayImageToConsole();
//         
//         //Testing some Data Matrix Methods
//      DataMatrix myDataMatrix = new DataMatrix();
//      myDataMatrix.displayTextToConsole();
//      DataMatrix myData = new DataMatrix("cat hat");
//      myData.generateImageFromText(); 
//      myData.displayTextToConsole();
//      myData.displayImageToConsole();
//      myData.translateImageToText(); 
//      myData.displayTextToConsole();
         
      
     
      
      //MyTest //remove later
      System.out.println("Mytest");
      //String[] myString = {"* *","   ","***"};
      //BarcodeImage myBC = new BarcodeImage(sImageIn_2);
      //myBC.displayToConsole();
      
      //System.out.println("testingGenerateImagefromText function");
      //DataMatrix testDM = new DataMatrix();
      //testDM.generateImageFromText();
      
      System.out.print("End of main.");
   }

}



/*************************************************/
/*             BARCODE IMAGE CLASS               */
/*************************************************/

//XXX class BarcodeImage

class BarcodeImage implements Cloneable
{
   public static final int MAX_HEIGHT = 30;    
   public static final int MAX_WIDTH = 65;
   private boolean[][] image_data = new boolean[MAX_HEIGHT][MAX_WIDTH];
   
   /*
    * Default Constructor
    */
   public BarcodeImage()
   {
      initialize();
   }
   
   /*
    * Constructor -takes a 1D array of Strings and converts it to 
    * the internal 2D array of booleans. 
    */
   public BarcodeImage(String[] str_data)
   {
      //XXX - Why are we reinitializing our instance here this way? 
      
      // Create and initialize 2D array
      //image_data = new boolean[MAX_HEIGHT][MAX_WIDTH]; 
            
//      for(int row=0; row<image_data.length; row++)
//      {
//         for(int column=0; column<image_data[row].length; column++)
//         {
//            image_data[row][column] = false;
//         }
//      }
      // Copy string parameter into 2D array and put in lower left
      //FIXME MAX_HEIGHT<str_data.length
//      int image_dataRow = MAX_HEIGHT - str_data.length;
      
      
//      for(int row=0; row<str_data.length; row++)
//      {
//         for(int column=0; column<str_data[row].length(); column++)
//         {
//            if(str_data[row].length() < MAX_WIDTH )
//            {
//               if (str_data[row].charAt(column) == ' ')
//               {
//                  this.image_data[image_dataRow][column]=false; 
//               }
//               else if(str_data[row].charAt(column) == '*')
//               {
//                  this.image_data[image_dataRow][column] = true;
//               } 
//            }         
//         }
//         image_dataRow++;
//      }
      initialize();
      int newImgHeight = str_data.length;
      int newImgWidth = smalestString(str_data); 
      
      if (checkSize(str_data)) {
         //Starting with a lest row
         int offset = MAX_HEIGHT- newImgHeight;
         for (int row = MAX_HEIGHT-1; row>=offset;row--) {
             for(int column=0; column<newImgWidth; column++)
             {
                   if (str_data[row-offset].charAt(column) == ' ')
                   {
                      setPixel(row,column,false);
                   }
                   else if(str_data[row-offset].charAt(column) == '*')
                   {
                      setPixel(row,column,true);
                   } 
             }    
         }

      }else {
         //TODO Find if string has a valid bar code image
      }
   }
   
   /*It does the job of checking the incoming data for every conceivable size 
    * or null error.  Smaller is okay.  Bigger or null is not.*/
   private boolean checkSize(String[] data)
   {
      int newImgHeight = data.length;
      int newImgWidth = smalestString (data);
      return (newImgHeight<MAX_HEIGHT && newImgWidth<MAX_WIDTH);
      
   }
   
   /*finds smallest string if array is not uniformed*/
   private int smalestString (String[] data)
   {
      int newImgWidth = MAX_WIDTH+1; 
      for(String str :data) {
         int strLenght= str.length();
         if (strLenght<newImgWidth) newImgWidth = strLenght; 
         if (newImgWidth==0) {
            return MAX_WIDTH+1; 
         }
      }
      return newImgWidth ;
   }
   
   private void initialize() {
      for(int row=0; row<image_data.length; row++)
      {
         for(int column=0; column<image_data[row].length; column++)
         {
            image_data[row][column]=false;
         }
      }
   }
   
   //Acessor / mutators
   
   //XXX getPixel()
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
   
   //XXX setPixel()
   boolean setPixel(int row, int col, boolean value)
   {
      if((row > MAX_HEIGHT || row < 0) || (col > MAX_WIDTH || col < 0))
      {
         return false;
      }
      else
      {
         this.image_data[row][col] = value;
         return true;
      }
   }
   
   
   public String[] getData() {
      return this.toStringArray(); 
   } 
   
   public boolean[][] getDataMtrix(){
      return this.image_data; 
   }
   
   /*
    * Copy constructor
    */
   // BarcodeImage(BarcodeImage other)
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
      //can implement with: return new BarcodeImage(this) but would need copy 
      //constructor
      BarcodeImage clone= new BarcodeImage(this);
      return clone;
   }
   
   //TODO Optional Methods but good for utility / debugging
   //checkSize(String[] data)
   
   
   public void displayToConsole()
   {
//      int row, column;
//      String booleanPrint = "";
//      String setIndex = "";
//      
//      for(row=0; row<image_data.length; row++)
//      {
//         for(column=0; column<image_data[row].length; column++)
//         {
//            if (image_data[row][column] == false)
//            {
//               setIndex = " ";
//            }
//            else
//            {
//               setIndex = "*";
//            }
//            booleanPrint += setIndex + "";             
//         }         
//         System.out.println("\"" + booleanPrint + "\"");
//         booleanPrint = "";
//      }

      String[] strArr = this.toStringArray();
      for (String row : strArr) {

         System.out.println("\"" + row + "\"");
      }

   }
   //Get the 1D string array form 2D boolean array 
   private String[] toStringArray()
   {
      String[] strArr = new String[MAX_HEIGHT]; 
      int i = 0; 
      String strRow = "";
      String strElement = "";
      for (boolean[] row :this.image_data) {
         for (boolean element : row)
         {
            if (element==false) strElement = " "; 
               else strElement = "*";
            strRow+=strElement; 
         }
         strArr[i] = strRow;
         i++;
         strRow = "";          
      }
      return strArr;
      
   }

}



/****************************************************/
/*             DATA MATRIX CLASS                    */
/****************************************************/

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
      cleanImage(); 
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
      image = new BarcodeImage();
      readText(text);
   }

/*
 * A mutator for image 
 */
/*accepts some image, represented as a BarcodeImage object to be described below
 *, and stores a copy of this image.  Depending on the sophistication of the 
 *implementing class, the internally stored image might be an exact clone of the
 * parameter, or a refined, cleaned and processed image.  Technically, there is 
 * no requirement that an implementing class use a BarcodeImage object 
 * internally, although we will do so.  For the basic DataMatrix option, it will 
 * be an exact clone.  Also, no translation is done here - i.e., any text string 
 * that might be part of an implementing class is not touched, updated or 
 * defined during the scan.*/
   public boolean scan(BarcodeImage bc)
   {
      // try-catch and finish and test method
      try
      {
        this.image=bc.clone();
        cleanImage();
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
/*accepts a text string to be eventually encoded in an image. No translation is 
 * done here - i.e., any BarcodeImage that might be part of an implementing 
 * class is not touched, updated or defined during the reading of the text*/
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
   /*Not technically an I/O operation, this method looks at the internal text 
    * stored in the implementing class and produces a companion BarcodeImage, 
    * internally (or an image in whatever format the implementing class uses).  
    * After this is called, we expect the implementing object to contain a 
    * fully-defined image and text that are in agreement with each other.*/
   public boolean generateImageFromText()
   {
      int txtLength = this.text.length();
      String [] data = new String [10]; 
      //top
      if (txtLength<BarcodeImage.MAX_WIDTH) {
         data[0] = "*";
         for (int i =0; i<txtLength;i++) {
            data[0]+=" *"; 
         }
         for (int n=1;n<9;n++)
         {
            data[n]="*";
         }
         
         for(char ch : this.text.toCharArray()) {
            int ascii  = (int) ch; 
            if (ascii>=128) {
               ascii-=128;
               data[1]+= "*";
            } else data[1]+= " ";
            
            if (ascii>=64) {
               ascii-=64;
               data[2]+= "*";
            } else data[2]+= " ";
            
            
            if (ascii>=32) {
               ascii-=32;
               data[3] += "*";
            } else data[3] += " ";
            
            if (ascii>=16) {
               ascii-=16;
               data[4] += "*";
            } else data[4] += " ";
            
            if (ascii>=8) {
               ascii-=8;
               data[5] += "*";
            } else data[5] += " ";
            
            if (ascii>=4) {
               ascii-=4;
               data[6] += "*";
            } else data[6] += " ";
            
            if (ascii>=2) {
               ascii-=2;
               data[7] += "*";
            } else data[7] += " ";
            
            if (ascii==1) {
               ascii-=1;
               data[8] += "*";
            } else data[8]+= " ";

         }  
         
         //buttom
         data[9] = "*";
         for (int i =0; i<txtLength;i++) {
            data[9]+="*"; 
         }
         
         this.image = new BarcodeImage(data);
         this.displayImageToConsole();
         
      }
//      System.out.println("Hi from generateImageFromText. Still in prgress");
//      System.out.println("thechar-asciival-binvalue-starString");
//      int charIndex;
//      char textChar;
//      String[] binStringArr = new String[text.length()]; 
//      
//      for(charIndex=0;charIndex<text.length();charIndex++)
//      {
//         textChar = text.charAt(charIndex);
//         System.out.print(textChar+"-");
//         System.out.print((int)textChar);
//         System.out.print("-"+Integer.toBinaryString(textChar));
//         String binString = Integer.toBinaryString(textChar);
//         String oneReplace = binString.replaceAll("1", "*");
//         String spaceReplace = oneReplace.replaceAll("0", " ");
//       
//         System.out.println("-"+spaceReplace);
//         binStringArr[charIndex] = spaceReplace;
//         //trying to implementing feature to change 1 to "*" and 0 to " "
//         // now must make them column wise not positiioned row wise
//         
//      }
//      // fix binary to be in 2D array format with |_ datamatrix border
//      image = new BarcodeImage(binStringArr);
//      for(int i=0;i<binStringArr.length;i++)
//      {
//         System.out.println(i+"is :"+binStringArr[i]);
//      }
//      image.displayToConsole();
//      System.out.println("end generateimagefromtext");
      return false;
      
   }
   
   //TODO translateImageToText()
   /*Not technically an I/O operation, this method looks at the internal text 
    * stored in the implementing class and produces a companion BarcodeImage, 
    * internally (or an image in whatever format the implementing class uses).  
    * After this is called, we expect the implementing object to contain a 
    * fully-defined image and text that are in agreement with each other.*/ 
   public boolean translateImageToText()
   {
      boolean[][] data = this.image.getDataMtrix(); 
      
      int imgHeight = data.length - this.actualHeight;
      int imgWidth=this.actualWidth; 
      char[] text = new char[this.actualWidth];
      
      for (int i=1;i<imgWidth;i++) {
         String binString = ""; 
         for(int j=imgHeight+1; j<imgHeight+this.actualHeight-1;j++)
         {
            if (data[j][i]) binString+=1+"";
               else binString+=0+""; 
         }
         int parseInt = Integer.parseInt(binString, 2);
         text[i-1] = (char)parseInt; 
      }
        this.text = new String(text);     
      
      return false;
      
   }
   
   /*
    * Prints out text string to console
    */
   public void displayTextToConsole()
   {
      System.out.println(this.text);
   }
   
   /*
    * Prints out image to console. Do this in the form of a dot-matrix
    * of blanks and asteriks 
    */
   public void displayImageToConsole()
   {
      String[] strArr = this.image.getData(); 
      for (String str :strArr ) {
         System.out.println("|" + str + "|");
      }
//      int row, col;
//      String booleanPrint = "";
//      String setIndex = "";
//      
//      for(row=0; row<BarcodeImage.MAX_HEIGHT; row++)
//      {
//         for(col=0; col<BarcodeImage.MAX_WIDTH; col++)
//         {
//            if (image.getPixel(row, col) == false)
//            {
//               setIndex = " ";
//            }
//            else
//            {
//               setIndex = "*";
//            }
//            booleanPrint += setIndex + "";             
//         }         
//         System.out.println("|" + booleanPrint + "|");
//         booleanPrint = "";
//      } 
   }
   

   //Private methods
   private void cleanImage()
   {
      String [] strArr = image.getData(); 
      for (int i = 0; i<strArr.length; i++) {
         strArr[i]=strArr[i].trim();
      }
      int arrHeight = 0;
      int maxLenght = 0; 
      for (String str :strArr) {
         if (str.length()!=0) {
            arrHeight++;
            if (str.length()>maxLenght) maxLenght=str.length(); 
         }
      }
      String [] newArr = new String [arrHeight];
      int i =0; 
      for (String str :strArr) {
         if (str.length()!=0) {
            if (str.length()<maxLenght) {
               int addWS = maxLenght -str.length(); 
               for (int n = 0; n<addWS; n++) {
                  str+=" "; 
               }
            }
            newArr[i++] = str; 
         }
      }
      this.image= new BarcodeImage(newArr); 
      this.actualHeight = arrHeight; 
      this.actualWidth = maxLenght; 
      
      /* get the image and look for CLLs
      TOP   "* * * * * * * * * * * * * * * * * *"
      Bottom"***********************************"
      
      */
            
//      int row, col;
//      String booleanPrint = "";
//      String setIndex = "";
//      
//      // TESTING PRINT OUT BY GET PIXEL
//      /*
//      System.out.println("From clean mage before cleaned " );
//      for(row=0; row<BarcodeImage.MAX_HEIGHT; row++)
//      {
//         for(col=0; col<BarcodeImage.MAX_WIDTH; col++)
//         {
//            if (image.getPixel(row, col) == false)
//            {
//               setIndex = " ";
//            }
//            else
//            {
//               setIndex = "*";
//            }
//            booleanPrint += setIndex + "";             
//         }         
//         System.out.println("|" + booleanPrint + "|");
//         booleanPrint = "";
//      }     
//      */
//      
//      // SEARCH FOR START OF BAR CODE AND END OF BAR CODE
//      // SET DIMENSIONS
//
//      booleanPrint = "";
//      setIndex = "";
//      boolean topLeftFound = false;
//      int topLeftRow = 0;
//      int topLeftCol = 0; 
//      
//      boolean bottomLeftFound = false;
//      int bottomLeftRow = 0;
//      int bottomLeftCol = 0;
//      
//      boolean bottomRightFound = false;
//      int bottomRightRow = 0;
//      int bottomRightCol = 0;
//         
//      for(row=0; row<BarcodeImage.MAX_HEIGHT; row++)
//      {
//         for(col=0; col<BarcodeImage.MAX_WIDTH; col++)
//         {
//            if (image.getPixel(row, col) == false)
//            {
//               setIndex = " ";
//            }
//            else
//            {
//               setIndex = "*";
//               if (topLeftFound == false)
//               {
//                  topLeftFound = true;
//                  topLeftRow = row;
//                  topLeftCol = col;
//               }
//               
//               if(row == BarcodeImage.MAX_HEIGHT -1 || 
//                     image.getPixel(row + 1, topLeftCol) == false)
//               {
//                  bottomLeftFound = true;
//                  bottomLeftRow = row;
//                  bottomLeftCol = topLeftCol;
//                  
//                  int searchCol = bottomLeftCol;
//                  while(bottomRightFound == false)
//                  {
//                     if(image.getPixel(row, searchCol) == false || 
//                           searchCol == BarcodeImage.MAX_WIDTH -1)
//                     {
//                        bottomRightFound = true;
//                        bottomRightRow = row;
//                        bottomRightCol = searchCol;
//                     }
//                     searchCol++;
//                  }
//               }                          
//            }
//            //booleanPrint += setIndex + "";             
//         }         
//         //System.out.println("|" + booleanPrint + "|");
//        // booleanPrint = "";
//      }
//      
//      int barcodeWidth = bottomRightCol - topLeftCol;
//      actualWidth = barcodeWidth;
//      
//      int barcodeHeight = bottomRightRow - topLeftRow;
//      actualHeight = barcodeHeight;
//      
//      int shiftLeftBy = topLeftCol;
//      int shiftDownBy = BarcodeImage.MAX_HEIGHT - bottomLeftRow - 1;
//      
//      // TEST PRINT OUTS FOR WIDTH AND HEIGHT
//      /*
//      System.out.println(" this is the start of code: row " + topLeftRow + 
//      " col " + topLeftCol);
//      System.out.println(" this is the bottom left of code: row " + 
//      bottomLeftRow + " col " + bottomLeftCol);
//      System.out.println(" this is the bottom right of code: row " + 
//      bottomRightRow + " col " + bottomRightCol);
//      System.out.println("the barCode dims are " + barcodeWidth + " width " 
//      + barcodeHeight + " height");
//      */
//      
//      //TESTING
//      //System.out.println("Cleaned image");
//      //System.out.println("shift down by " + shiftDownBy);
//      
//      // RE-POSITION IMAGE TO 2D ARRAY
//      for(row=BarcodeImage.MAX_HEIGHT - 1; row > 0; row--)
//      {
//         for(col=0; col<BarcodeImage.MAX_WIDTH; col++)
//         {
//            if (image.getPixel(row, col) == true)
//            {
//               image.setPixel(row, col, false);
//               image.setPixel(row + shiftDownBy, col - shiftLeftBy, true);
//            }            
//         }         
//      } 
   }
   
   
}
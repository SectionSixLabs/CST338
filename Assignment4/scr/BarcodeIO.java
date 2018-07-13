
//Interface

//TODO Question by Danny
//Ecipse said to make interface its own file, but in opposition to the assignment
//Specifications, do we just join them into one file at the end?

//TODO Barcode IO
public interface BarcodeIO
{
//TODO Scan()
/*accepts some image, represented as a BarcodeImage object to be described below
 *, and stores a copy of this image.  Depending on the sophistication of the 
 *implementing class, the internally stored image might be an exact clone of the
 * parameter, or a refined, cleaned and processed image.  Technically, there is 
 * no requirement that an implementing class use a BarcodeImage object 
 * internally, although we will do so.  For the basic DataMatrix option, it will 
 * be an exact clone.  Also, no translation is done here - i.e., any text string 
 * that might be part of an implementing class is not touched, updated or 
 * defined during the scan.*/
 public boolean scan(BarcodeImage bc);
 
 //TODO readText()
 /*accepts a text string to be eventually encoded in an image. No translation is 
  * done here - i.e., any BarcodeImage that might be part of an implementing 
  * class is not touched, updated or defined during the reading of the text*/
 public boolean readText(String text);
 
 //TODO generateImmageFromText()
 /*Not technically an I/O operation, this method looks at the internal text 
  * stored in the implementing class and produces a companion BarcodeImage, 
  * internally (or an image in whatever format the implementing class uses).  
  * After this is called, we expect the implementing object to contain a 
  * fully-defined image and text that are in agreement with each other.*/
 public boolean generateImageFromText();
 
 //TODO translateImageToText()
 /*Not technically an I/O operation, this method looks at the internal text 
  * stored in the implementing class and produces a companion BarcodeImage, 
  * internally (or an image in whatever format the implementing class uses).  
  * After this is called, we expect the implementing object to contain a 
  * fully-defined image and text that are in agreement with each other.*/
 public boolean translateImageToText();
 
 public void displayTextToConsole();
 public void displayImageToConsole();

}
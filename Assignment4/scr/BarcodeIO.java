
//Interface

//TODO Question by Danny
//Ecipse said to make interface its own file, but in opposition to the assignment
//Specifications, do we just join them into one file at the end?

public interface BarcodeIO
{

 public boolean scan(BarcodeImage bc);
 public boolean readText(String text);
 public boolean generateImageFromText();
 public boolean translateImageToText();
 public void displayTextToConsole();
 public void displayImageToConsole();

}
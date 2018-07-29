import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.text.DecimalFormat;

public class TimeClock extends JFrame implements ActionListener, Runnable
{
   
   public static int WIDTH = 300;
   public static int HEIGHT = 200;
   public static int PAUSE = 1000;
   
   public static double hourCounter = 0;
   public static double minuteCounter = 0;
   public static double secondCounter = 0;
   public DecimalFormat timeFormat = new DecimalFormat("#00");
   public boolean timeClockOn = false;
   public String defaultClockCounter = "Time Clock \n" + timeFormat.format(0) + " : " + 
         timeFormat.format(0) + 
               " : " + timeFormat.format(0) + "";
    
   private JPanel box;
   
   public JLabel clockTicker = new JLabel();
   
   
   public static void main(String[] args)
   {
      TimeClock gui = new TimeClock();
      
      gui.setVisible(true);
   }
   
   
   public JPanel getBox()
   {  
      return box;
   }
   
   public TimeClock()
   {
      setSize(WIDTH, HEIGHT);
      setTitle("Clock");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLayout(new BorderLayout());
      
      box = new JPanel();
      add(box, "Center");
      box.setVisible(true);
      clockTicker.setText(defaultClockCounter);
      box.add(clockTicker);
        
      // create JPanel buttonPanel, set layout to flowlayout, make startButton, add it to buttonPanel, 
      // add buttonPanel to border layout
      
      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new FlowLayout());
      JButton startButton = new JButton("Start/Stop Reset");
      startButton.addActionListener(this);
      buttonPanel.add(startButton);
      add(buttonPanel, "South");
      
      
   }
   
   public void actionPerformed(ActionEvent e)
   {
      if(!timeClockOn)
      {
         
         timeClockOn = true;
         System.out.println("from actin  " + timeClockOn);
         
      }
      else
      {
         timeClockOn = false;
         System.out.println("from actin after " + timeClockOn);
      }
         
      startThread();     
   }
   
   public void run()
   {
      //System.out.println("Is time clock on from run " + timeClockOn);
         
      if (timeClockOn)
      {
         doNothing(PAUSE);
         startThread();
     
         if (secondCounter > 59)
         {  
            secondCounter = 0;
            minuteCounter++;
         }     
         
         if (minuteCounter > 59)
         {
            minuteCounter = 0;
            secondCounter = 0;
            hourCounter++;
         }
                 
         if (hourCounter > 24)
         {
            minuteCounter = 0;
            secondCounter = 0;
            hourCounter = 0;
         }
      }
      else
      {
         minuteCounter = 0;
         secondCounter = 0;
         hourCounter = 0;
         
      }
        
         String clockCounter = "Time Clock \n" + timeFormat.format(hourCounter) + " : " + 
         timeFormat.format(minuteCounter) + 
               " : " + timeFormat.format(secondCounter) + "";
         clockTicker.setText(clockCounter);
       
         secondCounter++;       
   }
   
   public void startThread()
   {
      Thread theThread = new Thread(this);
      theThread.start();
   }   
   
   public void doNothing(int milliseconds)
   {
      try
      {
         Thread.sleep(milliseconds);
      }
      catch(InterruptedException e)
      {
         System.out.println("Error");
         System.exit(0);
      }
   }  
}

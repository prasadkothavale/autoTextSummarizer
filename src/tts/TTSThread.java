package tts;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TTSThread implements Runnable{
    private Thread t;
   private String summary;
   
   summarizer.gui.Main main;
   
   public TTSThread(String s, summarizer.gui.Main m){
       summary = s;
       main = m;
       
   }
   public void run() {
      System.out.println("Reading...");
      
      JAVATTS.speak(summary);
      
      main.jButton2.setEnabled(true);
      try {
         
     } catch (Exception e) {}
   }
   
   public void start ()
   {
      //System.out.println("Starting ");
      if (t == null)
      {
         t = new Thread (this, summary);
         t.start ();
      }
   }
   public void stopReading(){
        try {
            this.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(TTSThread.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

}

package summarizer.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import static summarizer.gui.Main.percent;
import static summarizer.gui.Main.summary;

public class InputTextSummaryThread implements Runnable {
    public static javax.swing.JProgressBar jpb = null;
    JTextArea jTextArea1,jTextArea2;
    public int percent;
    public InputTextSummaryThread(JProgressBar jpb, JTextArea jTA1, JTextArea jTA2) {
        this.jpb = jpb;
        jpb.setMaximum(100);
        jTextArea1 = jTA1;
        jTextArea2 = jTA2;
    }
    public void setValue(int value) {
        percent = value;
    }
    public void run() {
        
       jpb.setValue(0); 
        String input =  jTextArea1.getText();
      
       if(!input.endsWith(".")){input+=".";}
       Main.writeFile(input);
      // sentense.summary ss = new sentense.summary();
       jpb.setValue(5);
              
      // ss.separatesentense(new File("input.txt"));
       sentense.SimpleSpliter.splitSentence();
       jpb.setValue(10);
       pos_chunker.PosChunker.doPos_Chunker();
       jpb.setValue(15);
       
       
        try {
            //chain.ChainTable.chainTable2.clear();
            chain.ChainTable.chainTable3.clear();
            chain.ChainTable.doChaning();
        } catch (IOException ex) {
            System.out.println("Exception caught in chaining");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        jpb.setValue(100);
        
        BufferedReader br = null;
        String str = "";
        jTextArea2.setText(summary);
        
        
        
        
    }
}

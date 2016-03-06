package summarizer.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import static summarizer.gui.Main.percent;
import static summarizer.gui.Main.summary;

public class DocumentBrowserSummaryThread implements Runnable {
    public static javax.swing.JProgressBar jpb = null;
    JTextPane jTextPane1;
    public int percent;
    public DocumentBrowserSummaryThread(JProgressBar jpb, JTextPane jTA1) {
        this.jpb = jpb;
        jpb.setMaximum(100);
        jTextPane1 = jTA1;
        
    }
    public void setValue(int value) {
        percent = value;
    }
    public void run() {
        
       jpb.setValue(0); 
        
      // sentense.summary ss = new sentense.summary();
       jpb.setValue(5);
              
      // ss.separatesentense(new File("input.txt"));
       sentense.SimpleSpliter.splitSentence();
       jpb.setValue(10);
       //pos_chunker.PosChunker.doPos_Chunker();
       pos_chunker.POSMultiThreaded3.doPOS();
       //jpb.setValue(15);
       
       
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
        jTextPane1.setText(summary);
        
        
        
        
    }
}

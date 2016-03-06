package pos_chunker;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class Line {

    String string;
    int line;

    Line(String s, int i) {
        string = s;
        line = i;
    }
}

public class POSMultiThreaded3 {
    
    static ArrayList<String> buffer = new ArrayList<>();
    static int sentences_done = 0;
    static BufferedReader br = null;
    static boolean completed = false,writingDone=false;
    public static synchronized Line getLine() {
        try {
            String ret = br.readLine();
            if (ret != null) {
                buffer.add(ret);
                countLines(ret);
                return new Line(ret, (buffer.size() - 1));
            }
            else{
                return null;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(POSMultiThreaded3.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    static void countLines(String line){
        if(line.startsWith("◄")){
            try{
            String[] s1 = line.split("◄");
            String[] s2 = s1[s1.length-1].split("►");
            sentences_done = Integer.parseInt(s2[0]);
            }catch(Exception e){e.printStackTrace();}
        }
    }
            
    public static synchronized void writeFile(){
        if(!completed){
            completed = true;
            System.out.println("Writing POS.txt");
            try{
            OutputStream outputFile = new FileOutputStream("pos.txt");
            String str="";
            for(int i=0;i<buffer.size();i++){
                str+= buffer.get(i)+System.getProperty("line.separator");;
            }
            byte write[] = str.getBytes();
             for(byte x: write){
                 outputFile.write(x);
             }
             System.out.println("POS Tagging done");
            outputFile.close();
            POSMultiThreaded3.writingDone=true;
        }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void doPOS() {
        try {
            br = new BufferedReader(new FileReader("sentence.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(POSMultiThreaded3.class.getName()).log(Level.SEVERE, null, ex);
        }

        POSThread3 pos1 = new POSThread3();
        pos1.start();
         POSThread3 pos2 = new POSThread3();
        pos2.start();
        // POSThread3 pos3 = new POSThread3();
      //  pos3.start();
       //  POSThread3 pos4 = new POSThread3();
      //  pos4.start();
        
        while(!writingDone){
            try {
                //System.out.println(sentences_done+"/"+sentense.SimpleSpliter.count);
                 if(summarizer.gui.InputTextSummaryThread.jpb!=null)
                    summarizer.gui.InputTextSummaryThread.jpb.setValue(15+(sentences_done*50/sentense.SimpleSpliter.count));
                if(summarizer.gui.DocumentBrowserSummaryThread.jpb!=null)
                    summarizer.gui.DocumentBrowserSummaryThread.jpb.setValue(15+(sentences_done*50/sentense.SimpleSpliter.count));
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(POSMultiThreaded3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {
        try {
            br = new BufferedReader(new FileReader("sentence.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(POSMultiThreaded3.class.getName()).log(Level.SEVERE, null, ex);
        }

        POSThread3 pos1 = new POSThread3();
        pos1.start();
         POSThread3 pos2 = new POSThread3();
        pos2.start();
         POSThread3 pos3 = new POSThread3();
        pos3.start();
         POSThread3 pos4 = new POSThread3();
        pos4.start();
        
        while(!writingDone){
        Thread.sleep(1000);
        }
        

        //POSThread T2 = new POSThread();
        //T2.start();
        System.out.println("main ended");
    }

}

class POSThread3 extends Thread {
    private Thread t;
    MaxentTagger tagger;
    Line ln;
    POSThread3() {
         tagger =  new MaxentTagger("lib/models/english-bidirectional-distsim.tagger"); //LOCATION OF MODEL
    }

    public void run() {
        try {
            String tagged,newTag;
           while((ln =  POSMultiThreaded3.getLine()) != null){
                 tagged = tagger.tagString(ln.string);
                  newTag= tagged.replace('_', '/');
                  //System.out.println(newTag);
                  
                  POSMultiThreaded3.buffer.set(ln.line, newTag);
           }
           tagger = null;
           this.sleep(500);
           POSMultiThreaded3.writeFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {

        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }
}

package pos_chunker;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.*;
public class PosChunker {
    PosChunker(){}
    public static void doPos_Chunker(){
        BufferedReader br = null;
        String str = "";
        try {
                String sCurrentLine;
                br = new BufferedReader(new FileReader("sentence.txt"));
                MaxentTagger tagger =  new MaxentTagger("lib/models/english-bidirectional-distsim.tagger"); //LOCATION OF MODEL
                    String newTag = "";
                while ((sCurrentLine = br.readLine()) != null) {
                    String tagged = tagger.tagString(sCurrentLine);
                    
                    
                          newTag= tagged.replace('_', '/');
                    
                    //System.out.println(newTag);
                    str += newTag+System.getProperty("line.separator");

                }
                try{
       OutputStream outputFile = new FileOutputStream("pos.txt");
       byte write[] = str.getBytes();
        for(byte x: write){
            outputFile.write(x);
        }
        System.out.println("POS Tagging done");
       outputFile.close();
       }catch(Exception e){
           e.printStackTrace();
       }
                try{/*Chunker.doChunk();*/}
                    catch(Exception e){e.printStackTrace();}
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
    void doPos(){
        
    }
    public static void main(String args[]){
        doPos_Chunker();
    }
}

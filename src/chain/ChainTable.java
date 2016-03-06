package chain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.didion.jwnl.JWNLException;
import wordnet.MyWord;

public class ChainTable {
    //public static ArrayList<Chain> chainTable = new ArrayList<>();
    //public static ArrayList<Chain> chainTable2 = new ArrayList<>();
    public static ArrayList<Chain> chainTable3 = new ArrayList<>();
    int ctPointer=0;
    
    public static void doChaning() throws FileNotFoundException, IOException{
        String sCurrentLine;
        String[] tokens, data;
        MyWord temp;
        //Chain tempChain = new Chain();
        //Chain tempChain2 = new Chain();
        Chain tempChain3 = new Chain();
        //chainTable.add(tempChain);
        boolean newWord3, newPara=false;
        int count=0,line=0,ct3Lim=0,percent=summarizer.gui.Main.percent,totalLines=sentense.SimpleSpliter.count,linesDone=0,total3;
        if(percent==0){percent=33;}
        BufferedReader br = new BufferedReader(new FileReader("pos.txt"));
        System.out.println("Chaining Started...");
        try {
            wordnet.WordNetHandler.initialize();
        } catch (Exception ex) {
            System.err.print("Error loading dictionary");
        }
        while ((sCurrentLine = br.readLine()) != null) {
            tokens = sCurrentLine.split(" ");
            for (int i = 0 ; i < tokens.length ; ++i){
                data = tokens[i].split("/");
               // if(data.length==2){
                //System.out.println("["+data[0]+" "+data[1]+" ");
                //System.out.print((data.length==2)+" "+(data[1].equalsIgnoreCase("NN"))+"] ");
                //}
                if(data[0].contains("◄")){try{line=Integer.parseInt(tokens[i+1].split("/")[0]);}catch(Exception e){}}
                else if(data[0].contains("►")){}
                else if(data[0].contains("▬")){newPara=true;}
                else if(data.length>1 && (data[1].equalsIgnoreCase("NN")||data[1].equalsIgnoreCase("NNS")||data[1].equalsIgnoreCase("NNP")||data[1].equalsIgnoreCase("NNPS"))){
                  /* CHANGING PLURAL TO SINGULAR
                    if(data[1].equalsIgnoreCase("NNS")){
                        System.out.print("\n"+data[0]+" changed to ");
                        if(data[0].endsWith("ves"))data[0]=data[0].substring(0, data[0].length()-3)+"f";
                        else if(data[0].endsWith("s"))data[0]=data[0].substring(0, data[0].length()-1);
                        System.out.print(data[0]+"\n");
                    }
                 */
                     count++;
                       // if(count%25==0)System.out.println(count);
                    temp = new MyWord(data[0],line);  //TEMPORARILY COMMENTED
                    
                    //ALGORITHM 1 - COMPARE WORD WITH ALL CHAINs
                    
                    /*newWord = true;
                    for(Chain x:chainTable){
                        if(x.add(temp)){
                            //System.out.println(temp.word+" chained");
                           newWord=false; break;
                        }
                    }
                    if(newWord){
                        tempChain = new Chain();
                        tempChain.words.add(temp);
                        //System.out.println(temp.word+" chained");
                        //System.out.println(temp.Synonym.toString());
                        //System.out.println(temp.Hypernym.toString());
                        //System.out.println(temp.Hyponym.toString());
                        //System.out.println(temp.PartOf.toString());
                        //System.out.println(temp.Gloss.toString());
                        chainTable.add(tempChain);
                    }
                    
                    */
                    //ALGORITHM 2 -CHAINTABLE 2
             /*       if(!tempChain2.add(temp)){
                       
                        chainTable2.add(tempChain2);
                        tempChain2 = new Chain();
                        tempChain2.words.add(temp);
                    }
               */     
                    //ALGORITHM 3 - CHAINTABLE 3
                                            //TEMPORARILY COMMENTED
                    newWord3 = true;
                    if(newPara){
                        
                        ct3Lim=chainTable3.size();
                        //System.out.println("new para at line="+line+", limit="+ct3Lim);
                        newPara=false;
                    }
                    for(int j=ct3Lim;j<chainTable3.size();j++){
                        if(chainTable3.get(j).add(temp)){
                           // System.out.println(temp.word+" chained @ "+j+", limit="+ct3Lim+", size="+chainTable3.size());
                           // chainTable3.get(j).print();
                           newWord3=false; break;
                        }
                    }
                    if(newWord3){
                        tempChain3 = new Chain();
                        tempChain3.words.add(temp);
                        chainTable3.add(tempChain3);
                    }
                 //   
                    //System.out.println(temp.word+"\n"+temp.Synonym.toString()+temp.PartOf.toString()+temp.Hyponym.toString()+temp.Hypernym.toString()+temp.Gloss.toString()+"\n----------------\n");
                }
            }
            
            //if(line%1==0){
                if(summarizer.gui.InputTextSummaryThread.jpb!=null)
                    summarizer.gui.InputTextSummaryThread.jpb.setValue(65+(line*25/totalLines));
                if(summarizer.gui.DocumentBrowserSummaryThread.jpb!=null)
                    summarizer.gui.DocumentBrowserSummaryThread.jpb.setValue(65+(line*25/totalLines));
                
               // System.out.println(15+(line*75/totalLines)+"% Done");
            //}
	}
        //System.out.println(count);
                 //       chainTable2.add(tempChain2);
      /* System.out.println("------------Printing chaintable ------------");
       for(int i=0;i<chainTable.size();i++){
           chainTable.get(i).print();
           System.out.println();
       }   
      */      
       // System.out.println("----------Printing chaintable2---------------");
      // for(int i=0;i<chainTable2.size();i++){
        //   chainTable2.get(i).print();
          // System.out.println();
      // }
       
       System.out.println("----------Printing chaintable3---------------");
        for(int i=0;i<chainTable3.size();i++){
           chainTable3.get(i).print();
           System.out.println();
       }  
        //SORTING
        int cutOff = count*percent/100;
        int actual = 0,actual2 = 0,actual3 = 0,i,j;
        //CHAIN TABLE
       /* ArrayList<ChainIndex> sorted = new ArrayList<>();
        for(int k=0;k<chainTable.size();k++){
            if(sorted.isEmpty()){sorted.add(new ChainIndex(chainTable.get(k).score,k));}
            else{
                for(int l=0;l<sorted.size();l++){
                    if(sorted.get(l).score<chainTable.get(k).score){
                        sorted.add(l,new ChainIndex(chainTable.get(k).score,k));break;
                    }
                }
            }
        }
        */
       //CHAIN TABLE 2
        /*ArrayList<ChainIndex> sorted2 = new ArrayList<>();
        for(int k=0;k<chainTable2.size();k++){
            if(sorted2.isEmpty()){sorted2.add(new ChainIndex(chainTable2.get(k).score,k));}
            else{
                for(int l=0;l<sorted2.size();l++){
                    if(sorted2.get(l).score<chainTable2.get(k).score){
                        sorted2.add(l,new ChainIndex(chainTable2.get(k).score,k));break;
                    }
                }
            }
        }
        */
        //CHAIN TABLE 3
        ArrayList<ChainIndex> sorted3 = new ArrayList<>();
        for(int k=0;k<chainTable3.size();k++){
            if(sorted3.isEmpty()){sorted3.add(new ChainIndex(chainTable3.get(k).score,k));}
            else{
                for(int l=0;l<sorted3.size();l++){
                    if(sorted3.get(l).score<chainTable3.get(k).score){
                        sorted3.add(l,new ChainIndex(chainTable3.get(k).score,k));break;
                    }
                }
            }
        }
        
        System.out.println("sorting done");
      if(summarizer.gui.InputTextSummaryThread.jpb!=null)
        summarizer.gui.InputTextSummaryThread.jpb.setValue(95);
      if(summarizer.gui.DocumentBrowserSummaryThread.jpb!=null)
        summarizer.gui.DocumentBrowserSummaryThread.jpb.setValue(95);
/* ArrayList <Chain> sorted;
        sorted = (ArrayList<Chain>) chainTable.clone();
        System.out.println("Sorted printing...");
        
       for(ChainIndex x:sorted){
          System.out.println(x.score+", "+x.index);
       }
       */  
       //CHAIN SELECTION
       String op="";
       /*ArrayList<Integer> selected = new ArrayList<>();
       
           for(ChainIndex x:sorted){
               for(MyWord mw : chainTable.get(x.index).words){
                    if(!selected.contains(mw.lineNo)){
                        selected.add(mw.lineNo);
                       
                    }
                    actual++;
                }
               if(actual>cutOff)break;
           }
           Collections.sort(selected);
           */
          /* ArrayList<Integer> selected2 = new ArrayList<>();
       
           for(ChainIndex x:sorted2){
               for(MyWord mw : chainTable2.get(x.index).words){
                    if(!selected2.contains(mw.lineNo)){
                        selected2.add(mw.lineNo);
                       
                    }
                    actual2++;
                }
               if(actual2>cutOff)break;
           }
           Collections.sort(selected2);
           */
           ArrayList<Integer> selected3 = new ArrayList<>();
       
           for(ChainIndex x:sorted3){
               for(MyWord mw : chainTable3.get(x.index).words){
                    if(!selected3.contains(mw.lineNo)){
                        selected3.add(mw.lineNo);
                       
                    }
                    actual3++;
                }
               if(actual3>cutOff)break;
           }
           Collections.sort(selected3);
           
          // System.out.println("Printing selected\n"+selected);
          // System.out.println("Printing selected2\n"+selected2);
           System.out.println("Printing selected3\n"+selected3);
           System.out.println("Summarized to "+percent+"%");
           
           
           
           String fin="",fin2="",fin3="";
           //SENTENCE LINKING
        String str = "",str2="",str3="";
        try {
            int lnno,lnno2,lnno3;
             br = new BufferedReader(new FileReader("sentence.txt"));
            while((str = br.readLine())!=null){
                str2=str3=str;
               try{
                /*str = str.substring(1, str.length());
                lnno = Integer.parseInt(str.split("►")[0]);
                if(selected.contains(lnno)){
                    fin+=str.split("►")[1];
                }   */
             /*       str2 = str2.substring(1, str2.length());
                lnno2 = Integer.parseInt(str2.split("►")[0]);
                if(selected2.contains(lnno2)){
                    fin2+=str2.split("►")[1];
                }
                   */
                    str3 = str3.substring(1, str3.length());
                lnno3 = Integer.parseInt(str3.split("►")[0]);
                if(selected3.contains(lnno3)){
                    fin3+=str3.split("►")[1];
                }
                
                
                }catch(NumberFormatException e){}
            }
      /*  OutputStream outputFile = new FileOutputStream("summary.txt");
       byte write[] = fin.getBytes();
        for(byte x: write){
            outputFile.write(x);
        }
       */
       
      /* OutputStream outputFile = new FileOutputStream("summary2.txt");
       byte write2[] = fin2.getBytes();
        for(byte x: write2){
            outputFile.write(x);
        }
       outputFile.close();
       */
       OutputStream outputFile = new FileOutputStream("summary3.txt");
       byte write3[] = fin3.getBytes();
        for(byte x: write3){
            outputFile.write(x);
        }
       outputFile.close();
       summarizer.gui.Main.summary = fin3;
        }catch(Exception e){
            System.out.println(e);
        }
        //tts.JAVATTS.speak(fin3);
        //System.out.println("----------Summary------------\n"+fin);
    }
            
}

package sentense;

import java.io.*;

public class SimpleSpliter {
    public static int count;
    public static void splitSentence() {
        System.out.println("Sentense spliting started...");
        BufferedReader br = null;
        String str = "◄1►";
        try {

            
            count = 1;
            char c;
            br = new BufferedReader(new FileReader("input.txt"));

            while (true) {
                c = (char) br.read();
                //System.out.print(c+"("+(int)c+")");
                if(-1==(int)c||65535==(int)c)break;
                if (c == '.') {
                    count++;
                    str += c + System.getProperty("line.separator")+'◄'+count+'►';
                    
                    if (count % 100 == 0) {
                        System.out.println(count);
                    }
                }
                else if(c == (char)10){
                    count++;
                    str += c +"▬"+ System.getProperty("line.separator")+'◄'+count+'►';
                    
                    if (count % 100 == 0) {
                        System.out.println(count);
                    }
                }
                else{str += c;}
            }
            OutputStream outputFile = new FileOutputStream("sentence.txt");
                byte write[] = str.getBytes();
                for (byte x : write) {
                    outputFile.write(x);
                }
                
                //SMALL CASE FILTER
               /* String op="";
                 br = new BufferedReader(new FileReader("sentence.txt"));
                 String s;
                 while ((s = br.readLine()) != null) {
                     int i =0;
                     if(s.charAt(0)!='▬'){
                        while(s.charAt(i)!='►'){i++;}
                        if(i+1<s.length()){
                        if(s.charAt(i+1)>='a' && s.charAt(i+1)<='z'){
                            System.out.println("[removed] "+s);
                        }
                        else{
                            op+=s+System.getProperty("line.separator");
                        }
                        }
                     }else{
                          op+="▬"+System.getProperty("line.separator");
                     }
                 }
                 
                 
                 
                 outputFile = new FileOutputStream("sentence.txt");
                byte write2[] = op.getBytes();
                for (byte x : write2) {
                    outputFile.write(x);
                }
                
                //fullstop filter
                 op="";
                 br = new BufferedReader(new FileReader("sentence.txt"));
                 while ((s = br.readLine()) != null) {
                     if(s.charAt(0)!='▬'){
                        if(s.endsWith(".")){
                            op+= s+System.getProperty("line.separator");
                        }
                     }else{
                         op+="▬"+System.getProperty("line.separator");
                     }
                 }
                 outputFile = new FileOutputStream("sentence.txt");
                byte write3[] = op.getBytes();
                for (byte x : write3) {
                    outputFile.write(x);
                }
                * 
                */
                
                outputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        BufferedReader br = null;
        String str = "";
        try {

            String sCurrentLine;
            int prev = 0, count = 0;
            char c;
            br = new BufferedReader(new FileReader("input.txt"));
            System.out.println("[space] char: "+(int)'z');
           /* while (true) {
                c = (char) br.read();
                if(-1==(int)c)break;

                
                 c = (char)br.read();
                            
                 if(c=='.'){
                 str+=c+System.getProperty("line.separator");
                 count++;
                 if(count%100==0)System.out.println(count);
                 }
                            
                 str += c;
                 OutputStream outputFile = new FileOutputStream("sentence.txt");
                 byte write[] = str.getBytes();
                 for(byte x: write){
                 outputFile.write(x);
                 }
                 outputFile.close();
                 
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

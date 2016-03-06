package summarizer.gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PDFFilter {
    public static void doFilter() throws FileNotFoundException, IOException{
        System.out.println("pdf filter started...");
        BufferedReader  br;
        char c;
        String s;
        int total=0,sqTotal=0,noOfLines=1,col=0;
        int mean,variance,sd;
        //SMALL CASE FILTER
        
        String op="";
        
        
        //LINE BREAK FILTER
        br = new BufferedReader(new FileReader("input.txt"));
        while ((s = br.readLine()) != null) {    
            noOfLines++;
            total += s.length();
                
        }
        mean = total/noOfLines;
        System.out.println("Mean: "+mean);
        
        br = new BufferedReader(new FileReader("input.txt"));
        while ((s = br.readLine()) != null) {
            sqTotal += Math.pow((mean-s.length()),2);
        }
        
        variance = sqTotal/noOfLines;
        sd = (int)Math.pow(variance, 0.5);
        int cutoff = mean - sd;
        int oldcutoff = cutoff;
        
        System.out.println("variance: "+variance+" sd: "+sd+" cutoff "+cutoff);
        
        //SECOND FILTER
     for(int i=0; i<100; i++){
        System.out.println("Second filter");
        br = new BufferedReader(new FileReader("input.txt"));
        while ((s = br.readLine()) != null) {  
            if(s.length()>cutoff){
                noOfLines++;
                total += s.length();
            }   
        }
        mean = total/noOfLines;
        System.out.println("Mean: "+mean);
        
        br = new BufferedReader(new FileReader("input.txt"));
        
        while ((s = br.readLine()) != null) {
            if(s.length()>cutoff){
                sqTotal += Math.pow((mean-s.length()),2);
            }
        }
        
        variance = sqTotal/noOfLines;
        sd = (int)Math.pow(variance, 0.5);
        cutoff = mean - sd;
        
        System.out.println("variance: "+variance+" sd: "+sd+" cutoff "+cutoff);
        if(oldcutoff==cutoff){break;}
        oldcutoff = cutoff;
     }   
        op="";
        br = new BufferedReader(new FileReader("input.txt"));
        while ((s = br.readLine()) != null) {
               if(s.length()<=cutoff){
                   op+=(s+System.getProperty("line.separator"));
                   //System.out.println("new para");
               }else{
                   op+=(s.substring(0, s.length()));
               }
        }
        Main.writeFile(op);
        op="";
        //SMALL CASE FILTER
       /* System.out.println("small case filter started...");
        br = new BufferedReader(new FileReader("input.txt"));
        while ((s = br.readLine()) != null) {
            if(s.charAt(0)>='a' && s.charAt(0)<='z'){
                int i=0;
                String temp = "";
                String[] a = s.split(".");
                if(a.length==1){
                    
                } else{
                    for(int j=1; j<a.length;j++){
                        op += a[j];
                    }
                }
                //while(s.charAt(i)!='.'){temp+=s.charAt(i);i++;if(i>=s.length())break;}
                //i++;
                //op+=s.substring(i-1, s.length());
                if(a.length>0)
                System.out.println("[removed] "+a[0]);
            }
            else{
                op+=s;
            }
        }
        Main.writeFile(op);
        * 
        */
    }
    
    public static void main(String args[]){
        try {
            doFilter();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDFFilter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PDFFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

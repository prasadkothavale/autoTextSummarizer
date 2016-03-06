package sentense;


import java.io.*;
import java.util.ArrayList;

public  class summary
{
 
 File fp;  
 double scnt;
 ArrayList als;
  
 public summary()
  {

	
				
	
	
	
als=new ArrayList();	
  }

 


 


 public void setdocument(File fp) {

	try {
	    int sz=(int)fp.length();
	    byte bs[]=new byte[sz];			
	    FileInputStream fis=new FileInputStream(fp);
	    fis.read(bs);
	    		  			
	    fis.close(); 	
	  }

	catch(IOException ex){}
   }


public void separatesentense(File fp)
 {	long count = 0;
	Sentense sc=new Sentense(fp);
	sc.separatesentense(als);									         		
	//outdoc.setText("");	
	//outdoc.setText("NO OF SENTENSE :"+als.size() +"\n\n");
	scnt=als.size();
        String op = "";
	for(int i=0;i<als.size();i++)
	 {	  
	   slist sl=(slist)als.get(i);  	 
	   //outdoc.setText( outdoc.getText() + "\n " + (i+1) +":  " + sl.getrawsentense());
           op+=sl.getrawsentense()+System.getProperty("line.separator");
           count++;
           if(count%50==0)System.out.println(""+count);
           try{
       OutputStream outputFile = new FileOutputStream("sentence.txt");
       byte write[] = op.getBytes();
        for(byte x: write){
            outputFile.write(x);
        }
       outputFile.close();
       }catch(Exception e){
           e.printStackTrace();
       }
	 }		
 }










 public static void main(String str[])
 {
     //JFrame.setDefaultLookAndFeelDecorated(true);
     summary sm=new summary();
	
 }   
}

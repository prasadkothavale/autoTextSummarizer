package wordnet;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.didion.jwnl.JWNLException;

public class MyWord {
    public String word;
    public int lineNo;
    public MyWord(String w, int l){
        try {
            word = w;
            lineNo = l;
            WordNetHandler.getRelation(this);
        } catch (JWNLException | FileNotFoundException ex) {
            Logger.getLogger(MyWord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<String>Synonym = new ArrayList<>();
    public ArrayList<String>Hypernym = new ArrayList<>();
    public ArrayList<String>Hyponym = new ArrayList<>();
    public ArrayList<String>PartOf = new ArrayList<>();
    public ArrayList<String>Gloss = new ArrayList<>();
    
    public boolean isSame(String w){
        if(w.equalsIgnoreCase(word)) return true;
        else return false;
    }
    
    public boolean isSame(MyWord w){
        if(w.word.equalsIgnoreCase(word)) return true;
        else return false;
    }
    
    public boolean isSynonym(String w){
        if(Synonym.contains(w)) return true;
        else return false;
    }
    public boolean isSynonym(MyWord w){
        if(Synonym.contains(w.word)) return true;
        else if (w.Synonym.contains(this.word)) return true;
        else return false;
    }
    
    public boolean isHypernym(String w){
        if(Hypernym.contains(w)) return true;
        else return false;
    }
    
    public boolean isHypernym(MyWord w){
        return (Hypernym.contains(w.word)||w.Hypernym.contains(word));
    }
     
      public boolean isHyponym(String w){
        if(Hyponym.contains(w)) return true;
        else return false;
    }
      
      public boolean isHyponym(MyWord w){
        return (Hyponym.contains(w.word)||w.Hyponym.contains(word));
    }
      
     public boolean isPartOf(String w){
        if(PartOf.contains(w)) return true;
        else return false;
    }
     
    public boolean isPartOf(MyWord w){
        return (PartOf.contains(w.word)||w.PartOf.contains(word));
    }
     
     public boolean isGloss(String w){
        for(int i=0; i<Gloss.size(); i++){
             String s[] = Gloss.get(i).split(" ");
             for(String x:s){
                 if(x.equals(w)) return true;
             }
        }
        return false;
    }
     
     public boolean isGloss(MyWord w){
        for(int i=0; i<Gloss.size(); i++){
             String s[] = Gloss.get(i).split(" ");
             for(String x:s){
                 if(x.equals(w.word)) return true;
             }
        }
        for(int i=0; i<w.Gloss.size(); i++){
             String s[] = w.Gloss.get(i).split(" ");
             for(String x:s){
                 if(x.equals(word)) return true;
             }
        }
        return false;
    }
     
    public boolean isSybling(MyWord w){
        for(String x:Synonym){
            for(String y:w.Synonym){
                if(x.equalsIgnoreCase(y)) return true;
            }
            for(String y:w.PartOf){
                if(x.equalsIgnoreCase(y)) return true;
            }
            for(String y:w.Hypernym){
                if(x.equalsIgnoreCase(y)) return true;
            }
            for(String y:w.Hyponym){
                if(x.equalsIgnoreCase(y)) return true;
            }
        }
        
        for(String x:PartOf){
            for(String y:w.Synonym){
                if(x.equalsIgnoreCase(y)) return true;
            }
            for(String y:w.PartOf){
                if(x.equalsIgnoreCase(y)) return true;
            }
            for(String y:w.Hypernym){
                if(x.equalsIgnoreCase(y)) return true;
            }
            for(String y:w.Hyponym){
                if(x.equalsIgnoreCase(y)) return true;
            }
        }
        
        for(String x:Hyponym){
            for(String y:w.Synonym){
                if(x.equalsIgnoreCase(y)) return true;
            }
            for(String y:w.PartOf){
                if(x.equalsIgnoreCase(y)) return true;
            }
            for(String y:w.Hypernym){
                if(x.equalsIgnoreCase(y)) return true;
            }
            for(String y:w.Hyponym){
                if(x.equalsIgnoreCase(y)) return true;
            }
        }
        
        for(String x:Hypernym){
            for(String y:w.Synonym){
                if(x.equalsIgnoreCase(y)) return true;
            }
            for(String y:w.PartOf){
                if(x.equalsIgnoreCase(y)) return true;
            }
            for(String y:w.Hypernym){
                if(x.equalsIgnoreCase(y)) return true;
            }
            for(String y:w.Hyponym){
                if(x.equalsIgnoreCase(y)) return true;
            }
        }
        return false;
    }
    
    public boolean isSynonymSybling(MyWord w){
        for(String x:w.Synonym){
            for(String y:Synonym){
                if(x.equalsIgnoreCase(y)){/*System.out.println(x+"~"+y);*/ return true;}
            }
        }
        
        
        return false;
    }
}

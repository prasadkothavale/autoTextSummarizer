package chain;
import java.util.ArrayList;
import wordnet.MyWord;
public class Chain {
    ArrayList<MyWord> words = new ArrayList<>();
    int score=0;
    
    public void print(){
        System.out.print("\n["+score+"] ");
        for(int i=0; i<words.size(); i++)
            System.out.print(words.get(i).word+"("+words.get(i).lineNo+")--");
        System.out.print("\b\b");
    }
    
    public boolean add(MyWord w){
        if (words.isEmpty()) {
            return false;
        } else if (words.get(0).isSame(w)) {
            //System.out.println(words.get(0).word+"("+words.get(0).lineNo+") same "+w.word+"("+w.lineNo+")");
            words.add(w);
            score += 10;
            return true;
        } else if (words.get(0).isSynonym(w)) {
            //System.out.println(words.get(0).word+"("+words.get(0).lineNo+") synonym "+w.word+"("+w.lineNo+")");
            words.add(w);
            score += 10;
            return true;
        } else if (words.get(0).isPartOf(w)) {
           //System.out.println(words.get(0).word+"("+words.get(0).lineNo+") part of "+w.word+"("+w.lineNo+")");
            words.add(w);
            score += 7;
            return true;
        } else if (words.get(0).isHypernym(w)) {
            //System.out.println(words.get(0).word+"("+words.get(0).lineNo+") hypernym "+w.word+"("+w.lineNo+")");
            words.add(w);
            score += 5;
            return true;
        } else if (words.get(0).isHyponym(w)) {
           //System.out.println(words.get(0).word+"("+words.get(0).lineNo+") hyponym "+w.word+"("+w.lineNo+")");
            words.add(w);
            score += 3;
            return true;
        } else if (words.get(0).isGloss(w)) {
            //System.out.println(words.get(0).word+"("+words.get(0).lineNo+") gloss "+w.word+"("+w.lineNo+")");
            words.add(w);
            score += 2;
            return true;
        } else if (words.get(0).isSybling(w)) {
            //System.out.println(words.get(0).word+"("+words.get(0).lineNo+") Sybling "+w.word+"("+w.lineNo+")");
            words.add(w);
            score += 1;
            return true;
        }
        return false;
    }
}

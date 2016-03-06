package wordnet;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;
import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Pointer;
import net.didion.jwnl.data.PointerTarget;
import net.didion.jwnl.data.PointerType;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.dictionary.Dictionary;


public class WordNetHandler {
    static Dictionary dictionary;
    public static void initialize()throws JWNLException, FileNotFoundException{
         JWNL.initialize(new FileInputStream("file_properties.xml"));
         dictionary = Dictionary.getInstance();
    }
    public static void getRelation(MyWord wrd)throws JWNLException, FileNotFoundException{
        
        final IndexWord indexWord = dictionary.lookupIndexWord(POS.NOUN, wrd.word);
        
        try{
            final Synset[] senses = indexWord.getSenses();
           
                
                
         //IndexWord indexWord = wordnet.lookupIndexWord(POS.NOUN, "wife");
         Synset[] synSets = indexWord.getSenses();
         
         for (Synset synset : synSets){
            Word[] words = synset.getWords();
            for (Word word : words){
               wrd.Synonym.add(word.getLemma());
            }
         }
         
         for (Synset synset : synSets)
         {
               PointerTarget[] targets = synset.getTargets(PointerType.HYPERNYM);
               for (PointerTarget target : targets){
                  Word[] words = ((Synset) target).getWords();
                  for (Word word : words){
                     String tmp[] = word.getLemma().split("_");
                     for(String x:tmp){wrd.Hypernym.add(x);}
                     
                  }
               }
         }
         
         
         
         for (Synset synset : synSets){
               PointerTarget[] targets = synset.getTargets(PointerType.HYPONYM);
               for (PointerTarget target : targets){
                  Word[] words = ((Synset) target).getWords();
                  for (Word word : words){
                      String tmp[] = word.getLemma().split("_");
                      for(String x:tmp){wrd.Hyponym.add(x);}
                  }
               }
         }
                //
                 for (int i=0; i<senses.length; i++) {
                Synset sense = senses[i];
                //System.out.println((i+1) + ". " + sense.getGloss());
                wrd.Gloss.add(sense.getGloss());
                Pointer[] holo = sense.getPointers(PointerType.PART_HOLONYM);
                for (int j=0; j<holo.length; j++) {
                  Synset synset = (Synset) (holo[j].getTarget());
                  Word synsetWord = synset.getWord(0);
                  wrd.PartOf.add(synsetWord.getLemma());
                  //System.out.print("  -part-of-> " + synsetWord.getLemma());
                  //System.out.println(" = " + synset.getGloss());
                }
            }
        }catch(NullPointerException e){/*System.out.println("Word not found");*/}
    
    }
     public static void main(String args[]) throws JWNLException, FileNotFoundException{
        initialize();
        
       //  JWNL.initialize(new FileInputStream("file_properties.xml"));
       // final Dictionary dictionary = Dictionary.getInstance();
         //for(int aa=0; aa<1000; aa++){
        final IndexWord indexWord = dictionary.lookupIndexWord(POS.NOUN, "summary");
        try{
            final Synset[] senses = indexWord.getSenses();
            
               
                
                Set<String> synonyms = new HashSet<String>();
         //IndexWord indexWord = wordnet.lookupIndexWord(POS.NOUN, "wife");
         Synset[] synSets = indexWord.getSenses();
         
         for (Synset synset : synSets)
         {
            Word[] words = synset.getWords();
            
            for (Word word : words)
            {
               synonyms.add(word.getLemma());
            }
         }
         System.out.println(synonyms);
         //check if synonyms is empty or not.
         //to iterate through synonym list and form an array.
         /*String syns = new String;
         Iterator<String> itr = synonyms.iterator();
         int k=0;
         while(itr.hasNext())
         {
             syns = itr.next();
             System.out.println(syns);
             k++;
         }*/
         
         Set<String> hypernyms = new HashSet<String>();
         for (Synset synset : synSets)
         {
               PointerTarget[] targets = 
                  synset.getTargets(PointerType.HYPERNYM);
               for (PointerTarget target : targets)
               {
                  Word[] words = ((Synset) target).getWords();
                  for (Word word : words)
                  {
                     hypernyms.add(word.getLemma());
                  }
               }
         }
         System.out.println(hypernyms);
         
         Set<String> hyponyms = new HashSet<String>();
         for (Synset synset : synSets)
         {
               PointerTarget[] targets = 
                  synset.getTargets(PointerType.HYPONYM);
               for (PointerTarget target : targets)
               {
                  Word[] words = ((Synset) target).getWords();
                  for (Word word : words)
                  {
                     hyponyms.add(word.getLemma());
                  }
               }
         }
         System.out.println(hyponyms);
                //
                for (int i=0; i<senses.length; i++) {
                     Synset sense = senses[i];
                System.out.println((i+1) + ". " + sense.getGloss());
                Pointer[] holo = sense.getPointers(PointerType.PART_HOLONYM);
                for (int j=0; j<holo.length; j++) {
                  Synset synset = (Synset) (holo[j].getTarget());
                  Word synsetWord = synset.getWord(0);
                  System.out.print("  -part-of-> " + synsetWord.getLemma());
                  System.out.println(" = " + synset.getGloss());
                }
            }
        }catch(NullPointerException e){System.out.println("Word not found");}
     }
     //}
}

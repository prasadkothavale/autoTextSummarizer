package tts;

import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
public class JAVATTS {
    
    public static void speak(String text){
        System.out.println("TTS Started");
 try
 {
   System.setProperty("freetts.voices",
    "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
   
  // System.setProperty("freetts.voices",
   //                 "com.sun.speech.freetts.en.us.cmu_time_awb.AlanVoiceDirectory");
    
   Central.registerEngineCentral
    ("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
   Synthesizer  synthesizer =
    Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
   synthesizer.allocate();
   synthesizer.resume();
   synthesizer.speakPlainText(text, null); //METHOD TO CALL TTS
   synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
   synthesizer.deallocate();
  }
   catch(Exception e)
   {
     e.printStackTrace();
   }
 }
    
    
    
    
 public static void main(String[] args){
 try
 {
   //System.setProperty("freetts.voices",
    //"com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
   
   System.setProperty("freetts.voices",
                    "com.sun.speech.freetts.en.us.cmu_time_awb.AlanVoiceDirectory");
    
   Central.registerEngineCentral
    ("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
   Synthesizer  synthesizer =
    Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
   synthesizer.allocate();
   synthesizer.resume();
   synthesizer.speakPlainText("time is now 1 hours 00 minutes am.", null);
   synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
   synthesizer.deallocate();
  }
   catch(Exception e)
   {
     e.printStackTrace();
   }
 }
}

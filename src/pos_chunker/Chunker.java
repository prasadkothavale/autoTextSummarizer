package pos_chunker;

import java.io.*;
import java.net.*;
import java.util.*;

public class Chunker
{
	private List rules = new ArrayList();

	public static void doChunk() throws Exception
	{
		Chunker c = new Chunker((new File("lib\\rules")).toURL());

		BufferedReader in = new BufferedReader(new FileReader("lib\\pos_tag_dict"));

		String line = in.readLine();

		Map chunkTags = new HashMap();

		while (line != null)
		{
			if (!line.trim().equals(""))
			{
				String[] tags = line.split(" ");
				chunkTags.put(tags[0],tags[1]);
			}

			line = in.readLine();
		}

		in.close();

		in = new BufferedReader(new FileReader("pos.txt"));

		line = in.readLine();
                String op = "";
		while (line != null)
		{
			String[] tokens = line.split(" ");

			List wl = new ArrayList();
			List tl = new ArrayList();
			List pl = new ArrayList();

			for (int i = 0 ; i < tokens.length ; ++i)
			{
				String[] data = tokens[i].split("/");
                                if(data.length<=1){}else{
				wl.add(data[0]);
				pl.add(data[1]);

				String ct = (String)chunkTags.get(data[1]);

				if (ct == null) ct = "I";

				tl.add(ct);
                                }
			}

			tl = c.chunkSentence(wl,tl,pl);

			boolean inBaseNP = false;
			boolean lineBegin = true;

			for (int i = 0 ; i < wl.size() ; ++i)
			{
				String ct = (String)tl.get(i);

				if (inBaseNP)
				{
					if (ct.equals("B"))
					{
						//System.out.print(" ] [");
                                                op +=(" ] [");
					}
					else if (ct.equals("O"))
					{
						//System.out.print(" ]");
                                                op +=" ]";
						inBaseNP = false;
					}
				}
				else
				{
					if (ct.equals("B") || ct.equals("I"))
					{
						if (!lineBegin) {/*System.out.print(" ");*/op += " ";}
						lineBegin = false;
						//System.out.print("[");
                                                op += "[";
						inBaseNP = true;
					}
				}
				if (!lineBegin){ /*System.out.print(" ");*/op+=" ";}
				lineBegin = false;
				//System.out.print(wl.get(i) + "/" + pl.get(i));
                                op+=wl.get(i) + "/" + pl.get(i);
			}

			if (inBaseNP)
			{
				//System.out.print("]");
                                op+="]";
			}

			//System.out.println();
                        op+=System.getProperty("line.separator");
			line = in.readLine();
		}
                try{
       OutputStream outputFile = new FileOutputStream("chunked.txt");
       byte write[] = op.getBytes();
        for(byte x: write){
            outputFile.write(x);
        }
       outputFile.close();
       }catch(Exception e){
           e.printStackTrace();
       }
	}

	/**
	 * The only constructor that reads the rules from a URL.
	 * @param u the URL of the rules file.
	 **/
	public Chunker(URL u) throws IOException
	{
		//Open up the rules file read for reading
		BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream()));

		//read in the first rule from the file
		String rule = in.readLine();

		while (rule != null)
		{
			//while there are still rules to process...

			if (!rule.trim().equals(""))
			{
				//create and add a rule to the list of rules
				rules.add(new Rule(rule));
			}

			//read in the next rule;
			rule = in.readLine();
		}
	}

	/**
	 * This is the method which does all the work and returns
	 * an updated set of chunk tags.
	 * @param words an ordered List of the words within the sentence.
	 * @param tags an ordered List of the chunk tags within the sentence.
	 * @param pos an ordered List of the POS tags within the sentence.
	 * @return an ordered List of the updated chunk tags for the sentence.
	 **/
	public List chunkSentence(List words, List tags, List pos)
	{
		//add the word/pos/tag that represents the end of
		//the sentence, cos some of the rules match against
		//the end of the sentence
		words.add("ZZZ");
		pos.add("ZZZ");
		tags.add("Z");

		//Get an iterator over the rules and loop
		//through them...
		Iterator it = rules.iterator();
		while (it.hasNext())
		{
			//create an empty list to hold the new
			//chunk tags for this iterations
			List newTags = new ArrayList();

			//get the next rule we are going to apply
			Rule r = (Rule)it.next();

			//loop over all the words in the sentence
			for (int i = 0 ; i < words.size() ; ++i)
			{
				if (r.match(i,words,tags,pos))
				{
					//if the rule matches against the current
					//word in the sentence then and the new tag
					//from the rule to the new tag list
					newTags.add(r.getNewTag());
				}
				else
				{
					//the rule didn't match so simply copy the
					//chunk tag that was already assigned
					newTags.add(tags.get(i));
				}
			}

			//now replace the old tags with the new ones ready
			//for running the next rule, this stops rule-chaining
			tags = newTags;
		}

		//remove the last token from each list as these
		//are not part of the original input sentence
		words.remove(words.size()-1);
		pos.remove(pos.size()-1);
		tags.remove(tags.size()-1);

		//return the final updated chunk tag lists
		return tags;
	}
}
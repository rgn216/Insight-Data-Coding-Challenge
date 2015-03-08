import java.util.SortedMap;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Collection;
import java.util.Set;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WordCount{
        
    public static void main(String[] args) throws FileNotFoundException, IOException{ 
        
        SortedMap<String, Integer> wordCount = new TreeMap<String, Integer>();
        
        File folder = new File("../wc_input");
        File[] files = folder.listFiles();
        for (File f : files ){
            FileReader fileReader = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // convert to lowercase
            String Line = bufferedReader.readLine().toLowerCase();
            while( Line != null ){
                // split the line,
                String[] words = Line.split(" ");
                for ( int i = 0; i < words.length; i++){
                    // remove the punctuation 
                    words[i] = words[i].replaceAll("[^a-zA-Z]","");
                    if (!wordCount.containsKey(words[i]) && words[i].length() >0 ){
                        //update the value of the word if it is already in the hashtable
                        wordCount.put( words[i] , 1 );
                    }
                    else if (words[i].length() >0){
                        //otherwise create a new entry in the hashtable
                        wordCount.put(words[i] , wordCount.get(words[i]) + 1);
                    }
                    
                }
                Line = bufferedReader.readLine();
                if (Line != null){
                    Line = Line.toLowerCase();
                }
            }
            
            FileWriter fileWriter = new FileWriter(new File("../wc_output/wc_result.txt"));
            
            Set<String> K = new HashSet<String>();
            K = wordCount.keySet();
            
            for (String str : K ){
                fileWriter.write(str + "\t" + wordCount.get(str)+"\n");
            } 
                
            fileWriter.close();
           
        }       
    }
}

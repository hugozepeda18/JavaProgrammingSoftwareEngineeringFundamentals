import edu.duke.*;
import java.util.*;

public class MapGladLib {
	
	private HashMap<String, ArrayList<String>> myMap;

	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public MapGladLib(){
		myMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public MapGladLib(String source){
		initializeFromSource(source);
		myRandom = new Random();
	}

	private void initializeFromSource(String source) {
		String[] labels = {"country", "noun", "animal", "adjective", "name", "color", "timeframe"};
		for(String s: labels){
			ArrayList<String> list = readIt(source+"/"+s+".txt")
			myMap.put(s, list);
		}
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (label.equals("country")) {
			return randomFrom(myMap.get("country"));
		}
		if (label.equals("color")){
			return randomFrom(myMap.get("color"));
		}
		if (label.equals("noun")){
			return randomFrom(myMap.get("noun"));
		}
		if (label.equals("name")){
			return randomFrom(myMap.get("name"));
		}
		if (label.equals("adjective")){
			return randomFrom(myMap.get("adjective")); 
		}
		if (label.equals("animal")){
			return randomFrom(myMap.get("animal")); 
		}
		if (label.equals("timeframe")){
			return randomFrom(myMap.get("timeframe"));
		}
		if (label.equals("verb")){
			return randomFrom(myMap.get("verb"));
		}
		if (label.equals("fruit")){
			return randomFrom(myMap.get("fruit"));
		}
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		return "**UNKNOWN**";
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first+1,last));
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public void makeStory(){
	    System.out.println("\n");
		String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 60);
	}
	


}

/**
 * Reads a chosen CSV file of our preferences and prints each field.
 * 
 * @author Duke Software Team
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class FirstCSVExample {
	public void readFood() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		for (CSVRecord record : parser){
			System.out.print("Name: " + record.get("Name") + " ");
			System.out.print("Favorite Color: " + record.get("Favorite Color") + " ");
			System.out.println("Favorite Food: " + record.get("Favorite Food"));
		}
	}
}

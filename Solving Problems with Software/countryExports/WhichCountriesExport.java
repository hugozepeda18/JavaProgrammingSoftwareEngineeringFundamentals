/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public void gettingCountry(CSVParser parser, String countryOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Country");
            //Check if it contains exportOfInterest
            if (export.contains(countryOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                System.out.println(country + ": " + exports + ": " + value);
            }
        }
    }
    
    public void twoProducts(CSVParser parser, String product1, String product2) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(product1)) {
                if(export.contains(product2)){
                    String country = record.get("Country");
                    System.out.println(country);
                }
            }
        }
    }
    
    public void exportingCountries(CSVParser parser, String product1) {
        //for each row in the CSV File
        int count = 0;
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(product1)) {
                count++;
            }
        }
        System.out.println(count);
    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //listExporters(parser, "coffee");
        //parser = fr.getCSVParser();
        //gettingCountry(parser, "Nauru");
        //twoProducts(parser, "cotton", "flowers");
        exportingCountries(parser, "cocoa");
    }
}

/**
 * Find the highest (hottest) temperature in a file of CSV weather data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            //If largestSoFar is nothing
            if (largestSoFar == null) {
                largestSoFar = currentRow;
            }
            //Otherwise
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                //Check if currentRow’s temperature > largestSoFar’s
                if (currentTemp > largestTemp) {
                    //If so update largestSoFar to currentRow
                    largestSoFar = currentRow;
                }
            }
            //The largestSoFar is the answer
        }
        return largestSoFar;
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord coldestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            //If largestSoFar is nothing
            if (coldestSoFar == null) {
                coldestSoFar = currentRow;
            }
            //Otherwise
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(currentTemp == -9999) {
                    
                } else {
                    if (currentTemp < largestTemp) {
                    //If so update largestSoFar to currentRow
                    coldestSoFar = currentRow;
                    }
                }
                //Check if currentRow’s temperature > largestSoFar’s
            }
            //The largestSoFar is the answer
        }
        return coldestSoFar;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord lowestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            //If largestSoFar is nothing
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            }
            //Otherwise
            else {
                if(currentRow.get("Humidity").equals("N/A")){
                    System.out.println("Cannot work this row");
                }else{
                    double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                    double largestTemp = Double.parseDouble(lowestSoFar.get("Humidity"));
                    if (currentTemp < largestTemp) {
                        //If so update largestSoFar to currentRow
                        lowestSoFar = currentRow;
                    }
                }
            }
        }
        return lowestSoFar;
    }
    
    public double average(CSVParser parser) {
        //start with largestSoFar as nothing
        double average = 0;
        double count = 0;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            average += Double.parseDouble(currentRow.get("TemperatureF"));
            count++;
        }
        
        return average/count;
    }
    
    public void testAverage() {
        FileResource fr = new FileResource("data/2013/weather-2013-08-10.csv");
        double result = average(fr.getCSVParser());
        System.out.println(result);
    }
    
    public void testHottestInDay() {
        FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("TimeEST"));
    }
    
    public void testColdestInDay() {
        FileResource fr = new FileResource("data/2014/weather-2014-05-01.csv");
        CSVRecord largest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("DateUTC"));
    }
    
    public void testLowestInDay() {
        FileResource fr = new FileResource("data/2014/weather-2014-07-22.csv");
        CSVRecord largest = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("lowest humidity was " + largest.get("Humidity") +
                   " at " + largest.get("DateUTC"));
    }
    
    public CSVRecord hottestInManyDays(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord largestSoFar = null;
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = hottestHourInFile(fr.getCSVParser());
            if(largestSoFar == null){
                largestSoFar = current; 
            } else{
                double currentTemp = Double.parseDouble(current.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                //Check if currentRow’s temperature > largestSoFar’s
                if (currentTemp > largestTemp) {
                    //If so update largestSoFar to currentRow
                    largestSoFar = current;
                }
            }
        }
        return largestSoFar;
    }
    
    public CSVRecord coldestInManyDays(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestSoFar = null;
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            if(coldestSoFar == null){
                coldestSoFar = current; 
            } else{
                double currentTemp = Double.parseDouble(current.get("TemperatureF"));
                double largestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                //Check if currentRow’s temperature > largestSoFar’s
                if (currentTemp < largestTemp) {
                    //If so update largestSoFar to currentRow
                    coldestSoFar = current;
                }
            }
        }
        return coldestSoFar;
    }
    
    public CSVRecord lowestInManyDays(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestSoFar = null;
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            if(coldestSoFar == null){
                coldestSoFar = current; 
            } else{
                if(current.get("Humidity").equals("N/A")){
                    System.out.println("Cannot work this row");
                }else{
                    double currentTemp = Double.parseDouble(current.get("Humidity"));
                    double largestTemp = Double.parseDouble(coldestSoFar.get("Humidity"));
                    if (currentTemp < largestTemp) {
                        //If so update largestSoFar to currentRow
                        coldestSoFar = current;
                    }
                }
            }
        }
        return coldestSoFar;
    }
    
    public void testHottestManyDay() {
        CSVRecord largestSoFar = hottestInManyDays();
        System.out.println("hottest temperature was " + largestSoFar.get("TemperatureF") +
                   " at " + largestSoFar.get("DateUTC"));
    }
    
    public void testColdestManyDay() {
        CSVRecord largestSoFar = coldestInManyDays();
        System.out.println("lowest temperature was " + largestSoFar.get("TemperatureF") +
                   " at " + largestSoFar.get("DateUTC"));
    }
    
}

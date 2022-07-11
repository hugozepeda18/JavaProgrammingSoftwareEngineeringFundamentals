/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += 1;
            if (rec.get(1).equals("F")){
                totalGirls += 1;
            }else {
                totalBoys += 1;
            }
        }
        System.out.println("Total: " + totalBirths + " boys: " + totalBoys + " girls: " + totalGirls);
    }
    
    public int getRank(FileResource fr, String name, String gender){
        int positionBoys = 0;
        int positionGirls = 0;
        int totalGirls = 0;
        int totalBoys = 0; 
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals(gender)){
                positionGirls += 1;
                totalGirls += Integer.parseInt(rec.get(2));
                if(rec.get(0).equals(name)){
                    totalGirls -= Integer.parseInt(rec.get(2));
                    System.out.println(totalGirls);
                    return positionGirls;
                }
            }else{
                positionBoys += 1;
                totalBoys += Integer.parseInt(rec.get(2));
                if(rec.get(0).equals(name)){
                    totalBoys -= Integer.parseInt(rec.get(2));
                    System.out.println(totalBoys);
                    return positionBoys;
                }
            }
        }
        return -1; 
    }
    
    public void getName(FileResource fr, int rank, String gender){
        int positionBoys = 0;
        int positionGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals(gender)){
                positionGirls += 1;
                if(positionGirls == rank){
                    System.out.println("Name: " + rec.get(0));
                    break;
                } 
            }else{
                positionBoys += 1;
                if(positionBoys == rank){
                    System.out.println("Name: " + rec.get(0));
                    break;
                }
            }
        }
    }
    
    public void whatIsNameInYear(){
        FileResource fr = new FileResource();
        int rank = 0;
        getName(fr,rank,"");
        FileResource file = new FileResource();
        getName(file,rank,"");
    }
    
    public void yearOfHighestRank(){
        DirectoryResource dr =  new DirectoryResource();
        double average = 0;
        double suma = 0;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            int rank = getRank(fr,"Salvatore","M");
            if(rank != -1){
                average += rank;
                suma += 1;
            }
            System.out.println(f.getName());
        }
        System.out.println(average/suma);
    }
    
    public void testGetRank () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource();
        System.out.println(getRank(fr,"Frank","M"));
    }
    public void testGetName () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob2014.csv");
        getName(fr,0,"");
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
    }
    
}

/**
 * Finds a protein within a strand of DNA represented as a string of c,g,t,a letters.
 * A protein is a part of the DNA strand marked by start and stop codons (DNA triples)
 * that is a multiple of 3 letters long.
 *
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class TagFinder {
    public String findProtein(String dna) {
        int start = dna.indexOf("atg");
        if (start == -1) {
            return "";
        }
        int stop = dna.indexOf("tag", start+3);
        if ((stop - start) % 3 == 0) {
            return dna.substring(start, stop+3);
        }
        else {
            return "";
        }
    }
    
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon, startIndex);
        while(currIndex != -1){
            if((currIndex - startIndex) % 3 == 0){
                return currIndex;
            }else{
                currIndex = dnaStr.indexOf(stopCodon, currIndex+1);
            }
        }
        return dnaStr.length();
    }
    
    public int findStopCodonWithNegativeValue(String dnaStr, int startIndex, String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon, startIndex);
        while(currIndex != -1){
            if((currIndex - startIndex) % 3 == 0){
                return currIndex;
            }else{
                currIndex = dnaStr.indexOf(stopCodon, currIndex+1);
            }
        }
        return -1;
    }
    
    public String findGene(String dna) {
        int startIndex = dna.indexOf("atg");
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "taa");
        int tagIndex = findStopCodon(dna, startIndex, "tag");
        int tgaIndex = findStopCodon(dna, startIndex, "tga");
        
        int minIndex = Math.min(tgaIndex, Math.min(taaIndex,tagIndex));
        if(minIndex == dna.length()){
            return "";
        }
        return dna.substring(startIndex, minIndex+3);
    }
    
    public String findGeneNegativeValue(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodonWithNegativeValue(dna, startIndex, "TAA");
        int tagIndex = findStopCodonWithNegativeValue(dna, startIndex, "TAG");
        int tgaIndex = findStopCodonWithNegativeValue(dna, startIndex, "TGA");
        int minIndex; 
        if(taaIndex == -1 || (tgaIndex != -1 && tagIndex < taaIndex)){
            minIndex = tgaIndex;
        }else{
            minIndex = taaIndex;
        }
        if(minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }
        if(minIndex == -1){
            return "";
        }
        return dna.substring(startIndex, minIndex+3);
    }
    
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        int minIndex = Math.min(tgaIndex, Math.min(taaIndex,tagIndex));
        if(minIndex == dna.length()){
            return "";
        }
        return dna.substring(startIndex, minIndex+3);
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        while(true){
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length(); 
        }
    }
    
    public void testing() {
        String a = "cccatggggtttaaataataataggagagagagagagagttt";
        String ap = "atggggtttaaataataatag";
        //String a = "atgcctag";
        //String ap = "";
        //String a = "ATGCCCTAG";
        //String ap = "ATGCCCTAG";
        String result = findProtein(a);
        if (ap.equals(result)) {
            System.out.println("success for " + ap + " length " + ap.length());
        }
        else {
            System.out.println("mistake for input: " + a);
            System.out.println("got: " + result);
            System.out.println("not: " + ap);
        }
    }
    
    public void testingNewCode(){
        String dna = "AATGCTAACTAGCTGACTAAT";
        System.out.println(findGene(dna));
    }
    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        int countGenes = 0;
        int countGenesLarger = 0;
        int maxGenes = 0;
        int average = 0;
        while(true){
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()){
                break;
            }
            countGenes = countGenes + 1;
            if(currentGene.length() > 60){
                countGenesLarger = countGenesLarger + 1;
                if(currentGene.length() > maxGenes){
                    maxGenes = currentGene.length();
                }
            }
            if(countingCT(currentGene) > 0.35){
                average = average + 1;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        System.out.println("This dna has a total of: " + countGenes);
        System.out.println("This dna has a total of greater than 60: " + countGenesLarger);
        System.out.println("The greater is: " + maxGenes);
        System.out.println("The average is: " + average);
        return geneList;
    }
    
    public void testOn(String dna){
        System.out.println("Testing getAllGenes on" + dna);
        StorageResource genes = getAllGenes(dna);
        for(String g: genes.data()){
            System.out.println(g);
        }
    }
    
    public float countingCT(String dna){
        int index = 0, count = 0;
        String str = "C";
        while (true)
        {
            index = dna.indexOf(str, index);
            if (index != -1)
            {
                count ++;
                index += str.length();
            }
            else {
                break;
            }
        }
        System.out.println(count);
        str = "G";
        index = 0;
        while (true)
        {
            index = dna.indexOf(str, index);
            if (index != -1)
            {
                count ++;
                index += str.length();
            }
            else {
                break;
            }
        }
        return ((float)count/dna.length());
    }
    public void countingCTG(String dna){
        int index = 0, count = 0;
        String str = "CTG";
        while (true)
        {
            index = dna.indexOf(str, index);
            if (index != -1)
            {
                count ++;
                index += str.length();
            }
            else {
                break;
            }
        }
        System.out.println(count);
    }
    
    public void testCountingCT(){
        String dna = "AATGCTAACTAGCTGACTAAT";
        System.out.println(countingCT(dna));
    }
    
    public void testTotalGenes(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        StorageResource genes = getAllGenes(dna);
        //countingCTG(dna);
    }
}

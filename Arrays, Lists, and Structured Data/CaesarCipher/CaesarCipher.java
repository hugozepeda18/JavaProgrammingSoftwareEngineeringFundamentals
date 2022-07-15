import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        //Compute the shifted alphabet
        String shiftedUpperAlphabet = upperAlphabet.substring(key)+
        upperAlphabet.substring(0,key);
        
        String shiftedLowerAlphabet = lowerAlphabet.substring(key)+
        lowerAlphabet.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            if(Character.isUpperCase(currChar)){
                int idx = upperAlphabet.indexOf(currChar);
                if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedUpperAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
                }
            } else {
                int idx = lowerAlphabet.indexOf(currChar);
                if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedLowerAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
                }
            }
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
 
    public void testCaesar() {
        int key = 15;
        //FileResource fr = new FileResource();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";//fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
}


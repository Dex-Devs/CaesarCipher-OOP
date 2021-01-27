package caesarcipheroop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestCaesar {

    // helper method to get frequencies / occurence of LETTERS in an encrypted message
    private void countLetters(String encrypted, int [] frequencies) {
        
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        // loop through the encrypted Strings's charactere
        for(int i = 0 ; i < encrypted.length() ; i++){
            // get index of counter to increment
            int index = alphabet.indexOf(Character.toUpperCase(encrypted.charAt(i)));
            
            if(index != -1) {
                frequencies[index] += 1;
            }
        }
        
        System.out.println("MOST COMMON WORD LENGTH: " + maxIndex(frequencies));
        
    }
    
    // helper method to get the index of the character with the highest count
    private int maxIndex(int [] freq) {
        int maxIndex = 0;
        
        // loop throught the frequencies
        for(int freqIndex = 0 ; freqIndex < freq.length ; freqIndex++){
            
            // compare values
            if(freq[freqIndex] > freq[maxIndex])
                // override maxIndex
                maxIndex = freqIndex;
            
            
        }
       
        // return max index
        return maxIndex;
    }
    
    // figure out the key for decryption
    String breakCaesar(String input) {
        
        int dkey, maxIndex; 
        
        int [] frequencies = new int[26]; // character occurence counter storage
        
        countLetters(input, frequencies);
        
        maxIndex = maxIndex(frequencies);
        
        // get the decryption key by getting the index from the 4th index with value 'E'
        dkey = maxIndex - 4;
        if(maxIndex < 4) {
            dkey = 26 - (4 - maxIndex);
        }
        
        CaesarCipher cipher = new CaesarCipher(dkey);
        // return decrypted
        return cipher.decrypt(input);
    }
    
    // test cipher
    void simpleTest(){
        
        File fileToRead = new File("C:\\Users\\320\\Documents\\Dexter\\File Handling Test\\manyTexts.txt"); // file from local machine
        
        StringBuilder sBuilder = new StringBuilder();
        
        // file existing on local machine??
        if(fileToRead.exists()){
            
            // read file
            try(Scanner scan = new Scanner(fileToRead)){
                
                while(scan.hasNext()){
                    sBuilder.append(scan.next()).append(" ");
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TestCaesar.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // auto close reader
            
            // print message from file
            
            String messageFromFile = sBuilder.toString().trim();
            System.out.println("Message from file: " + messageFromFile);
            
            CaesarCipher cipher = new CaesarCipher(15);
            String encrypted = cipher.encrypt(messageFromFile); // encrypt message
            
            // print encrypted
            System.out.println("Encrypted message: " + encrypted);
            
            String decrypted = cipher.decrypt(encrypted); // decrypt encrypted
            
            // print decrypted message
            System.out.println("Decrypted message: " + decrypted);
        
            String autoDecrypt = breakCaesar(encrypted); // decrypt without knowing the key
            
            // print auto decrypted message
            System.out.println("Automatedly decrypted message: " + autoDecrypt);
            
        }
    }
    
    
    public static void main(String[] args) {
        new TestCaesar().simpleTest();
    }
    
}

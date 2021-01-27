package caesarcipheroop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestCaesarTwoKeys {
    
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
        } // loop end
       
        // return max index
        return maxIndex;
    }
    
    // helper method to get every other character of a string given the starting position
    private String halfOfString(String encrypted, int start) {
        
        StringBuilder sBuilder = new StringBuilder();
        
        // loop through characters of the String
        for(int k = start ; k < encrypted.length() ; k+=2) {
            sBuilder.append(encrypted.charAt(k));
        } // loop end
        
        // return string
        return sBuilder.toString();
    }
    
    String breakCaesarCipher(String encrypted) {
        
        int dkey1, dkey2, maxIndex1, maxIndex2;
        
        int[] freq1 = new int[26]; // counter for message encrypted using key 1
        int[] freq2 = new int[26]; // counter for message encrypted using key 2
        
        // separate characters encrypted with key1 from characters encrypted with key 2
        String key1Encrypted = halfOfString(encrypted, 0);
        String key2Encrypted = halfOfString(encrypted, 1);
        
        // count occurences
        countLetters(key1Encrypted, freq1); 
        countLetters(key2Encrypted, freq2);
        
        // get max indeces
        maxIndex1 = maxIndex(freq1);
        maxIndex2 = maxIndex(freq2);
        
        // get keys by calculating the location of the index from index 4 which has the value of 'E'
        dkey1 = maxIndex1 - 4;
        dkey2 = maxIndex2 - 4;
        
        if(maxIndex1 < 4 || maxIndex2 < 4) {
            dkey1 = 26 - (4 - maxIndex1);
            dkey2 = 26 - (4 - maxIndex2);
        }
        
        System.out.println("Keys found: [" + dkey1 + ", " + dkey2 + "]");
        CaesarCipherTwoKeys cipher = new CaesarCipherTwoKeys(dkey1, dkey2);
        
        // return decrypted
        return cipher.decrypt(encrypted);
        
    }
    
    void simpleTest(){
        
        File fileToRead = new File("C:\\Users\\320\\Documents\\Dexter\\File Handling Test\\mysteryTwoKeysQuiz.txt"); // file from local machine
        //  File fileToRead = new File("C:\\Users\\320\\Documents\\NetBeansProjects\\CaesarCipherOOP\\src\\files\\manyText.txt"); -- ACCESS FILES OF PROJECT
        
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
        }
        // auto close reader
            
        // print message from file
            
        String messageFromFile = sBuilder.toString().trim();
        System.out.println("Message from file: " + messageFromFile);
        
        CaesarCipherTwoKeys cipherTwoKeys = new CaesarCipherTwoKeys(17, 3);
        String encrypted = cipherTwoKeys.encrypt(messageFromFile); // encrypt message
        
        // print encrypted message
        System.out.println("Encrypted message: " + encrypted);
        
        String decrypted = cipherTwoKeys.decrypt(encrypted); // decrypt message
        
        // print decrypted message
        System.out.println("Decrypted message: " + decrypted);
        
        String autoDecrypt = breakCaesarCipher(messageFromFile); // decrypt wihtout knowing the keys
        
        // print decrypted message
        System.out.println("Automatedly decrypted message: " + autoDecrypt);
        
        
    }
    
    public static void main(String [] args) {
        new TestCaesarTwoKeys().simpleTest();
    }
}

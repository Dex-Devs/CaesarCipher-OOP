package caesarcipheroop;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key){
        mainKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(mainKey) + alphabet.substring(0, mainKey);
    }
    
    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        
        // loop through input's characters
        for(int i = 0 ; i < input.length() ; i ++){
            // get alphabet index of character
           int index = alphabet.indexOf(Character.toUpperCase(input.charAt(i)));
           
           // index found?
           if(index != -1)
               sb.setCharAt(i, shiftedAlphabet.charAt(index));
        }
        
        return sb.toString();
       
    }
    
    public String decrypt(String input){
        CaesarCipher decryptObject = new CaesarCipher(26-this.mainKey);
        return decryptObject.encrypt(input);
        
    }
}

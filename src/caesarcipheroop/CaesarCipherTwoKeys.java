package caesarcipheroop;

public class CaesarCipherTwoKeys {
    
    /* THIS CLASS IS FOR DECRYPTING A MESSAGE WITH 2 KEYS GIVEN THAT:
    **
    **      - The first key was used to encrypt all the characters of the message
    **      - The second key was used to encrypt every other character of the message
    */
    
   private String alphabet;
   private String shiftedAlphabet1;
   private String shiftedAlphabet2;
   private int key1;
   private int key2;
   
   public CaesarCipherTwoKeys(int key1, int key2){
       this.key1 = key1;
       this.key2 = key2;
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        this.shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
   }
   // AND
   public String encrypt(String input) {
       
       StringBuilder sb = new StringBuilder(input);
       
       // loop through input's characters
       for(int i = 0 ; i < input.length() ; i++) {
           // get alphabet index of character
           int index = alphabet.indexOf(Character.toUpperCase(input.charAt(i)));
           
           // index found?
           if(index != -1){
               // check position
               if(i % 2 == 0){
                   // replace with apporipriate encryption value
                   sb.setCharAt(i, shiftedAlphabet1.charAt(index));
               }else {
                   // replace with apporipriate encryption value
                   sb.setCharAt(i, shiftedAlphabet2.charAt(index));
               }
           }
       } // loop end
       
       return sb.toString();
   }
   
   public String decrypt(String input) {
       CaesarCipherTwoKeys decryptToKeys = new CaesarCipherTwoKeys(26-this.key1, 26-this.key2);
        return decryptToKeys.encrypt(input);
   } 
}

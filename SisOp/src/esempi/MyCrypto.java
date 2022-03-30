package esempi;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class MyCrypto {
 
    public static byte[] decrypt(String key, byte[] inputBytes) throws CryptoException{
    	 byte[] outputBytes=null;
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            outputBytes = cipher.doFinal(inputBytes);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException  | InvalidKeyException 
        		| BadPaddingException | IllegalBlockSizeException ex) {
        }
        return outputBytes;
    }

}

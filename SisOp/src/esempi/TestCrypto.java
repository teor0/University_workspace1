package esempi;
import java.io.*;
import javax.swing.JFileChooser;
public class TestCrypto {
	public static void main(String[] args) {
		int i=50;
		String key = String.format("%016d", i);
		System.out.println(key);
		/*JFileChooser jf=new JFileChooser();
		File inputFile=new File("test.txt");
		int v=jf.showOpenDialog(null);
		if(v==JFileChooser.APPROVE_OPTION)
			inputFile =new File(jf.getSelectedFile().getAbsolutePath());*/
        File encryptedFile = new File("C:\\Users\\orlan\\Desktop\\document2021.encryptedOK");
        File decryptedFile = new File("C:\\Users\\orlan\\Desktop\\document2021.decryptedOK");
         
        try {
            //CryptoUtils.encrypt(key, inputFile, encryptedFile);
            CryptoUtils.decrypt("0000001123581321", encryptedFile, decryptedFile);
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        System.out.println("File cryptato e decryptato correttamente");
    }
}

package org.calminfotech.utils;

import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.springframework.stereotype.Service;

import com.sun.jersey.core.util.Base64;

@Service
public class Encryptor extends CustomHibernateDaoSupport {

    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    byte[] arrayBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    SecretKey key;

    public Encryptor() throws Exception {
        myEncryptionKey = "ThisIsSpartaThisIsSparta";
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        ks = new DESedeKeySpec(arrayBytes);
        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = skf.generateSecret(ks);
    }


    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encode(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }


    public String decrypt(String encryptedString) {
        String decryptedText=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decode(encryptedString.getBytes());
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }

    

	
	

	public String createActivationCode(String email) {

		String code = null;
		/*
		 * 
		 * Append system timestamp to email. That way code will be unique
		 * chances are very slim for an email to register at the very same
		 * instance.
		 */
		while (true) {
			try {
				long timestamp = System.currentTimeMillis();
				String str = email + ":" + timestamp;
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(str.getBytes());
				byte[] byteData = md.digest();

				/*
				 * Generate code from MessageDigest object
				 */
				StringBuilder sf = new StringBuilder();
				for (byte b : byteData) {
					sf.append(String.format("%02x", b & 0xff));
				}
				System.out.println(sf.toString());
				code = sf.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (!this.exist(code))
				break;
		}

		return code;
	}

	
	
	
	
	public boolean exist(String activationCode) {
		List list = getHibernateTemplate().find(
				"from UserActivation where activationCode = ?", activationCode);
		if (list.size() != 0)
			return true;
		return false;
	}
	
	
	public String createPasswordCode(String password) {

		String code = null;
		/*
		 * 
		 * Append system timestamp to email. That way code will be unique
		 * chances are very slim for an email to register at the very same
		 * instance.
		 */
		while (true) {
			try {
				long timestamp = System.currentTimeMillis();
				//String str = email + ":" + timestamp;
				String str = password;
				
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(str.getBytes());
				byte[] byteData = md.digest();

				/*
				 * Generate code from MessageDigest object
				 */
				StringBuilder sf = new StringBuilder();
				for (byte b : byteData) {
					sf.append(String.format("%02x", b & 0xff));
				}
				System.out.println(sf.toString());
				code = sf.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (!this.exist(code))
				break;
		}

		return code;
	}
	
}

package com.plant.server.util.crypto;

import com.plant.server.business.services.exceptions.InternalErrorException;
import com.plant.server.commons.properties.CommonProperties;
import com.plant.server.util.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Component
public class CryptUtil {

    @Autowired
    private CommonProperties commonProperties;

    public boolean checkHash(String toHash, String hashed) {
        return !StringUtils.isEmpty(hashed) && hashed.equals(generateHash(toHash));
    }
    public static boolean checkHashed(String hashed1, String hashed2) {
        return !StringUtils.isEmpty(hashed1) && hashed1.equals(hashed2);
    }
    public String generateHash(String toHash) {
        return generateHash(this.commonProperties.getPrivateKeyHash(), toHash);
    }
    public static String generateHash(String privateKey, String toHash) {
        return generateHashAux(privateKey+toHash+privateKey);
    }
    private static String generateHashAux(String toHash) {
        return DigestUtils.md5DigestAsHex(toHash.getBytes());
    }
    public String encrypt(String toEncrypt) throws InternalErrorException {
        try {
            return encrypt(this.commonProperties.getPrivateKeySymmetric(), this.commonProperties.getPrivateKeySymmetricSalt(), toEncrypt);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }
    private static String encrypt(String privateKey, String privateKeySalt, String toEncrypt) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        if (toEncrypt==null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(CryptoCipher.getCryptCipher(privateKey, privateKeySalt).doFinal(toEncrypt.getBytes("UTF-8")));
    }
    public String decrypt(String toDecrypt) throws InternalErrorException {
        try {
            return decrypt(this.commonProperties.getPrivateKeySymmetric(), this.commonProperties.getPrivateKeySymmetricSalt(), toDecrypt);
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    }
    private static String decrypt(String privateKey, String privateKeySalt, String toDecrypt) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
        if (toDecrypt==null) {
            return null;
        }
        return new String(CryptoCipher.getDecryptCipher(privateKey, privateKeySalt).doFinal(Base64.getDecoder().decode(toDecrypt)));
    }
    public static void main(String[] args) {
        try {
            String privateKey = "jaren";
//			String toHash = "toHash";
//			System.out.println(generateHash(privateKey, toHash));
            String privateKeySalt = "jaren";
            String toEncrypt = "toEncrypt";
            System.out.println(encrypt(privateKey, privateKeySalt, toEncrypt));
            System.out.println(decrypt(privateKey, privateKeySalt, encrypt(privateKey, privateKeySalt, toEncrypt)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
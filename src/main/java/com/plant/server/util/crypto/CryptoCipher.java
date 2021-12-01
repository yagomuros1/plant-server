package com.plant.server.util.crypto;


import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class CryptoCipher {
    private static boolean ciphersInit;
    private static Cipher privateCryptCipher;
    private static Cipher privateDecryptCipher;

    private static void initCiphers(String privateKey, String privateKeySalt) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        IvParameterSpec ivspec = new IvParameterSpec(iv);

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(privateKey.toCharArray(), privateKeySalt.getBytes(), 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

        privateCryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        privateCryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);

        privateDecryptCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        privateDecryptCipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);

        ciphersInit = true;
    }

    public static Cipher getCryptCipher(String privateKey, String privateKeySalt) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        if (!ciphersInit) {
            initCiphers(privateKey, privateKeySalt);
        }
        return privateCryptCipher;
    }
    public static Cipher getDecryptCipher(String privateKey, String privateKeySalt) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        if (!ciphersInit) {
            initCiphers(privateKey, privateKeySalt);
        }
        return privateDecryptCipher;
    }


}
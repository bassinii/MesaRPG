/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Tiago
 */
public class SenhaUtil {

    public static String convertStringToMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public static String gerarNovaSenha() {
        String[] carct = {  "0", "1", "2", "3", "4", "5", "6", "7",
                            "8", "9", "a", "b", "c", "d", "e", "f",
                            "g", "h", "i", "j", "k", "l", "m", "n",
                            "o", "p", "q", "r", "s", "t", "u", "v",
                            "w", "x", "y", "z", "A", "B", "C", "D",
                            "E", "F", "G", "H", "I", "J", "K", "L",
                            "M", "N", "O", "P", "Q", "R", "S", "T",
                            "U", "V", "W", "X", "Y", "Z"};
        String senha = "";
        for (int x = 0; x < 10; x++) {
            int j = (int) (Math.random() * carct.length);
            senha += carct[j];
        }
        return senha;
    }

}

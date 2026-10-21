package com.example;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;

public class ClauPublica {
     public static void main(String[] args) throws Exception {
        
        ClauPublica cp = new ClauPublica();
        
        String msg = "Missatge de prova per xifrar áéíóú àèìòù äëïöü";
        
        KeyPair parellClaus = cp.generaParellClausRSA();
        
        byte[] msgXifrat = cp.xifraRSA(msg, parellClaus.getPublic());
        
        System.out.println("=========================================");
        System.out.println("Text xifrat: ");
        System.out.println(DatatypeConverter.printHexBinary(msgXifrat));
        System.out.println("=========================================");
        
        String msgDesxifrat = cp.desxifraRSA(msgXifrat, parellClaus.getPrivate());
        System.out.println("Text desxifrat: " + msgDesxifrat);
        System.out.println("=========================================");
        
        String strClauPub = DatatypeConverter.printHexBinary(parellClaus.getPublic().getEncoded());
        String strClauPriv = DatatypeConverter.printHexBinary(parellClaus.getPrivate().getEncoded());
        
        System.out.println("=========================================");
        System.out.println("Clau pública: " + strClauPub);
        System.out.println("=========================================");
        System.out.println("Clau privada: " + strClauPriv);
        System.out.println("=========================================");
    }


    
    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator keys = KeyPairGenerator.getInstance("RSA");
        keys.initialize(2048);
        return keys.genKeyPair();
    }
    
    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        return cipher.doFinal(msg.getBytes("UTF-8"));
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada);
        byte[] msgXifratBytes = cipher.doFinal(msgXifrat);
        return new String(msgXifratBytes, "UTF-8");
    }
}

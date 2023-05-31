package org.example;

import java.security.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();

        PrivateKey privateKey = pair.getPrivate();

        PublicKey publicKey = pair.getPublic();

        GetInputText getInputText = new GetInputText();
        String message = getInputText.returnMyString();


        //creation of Signature object
        Signature signature = Signature.getInstance("SHA256withRSA");

        // Initializing the signature for signing (use of private key)
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        byte[] digitalSignature = signature.sign();

        System.out.println("Original message: " + message);
        Printer printer = new Printer();
       // printer.printTheSignature(digitalSignature);
        System.out.println("Digital signature length: " + digitalSignature.length);

        System.out.println("Ar pakeisti skaitmeninio parašo reikšmę?");

        GetInputBool getInputBool = new GetInputBool();

        boolean change = getInputBool.getBooleanInput();

        byte[] signatureForModification = digitalSignature.clone();

        if(change)
        {
            // Modify the digital signature (example: invert the first byte)
            signatureForModification[0] = (byte) ~signatureForModification[0];

            System.out.println("Modified digital signature: " + signatureForModification);
        }

        String message2 = getInputText.returnMyString();
        signature.update(message2.getBytes());
        signatureForModification = signature.sign();
        //A call to this method resets this signature object to the state it was in when previously initialized for signing via a call to initSign(PrivateKey)

        // Verify the signature
        signature.initVerify(publicKey);
        signature.update(message2.getBytes());
        boolean isVerified = signature.verify(signatureForModification);

        System.out.println("Signature verification result: " + isVerified);


        /*
        if(Arrays.equals(signatureForModification, signatureForModification))
            System.out.println("signature is veryfied");
        else
            System.out.println("signature is NOT veryfied");

         */

        }
    }


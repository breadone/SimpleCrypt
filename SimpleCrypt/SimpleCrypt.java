package SimpleCrypt;

import java.util.Random;

public class SimpleCrypt {

    public static void main(String[] args) {
        System.out.println(encrypt("user", "pass"));          
    }

    public static String encrypt(String username, String password) {
        int p, q, N, phiN, e = 0;
        Boolean prime = false, coprime = false;
        char encChar;
        int[] CharCode = new int[password.length()];
        String encryptedText = "";
        Random rnd = new Random();

        //generate p and q and checks theyre prime
        do {
            p = rnd.nextInt(75) + 1;
            prime = true;
            for (int i = 2; i<=p - 1; i++) {
                if (p % i == 0) {
                    prime = false;
                    break;
                }
            }
        } while (prime != true);

        do {
            q = rnd.nextInt(75) + 1;
            prime = true;
            for (int i = 2; i<=q - 1; i++) {
                if (q % i == 0) {
                    prime = false;
                    break;
                }
            }
        } while (prime != true);

        N = p * q;
        phiN = (p-1) * (q-1);

        for (int i = 2; i <= N - 1; i++) {
            coprime = true;

            for (int j = 2; j <= i; j++) {
                if ((N % j == 0) && (i % j == 0)) {
                    coprime = false;
                }
            }

            if (coprime == true) {
                for (int j = 2; j <+ i; j++) {
                    if ((phiN % j == 0) && (i % j == 0)) {
                        coprime = false;
                    }
                }
            }
            if (coprime == true) {e = i;}
        }
        
        //System.out.println((CharCode[1] ^ e) % N);

        for (int i = 0; i <= password.length() - 1; i++) {
            CharCode[i] = (int) password.charAt(i);
            encChar = (char) ((CharCode[i] ^ e) % N);
            encryptedText += encChar;
        }

        return encryptedText;
    }


}
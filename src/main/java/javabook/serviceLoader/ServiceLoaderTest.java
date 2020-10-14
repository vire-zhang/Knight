package javabook.serviceLoader;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ServiceLoader;

public class ServiceLoaderTest {
    public static ServiceLoader<Cipher> cipherLoader
            = ServiceLoader.load(Cipher.class);

    public static void main(String[] args) throws UnsupportedEncodingException
    {
        Cipher cipher = getCipher(1);
        var message = "Meet me at the toga party.";
        byte[] bytes = cipher.encrypt(message.getBytes(), new byte[] { 3 });
        var encrypted = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(encrypted);
        byte[] bytes1 = cipher.decrypt(encrypted.getBytes(), new byte[]{3});
        System.out.println(new String(bytes1, StandardCharsets.UTF_8));
    }

    public static Cipher getCipher(int minStrength)
    {
        for (Cipher cipher : cipherLoader)
            // Implicitly calls iterator
            if (cipher.strength() >= minStrength) return cipher;
        return null;
    }
}

package org.multipoly.security;

import org.multipoly.User.User;
import org.restlet.engine.util.Base64;
import org.restlet.ext.crypto.internal.CryptoUtils;
import org.umlg.runtime.types.Password;
import org.umlg.runtime.util.PasswordEncryptionService;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Date: 2014/11/06
 * Time: 8:39 PM
 */
public class SecurityUtil {

    public static String formatCredentials(String identifier, char[] secret) {
        // Data buffer
        StringBuffer sb = new StringBuffer();

        // Indexes buffer
        StringBuffer isb = new StringBuffer();
        String timeIssued = Long.toString(System.currentTimeMillis());
        int i = timeIssued.length();
        sb.append(timeIssued);

        isb.append(i);

        sb.append('/');
        sb.append(identifier);

        i += identifier.length() + 1;
        isb.append(',').append(i);

        sb.append('/');
        sb.append(secret);

        // Store indexes at the end of the string
        sb.append('/');
        sb.append(isb);

        try {
            return Base64.encode(CryptoUtils.encrypt("AES", "My Server KeyXXX".getBytes(), sb.toString()), false);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verify(User user, char[] secret) {
        Password password = new Password(user.getPassword());
        try {
            return new PasswordEncryptionService().authenticate(
                    new String(secret),
                    password.getEncryptedPassword(),
                    password.getSalt());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}

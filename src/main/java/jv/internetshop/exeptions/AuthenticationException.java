package jv.internetshop.exeptions;

public class AuthenticationException extends Exception {
    public AuthenticationException(String message) {
        super(message);
    }
}

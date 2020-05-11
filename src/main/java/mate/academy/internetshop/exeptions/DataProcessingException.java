package mate.academy.internetshop.exeptions;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Exception exception) {
        super(message, exception);
    }
}

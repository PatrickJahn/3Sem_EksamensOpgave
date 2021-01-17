package security.errorhandling;

/**
 *
 * @author lam@cphbusiness.dk
 */
public class DogException extends Exception{

    public DogException(String message) {
        super(message);
    }

    public DogException() {
        super("Could not be Authenticated");
    }  
}

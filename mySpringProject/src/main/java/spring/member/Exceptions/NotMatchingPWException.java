package spring.member.Exceptions;

public class NotMatchingPWException extends RuntimeException{
    public NotMatchingPWException(String message) {
        super(message);
    }
}

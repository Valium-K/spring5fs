package spring.member.Exceptions;

public class DuplicationException extends RuntimeException{
    public DuplicationException(String message) {
        super(message);
    }
}

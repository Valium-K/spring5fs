package chap03.spring.exception;

public class DuplicateMemberException extends RuntimeException{
    public DuplicateMemberException(String message) {
        super(message);
    }
}

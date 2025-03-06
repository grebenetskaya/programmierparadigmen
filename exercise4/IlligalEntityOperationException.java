package Aufgabe4;

public class IlligalEntityOperationException extends Exception {

    public IlligalEntityOperationException(){
        super();
    }

    public IlligalEntityOperationException(String message){
        super(message);
    }

    public IlligalEntityOperationException(Throwable cause) {
        super(cause == null ? null : cause.toString());
        initCause(cause);
    }

    public IlligalEntityOperationException(String message, Throwable cause) {
        super(message);
        initCause(cause);
    }
}

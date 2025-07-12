package jp.quangit.rest_api.service.error;

public class IdInvalidException extends Exception{
    //constructor with message
    public IdInvalidException(String message) {
        super(message);
    }
}

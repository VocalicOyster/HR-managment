package it.portfolio.hr.humanResource.exceptions.user;


public class UserException extends Exception{
    public UserException(String message, int statusCode) {
        super(message);
    }
}

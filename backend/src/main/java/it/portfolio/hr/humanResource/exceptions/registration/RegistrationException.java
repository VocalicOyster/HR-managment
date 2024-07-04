package it.portfolio.hr.humanResource.exceptions.registration;

public class RegistrationException extends Exception{
    public RegistrationException(String message, int statusCode) {
        super(message);
    }
}

package it.portfolio.hr.humanResource.exceptions.vacation;

public class VacationException extends Exception{
    public VacationException(String message, int statusCode) {
        super(message);
    }
}

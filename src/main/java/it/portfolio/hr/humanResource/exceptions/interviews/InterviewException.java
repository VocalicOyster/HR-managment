package it.portfolio.hr.humanResource.exceptions.interviews;

public class InterviewException extends Exception{

    public InterviewException(String message, int internalCode) {
        super(message);
    }
}

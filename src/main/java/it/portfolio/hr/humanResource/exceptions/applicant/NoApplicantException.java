package it.portfolio.hr.humanResource.exceptions.applicant;

public class NoApplicantException extends Exception{
    private int internalCode;
    private String message;

    public NoApplicantException(int internalCode, String message) {
        this.internalCode = internalCode;
        this.message = message;
    }

    public NoApplicantException() {
    }
}

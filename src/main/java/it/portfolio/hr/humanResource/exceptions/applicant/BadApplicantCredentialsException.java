package it.portfolio.hr.humanResource.exceptions.applicant;

public class BadApplicantCredentialsException extends Exception{
    private int internalCode;
    private String message;

    public BadApplicantCredentialsException(int internalCode, String message) {
        this.internalCode = internalCode;
        this.message = message;
    }

    public BadApplicantCredentialsException() {
    }
}

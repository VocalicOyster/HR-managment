package it.portfolio.hr.humanResource.exceptions.applicant;

public class ApplicantException extends Exception {

    private String message;
    private int statusCode;
    public ApplicantException(String message, int internalCode) {
        super(message);
    }
}

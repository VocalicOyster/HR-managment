package it.portfolio.hr.humanResource.exceptions.applicant;

public class AlreadyPresentException extends Exception {
    private int internalCode;
    private String message;

    public AlreadyPresentException(int internalCode, String message) {
        this.internalCode = internalCode;
        this.message = message;
    }

    public AlreadyPresentException() {
    }

}

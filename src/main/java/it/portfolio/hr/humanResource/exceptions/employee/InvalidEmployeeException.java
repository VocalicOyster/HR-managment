package it.portfolio.hr.humanResource.exceptions.employee;

public class InvalidEmployeeException extends Exception{
    private int internalCode;
    private String message;

    public InvalidEmployeeException(int internalCode, String message) {
        this.internalCode = internalCode;
        this.message = message;
    }

    public InvalidEmployeeException() {
    }

    public int getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(int internalCode) {
        this.internalCode = internalCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

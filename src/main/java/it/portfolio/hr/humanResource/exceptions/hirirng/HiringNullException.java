package it.portfolio.hr.humanResource.exceptions.hirirng;

public class HiringNullException extends Exception{

    private int internalCode;
    private String message;

    public HiringNullException(int internalCode, String message) {
        this.internalCode = internalCode;
        this.message = message;
    }

    public HiringNullException() {
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

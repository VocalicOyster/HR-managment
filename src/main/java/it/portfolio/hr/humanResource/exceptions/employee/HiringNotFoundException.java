package it.portfolio.hr.humanResource.exceptions.employee;

public class HiringNotFoundException extends Throwable {
    private int internalCode;
    private String message;

    public HiringNotFoundException(int internalCode, String message) {
        this.internalCode = internalCode;
        this.message = message;
    }

    public HiringNotFoundException() {
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

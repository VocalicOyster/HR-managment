package it.portfolio.hr.humanResource.exceptions.department;

public class DepartmentException extends Exception{

    private int internalCode;
    private String message;

    public DepartmentException(int internalCode, String message) {
        this.internalCode = internalCode;
        this.message = message;
    }

    public DepartmentException() {
    }
}

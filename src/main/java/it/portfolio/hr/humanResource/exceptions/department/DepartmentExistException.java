package it.portfolio.hr.humanResource.exceptions.department;

public class DepartmentExistException extends Throwable {
    private int internalCode;
    private String message;

    public DepartmentExistException(int internalCode, String message) {
        this.internalCode = internalCode;
        this.message = message;
    }

    public DepartmentExistException() {
    }
}

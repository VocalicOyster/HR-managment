package it.portfolio.hr.humanResource.exceptions.department;

public class DepartmentException extends Exception{

    public DepartmentException(String message, int internalCode) {
        super(message);
    }
}

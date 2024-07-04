package it.portfolio.hr.humanResource.models.DTOs;

public abstract class Response {
    private int status;
    private String message;

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response() {
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}

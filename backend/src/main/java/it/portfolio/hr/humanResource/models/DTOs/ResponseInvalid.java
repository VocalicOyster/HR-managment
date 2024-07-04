package it.portfolio.hr.humanResource.models.DTOs;

import it.portfolio.hr.humanResource.models.DTOs.Response;

public class ResponseInvalid extends Response {
    public ResponseInvalid(int status, String message) {
        super(status, message);
    }
}

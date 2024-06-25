package it.portfolio.hr.humanResource.exceptions.candidateEv;

public class CandidateEvaluationException extends Exception {

    private int internalCode;
    private String message;

    public CandidateEvaluationException(int internalCode, String message) {
        this.internalCode = internalCode;
        this.message = message;
    }

    public CandidateEvaluationException() {
    }
}

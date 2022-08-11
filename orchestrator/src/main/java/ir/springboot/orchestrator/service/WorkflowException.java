package ir.springboot.orchestrator.service;

public class WorkflowException extends RuntimeException{

    public WorkflowException(String message) {
        super(message);
    }
}

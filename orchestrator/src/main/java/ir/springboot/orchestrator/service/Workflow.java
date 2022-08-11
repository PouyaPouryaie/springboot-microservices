package ir.springboot.orchestrator.service;

import java.util.List;

public interface Workflow {

    List<WorkflowStep> getSteps();
}

package com.eworkgroup.automationdb.results;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link JobResult} entity
 */
public record JobResultDto(
        String name, String aut, String env, String browser, String viewport, String host,
        Long startDate, Long endDate, Boolean isParallel, List<TestResultDto> testMethodResults
) implements Serializable {

    /**
     * A DTO for the {@link TestResult} entity
     */
    public record TestResultDto(
            Integer trId, String name, String instanceName, String parameters, String description, Long duration,
            List<String> groupList, Boolean isDataDriven, Boolean wasRetried, String throwable, Integer result
    ) implements Serializable {

    }
}

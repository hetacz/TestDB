package com.eworkgroup.automationdb.results;

import com.eworkgroup.automationdb.env.AutEnv;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobResult implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    private String browser;
    private String viewport;
    private String host;
    @Column(nullable = false)
    private LocalDateTime startTime;
    @Column(nullable = false)
    private LocalDateTime endTime;
    private Boolean isParallel;
    @ManyToOne
    @ToString.Exclude
    private AutEnv autEnv;
    @OneToMany(mappedBy = "jobResult", fetch = FetchType.LAZY/*, cascade = CascadeType.ALL, orphanRemoval = true*/)
    @ToString.Exclude
    private List<TestResult> testResults = new ArrayList<>();

    public JobResult(
            String name,
            String browser,
            String viewport,
            String host,
            LocalDateTime startTime,
            LocalDateTime endTime,
            Boolean isParallel
    ) {
        super();
        this.name = name;
        this.browser = browser;
        this.viewport = viewport;
        this.host = host;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isParallel = isParallel;
    }

    public void addTestResult(TestResult testResult) {
        testResults.add(testResult);
    }

    public void removeTestResult(TestResult testResult) {
        testResults.remove(testResult);
    }
}

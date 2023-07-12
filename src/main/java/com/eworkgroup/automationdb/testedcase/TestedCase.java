package com.eworkgroup.automationdb.testedcase;

import com.eworkgroup.automationdb.env.AutEnv;
import com.eworkgroup.automationdb.results.TestResult;
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
public class TestedCase implements Serializable {

    @Id
    private Long id; // should be the same as trid
    @Column(nullable = false)
    private String name;
    private String description;
    private Boolean isDataDriven;
    @Column(nullable = false)
    private LocalDateTime createDate;
    @ManyToMany(mappedBy = "testedCases", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private List<AutEnv> autEnvs = new ArrayList<>();
    @OneToMany(mappedBy = "testedCase", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonIgnore
    private List<TestResult> testResults = new ArrayList<>();

    public TestedCase(Long id, String name, String description, Boolean isDataDriven, LocalDateTime createDate) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.isDataDriven = isDataDriven;
        this.createDate = createDate;
    }

    public void addAutEnv(AutEnv autEnv) {
        autEnvs.add(autEnv);
    }

    public void removeAutEnv(AutEnv autEnv) {
        autEnvs.remove(autEnv);
    }

    public void addTestResult(TestResult testResult) {
        testResults.add(testResult);
    }

    public void removeTestResult(TestResult testResult) {
        testResults.remove(testResult);
    }
}

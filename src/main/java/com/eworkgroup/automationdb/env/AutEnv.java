package com.eworkgroup.automationdb.env;

import com.eworkgroup.automationdb.Aut;
import com.eworkgroup.automationdb.Environment;
import com.eworkgroup.automationdb.testedcase.TestedCase;
import com.eworkgroup.automationdb.results.JobResult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AutEnv implements Serializable {

    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Aut aut;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Environment environment;
    @OneToMany(mappedBy = "autEnv", fetch = FetchType.LAZY/*, cascade = CascadeType.ALL, orphanRemoval = true*/)
    @ToString.Exclude
    @JsonIgnore
    private List<JobResult> jobResults = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "AUT_ENV_TESTED_CASE",
            joinColumns = @JoinColumn(name = "AUT_ENV_ID"),
            inverseJoinColumns = @JoinColumn(name = "TESTED_CASE_ID")
    )
    @JsonIgnore
    @ToString.Exclude
    private List<TestedCase> testedCases = new ArrayList<>();

    public AutEnv(Long id, String aut, String environment) {
        super();
        this.id = id;
        this.aut = Aut.valueOf(aut);
        this.environment = Environment.valueOf(environment);
    }

    public void setTestedCases(List<TestedCase> testedCases) {
        this.testedCases = testedCases;
    }

    public void addJobResult(JobResult jobResult) {
        jobResults.add(jobResult);
    }

    public void removeJobResult(JobResult jobResult) {
        jobResults.remove(jobResult);
    }

    public void addTRCase(TestedCase testedCase) {
        testedCases.add(testedCase);
    }

    public void removeTRCase(TestedCase testedCase) {
        testedCases.remove(testedCase);
    }
}

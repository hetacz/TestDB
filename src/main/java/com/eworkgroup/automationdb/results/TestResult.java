package com.eworkgroup.automationdb.results;

import com.eworkgroup.automationdb.Result;
import com.eworkgroup.automationdb.testedcase.TestedCase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestResult implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Integer trId;
    @Column(nullable = false)
    private String method;
    private String instance;
    private String parameters;
    // private List<String> groupList;
    private String throwable;
    private Boolean isDataDriven;
    private Boolean wasRetried;
    @Column(nullable = false)
    private Long length;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Result result;
    @ManyToOne
    @JsonIgnore
    private JobResult jobResult;
    @ManyToOne
    @JsonIgnore
    private TestedCase testedCase;

    public TestResult(
            Integer trId,
            String method,
            String instance,
            String parameters,
            String throwable,
            Boolean isDataDriven,
            Boolean wasRetried,
            Long length,
            Result result
    ) {
        super();
        this.trId = trId;
        this.method = method;
        this.instance = instance;
        this.parameters = parameters;
        this.throwable = throwable;
        this.isDataDriven = isDataDriven;
        this.wasRetried = wasRetried;
        this.length = length;
        this.result = result;
    }
}

package com.eworkgroup.automationdb.results;

import com.eworkgroup.automationdb.Aut;
import com.eworkgroup.automationdb.Environment;
import com.eworkgroup.automationdb.Result;
import com.eworkgroup.automationdb.Utils;
import com.eworkgroup.automationdb.env.AutEnv;
import com.eworkgroup.automationdb.env.AutEnvRepository;
import com.eworkgroup.automationdb.testedcase.TestedCase;
import com.eworkgroup.automationdb.testedcase.TestedCaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Slf4j
@Service
public class JobResultService {

    @Autowired
    private AutEnvRepository autEnvRepository;
    @Autowired
    private JobResultRepository jobResultRepository;
    @Autowired
    private TestResultRepository testResultRepository;
    @Autowired
    private TestedCaseRepository testedCaseRepository;

    @Transactional
    public JobResult saveJobResult(JobResultDto jobResultDto) {
        JobResult jobResult = fromResults(jobResultDto);
        AutEnv autEnv = resolveAutEnv(jobResultDto);
        jobResult.setAutEnv(autEnv);
        autEnv.addJobResult(jobResult);
        jobResultDto.testMethodResults().forEach(testResultDto -> {
            TestResult testResult = fromResults(testResultDto);
            testResult.setJobResult(jobResult);
            jobResult.addTestResult(testResult);
            jobResultRepository.save(jobResult); // testResultRepository.save(testResult); // cascade
            testedCaseRepository.findById(Long.valueOf(testResultDto.trId())).ifPresentOrElse(testedCase -> {
                testedCase.addTestResult(testResult);
                if (isAlreadySavedAsAutEnvTestedCase(autEnv, testedCase)) {
                    addToEnv(autEnv, testedCase);
                }
                updateTestedCase(testResultDto, testedCase);
                testResult.setTestedCase(testedCase);
                testedCaseRepository.save(testedCase);
            }, () -> {
                TestedCase testedCase = fromResults(jobResultDto, testResultDto);
                testedCase.addTestResult(testResult);
                testedCaseRepository.save(testedCase);
                testResult.setTestedCase(testedCase);
                addToEnv(autEnv, testedCase);
            });
        });
        return jobResult;
    }

    @Transactional
    public List<JobResult> getJobResults(String aut, String env) {
        return jobResultRepository.findAllJobResultsByAutEnvAutAndAutEnvEnvironment(
                Aut.valueOf(aut),
                Environment.valueOf(env)
        );
    }

    @Transactional
    public JobResult getJobResults1(String aut, String env) {
        return jobResultRepository.findFirstByAutEnvAutAndAutEnvEnvironmentOrderByStartTimeDesc(
                Aut.valueOf(aut),
                Environment.valueOf(env)
        ).orElseThrow();
    }

    private boolean isAlreadySavedAsAutEnvTestedCase(AutEnv autEnv, TestedCase testedCase) {
        return testedCase
                .getAutEnvs()
                .stream()
                .noneMatch(e -> areAutEnvsEqual(autEnv, e));
    }

    private boolean areAutEnvsEqual(AutEnv autEnv, AutEnv self) {
        return self.getAut() == autEnv.getAut() &&
                self.getEnvironment() == autEnv.getEnvironment();
    }

    private AutEnv resolveAutEnv(JobResultDto jobResultDto) {
        return autEnvRepository
                .findByAutAndEnvironment(Aut.valueOf(jobResultDto.aut()), Environment.valueOf(jobResultDto.env()))
                .orElseThrow();
    }

    private void updateTestedCase(JobResultDto.TestResultDto testResultDto, TestedCase testedCase) {
        testedCase.setName(testResultDto.name());
        testedCase.setDescription(testResultDto.description());
        testedCase.setIsDataDriven(testResultDto.isDataDriven());
    }

    private void addToEnv(AutEnv autEnv, TestedCase testedCase) {
        testedCase.addAutEnv(autEnv);
        autEnv.addTRCase(testedCase);
        autEnvRepository.save(autEnv);
    }

    private JobResult fromResults(JobResultDto jrDto) {
        JobResult jobResult = new JobResult(
                Utils.parse(jrDto.name()),
                Utils.parse(jrDto.browser()),
                Utils.parse(jrDto.viewport()),
                Utils.parse(jrDto.host()),
                fromLong(jrDto.startDate()),
                fromLong(jrDto.endDate()),
                jrDto.isParallel()
        );
        jobResult.setAutEnv(jobResult.getAutEnv());
        return jobResult;
    }

    private TestResult fromResults(JobResultDto.TestResultDto trDto) {
        return new TestResult(
                trDto.trId(),
                Utils.parse(trDto.name()),
                Utils.parse(trDto.instanceName()),
                Utils.parse(trDto.parameters()),
                Utils.parse(trDto.throwable()),
                trDto.isDataDriven(),
                trDto.wasRetried(),
                trDto.duration(),
                Result.fromInt(trDto.result())
        );
    }

    private TestedCase fromResults(JobResultDto jrDto, JobResultDto.TestResultDto trDto) {
        return new TestedCase(
                Long.valueOf((trDto.trId())),
                Utils.parse(trDto.name()),
                Utils.parse(trDto.description()),
                trDto.isDataDriven(),
                fromLong(jrDto.startDate())
        );
    }

    private LocalDateTime fromLong(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }
}

package com.eworkgroup.automationdb;

import com.eworkgroup.automationdb.env.AutEnv;
import com.eworkgroup.automationdb.results.TestResultRepository;
import com.eworkgroup.automationdb.testedcase.TestedCase;
import com.eworkgroup.automationdb.results.JobResult;
import com.eworkgroup.automationdb.results.JobResultDto;
import com.eworkgroup.automationdb.results.JobResultService;
import com.eworkgroup.automationdb.testedcase.TestedCaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Transient;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@SpringBootTest(classes = AutomationdbApplication.class)
public class SaveTest {

    @Autowired
    private TestedCaseRepository testedCaseRepository;

    @Autowired
    private TestResultRepository testResultRepository;

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private JobResultService jobResultService;

    @Test
    @DirtiesContext
    @Transactional
    public void saveJobResults() {
        log.warn("{}", em.createNativeQuery("SELECT * FROM aut_env").getResultList().size());
        JobResultDto jobResultDto = new JobResultDto(
                "testN",
                "SP",
                "PROD",
                "testB",
                "testV",
                "testH",
                1674221000L,
                1674222000L,
                true,
                List.of(
                        new JobResultDto.TestResultDto(
                                10000,
                                "method1",
                                "instance1",
                                "",
                                "",
                                10000L,
                                List.of("TEST", "PREPROD", "PROD"),
                                true,
                                false,
                                "BOOM",
                                2
                        )
                )
        );
        var q0 = em.createQuery("SELECT tc FROM TestedCase tc", TestedCase.class).getResultList();
        Assertions.assertThat(q0).hasSize(21);
        jobResultService.saveJobResult(jobResultDto);
        var q = em.createQuery("SELECT jr FROM JobResult jr", JobResult.class).getResultList();
        Assertions.assertThat(q).hasSize(1);
        Assertions.assertThat(q.get(0).getAutEnv().getId()).isEqualTo(21L);
        Assertions.assertThat(q.get(0).getTestResults().stream().filter(testResult -> Objects.equals(testResult
                .getJobResult()
                .getId(), q.get(0).getId())));
        var q2 = em.createQuery("SELECT tc FROM TestedCase tc", TestedCase.class).getResultList();
        var tc = testedCaseRepository.findByAutEnvsAutAndAutEnvsEnvironment(Aut.SP, Environment.PROD).orElseThrow();
        Assertions.assertThat(tc.getId()).isEqualTo(10000L);
    }
}

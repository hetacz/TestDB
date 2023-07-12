package com.eworkgroup.automationdb.results;

import com.eworkgroup.automationdb.Aut;
import com.eworkgroup.automationdb.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobResultRepository extends JpaRepository<JobResult, Long> {

    List<JobResult> findAllJobResultsByAutEnvAutAndAutEnvEnvironment(Aut aut, Environment environment);

    Optional<JobResult> findFirstByAutEnvAutAndAutEnvEnvironmentOrderByStartTimeDesc(Aut aut, Environment environment);
}

package com.eworkgroup.automationdb.testedcase;

import com.eworkgroup.automationdb.Aut;
import com.eworkgroup.automationdb.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestedCaseRepository extends JpaRepository<TestedCase, Long> {

    Optional<TestedCase> findByAutEnvsAutAndAutEnvsEnvironment(Aut aut, Environment environment);
}

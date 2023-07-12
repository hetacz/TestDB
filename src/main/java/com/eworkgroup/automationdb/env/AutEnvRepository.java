package com.eworkgroup.automationdb.env;

import com.eworkgroup.automationdb.Aut;
import com.eworkgroup.automationdb.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutEnvRepository extends JpaRepository<AutEnv, Long> {

    Optional<AutEnv> findByAutAndEnvironment(Aut aut, Environment environment);
}

package com.eworkgroup.automationdb;

import com.eworkgroup.automationdb.env.AutEnv;
import com.eworkgroup.automationdb.env.AutEnvRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest(classes = AutomationdbApplication.class)
public class InitialTest {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private AutEnvRepository autEnvRepository;

    @Test
    public void initialTablesTest() {
        Assertions.assertThat(em.createNativeQuery("SELECT * FROM env").getResultList().size()).isEqualTo(12);
        Assertions
                .assertThat(em.createNativeQuery("SELECT * FROM env_tested_case").getResultList().size())
                .isEqualTo(44);
    }
}

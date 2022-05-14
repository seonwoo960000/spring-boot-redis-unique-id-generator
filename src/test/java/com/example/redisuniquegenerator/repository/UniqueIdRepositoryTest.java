package com.example.redisuniquegenerator.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.example.redisuniquegenerator.entity.UniqueId;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class UniqueIdRepositoryTest {

    @Autowired
    UniqueIdRepository uniqueIdRepository;

    @Test
    void test() {
        UniqueId result = uniqueIdRepository.save(new UniqueId("12345"));
        Optional<UniqueId> retrieved = uniqueIdRepository.findById(result.getId());
        assertThat(retrieved.get()).isNotNull();
        assertThat(retrieved.get().getValue()).isEqualTo("12345");
    }
}
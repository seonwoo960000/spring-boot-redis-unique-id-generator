package com.example.redisuniquegenerator.repository;

import org.jmock.lib.concurrent.Blitzer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.redisuniquegenerator.service.UniqueIdService;

@SpringBootTest
@ActiveProfiles("test")
public class LoadTest {

    Blitzer blitzer = new Blitzer(1000000, 500);

    @Autowired
    UniqueIdService uniqueIdService;

    @Test
    void test() throws InterruptedException {
        blitzer.blitz(() -> uniqueIdService.insert());
    }
}

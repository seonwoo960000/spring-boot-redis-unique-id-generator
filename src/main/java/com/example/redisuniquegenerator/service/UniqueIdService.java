package com.example.redisuniquegenerator.service;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.example.redisuniquegenerator.entity.UniqueId;
import com.example.redisuniquegenerator.repository.UniqueIdRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UniqueIdService {

    private final UniqueIdRepository uniqueIdRepository;
    private final RedisService redisService;
    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private final RedisUniqueIdGenerator redisUniqueIdGenerator;

    public void insert() {
        // find key from redis
        String uniqueId = null;
        while (true) {
            uniqueId = redisService.pop("uniqueId");
            if (uniqueId != null) break;
            else {
                threadPoolTaskExecutor.submit(() -> redisUniqueIdGenerator.fillRedisUniqueIds(100));
//                Thread.sleep(1000);
            }
        }

        uniqueIdRepository.save(new UniqueId(uniqueId));
        log.info("added {}", uniqueId);
    }

}

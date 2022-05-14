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
        String key = null;
        while (true) {
            key = redisService.get("uniqueId");
            if (key != null) break;
            else {
                threadPoolTaskExecutor.submit(() -> redisUniqueIdGenerator.fillRedisUniqueIds(100));
            }
        }

        uniqueIdRepository.save(new UniqueId(key));
        log.info("added {}", key);
    }


}

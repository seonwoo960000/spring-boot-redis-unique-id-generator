package com.example.redisuniquegenerator.service;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import com.example.redisuniquegenerator.repository.UniqueIdRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisUniqueIdGeneratorImpl implements RedisUniqueIdGenerator{

    private final UniqueIdRepository uniqueIdRepository;
    private final RedisService redisService;
    private static SecureRandom secureRandom = new SecureRandom();
    private final String REDIS_KEY_NAME = "uniqueUd";

    @Override
    public String generate() {
        return String.valueOf(secureRandom.nextLong() % 999999999);
    }

    @Override
    public boolean isValid(String uniqueId) {
        return uniqueIdRepository.findUniqueIdByValue(uniqueId) == null;
    }

    @Override
    public boolean shouldPushToRedis(int limit) {
        return redisService.getSize(REDIS_KEY_NAME) < limit;
    }

    @Override
    public void pushKeyToRedis(String uniqueId) {
        redisService.add(REDIS_KEY_NAME, uniqueId);
    }
}

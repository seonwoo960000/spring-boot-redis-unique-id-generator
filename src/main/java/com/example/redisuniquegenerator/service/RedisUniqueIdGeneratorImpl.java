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

    @Override
    public String generate() {
        return String.valueOf(secureRandom.nextLong() % 999999999);
    }

    @Override
    public boolean isValid(String key) {
        return uniqueIdRepository.findUniqueIdByValue(key) == null;
    }

    @Override
    public boolean shouldPushToRedis(int limit) {
        return redisService.getSize("uniqueId") < limit;
    }

    @Override
    public void pushKeyToRedis(String key) {
        redisService.insert("uniqueId", key);
    }
}

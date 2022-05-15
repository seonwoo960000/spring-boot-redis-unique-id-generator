package com.example.redisuniquegenerator.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate redisTemplate;

    public String pop(String key) {
        return (String) redisTemplate.opsForSet().pop(key);
    }

    public void add(String key, String value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public Long getSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }
}

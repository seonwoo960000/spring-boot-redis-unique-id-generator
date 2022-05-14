package com.example.redisuniquegenerator.service;

public interface RedisUniqueIdGenerator {

    // Generate Unique Id
    String generate();

    // Check if key is unique
    boolean isValid(String key);

    // Check if it is valid to push to Redis (ex) size of the UniqueIds data structure in Redis)
    boolean shouldPushToRedis(int limit);

    // Push unique id to redis
    void pushKeyToRedis(String key);

    // Fill Redis' UniqueIds data structure with unique ids until limit
    default void fillRedisUniqueIds(int limit) {
        while (shouldPushToRedis(limit)) {

            String key = generate();
            if (!isValid(key)) {continue;}

            pushKeyToRedis(key);
        }
    }
}
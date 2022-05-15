package com.example.redisuniquegenerator.service;

public interface RedisUniqueIdGenerator {

    // Generate Unique Id
    String generate();

    // Check if Unique Id is unique
    boolean isValid(String uniqueId);

    // Check if it is valid to push to Redis (ex) size of the UniqueIds data structure in Redis)
    boolean shouldPushToRedis(int limit);

    // Push Unique Id to redis
    void pushKeyToRedis(String uniqueId);

    // Fill Redis' UniqueIds data structure with unique ids until limit
    default void fillRedisUniqueIds(int limit) {
        while (shouldPushToRedis(limit)) {

            String key = generate();
            if (!isValid(key)) {continue;}

            pushKeyToRedis(key);
        }
    }
}
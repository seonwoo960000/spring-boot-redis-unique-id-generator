package com.example.redisuniquegenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.redisuniquegenerator.entity.UniqueId;

public interface UniqueIdRepository extends JpaRepository<UniqueId, Long> {

    public UniqueId findUniqueIdByValue(String value);
}

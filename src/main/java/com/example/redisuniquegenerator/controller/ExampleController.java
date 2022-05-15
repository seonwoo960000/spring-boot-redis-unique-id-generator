package com.example.redisuniquegenerator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.redisuniquegenerator.service.RedisService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ExampleController {

    private final RedisService redisService;

    @PostMapping("/set/{key}/{uniqueId}")
    public void set(@PathVariable String key, @PathVariable String uniqueId) {
        redisService.add(key, uniqueId);
    }

    @GetMapping("/keySize/{key}")
    public Long getKeySize(@PathVariable String key) {
        return redisService.getSize(key);
    }
}

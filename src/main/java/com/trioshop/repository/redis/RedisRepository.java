package com.trioshop.repository.redis;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RedisRepository {
    private static final String BLACKLIST_PREFIX = "blacklist:";

    private final RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOperations;

    public void deleteToken(String userId) {
        redisTemplate.delete(userId);
    }

    public void save(String userId, String refreshToken) {
        valueOperations.set(userId, refreshToken);
    }

    public String findById(String userId) {
        if (userId == null) {
            return null;
        }
        return (String) valueOperations.get(userId);
    }

    public void addTokenToBlacklist(String token, long expirationTime) {
        valueOperations.set(BLACKLIST_PREFIX + token, "true", expirationTime, TimeUnit.MILLISECONDS);
    }

    public Boolean isTokenBlacklisted(String token) {
        return redisTemplate.hasKey(BLACKLIST_PREFIX + token);
    }
}



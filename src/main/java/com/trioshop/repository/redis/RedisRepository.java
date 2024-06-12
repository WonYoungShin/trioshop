package com.trioshop.repository.redis;

import io.jsonwebtoken.lang.Arrays;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static java.lang.String.*;

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

    public void viewSave(Long userCode, Long boardCode){
        String board;

        if(redisTemplate.hasKey(valueOf(userCode))) {
            board = (String) valueOperations.get(valueOf(userCode))+boardCode + "_";
        }else {
            board = boardCode + "_";
        }
        valueOperations.set(valueOf(userCode), board, 2, TimeUnit.HOURS);
    }

    public String findById(String userId ) {
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

    public Boolean isCodeBoardView(Long userCode, Long boardCode) {
        String boards = (String) valueOperations.get(valueOf(userCode));
        if(Objects.nonNull(boards)) {
            String[] boardArr = boards.split("_");
            String boardCodeStr = valueOf(boardCode);
            for (String s : boardArr) {
                if (s.equals(boardCodeStr)) {
                    return false;
                }
            }
        }

        return true;
    }
}



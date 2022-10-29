package com.example.test.redistest;

import com.example.test.redistest.service.RedisTestService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WriteThroughThread extends Thread{

    private final RedisTestService redisTestService;

    @Override
    public void run() {
        redisTestService.updateLikeCountUseRedisson(1L);
    }

}

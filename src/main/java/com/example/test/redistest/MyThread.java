package com.example.test.redistest;

import com.example.test.redistest.entity.Post;
import com.example.test.redistest.repository.PostRepository;
import com.example.test.redistest.service.RedisTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
public class MyThread extends Thread{

    private final RedisTestService redisTestService;

    @Override
    @Transactional
    public void run() {
        redisTestService.updateLikeCount(1L);
    }

}

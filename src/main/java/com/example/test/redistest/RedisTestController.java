package com.example.test.redistest;

import com.example.test.redistest.entity.Post;
import com.example.test.redistest.repository.PostRepository;
import com.example.test.redistest.service.RedisTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redisTest")
@RequiredArgsConstructor
public class RedisTestController {

    private final RedisTestService redisTestService;

    @GetMapping("/{postId}")
    public String updateLikeCount(@PathVariable Long postId) {

        for (int i = 1; i <= 10000; i++) {
            MyThread myThread = new MyThread(redisTestService);
            myThread.run();
        }

        return "abc";
    }

    @GetMapping("/writeThrough/{postId}")
    public String updateLikeCountUseRedisWriteThrough(@PathVariable Long postId) {

        for (int i = 1; i <= 10000; i++) {
            WriteThroughThread writeThroughThread = new WriteThroughThread(redisTestService);
            writeThroughThread.run();
        }

        return "abc";
    }

    @PostMapping
    public String savePost() {

        Post post = new Post(1L, "title1", "content1", 0);
        redisTestService.save(post);
        return "success";
    }

    @GetMapping("/check")
    public String check() {

        return "success";
    }
}

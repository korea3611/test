package com.example.test.redistest.service;

import com.example.test.redistest.entity.Post;
import com.example.test.redistest.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisTestService {

    private final PostRepository postRepository;
    private final RMapCache<String, Post> postRMapCache;
    private final RedissonClient redissonClient;

    public void save(Post post){
        postRMapCache.put(String.valueOf(post.getId()), post);

    }

    @Transactional
    public void updateLikeCount(Long postId) {
        Optional<Post> byId = postRepository.findById(postId);
        Post post = byId.get();
        post.likeCountUp();
    }

//    @Transactional
//    public void updateLikeCountUseRedisson(Long postId) {
//        Post post = postRMapCache.get(String.valueOf(postId));
//        post.likeCountUp();
//        postRMapCache.put(String.valueOf(postId), post);
//    }

    @Transactional
    public void updateLikeCountUseRedisson(Long postId) {
        final String lockName = "like:lock";
        final RLock lock = redissonClient.getLock(lockName);

        try {
            if(!lock.tryLock(1, 3, TimeUnit.SECONDS))
                return;

            Post post = postRMapCache.get(String.valueOf(postId));
            post.likeCountUp();
            postRMapCache.put(String.valueOf(postId), post);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(lock != null && lock.isLocked()) {
                lock.unlock();
            }
        }
    }


}

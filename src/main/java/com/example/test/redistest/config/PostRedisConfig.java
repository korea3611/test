package com.example.test.redistest.config;

import com.example.test.redistest.entity.Post;
import com.example.test.redistest.repository.PostRepository;
import org.redisson.api.MapOptions;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.api.map.MapWriter;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Map;

@Configuration
@EnableCaching
public class PostRedisConfig {

    private PostRepository postRepository;
    private RedissonClient redissonClient;

    public PostRedisConfig(PostRepository postRepository, RedissonClient redissonClient) {
        this.postRepository = postRepository;
        this.redissonClient = redissonClient;
    }

    @Bean
    public RMapCache<String, Post> postRMapCache(){
        final RMapCache<String, Post> postRMapCache
                = redissonClient.getMapCache("Post", MapOptions.<String, Post>defaults()
                .writer(getPostMapWriter())
                .writeMode(MapOptions.WriteMode.WRITE_BEHIND));
//                .writeBehindBatchSize(5000)
//                .writeBehindDelay(50000));

        return postRMapCache;
    }
    private MapWriter<String, Post> getPostMapWriter(){
        return new MapWriter<String, Post>(){
            @Override
            public void write(Map<String, Post> map) {
                map.forEach((k, v) -> {
                    postRepository.save(v);
                });
            }

            @Override
            public void delete(Collection<String> keys) {
                keys.stream().forEach(key -> {
                    postRepository.deleteById(Long.valueOf(key));
                });
            }
        };
    }
}

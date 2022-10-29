package com.example.test.async.service;

import com.example.test.async.AsyncTestEntity;
import com.example.test.async.repository.AsyncRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AsyncService {

    private final AsyncRepository asyncRepository;
    //비동기로 동작하는 메소드
    @Async
    public void onAsync(int i) {
        Optional<AsyncTestEntity> byId = asyncRepository.findById((long) i);
        AsyncTestEntity asyncTestEntity = byId.get();
        System.out.println(asyncTestEntity.getName());
    }

    public void save(int i) {
//        logger.info("onAsync i=" + i);
        AsyncTestEntity asyncTestEntity = AsyncTestEntity.builder()
                .id((long) i)
                .name("이름"+i)
                .build();
        asyncRepository.save(asyncTestEntity);
    }
}

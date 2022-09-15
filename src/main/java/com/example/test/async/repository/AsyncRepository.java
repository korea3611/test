package com.example.test.async.repository;

import com.example.test.async.AsyncTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsyncRepository extends JpaRepository<AsyncTestEntity, Long> {
}

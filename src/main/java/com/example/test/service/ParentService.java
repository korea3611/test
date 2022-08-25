package com.example.test.service;

import com.example.test.Child;
import com.example.test.dto.ParentAndChildDto;
import com.example.test.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ParentService {

    private final ParentRepository parentRepository;

    public PageImpl<ParentAndChildDto> getParentAndChild1() {
        return parentRepository.findChildOfParent1();
    }

    public Map<Long, List<Child>> getParentAndChild2() {
        return parentRepository.findChildOfParent2();
    }
}

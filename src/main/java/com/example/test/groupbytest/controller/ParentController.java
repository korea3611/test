package com.example.test.groupbytest.controller;

import com.example.test.groupbytest.Child;
import com.example.test.groupbytest.dto.ParentAndChildDto;
import com.example.test.groupbytest.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;

    @GetMapping("1")
    public ResponseEntity<?> getParentAndChild1() {
        PageImpl<ParentAndChildDto> parentAndChild = parentService.getParentAndChild1();
        return new ResponseEntity<>(parentAndChild, HttpStatus.OK);
    }

    @GetMapping("2")
    public ResponseEntity<?> getParentAndChild2() {
        Map<Long, List<Child>> parentAndChild2 = parentService.getParentAndChild2();
        return new ResponseEntity<>(parentAndChild2, HttpStatus.OK);
    }
}

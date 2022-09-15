package com.example.test.groupbytest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParentAndChildDto {

    private Long id;
    private String name;
    private List<ChildDto> childDtoList = new ArrayList<>();

}

package com.example.test.repository;

import com.example.test.Child;
import com.example.test.config.PageRequest;
import com.example.test.dto.ChildDto;
import com.example.test.dto.ParentAndChildDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.example.test.QChild.child;
import static com.example.test.QParent.parent;
import static com.querydsl.core.group.GroupBy.*;

@RequiredArgsConstructor
@Repository
public class ParentRepository {

    private final JPAQueryFactory queryFactory;

    public PageImpl<ParentAndChildDto> findChildOfParent1() {

        Pageable pageable = new PageRequest(1, 100, Sort.Direction.ASC, "id").of();

        List<ParentAndChildDto> childDtoList = queryFactory.from(parent)
                .leftJoin(parent.children, child)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .transform(groupBy(parent.id).list(
                        Projections.fields(
                                ParentAndChildDto.class,
                                parent.id,
                                parent.name,
                                list(
                                        Projections.fields(
                                                ChildDto.class,
                                                child.id,
                                                child.name
                                        )
                                ).as("childDtoList")
                        )
                ));

        Long count = queryFactory
                .select(parent.count())
                .from(parent)
                .leftJoin(parent.children, child)
                .fetchOne();

        return new PageImpl<>(childDtoList, pageable, count);

    }

    public Map<Long, List<Child>> findChildOfParent2() {

        return queryFactory.from(parent)
                .leftJoin(parent.children, child)
                .transform(groupBy(parent.id).as(list(child)));

    }
}

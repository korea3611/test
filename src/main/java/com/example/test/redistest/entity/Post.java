package com.example.test.redistest.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ColumnDefault("0")
    @Column(name = "like_count", nullable = false)
    private Integer likeCount;

    public void likeCountUp() {
        this.likeCount++;
    }
}

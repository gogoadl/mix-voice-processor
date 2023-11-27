package com.hw.mixvoice.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자 자동 추가
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // GenerationType.IDENTITY 옵션을 추가해야만  auto_increment 사용가능
    private long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    private String category;

    @Builder
    public Posts(String title, String content, String author, String category)
    {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
    }

    @Builder
    public void update(String title, String content)
    {
        this.title = title;
        this.content = content;
    }



}

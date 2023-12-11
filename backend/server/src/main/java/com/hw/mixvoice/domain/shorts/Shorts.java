package com.hw.mixvoice.domain.shorts;

import com.hw.mixvoice.domain.posts.BaseTimeEntity;
import com.hw.mixvoice.domain.user.User;
import lombok.Builder;

import javax.persistence.*;

public class Shorts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shorts_id")
    // GenerationType.IDENTITY 옵션을 추가해야만  auto_increment 사용가능
    private long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String author;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String category;

    private Long viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Builder
    public Shorts(String title, String content, String author, String category)
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

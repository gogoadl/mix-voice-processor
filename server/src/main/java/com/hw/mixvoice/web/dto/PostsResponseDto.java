package com.hw.mixvoice.web.dto;

import com.hw.mixvoice.domain.posts.Posts;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private String lastModifiedDate;
    private String category;

    public PostsResponseDto(Posts entity)
    {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.lastModifiedDate = entity.getModifiedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.category = entity.getCategory();
    }

}

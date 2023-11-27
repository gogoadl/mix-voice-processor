package com.hw.mixvoice.web.dto;


import com.hw.mixvoice.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;
    private String category;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author, String category)
    {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
    }

    public Posts toEntity() {
        return Posts.builder().title(title).content(content).author(author).category(category).build();
    }
}

package com.hw.mixvoice.web.dto;

import com.hw.mixvoice.domain.posts.Posts;
import com.hw.mixvoice.domain.shorts.Shorts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShortsSaveDto {
    private String title;
    private String content;
    private String url;

    @Builder
    public ShortsSaveDto(String title, String content, String url)
    {
        this.title = title;
        this.content = content;
        this.url = url;
    }

    public Shorts toEntity() {
        return Shorts.builder().title(title).content(content).url(url).build();
    }
}

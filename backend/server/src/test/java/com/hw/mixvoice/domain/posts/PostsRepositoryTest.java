package com.hw.mixvoice.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup()
    { postsRepository.deleteAll(); }

    @Test
    public void read_load()
    {

        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                             .title(title)
                             .content(content)
                             .author("gogoadl@naver.com")
                             .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_Register()
    {
        LocalDateTime now = LocalDateTime.of(2016,6,4,0,0,0);
        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println("CreateDate = " + posts.getCreatedTime() + " modifiedDate = " + posts.getModifiedTime());

        assertThat(posts.getCreatedTime()).isAfter(now);
        assertThat(posts.getModifiedTime()).isAfter(now);
    }

}

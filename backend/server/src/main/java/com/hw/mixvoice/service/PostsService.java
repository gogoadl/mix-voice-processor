package com.hw.mixvoice.service;

import com.hw.mixvoice.domain.posts.Posts;
import com.hw.mixvoice.domain.posts.PostsRepository;
import com.hw.mixvoice.web.dto.PostsListResponseDto;
import com.hw.mixvoice.web.dto.PostsResponseDto;
import com.hw.mixvoice.web.dto.PostsSaveRequestDto;
import com.hw.mixvoice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // final이 선언된 모든 필드를 인자값으로 하는 생성자를 만들어 줌
@Service
public class PostsService {
    private final PostsRepository postsReporitory;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto)
    {
        return postsReporitory.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto)
    {
        Posts posts = postsReporitory.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id)
    {
        Posts posts = postsReporitory.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsReporitory.delete(posts);
    }

    public PostsResponseDto findById(Long id)
    {
        Posts entity = postsReporitory.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc()
    { // postRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto로 변환 -> List로 반환하는 메소드
        return postsReporitory.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findByCategory(String category)
    { // postRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto로 변환 -> List로 반환하는 메소드
        return postsReporitory.findByCategory(category).stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

}

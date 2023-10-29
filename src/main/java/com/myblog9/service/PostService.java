package com.myblog9.service;

import com.myblog9.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);

    void deletePostById(long id);

    PostDto getPostById(long id);

    PostDto updatePost(long id, PostDto postDto);
}

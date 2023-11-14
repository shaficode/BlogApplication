package com.myblog9.service;

import com.myblog9.payload.CommentDto;

public interface CommentService {
    public CommentDto createComment(long postId, CommentDto commentDto);
}

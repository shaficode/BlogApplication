package com.myblog9.service.impl;

import com.myblog9.entity.Comment;
import com.myblog9.entity.Post;
import com.myblog9.exception.ResourceNotFound;
import com.myblog9.payload.CommentDto;
import com.myblog9.repository.CommentRepository;
import com.myblog9.repository.PostRepository;
import com.myblog9.service.CommentService;

public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepo;
    private PostRepository postRepo;

    public CommentServiceImpl(CommentRepository commentRepo, PostRepository postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post Not Found With Id:" + postId)
        );
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment c = commentRepo.save(comment);

       return mapToDto(c);
    }
    Comment mapToEntity(CommentDto dto){
        Comment comment = new Comment();
        comment.setName(dto.getName());
        comment.setEmail(dto.getEmail());
        comment.setBody(dto.getBody());

        return comment;

    }
    CommentDto mapToDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());

        return dto;
    }
}

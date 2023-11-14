package com.myblog9.controller;

import com.myblog9.payload.PostDto;
import com.myblog9.payload.PostResponse;
import com.myblog9.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post is deleted", HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable  long id){
      PostDto postDto = postService.getPostById(id);
      return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(
        @PathVariable long id,
        @RequestBody PostDto postDto
    ){
       PostDto dto = postService.updatePost(id, postDto);
       return  new ResponseEntity<>(dto,HttpStatus.OK);
    }

    //below url is for checking pagination and sorting of content in page.
    //http://localhost:8080/api/posts?pageNo=0&pageSize=3&sortBy=title&sortDir=asc
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        PostResponse response = postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
        return response;
    }

}
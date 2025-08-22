package com.moretolearn.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.*;

import com.moretolearn.model.Post;

import java.util.List;

@HttpExchange("/posts")
public interface JsonPlaceholderClient {

    @GetExchange
    List<Post> getAllPosts();

    @GetExchange("/{id}")
    Post getPostById(@PathVariable int id);

    @PostExchange
    Post createPost(@RequestBody Post post);

    @PutExchange("/{id}")
    Post updatePost(@PathVariable int id, @RequestBody Post post);

    @DeleteExchange("/{id}")
    void deletePost(@PathVariable int id);
    
    @GetExchange("/{id}")
    Post getPostByIdWithToken(@PathVariable int id, @RequestHeader("Authorization") String token);
}

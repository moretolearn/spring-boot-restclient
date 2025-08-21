package com.moretolearn.controller;

import org.springframework.web.bind.annotation.*;

import com.moretolearn.client.JsonPlaceholderClient;
import com.moretolearn.model.Post;

import java.util.List;

@RestController
@RequestMapping("/local/posts")
public class PostController {

    private final JsonPlaceholderClient client;

    public PostController(JsonPlaceholderClient client) {
        this.client = client;
    }

    @GetMapping
    public List<Post> getAll() {
        return client.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getOne(@PathVariable int id) {
        return client.getPostById(id);
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        return client.createPost(post);
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable int id, @RequestBody Post post) {
        return client.updatePost(id, post);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        client.deletePost(id);
        return "Deleted post with id " + id;
    }
}

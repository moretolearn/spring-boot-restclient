package com.moretolearn.client;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.*;

import com.moretolearn.model.Post;

import java.util.List;

@HttpExchange(url = "/posts", headers = { "Content-Type=application/json", "Accept=application/json",
		"Authorization=Bearer fixed-token" }, accept = MediaType.APPLICATION_JSON_VALUE, contentType = MediaType.APPLICATION_JSON_VALUE)
public interface JsonPlaceholderClient {

	@GetExchange()
	List<Post> getAllPosts();

	@GetExchange("/{id}")
	Post getPostById(@PathVariable int id);

	@PostExchange
	Post createPost(@RequestBody Post post);

	@PutExchange("/{id}")
	Post updatePost(@PathVariable int id, @RequestBody Post post);

	@DeleteExchange(value = "/{id}", headers = { "Content-Type=application/json", "Accept=application/json",
			"Authorization=Bearer fixed-token" }, accept = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	void deletePost(@PathVariable int id);

	@GetExchange("/{id}")
	Post getPostByIdWithToken(@PathVariable int id, @RequestHeader("Authorization") String token);

	@GetExchange(value = "/{id}", accept = MediaType.APPLICATION_JSON_VALUE)
	Post getPostByIdWithTokenAccept(@PathVariable int id, @RequestHeader("Authorization") String token);

	@PostExchange(contentType = MediaType.APPLICATION_JSON_VALUE, accept = MediaType.APPLICATION_JSON_VALUE)
	Post createPostWithContentType(@RequestBody Post post);
}

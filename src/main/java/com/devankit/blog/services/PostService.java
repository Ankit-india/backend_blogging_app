package com.devankit.blog.services;

import java.util.List;

import com.devankit.blog.entities.Post;
import com.devankit.blog.payloads.PostDto;
import com.devankit.blog.payloads.PostResponse;

public interface PostService {

	// Create 
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	// update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	// Delete
	void deletePost(Integer postId);
	
	// get post by Id
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy);
	
	// get All post
	PostDto getPostById(Integer postId);
	
	// get all post by Category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	// get all posts by user
	List<PostDto> getPostsByUser(Integer userId);
	
	// Search Post
	List<PostDto> searchPosts(String keyword);
	
}

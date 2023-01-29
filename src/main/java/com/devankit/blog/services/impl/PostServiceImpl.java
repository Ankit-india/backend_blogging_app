package com.devankit.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devankit.blog.entities.Category;
import com.devankit.blog.entities.Post;
import com.devankit.blog.entities.User;
import com.devankit.blog.exceptions.ResourceNotFoundException;
import com.devankit.blog.payloads.PostDto;
import com.devankit.blog.payloads.PostResponse;
import com.devankit.blog.repositories.CategoryRepo;
import com.devankit.blog.repositories.PostRepo;
import com.devankit.blog.repositories.UserRepo;
import com.devankit.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired 
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setAddedDate(new Date());
		post.setImageName("default.png");
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new  ResourceNotFoundException("Post", "postid", postId));
		
		post.setContent(postDto.getContent());
		post.setTitle(postDto.getTitle());
		post.setAddedDate(new Date());
		post.setImageName(postDto.getImageName());
		Post updatedPost = this.postRepo.save(post);
		PostDto updatedPostDto = this.modelMapper.map(updatedPost, PostDto.class);
		return updatedPostDto;
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		PostDto resPost = this.modelMapper.map(post, PostDto.class);
		return resPost;
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy) {
		Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
		Page<Post> pagePost =  this.postRepo.findAll(p);
		List<Post> allPosts = pagePost.getContent();
		List<PostDto> resAllPosts = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse retPosts = new PostResponse();
		
		retPosts.setContent(resAllPosts);
		retPosts.setPageNumber(pagePost.getNumber());
		retPosts.setPageSize(pagePost.getSize());
		retPosts.setTotalElements(pagePost.getTotalElements());
		retPosts.setTotalPages(pagePost.getTotalPages());
		retPosts.setLastPage(pagePost.isLast());
		return retPosts;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		
		Category categ = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		List<Post> posts = this.postRepo.findByCategory(categ);
		
		List<PostDto> resPosts = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return resPosts;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		
		List<PostDto> resPosts = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return resPosts;
	}
	
	@Override
	public List<PostDto> searchPosts(String keyword){
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

}

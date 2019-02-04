package com.css.vob.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.vob.repository.PostRepository;
import com.css.vob.model.Posts;


@Service
public class PostsService {
	
	@Autowired 
	PostRepository postRepository;
	
	/* get post by post_id */
	public Optional<Posts> findOnePost(Long post_id) {
		return postRepository.findById(post_id);
	}
	
	/*get all posts*/
	public List<Posts> findAllPosts(){
		return (List<Posts>) postRepository.findAll();
	}
	
	/* create post for specific user */
	public void createPost(Posts post) {
		postRepository.save(post);
	}
	
	public List<Posts> findByU_id(Long u_id){
		List<Posts> post=new ArrayList<>();
		postRepository.findPostsByU_id(u_id).forEach(post::add);
		return post;
		
	}
	
	/* update post */
	public void updatePost(Posts post) {
		postRepository.save(post);
	}
	
	
	
	
}

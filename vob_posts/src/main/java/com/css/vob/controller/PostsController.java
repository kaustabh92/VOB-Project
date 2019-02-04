package com.css.vob.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.css.vob.service.PostsService;
//import com.css.vob.exception.PostNotFoundException;
import com.css.vob.model.Posts;
import com.css.vob.model.UserDetails;
import com.css.vob.proxy.UserDetailsProxy;

@RestController
@RequestMapping("/voiceofbengal")
public class PostsController {
	
	
	@Autowired
	PostsService postService;
	
	@Autowired
	UserDetailsProxy userProxy;
	
	/* get post by post_id */
	@GetMapping("/posts/{post_id}")
	public Optional<Posts> getpostById(@PathVariable Long post_id) {
		Optional<Posts> post=postService.findOnePost(post_id);

		return post;
	}
	
	@GetMapping("/posts")
	public List<Posts> getAllPosts(){
		return postService.findAllPosts();
	}
	
	/* create post for a user */
	/*@PostMapping("/userdetails/{user_id}/post")
	public ResponseEntity<Object> createPosts(@PathVariable("user_id") Long user_id,@RequestBody Posts post) {

		
		RestTemplate rt=new RestTemplate();
		HttpHeaders header=new HttpHeaders(); 
		
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity=new HttpEntity<>(header);
		Posts usr=rt.exchange("http://localhost:8091//userdetails/"+user_id, HttpMethod.GET,entity,Posts.class).getBody();
		post.setUsers(usr);
		postService.createPost(post);
		
		URI location=ServletUriComponentsBuilder.
				fromCurrentRequest().
				path("/{user_id}").
				buildAndExpand(post.getPost_id()).toUri();
		return ResponseEntity.created(location).build();
	}*/
	
	@PostMapping("/posts")
	public void createPosts(@RequestBody Posts post) {
		postService.createPost(post);
	}
	
	@GetMapping("/findpostbyareacode/{areacode}")
	List<Posts> getPostByAreacode(@PathVariable(value="areacode") Long areacode){
		
		RestTemplate restTemplate=new RestTemplate();
		
		ResponseEntity<List<UserDetails>> response= 
				restTemplate.exchange("http://localhost:8761/vob_userdetails/voiceofbengal/userdetailsbyareacode/"+areacode, HttpMethod.GET,
						null, new ParameterizedTypeReference<List<UserDetails>>(){	
		});
						
		List<UserDetails> body=response.getBody();
		List<Posts> postList=new ArrayList<>();
		List<Posts> totalPost=new ArrayList<>();
		for(UserDetails users : body) {
			totalPost=(List<Posts>) postService.findByU_id(users.getU_id());
			for(Posts posts : totalPost) {
				postList.add(posts);
			}
		}
		
		return postList;
		
	}
	
	@GetMapping("/findpostbyareacode-feign/{areacode}")
	public List<Posts> getPostByAreacodeUsingfeign(@PathVariable(value="areacode") Long areacode){
		
		List<UserDetails> usersList=userProxy.getUserByAreacode(areacode);
		
		List<Posts> postList=new ArrayList<>();
		List<Posts> totalPost=new ArrayList<>();
		for(UserDetails users : usersList) {
			totalPost=(List<Posts>) postService.findByU_id(users.getU_id());
			for(Posts posts : totalPost) {
				postList.add(posts);
			}
		}
		
		return postList;
	}
	
	@GetMapping("/getAllUsers-feign")
	public List<UserDetails> getAllUsers(){
		return userProxy.getAllUsers();
	}
	
}

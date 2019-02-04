 package com.css.vob.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.css.vob.exception.UserNotFoundException;
import com.css.vob.model.UserDetails;
import com.css.vob.service.UserDetailsService;

@RestController
@RequestMapping("/voiceofbengal")
public class UserController {
	@Autowired
	UserDetailsService userdetailsService;
	
	/* to save an user */
	@PostMapping("/userdetails")
	public ResponseEntity<Object> createUsers(@Valid @RequestBody UserDetails users) {
		UserDetails user = userdetailsService.svae(users);
		//UserDetails.builder().
		
		URI location=ServletUriComponentsBuilder.
				fromCurrentRequest().
				path("/{user_id}").
				buildAndExpand(user.getUser_id()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	/* get all users */
	@GetMapping("/userdetails")
	public List<UserDetails> getAllUsers(){
		return userdetailsService.findAllUser();
	}
	
	/* get user by user_id */
	@GetMapping("/userdetails/{user_id}")
	public Optional<UserDetails> getUserById(@PathVariable Long user_id){
		//System.out.print("Breakpoint");
		Optional<UserDetails> users=userdetailsService.findOneUser(user_id);
		if(!users.isPresent()) {
			throw new UserNotFoundException("id-"+user_id);
		}
		
		return users;
	}
	
	/* delete an user */
	@DeleteMapping("/userdetails/{user_id}")
	public void deleteUser(@PathVariable Long user_id) {
		userdetailsService.deleteUser(user_id);
		
	}
	
	/* get userdetails by areacode */
	@GetMapping("/userdetailsbyareacode/{areacode}")
	public ResponseEntity<List<UserDetails>> getUserByAreacode(@PathVariable(value="areacode") Long areacode){
		List<UserDetails> user=userdetailsService.getuserByAreacode(areacode);
		
		if(user==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user);
	}
	
}

package com.css.vob.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.css.vob.model.UserDetails;
import com.css.vob.repository.UserRepository;

@Service
public class UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	
	/*to save an user */
	public UserDetails svae(UserDetails user) {
		return userRepository.save(user);
	}
	/* search all user */
	public List<UserDetails> findAllUser(){
		return (List<UserDetails>) userRepository.findAll();
	}
	/* get an user by id */
	public Optional<UserDetails> findOneUser(Long user_id) {
		return userRepository.findById(user_id);
	}
	/* delete an user */
	public void deleteUser(Long user_id) {
		userRepository.deleteById(user_id);
	}
	
	/*get a user by areacode*/
	public List<UserDetails> getuserByAreacode(Long areacode){
		List<UserDetails> user=new ArrayList<>();
		userRepository.findByAreacode(areacode).forEach(user :: add);
		return user;
	}

	
	
}

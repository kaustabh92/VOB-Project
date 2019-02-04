package com.css.vob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.css.vob.model.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails,Long> {

	
		List<UserDetails> findByAreacode(Long areacode);
}

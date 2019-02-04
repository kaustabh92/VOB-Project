package com.css.vob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.css.vob.model.*;

public interface PostRepository extends CrudRepository<Posts,Long> {
	
	@Transactional
	@Query(nativeQuery=true,value="select * from vob_posts where u_id = :u_id")
	public List<Posts> findPostsByU_id(@Param("u_id") Long u_id);
}

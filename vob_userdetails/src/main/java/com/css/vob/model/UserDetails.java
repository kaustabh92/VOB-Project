package com.css.vob.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="user_details")
@Builder
@Data
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserDetails implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id", unique=true)
	private Long user_id;
	
	@Column(name="user_name", length=35, nullable=false)
	@Size(min=2,message="Name should have atleast 2 character")
	private String user_name;
	
	@Column(name="gender", nullable=false)
	private String gender;
	
	@Column(name="dob")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dob;
	
	@Column(name="email", length=50, unique=true, nullable=false)
	private String email;
	
	@Column(name="phno", length=11, nullable=false)
	private String phno;
	
	@Column(name="password", nullable=false)
	//@JsonIgnore
	private String password;
	
	@Column(name="areacode", length=11)
	private Long areacode;

	
	@Column(name="u_Id")
	private Long u_id;

	

	
	

}

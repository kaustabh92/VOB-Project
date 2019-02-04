package com.css.vob.model;

import java.util.Date;

import com.css.vob.model.UserDetails.UserDetailsBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Data
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserDetails {

	private Long user_id;
	

	private String user_name;
	
	
	private String gender;
	
	
	private Date dob;
	
	
	private String email;
	
	
	private String phno;
	
	
	private String password;
	
	
	private Long areacode;

	
	private Long u_id;

}

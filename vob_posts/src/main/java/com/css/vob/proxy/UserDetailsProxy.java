package com.css.vob.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.css.vob.model.UserDetails;


//@FeignClient(name="vob-userdetails",url="localhost:8091/voiceofbengal")
@FeignClient(name="netflix-zuul-api-gateway-server")
//@RibbonClient(name="vob-userdetails")
public interface UserDetailsProxy {
	//@GetMapping("/userdetailsbyareacode/{areacode}")
	@GetMapping("/vob-userdetails/userdetailsbyareacode/{areacode}")
	public List<UserDetails> getUserByAreacode(@PathVariable(value="areacode") Long areacode);
	
	@GetMapping("/vob-userdetails/userdetails")
	public List<UserDetails> getAllUsers();
}

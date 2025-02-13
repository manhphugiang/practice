package ca.sheridancollege.giangma.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.giangma.repositories.SecurityRepository;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class UserDetailsSeriveImpl implements UserDetailsService {
	
	private SecurityRepository secRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


		// first step is find the user based on the user name 
		ca.sheridancollege.giangma.beans.User user = secRepo.findUserByUserName(username);
		// if the user doesn't exist, throw exception 
		if(user == null ) {
			System.out.println("User not found");
			
			throw new UsernameNotFoundException("Could not find user");
		}
		// Find the roles connected to that user 
		List<String> roles = secRepo.getRolesById(user.getUserID());
		// convert the roles in to the list of the granted authorities
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		
		for (String r : roles) {
			grantList.add(new SimpleGrantedAuthority(r));
		}
		
		// create a spring user based on our informations
		// import org.springframework.security.core.userdetails.User;
		
		User springUser = new User(user.getUserName(), 
				user.getEncryptedPassword(), grantList);
		
		return (UserDetails)springUser;
		
	}

}

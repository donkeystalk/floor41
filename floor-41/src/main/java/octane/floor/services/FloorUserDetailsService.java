package octane.floor.services;

import octane.floor.models.FloorUser;
import octane.floor.repos.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class FloorUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	
	public void createFloorUser(FloorUser user)
	{
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		user.setPassword(encoder.encodePassword(user.getPassword(), null));
		userRepository.createFloorUser(user);
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		return userRepository.loadUserByUsername(username);
	}

}

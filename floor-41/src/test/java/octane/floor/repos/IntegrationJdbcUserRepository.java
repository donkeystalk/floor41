package octane.floor.repos;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import octane.floor.exceptions.DuplicateUsernameException;
import octane.floor.models.FloorAuthority;
import octane.floor.models.FloorUser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/applicationContext.xml")
public class IntegrationJdbcUserRepository {

	@Autowired
	private JdbcUserRepository repo;
	
	private Md5PasswordEncoder encoder = new Md5PasswordEncoder();
	
	@Test
	public void testInject()
	{
		assertNotNull(repo);
	}
	
	@Rollback(true)
	@Transactional
	@Test
	public void testCreateFloorUser()
	{
		FloorUser user = createUser("junituser", "testPassword");
		repo.createFloorUser(user);
		user= repo.loadUserByUsername("junituser");
		assertEquals(1, user.getAuthorities().size());
		assertEquals("junituser", user.getUsername());
		assertThat(user.getPassword(), is(not("testPassword")));
		assertNotNull(user);
	}
	
	@Rollback(true)
	@Transactional
	@Test(expected=DuplicateUsernameException.class)
	public void testInsertDuplicateFloorUser()
	{

		FloorUser user = createUser("junituser", "testPassword");
		repo.createFloorUser(user);
		user = createUser("junituser", "testPassword");
		repo.createFloorUser(user);
	}
	
	@Rollback(true)
	@Transactional
	@Test
	public void testAddAuthority()
	{
		FloorUser user = createUser("junituser", "testPassword");
		repo.createFloorUser(user);
		user = repo.loadUserByUsername(user.getUsername());
		assertEquals(1, user.getAuthorities().size());
		FloorAuthority auth = createAuthority("ROLE_Admin");
		repo.addAuthority(user.getUsername(), auth.getAuthority());
		user = repo.loadUserByUsername(user.getUsername());
		assertEquals(2, user.getAuthorities().size());
	}
	
	@Rollback(true)
	@Transactional
	@Test(expected=DuplicateKeyException.class)
	public void testAddDuplicateAuthority()
	{
		FloorUser user = createUser("junituser", "testPassword");
		repo.createFloorUser(user);
		user = repo.loadUserByUsername(user.getUsername());
		assertEquals(1, user.getAuthorities().size());
		FloorAuthority auth = createAuthority("ROLE_Admin");
		repo.addAuthority(user.getUsername(), auth.getAuthority());
		repo.addAuthority(user.getUsername(), auth.getAuthority());
	}
	
	private FloorAuthority createAuthority(String authority)
	{
		FloorAuthority auth = new FloorAuthority();
		auth.setAuthority(authority);
		return auth;
	}
	
	private FloorUser createUser(String username, String password)
	{
		FloorUser user = new FloorUser();
		user.setUsername("junituser");
		user.setPassword(encoder.encodePassword("testPass", null));
		return user;
	}
	
}

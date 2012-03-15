package octane.floor.repos;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import octane.floor.exceptions.DuplicateUsernameException;
import octane.floor.models.FloorAuthority;
import octane.floor.models.FloorUser;

public class JdbcUserRepository implements UserRepository {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<FloorUser> FLOOR_USER_ROW_MAPPER = new RowMapper<FloorUser>() {
		public FloorUser mapRow(ResultSet rs, int rowNum) throws SQLException {
			FloorUser user = new FloorUser();
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			return user;
		}
	};

	private RowMapper<GrantedAuthority> FLOOR_AUTHORITY_ROW_MAPPER = new RowMapper<GrantedAuthority>(){
		public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
			FloorAuthority fa = new FloorAuthority();
			fa.setAuthority(rs.getString("authority"));
			return fa;
		}		
	};
	
	public void createFloorUser(FloorUser user)
	{
		Object[] params = new Object[]{user.getUsername()};
		SqlRowSet rs = jdbcTemplate.queryForRowSet(JdbcUserDetailsManager.DEF_USERS_BY_USERNAME_QUERY, params);
		if(rs.first())
		{
			throw new DuplicateUsernameException("User : " + user.getUsername() + " already exists.");
		}
		params = new Object[]{user.getUsername(), user.getPassword(), true};
		jdbcTemplate.update(JdbcUserDetailsManager.DEF_CREATE_USER_SQL, params);
		params = new Object[]{user.getUsername(), "ROLE_User"};
		jdbcTemplate.update(JdbcUserDetailsManager.DEF_INSERT_AUTHORITY_SQL, params);
	}
	
	public FloorUser loadUserByUsername(String username) {
		Object[] params = new Object[]{username};
		FloorUser user = jdbcTemplate.queryForObject(JdbcUserDetailsManager.DEF_USERS_BY_USERNAME_QUERY, params, FLOOR_USER_ROW_MAPPER);
		user.setAuthorities(jdbcTemplate.query(JdbcUserDetailsManager.DEF_AUTHORITIES_BY_USERNAME_QUERY, params, FLOOR_AUTHORITY_ROW_MAPPER));
		return user;
	}

	public void addAuthority(String username, String authority) {
		Object[] params = new Object[]{username, authority};
		jdbcTemplate.update(JdbcUserDetailsManager.DEF_INSERT_AUTHORITY_SQL, params);
	}
	
}

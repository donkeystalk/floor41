package octane.floor.models;

import java.util.Collection;
import java.util.List;

import octane.floor.annotations.FieldMatch;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@FieldMatch.List({
	@FieldMatch(first="password", second="confirmPassword", message="Passwords must match.")
})
public class FloorUser implements UserDetails{

	private static final long serialVersionUID = 5027544886439266745L;
	
	@NotBlank
	@Length(min=5, max=20)
	private String username;
	private List<GrantedAuthority> authorities;
	@NotBlank
	@Length(min=5, max=20)
	private String password;
	private String confirmPassword;
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}

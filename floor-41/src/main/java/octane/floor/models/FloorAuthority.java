package octane.floor.models;

import org.springframework.security.core.GrantedAuthority;

public class FloorAuthority implements GrantedAuthority{
	
	private static final long serialVersionUID = -8580247064029700242L;
	private String authority;

	public String getAuthority() {
		return authority;
	}
	
	public void setAuthority(String authority){
		this.authority = authority;
	}

}

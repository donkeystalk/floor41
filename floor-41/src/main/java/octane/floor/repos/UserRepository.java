package octane.floor.repos;


import octane.floor.models.FloorUser;

public interface UserRepository {
	FloorUser loadUserByUsername(String username);
	void createFloorUser(FloorUser user);
	void addAuthority(String username, String authority);
}

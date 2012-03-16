package octane.floor.repos;

import java.util.List;

import octane.floor.models.JobCategory;
import octane.floor.models.UserExperience;

public interface ProfileRepository {
	List<UserExperience> findUserExperienceByUsername(String username);
	void insertUserExperience(UserExperience ue);
	void insertJobCategory(JobCategory jc);
}

package octane.floor.repos;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import octane.floor.models.JobCategory;
import octane.floor.models.UserExperience;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration("classpath:config/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class IntegrationHibernateProfileRepository {

	@Autowired
	private HibernateProfileRepository repo;
	
	@Test
	public void testInject()
	{
		assertNotNull(repo);
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void testInsertUserExperience()
	{
		UserExperience ue = createUserExperience("junitUser");
		repo.insertUserExperience(ue);
		List<UserExperience> experience = repo.findUserExperienceByUsername("junitUser");
		assertNotNull(experience);
		assertEquals(1, experience.size());
	}

	private JobCategory createJobCategory()
	{
		JobCategory jc = new JobCategory();
		jc.setId(2);
		jc.setName("Java");
		jc.setParentId(2);
		return jc;
	}
	
	private UserExperience createUserExperience(String username) {
		UserExperience ue = new UserExperience();
		ue.setStartDate(new Date());
		ue.setJobCategory(createJobCategory());
		ue.setEndDate(null);
		ue.setLocation("Milwaukee");
		ue.setStartDate(new Date());
		ue.setTitle("Associate Developer Analyst");
		ue.setUsername(username);
		return ue;
	}
	
}

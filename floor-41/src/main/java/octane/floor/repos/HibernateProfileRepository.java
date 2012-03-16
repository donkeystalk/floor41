package octane.floor.repos;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import octane.floor.models.JobCategory;
import octane.floor.models.UserExperience;

@Repository
public class HibernateProfileRepository implements ProfileRepository{

	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	public void insertJobCategory(JobCategory jc)
	{
		hibernateTemplate.saveOrUpdate(jc);
	}
	
	public List<UserExperience> findUserExperienceByUsername(String username) {
		return hibernateTemplate.find("from UserExperience where username = ?", username);
	}
	
	public JobCategory findJobCategoryById(int id)
	{
		return hibernateTemplate.get(JobCategory.class, id);
	}

	public void insertUserExperience(UserExperience ue) {
		hibernateTemplate.saveOrUpdate(ue);
	}

}

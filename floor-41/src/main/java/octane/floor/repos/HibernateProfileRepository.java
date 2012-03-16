package octane.floor.repos;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import octane.floor.models.UserExperience;

@Repository
public class HibernateProfileRepository implements ProfileRepository{

	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	public List<UserExperience> findUserExperienceByUsername(String username) {
		return hibernateTemplate.find("from UserExperience where username = ?", username);
	}

	@Override
	public void insertUserExperience(UserExperience ue) {
		hibernateTemplate.saveOrUpdate(ue);
	}

}

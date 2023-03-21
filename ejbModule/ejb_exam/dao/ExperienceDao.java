package ejb_exam.dao;

import javax.enterprise.context.RequestScoped;

import ejb_exam.entities.Experience;
@RequestScoped
public class ExperienceDao extends DaoEntite<Experience, Long> {

	@Override
	protected Class<Experience> getTypeClass() {
		return Experience.class;
	}
	
}

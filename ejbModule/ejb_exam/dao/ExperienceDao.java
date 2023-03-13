package ejb_exam.dao;

import ejb_exam.entities.Experience;

public class ExperienceDao extends DaoEntite<Experience, Long> {

	@Override
	protected Class<Experience> getTypeClass() {
		return Experience.class;
	}
	
}

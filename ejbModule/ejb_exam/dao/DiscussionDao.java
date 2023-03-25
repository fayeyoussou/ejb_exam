package ejb_exam.dao;

import javax.enterprise.context.RequestScoped;

import ejb_exam.entities.Discussion;

@RequestScoped
public class DiscussionDao extends DaoEntite<Discussion, Long> {

	@Override
	protected Class<Discussion> getTypeClass() {
		return Discussion.class;
	}

}

package ejb_exam.dao;

import ejb_exam.entities.User;

public class UserDao extends DaoEntite<User, Long> {

	@Override
	protected Class<User> getTypeClass() {
		return User.class;
	}

}

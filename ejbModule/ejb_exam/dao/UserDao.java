package ejb_exam.dao;

import javax.enterprise.context.RequestScoped;

import ejb_exam.entities.User;
@RequestScoped
public class UserDao extends DaoEntite<User, Long> {
	@Override
	protected Class<User> getTypeClass() {
		return User.class;
	}
}

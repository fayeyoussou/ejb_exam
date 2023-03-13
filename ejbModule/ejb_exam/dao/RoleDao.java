package ejb_exam.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ejb_exam.entities.Role;
@RequestScoped
public class RoleDao extends DaoEntite<Role,Long> {

	@Override
	protected Class<Role> getTypeClass() {
		return Role.class;
	}

}

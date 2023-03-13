package ejb_exam.service.Role;


import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ejb_exam.dao.RoleDao;
import ejb_exam.dto.Mapper;
import ejb_exam.dto.request.RoleRequest;
import ejb_exam.dto.response.RoleResponse;
import ejb_exam.entities.Role;

@Stateless
public class RoleService implements RoleLocalService {
	@Inject
	private RoleDao roleDao;

	@Override
	public Role getRole(Long id) {
		return roleDao.getById(id);
	}

	@Override
	public RoleResponse getRoleById(Long id) {
		Role role = getRole(id);
		return Mapper.roleToResponse(role);
	}

	@Override
	public RoleResponse saveRole(RoleRequest roleRequest) {
		Role role  = Mapper.roleRequestToRole(roleRequest);
		roleDao.save(role);
		return Mapper.roleToResponse(role);
	}

	@Override
	public RoleResponse editRole(Long id,RoleRequest roleRequest) {
		Role role = getRole(id);
		role.setNom(roleRequest.getNom());
		roleDao.edit(role);
		return Mapper.roleToResponse(roleDao.edit(role));
	}

	@Override
	public Boolean delete(Long id) {
		return roleDao.delete(id);
	}

	@Override
	public List<RoleResponse> list() {
		return Mapper.rolesToRolesResponses(roleDao.list());
	}
	
}

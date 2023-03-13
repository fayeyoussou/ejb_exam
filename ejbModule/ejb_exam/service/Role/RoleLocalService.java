package ejb_exam.service.Role;

import java.util.List;

import javax.ejb.Local;

import ejb_exam.dto.request.RoleRequest;
import ejb_exam.dto.response.RoleResponse;
import ejb_exam.entities.Role;

@Local
public interface RoleLocalService {
	Role getRole(Long id);
	List<RoleResponse> list();
	RoleResponse getRoleById(Long id);
	RoleResponse saveRole(RoleRequest role);
	RoleResponse editRole(Long id,RoleRequest role);
	Boolean delete(Long id);
}

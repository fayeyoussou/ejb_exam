package ejb_exam.service.Role;


import javax.ejb.Remote;

import ejb_exam.dto.request.RoleRequest;
import ejb_exam.dto.response.RoleResponse;
import ejb_exam.entities.Role;

@Remote
public interface RoleRemoteService {
	Role getRole(Long id);
	RoleResponse getRoleById(Long id);
	RoleResponse saveRole(RoleRequest role);
	RoleResponse editRole(Long id,RoleRequest role);
	Boolean delete(Long id);
}

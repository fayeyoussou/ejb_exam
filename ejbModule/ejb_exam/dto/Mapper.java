package ejb_exam.dto;

import java.util.ArrayList;
import java.util.List;

import ejb_exam.dto.request.RoleRequest;
import ejb_exam.dto.response.RoleResponse;
import ejb_exam.entities.Role;

public abstract class Mapper {
	public static RoleResponse roleToResponse(Role role) {
		RoleResponse roleResponse = new RoleResponse();
		roleResponse.setId(role.getId());
		roleResponse.setNom(role.getNom());
		return roleResponse;
	}
	public static Role roleRequestToRole(RoleRequest roleRequest) {
		Role role = new Role();
		role.setNom(roleRequest.getNom());
		return role;
	}
	public static List<RoleResponse> rolesToRolesResponses(List<Role> roles){
		List<RoleResponse> roleResponses = new ArrayList<>();
		roles.forEach(role->roleResponses.add(roleToResponse(role)));
		return roleResponses;
	}
}

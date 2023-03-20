package ejb_exam.service.auth;

import java.util.List;

import org.wildfly.security.password.Password;

import ejb_exam.dto.request.RoleRequest;
import ejb_exam.dto.response.RoleResponse;
import ejb_exam.entities.Role;
import ejb_exam.entities.User;

public interface AuthLocalService {
	UserResponse login(LoginRequest);
}

package ejb_exam.service.auth;



import javax.ejb.Local;

import ejb_exam.dto.request.LoginRequest;
import ejb_exam.dto.request.RegisterRequest;
import ejb_exam.dto.response.UserResponse;
import ejb_exam.entities.CV;
import ejb_exam.entities.User;

@Local
public interface AuthLocalService {
	User login(LoginRequest request);
	User register(RegisterRequest request);
	void mailUser(CV cv);
	User isLogin(String user, String pass);
}

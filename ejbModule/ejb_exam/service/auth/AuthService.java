package ejb_exam.service.auth;


import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import ejb_exam.dao.ExperienceDao;
import ejb_exam.dao.RoleDao;
import ejb_exam.dao.UserDao;
import ejb_exam.dto.request.ExperienceRequest;
import ejb_exam.dto.request.LoginRequest;
import ejb_exam.dto.request.RegisterRequest;
import ejb_exam.entities.Experience;
import ejb_exam.entities.User;

@Stateless
public class AuthService implements AuthLocalService {
	private static final Logger log = Logger.getLogger(AuthService.class);

	@Inject private UserDao userDao;
	@Inject private ExperienceDao experienceDao;
	@Inject private RoleDao roleDao;
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public User login(LoginRequest request) {
		try {
			User user = userDao.filterByFieldName("login", request.getLogin().toLowerCase()).get(0);
			if (passwordEncoder.matches(request.getPassword(), user.getPassword()))
				return user;
			else
				throw new Exception("password incorrect");
		} catch (Exception e) {
			log.error(e.fillInStackTrace());
			return null;
		}

	}

	@Override
	public User register(RegisterRequest request) {

		try {
			User user = new User();
			user.setAge(request.getAge());
			user.setLogin(request.getLogin().toLowerCase());
			user.setMail(request.getMail().toLowerCase());
			user.setNiveau(request.getNiveau());
			user.setNom(request.getNom().toLowerCase());
			user.setPassword(passwordEncoder.encode(request.getPassword()));
			user.setPrenom(request.getPrenom().toLowerCase());
			user.setTelephone(request.getTelephone());
			user.setRole(roleDao.filterByFieldName("nom", "USER").get(0));
			user = userDao.save(user);
			for (ExperienceRequest exp : request.getExperiences()) {
				Experience experience = new Experience();
				experience.setDescription(exp.getDescription());
				experience.setTitle(exp.getTitle());
				experience.setUser(user);
				experienceDao.save(experience);
				user.getExperiences().add(experience);
			}
			mailUser(user);
			return user;
		} catch (Exception e) {
			log.error(e.fillInStackTrace());
			return null;
		}

	}

	@Override
	public Boolean mailUser(User user) {
		try {
			return true;
		} catch (Exception e) {
			log.error(e.fillInStackTrace());
			return false;
		}
	}
	

}

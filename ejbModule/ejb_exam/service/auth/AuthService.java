package ejb_exam.service.auth;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ejb_exam.dao.CVDao;
import ejb_exam.dao.ExperienceDao;
import ejb_exam.dao.RoleDao;
import ejb_exam.dao.UserDao;
import ejb_exam.dto.request.ExperienceRequest;
import ejb_exam.dto.request.LoginRequest;
import ejb_exam.dto.request.RegisterRequest;
import ejb_exam.entities.CV;
import ejb_exam.entities.Experience;
import ejb_exam.entities.User;

@Stateless
public class AuthService implements AuthLocalService {
	private static final Logger log = Logger.getLogger(AuthService.class);

	@Inject private UserDao userDao;
	@Inject private ExperienceDao experienceDao;
	@Inject private RoleDao roleDao;
	@Inject private CVDao cVDao;
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
			
			user.setLogin(request.getLogin().toLowerCase());
			
			user.setNom(request.getNom().toLowerCase());
			user.setPassword(passwordEncoder.encode(request.getPassword()));
			user.setPrenom(request.getPrenom().toLowerCase());
			
			user.setRole(roleDao.filterByFieldName("nom", "USER").get(0));
			user = userDao.save(user);
			CV cv = new CV();
			cv.setMail(request.getMail().toLowerCase());
			cv.setNiveau(request.getNiveau());
			cv.setTelephone(request.getTelephone());
			cv.setAge(request.getAge());
			cv.setPrenom(request.getPrenom());
			cv.setNom(request.getNom());
			cv.setUser(user);
			cVDao.save(cv);
			for (ExperienceRequest exp : request.getExperiences()) {
				Experience experience = new Experience();
				experience.setDescription(exp.getDescription());
				experience.setTitle(exp.getTitle());
				experience.setCv(cv);
				experienceDao.save(experience);
				cv.getExperiences().add(experience);
			}
			mailUser(cv);
			return user;
		} catch (Exception e) {
			log.error(e.fillInStackTrace());
			return null;
		}

	}

	@Override
	public void mailUser(CV cv) {
		try {
			String username = "fayeyoussoupha@gmail.com";
	        String password = "infdaiydzsivrjca";

	        // Configurez les propriétés pour l'envoi de l'e-mail
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");

	        // Créez une session avec l'authentification
	        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	        });

	        // Créez le message à envoyer
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(username));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(cv.getMail()));
	        message.setSubject("inscription reussie");
	        message.setText("vous avez bien reussit votre inscription pour l'account : \n"+cv.getUser().getLogin());

	        // Envoyez le message
	        Transport.send(message);
		} catch (Exception e) {
			log.error(e.fillInStackTrace());
			
		}
	}

	@Override
	public User isLogin(String login, String pass) {
		User user = userDao.findOneByFieldName("login", login);
		if(user != null && user.getPassword().equalsIgnoreCase(pass)) return user;
		return null;
	}
	
	

}

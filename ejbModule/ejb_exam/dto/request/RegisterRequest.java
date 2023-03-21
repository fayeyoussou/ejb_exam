package ejb_exam.dto.request;

import java.util.List;

import lombok.Data;
@Data
public class RegisterRequest {
	private String login;
	private String password;
	private String prenom;
	private String nom;
	private String mail;
	private String telephone;
	private int age;
	private String niveau;
	private List<ExperienceRequest> experiences;
}

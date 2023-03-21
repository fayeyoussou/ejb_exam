package ejb_exam.dto.response;

import java.util.List;

import ejb_exam.entities.Experience;
import lombok.Data;

@Data
public class UserResponse {
	private String login;
	private String password;
	private String prenom;
	private String nom;
	private String mail;
	private String telephone;
	private int age;
	private String niveau;
	private List<Experience> experiences;
}

package ejb_exam.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "cvs")
@Data
public class Cv {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id",nullable = false)
	private User user;
	private String nom;
	private String prenom;
	private int age;
	private String mail;
	private String telephone;
	private String niveau;
	@ManyToMany(mappedBy = "cvs",cascade = CascadeType.ALL)
	private List<Experience> experiences= new ArrayList<>();

}

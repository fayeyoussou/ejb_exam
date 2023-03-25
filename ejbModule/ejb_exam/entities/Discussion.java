package ejb_exam.entities;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



import lombok.Data;

@Entity
@Table(name = "discussions")
@Data
public class Discussion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String sujet;
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name = "createur",nullable = false)
	private User createur;
	@OneToMany(mappedBy = "discussion",cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	private List<Message> messages= new ArrayList<>();
	@Column(name = "date_creation", nullable = false)
    private OffsetDateTime dateMessage = OffsetDateTime.now(ZoneOffset.UTC);
	private int vue = 0;
}

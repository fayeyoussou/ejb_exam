package ejb_exam.dto.response;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ejb_exam.entities.Message;
import ejb_exam.entities.User;
import lombok.Data;
@Data
public class DiscussionListResponse {
	
	private Long id;
	
	private String sujet;
	
	private String createur;
	
	private int nombreMessages= 0;
    private String dateMessage ;
    private String dernierMessageUser;
    private String dernierMessageDate;
	private int vue = 0;
}

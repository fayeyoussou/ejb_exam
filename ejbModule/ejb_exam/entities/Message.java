package ejb_exam.entities;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Entity @Table(name = "messages") @Data
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "TEXT",nullable = false)
	private String contenu;
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name = "envoyeur",nullable = false)
	private User envoyeur;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name = "discussion_id",nullable = false)
	private Discussion discussion;
	@Column(name = "date_message", nullable = false)
    private OffsetDateTime dateMessage = OffsetDateTime.now(ZoneOffset.UTC);
}

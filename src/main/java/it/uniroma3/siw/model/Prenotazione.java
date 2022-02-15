package it.uniroma3.siw.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
public class Prenotazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private Long id;
	
	@Column
	private String data_prenotazione;
	
	@Column
	private String data_intervento;
	

	@ManyToOne
	private Meccanico meccanico;
	
	@ManyToOne
	private Utente cliente;
	
	@ManyToOne
	private Tipologia tipologia;
	
	@Column
	private Float prezzo;
	
	@Column (columnDefinition="TEXT")
	private String descrizione;
	
	@Column
	private Boolean conferma;
	
	
}

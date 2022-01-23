package it.uniroma3.siw.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
public class Prenotazione {
	
	@EmbeddedId
	private PrenotazioneId id;
	
	@Column
	private Date data_prenotazione;
	
	@Column
	private Date data_intervento;
	

	@ManyToOne
	@MapsId("Meccanico_id")
	@JoinColumn(name ="Meccanico_id")
	private Meccanico meccanico;
	
	@ManyToOne
	@MapsId("Utente_id")
	@JoinColumn(name = "Utente_id")
	private Utente cliente;
	
	@ManyToOne
	@MapsId("Intervento_id")
	@JoinColumn(name = "Intervento_id")
	private Tipologia tipologia;
	
}

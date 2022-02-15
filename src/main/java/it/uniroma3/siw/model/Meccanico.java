package it.uniroma3.siw.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
public class Meccanico {

	public Meccanico(){};
	public Meccanico(String nome, String cognome, List<Tipologia> tipologia) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.tipologia = tipologia;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String cognome;
	
	@ManyToMany
	private List<Tipologia> tipologia;
	
	@OneToMany
	private List<Prenotazione> prenotazioni;
	
	
}

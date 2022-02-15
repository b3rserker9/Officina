package it.uniroma3.siw.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
public class Tipologia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter (value = AccessLevel.NONE)
	private Long id;
	
	@Column
	private String nome;
	
	@Column(columnDefinition="TEXT")
	private String descrizione;
	
	@Column
	private String img;
	
	@Column
	private float prezzo;

	public Tipologia(){}
	public Tipologia(String nome, String descrizione, float prezzo, String img) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.img = img;
	}
	
	

}

package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.Utente;



public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Long>{
	
	public List<Prenotazione> findByCliente(Utente cliente);

}

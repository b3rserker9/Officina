package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.PrenotazioneRepository;

@Service
public class PrenotazioneService {
	@Autowired
	private PrenotazioneRepository prenotazionerepository;
	
	 @Transactional
	    public Prenotazione save(Prenotazione prenotazione) {
	        return this.prenotazionerepository.save(prenotazione);
	    }

	 @Transactional
		public List<Prenotazione> findbycliente(Utente user) {
			return  this.prenotazionerepository.findByCliente(user);
		}
	 
	 @Transactional
		public Prenotazione prenotazioneId(Long id) {
			Optional<Prenotazione> optional = prenotazionerepository.findById(id);
			return optional.orElse(null);
		}
	 
	 @Transactional
		public List<Prenotazione> all() {
			return  (List<Prenotazione>) this.prenotazionerepository.findAll();
		}
}

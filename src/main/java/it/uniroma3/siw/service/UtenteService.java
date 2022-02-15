package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Meccanico;
import it.uniroma3.siw.model.Tipologia;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.UtenteRepository;

@Service
public class UtenteService {

	@Autowired
	UtenteRepository utenteRepository;
	
	@Transactional
	public Utente getphone(String phone) {
		Optional<Utente> optional = utenteRepository.findByPhone(phone);
		return optional.orElse(null);
	}
	
	@Transactional
	public Utente save(Utente u) {
		
		return this.utenteRepository.save(u);
	}
	
	@Transactional
	public List<Utente> getClienti() {
		return utenteRepository.findClienti();
	}
	
	@Transactional
	public void delete(Long id) {
		 this.utenteRepository.deleteById(id);
	}

	@Transactional
	public Utente findById(Long id) {
		Optional<Utente> r = this.utenteRepository.findById(id);
		return r.orElse(null);
	}
	
}

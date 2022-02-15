package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Tipologia;
import it.uniroma3.siw.repository.TipologiaRepository;

@Service
public class TipologiaService {
	
	@Autowired
	private TipologiaRepository tipologiarepository;
	
	@Transactional
	public List<Tipologia> tipi() {
		System.out.println(this.tipologiarepository.findAll());
		return (List<Tipologia>) this.tipologiarepository.findAll();
	}

	@Transactional
	public Tipologia save(Tipologia t) {
		return this.tipologiarepository.save(t);
	}
	
	@Transactional
	public Tipologia findById(Long id) {
		Optional<Tipologia> r = this.tipologiarepository.findById(id);
		return r.orElse(null);
	}
	
	@Transactional
	public Tipologia findByNome(String n) {
		Optional<Tipologia> r = this.tipologiarepository.findByNome(n);
		return r.orElse(null);
	}


}

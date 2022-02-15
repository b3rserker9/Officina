package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Meccanico;
import it.uniroma3.siw.model.Tipologia;
import it.uniroma3.siw.repository.MeccanicoRepository;


@Service
public class MeccanicoService {
	
	@Autowired
	private MeccanicoRepository meccanicoRepository;
	
	@Transactional
	public List<Meccanico> all() {
		return (List<Meccanico>) this.meccanicoRepository.findAll();
	}
	
	@Transactional
	public Meccanico meccanicoid(Long id) {
		Optional<Meccanico> optional = meccanicoRepository.findById(id);
		return optional.orElse(null);
	}
	
	@Transactional
    public List<Meccanico> meccanicobyTipologia(Long id) {
        List<Meccanico> meccanico = this.meccanicoRepository.findByIdTipologia(id);
        return meccanico;
    }
	
	@Transactional
	public Meccanico save(Meccanico m) {
		return this.meccanicoRepository.save(m);
	}

}

package it.uniroma3.siw;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import it.uniroma3.siw.model.Meccanico;
import it.uniroma3.siw.model.Tipologia;
import it.uniroma3.siw.repository.MeccanicoRepository;
import it.uniroma3.siw.repository.TipologiaRepository;
import it.uniroma3.siw.service.TipologiaService;

@Service
public class runner  {
	
	@Autowired
	private TipologiaRepository tipologiarepository;
	
	@Autowired
	private MeccanicoRepository meccanicorepository;
	
	/*@PostConstruct
	public void popola() {

		Tipologia t1 = new Tipologia("Cambio olio", "prova", 50,"Cambio_olio.png");
		Tipologia t2 = new Tipologia("Tagliando", "prova2", 79,"Tagliando.png");
		Tipologia t3 = new Tipologia("Autolavaggio", "prova3", 15,"Autolavaggio.png");
		
		this.tipologiarepository.save(t1);
		this.tipologiarepository.save(t2);
		this.tipologiarepository.save(t3);
		
		List<Tipologia> s = new ArrayList<Tipologia>();
		s.add(t1);
		s.add(t3);
		Meccanico m1 = new Meccanico("Mario", "prova", s);
		meccanicorepository.save(m1);
		s = new ArrayList<Tipologia>();
		s.add(t2);
		s.add(t3);
		Meccanico m2 = new Meccanico("Marco", "prova2", s);
		meccanicorepository.save(m2);
		
	}*/
}

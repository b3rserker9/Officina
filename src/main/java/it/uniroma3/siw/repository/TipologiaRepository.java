package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Tipologia;


public interface TipologiaRepository extends CrudRepository<Tipologia, Long> {

	Optional<Tipologia> findByNome(String n);
	

}

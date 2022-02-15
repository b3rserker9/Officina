package it.uniroma3.siw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long> {

	public Optional<Utente> findByPhone(String phone_number);
	
	@Query(value = "SELECT * FROM utente u Join credentials c ON (u.id = c.user_id) WHERE c.role = 'DEFAULT'", nativeQuery = true)
	public List<Utente> findClienti();
}

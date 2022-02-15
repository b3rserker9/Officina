package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Credentials;

public interface CredentialsRepository extends CrudRepository<Credentials, Long> {
	
	public Optional<Credentials> findByUsername(String username);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "delete from credentials c WHERE c.user_id = ?1", nativeQuery = true)
	 void deleteUser(Long id);
}

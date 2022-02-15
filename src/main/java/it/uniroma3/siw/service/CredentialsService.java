package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.repository.UtenteRepository;

@Service
public class CredentialsService {

    @Autowired
    protected PasswordEncoder passwordEncoder;

	@Autowired
	protected CredentialsRepository credentialsRepository;
	
	@Autowired
	UtenteRepository utenteRepository;
	
	@Transactional
	public Credentials getCredentials(Long id) {
		Optional<Credentials> result = this.credentialsRepository.findById(id);
		return result.orElse(null);
	}

	@Transactional
	public Credentials getCredentials(String username) {
		Optional<Credentials> result = this.credentialsRepository.findByUsername(username);
		return result.orElse(null);
	}
		
    @Transactional
    public Credentials saveCredentials(Credentials credentials) {
    	//credentials.setRole(Credentials.ADMIN_ROLE);
       credentials.setRole(Credentials.DEFAULT_ROLE);
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
    }
    
    @Transactional
    public Credentials saveCliente(Credentials credentials) {
    	//credentials.setRole(Credentials.ADMIN_ROLE);
       credentials.setRole(Credentials.DEFAULT_ROLE);
        return this.credentialsRepository.save(credentials);
    }
    
    @Transactional
    public Credentials saveAdmin(Credentials credentials) {
        credentials.setRole(Credentials.ADMIN_ROLE);
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
    }
    
    @Transactional
    public String getRoleAuthenticated() {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = this.getCredentials(userDetails.getUsername());
    	return credentials.getRole();
    }
    
    @Transactional
	public void delete(Long id) {
		 this.credentialsRepository.deleteUser(id);
		 this.utenteRepository.deleteById(id);
	}
}

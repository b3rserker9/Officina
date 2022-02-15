package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

import org.springframework.validation.Errors;

import it.uniroma3.siw.model.Tipologia;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.TipologiaService;

@Component
public class TipologiaValidator implements Validator {

	@Autowired
	private TipologiaService tipologiaservice;
	
	@Override
	public void validate(Object o, Errors errors) {
		Tipologia tipo =(Tipologia) o;
		String nome = tipo.getNome().trim();
		
		if(nome.isEmpty())
			errors.rejectValue("nome", "required");
		else if (this.tipologiaservice.findByNome(nome) != null)
			errors.rejectValue("nome", "duplicate");
		
	}

	 @Override
	    public boolean supports(Class<?> clazz) {
	        return Utente.class.equals(clazz);
	    }
}

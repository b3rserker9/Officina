package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.controller.validator.CredentialsValidator;
import it.uniroma3.siw.controller.validator.UtenteValidator;
import it.uniroma3.siw.service.CredentialsService;

@Controller
public class AuthenticationController {

	@Autowired
	private UtenteValidator userValidator;
	
	@Autowired
	private CredentialsValidator credentialsValidator;

	@Autowired
	private CredentialsService credentialsService;
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String RegisterForm (Model model) {
		model.addAttribute("user", new Utente());
		model.addAttribute("credentials", new Credentials());
		return "register";
	}
	
	 @RequestMapping(value = "/register" , method = RequestMethod.POST)
	    public String registerUser(@ModelAttribute("user") Utente user,
	                 BindingResult userBindingResult,
	                 @ModelAttribute("credentials") Credentials credentials,
	                 BindingResult credentialsBindingResult,
	                 Model model) {
		 System.out.println("CIAO");

	        this.userValidator.validate(user, userBindingResult);
	        this.credentialsValidator.validate(credentials, credentialsBindingResult);

	        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {

	            credentials.setUser(user);
	            credentialsService.saveCredentials(credentials);
	            return "index";
	        }
	        return "register";
	    }
	 
	 @RequestMapping(value = "/default", method = RequestMethod.GET)
	    public String defaultAfterLogin(Model model) {
	        
	    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
	    	Utente utente = credentials.getUser();
	    	model.addAttribute("user", utente);
	    	
	    	//se e' admin
	    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
	            return "admin/home";
	        }
	    	//se non lo e'
	        return "home";
	    }
}

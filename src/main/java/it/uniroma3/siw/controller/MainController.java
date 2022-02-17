package it.uniroma3.siw.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JacksonInject.Value;

import it.uniroma3.siw.controller.validator.CredentialsValidator;
import it.uniroma3.siw.controller.validator.MeccanicoValidator;
import it.uniroma3.siw.controller.validator.TipologiaValidator;
import it.uniroma3.siw.controller.validator.UtenteValidator;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Meccanico;
import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.Tipologia;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.MeccanicoService;
import it.uniroma3.siw.service.PrenotazioneService;
import it.uniroma3.siw.service.TipologiaService;
import it.uniroma3.siw.service.UtenteService;

@Controller
public class MainController {
	
	@Autowired
	private TipologiaService tipologiaservice;
	
	@Autowired
	private MeccanicoService meccanicoservice;
	
	@Autowired
	private UtenteService utenteservice;
	
	@Autowired
	private UtenteValidator userValidator;
	
	@Autowired
	private CredentialsValidator credentialsValidator;
	
	@Autowired
	private TipologiaValidator tipologiavalidator;
	
	@Autowired
	private MeccanicoValidator meccanicovalidator;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private PrenotazioneService prenotazioneservice;

	@GetMapping(value ={"/","/index","/login"})
	public String index() {
		/*System.out.println(error.orElse(null));
		if(error.orElse(null) != null) {
			model.addAttribute("error",true);
			System.out.println("true");
		}else {
			model.addAttribute("error",false);
			System.out.println("false");
		}*/
		return "index";
	}

	@RequestMapping(value = "admin/prenota/{id}", method = RequestMethod.GET)
	public String Prenota (Model model,@PathVariable("id") Long id) {
		Tipologia t = this.tipologiaservice.findById(id);
		model.addAttribute("meccanici",this.meccanicoservice.meccanicobyTipologia(id));
		model.addAttribute("Cliente",this.utenteservice.getClienti());
		model.addAttribute("tipologia",t);
		Prenotazione prenota = new Prenotazione();
		prenota.setTipologia(t);
		prenota.setPrezzo(t.getPrezzo());
		model.addAttribute("prenota",prenota);
		return "/admin/Prenota_Intervento";
	}
	

	
	@RequestMapping(value = "admin/prenota_tipo", method = RequestMethod.GET)
	public String Prenota_tipo(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		Utente utente = credentials.getUser();
		
		model.addAttribute("users", this.utenteservice.getClienti());
		model.addAttribute("user",utente);
		System.out.println(tipologiaservice.tipi());
		model.addAttribute("tipi",tipologiaservice.tipi());
		return "/admin/prenota_tipo";
	}
	
	
	@RequestMapping(value = "/clienti/tipi", method = RequestMethod.GET)
	public String tipologia(Model model) {
	
		model.addAttribute("tipi",tipologiaservice.tipi());
		return "/clienti/interventi";
	}
	
	@RequestMapping(value = "/prenota", method = RequestMethod.POST)
	public String Prenota_post (Model model,@ModelAttribute("prenota") Prenotazione prenota) {
		prenota.setConferma(false);
		prenota.setData_prenotazione(new SimpleDateFormat("dd/MMM/yyyy HH:mm").format(new Date()));
		prenotazioneservice.save(prenota);
		return "redirect:/default";
	}
	
	@GetMapping(value = "admin/confermaIntervento/{id}")
    public String confermaIntervento(@PathVariable("id") Long id, Model model) {
		Prenotazione p =this.prenotazioneservice.prenotazioneId(id);;
		System.out.println(p);
        model.addAttribute("meccanici", meccanicoservice.meccanicobyTipologia(p.getTipologia().getId()));
        model.addAttribute("prenotazione",p);
        return "/admin/conferma_intervento";
    }
	
	@PostMapping(value = "/admin/conferma_prenotazione")
	public String postconferma(Model model,@ModelAttribute("prenotazione") Prenotazione prenotazione,@RequestParam Long ClienteId) throws ParseException {
		Prenotazione p =this.prenotazioneservice.prenotazioneId(ClienteId);
		p.setMeccanico(prenotazione.getMeccanico());
		p.setData_intervento(prenotazione.getData_intervento());
		p.setConferma(true);
		System.out.println(prenotazione);
		this.prenotazioneservice.save(p);
		return "redirect:/admin/Interventi";
	}
	
	@GetMapping(value ="clienti/cronologia")
	public String Cronologia(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		Utente utente = credentials.getUser();
		model.addAttribute("prenotazione",this.prenotazioneservice.findbycliente(utente));
		return "/clienti/cronologia_interventi";
	}
	
	@GetMapping(value ="admin/all_tipologie")
	public String getTipologia(Model model) {	
		model.addAttribute("tipologia",new Tipologia()); 
		model.addAttribute("tipi",this.tipologiaservice.tipi());
		return "admin/Tipologie";
	}
	@RequestMapping(value = "/NewTipologia", method = RequestMethod.POST)
	public String new_tipologia (Model model,@ModelAttribute("tipologia") Tipologia tipologia,@RequestParam("file") MultipartFile file, BindingResult tipologiaBindingResult ) throws IllegalStateException, IOException {
		String baseDir="C:\\Users\\utente\\Documents\\workspace-spring-tool-suite-4-4.11.1.RELEASE\\Officina\\src\\main\\resources\\static\\images\\";
		String s =tipologia.getNome().replaceAll(" ", "_");
		System.out.println(file.getContentType());
		file.transferTo(new File(baseDir + s +".jpg"));
		
		this.tipologiavalidator.validate(tipologia, tipologiaBindingResult);
		 if(!tipologiaBindingResult.hasErrors()) {
			 tipologia.setImg("/images/" + s +".jpg");
		this.tipologiaservice.save(tipologia);
		
		return "redirect:admin/all_tipologie";
		 }
		 model.addAttribute("tipi",this.tipologiaservice.tipi());
		return "admin/tipologie";
	}
	
	@GetMapping(value ="admin/Meccanici")
	public String meccanici(Model model) {
		model.addAttribute("meccanico",new Meccanico());
		model.addAttribute("meccanici",this.meccanicoservice.all());
		model.addAttribute("tipi",tipologiaservice.tipi());
		return "admin/Meccanici";
	}
	
	@PostMapping(value ="/NewMeccanico")
	public String newmeccanico(Model model, @ModelAttribute("meccanico") Meccanico meccanico ,BindingResult meccanicoBindingResult) {
	
		this.meccanicovalidator.validate(meccanico, meccanicoBindingResult);
		 if(!meccanicoBindingResult.hasErrors()) {
		this.meccanicoservice.save(meccanico);
		return "redirect:admin/Meccanici";
		 }
		 model.addAttribute("meccanici",this.meccanicoservice.all());
			model.addAttribute("tipi",tipologiaservice.tipi());
		return "admin/Meccanici";
	}
	
	@GetMapping(value ="admin/Clienti")
	public String clienti(Model model) {
		model.addAttribute("user", new Utente());
		model.addAttribute("credentials", new Credentials());
		model.addAttribute("Cliente",this.utenteservice.getClienti());

		return "admin/Clienti";
	}
	
	@PostMapping(value ="NewCliente")
	 public String registerUser(@ModelAttribute("user") Utente user,
             BindingResult userBindingResult,
             @ModelAttribute("credentials") Credentials credentials,
             BindingResult credentialsBindingResult,
             Model model) {

    this.userValidator.validate(user, userBindingResult);
    this.credentialsValidator.validate(credentials, credentialsBindingResult);

    if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {

        credentials.setUser(user);
        credentialsService.saveCredentials(credentials);
        return "redirect:admin/Clienti";
    }
    model.addAttribute("Cliente",this.utenteservice.getClienti());
		return "admin/Clienti";
	}
	
	
	
	
	@GetMapping("/admin/deleteCliente")
	public String deleteEmployee(@RequestParam Long ClienteId) {
		
		this.credentialsService.delete(ClienteId);
		return "redirect:/admin/Clienti";
	}
	
	@GetMapping(value ="/admin/Interventi")
	public String interventi(Model model) {
		model.addAttribute("prenotazione",this.prenotazioneservice.all());
		return "admin/Interventi";
	}
	
	@PostMapping(value = "/admin/editCliente")
    public String editCliente(@RequestParam Long ClienteId,@ModelAttribute("user") Utente utente, Model model,   BindingResult userBindingResult) {
		System.out.println(utente.getNome());
		
		this.userValidator.validate(utente, userBindingResult);
		  if(!userBindingResult.hasErrors()) {
		Utente u = this.utenteservice.findById(ClienteId);
		u.setNome(utente.getNome());
		u.setCognome(utente.getCognome());
		u.setPhone(utente.getPhone());
		this.utenteservice.save(u);
		return "redirect:/admin/Clienti";
		  }
		  model.addAttribute("credentials", new Credentials());
	 model.addAttribute("Cliente",this.utenteservice.getClienti());
		return "/admin/Clienti";
    }

	
	
	
}

package es.ucm.fdi.iw.controller;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.ucm.fdi.iw.model.Quest;
import es.ucm.fdi.iw.model.QuestQueries;
import es.ucm.fdi.iw.model.Reporte;
import es.ucm.fdi.iw.model.ReporteQueries;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.UserQueries;

@Controller	
public class RootController {

	private static Logger log = Logger.getLogger(RootController.class);
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("s", "/static");
    }

	@GetMapping({"/", "/index"})
	public String root(Model model, Principal principal) {
		log.info(principal.getName() + " de tipo " + principal.getClass());		
		// org.springframework.security.core.userdetails.User
		return "home";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "logout";
	}
	
	@GetMapping("/upload")
	public String upload() {
		return "upload";
	}
		
	@GetMapping("/quest")
	public String quest(Model m) {
		List<Quest> listaQuests = QuestQueries.findAllQuests(entityManager);
		m.addAttribute("all_quests", listaQuests);
		
		return "quest";
	}
	@GetMapping("/matches")
	public String matches() {
		return "matches";
	}
	@GetMapping("/user")
	public String user() {
		return "user";
	}
	@GetMapping("/hacer_quest")
	public String hacer_quest() {
		return "hacer_quest";
	}
	@GetMapping("/match")
	public String match() {
		return "match";
	}
	@GetMapping("/reportes")
	public String reportes(Model m) {
		List<Reporte> listaNuevos = ReporteQueries.findNoVistos(entityManager);
		m.addAttribute("reportes_nuevos", listaNuevos);
		List<Reporte> listaVistos = ReporteQueries.findVistos(entityManager);
		m.addAttribute("reportes_vistos", listaVistos);
		return "reportes";
  }
	@GetMapping("/messages") 
	public String messages(){
		return "messages";
	}
	
	@GetMapping("/editores") 
	@Transactional
	public String editores(Model m){
		List<User> lista = UserQueries.findEditores(entityManager);
		m.addAttribute("editores", lista);
		return "editores";
	}
	
	@GetMapping("/registro") 
	public String registro(){
		return "registro";
	}
	@GetMapping("/quest_admin") 
	public String quest_admin(){
		return "quest_admin";
	}
	@RequestMapping(value = "/addUser", method=RequestMethod.POST)
	@Transactional
	public String addUser(
			@RequestParam String user, 
			@RequestParam String password,
			@RequestParam String password2,
			@RequestParam String email,
			@RequestParam int edad,
			@RequestParam String ciudad,
			@RequestParam String resumen,
		
			@RequestParam(required=false) String isAdmin, Model m) {
		User u = new User();
		if(!password.equals(password2)) {
			return "error";
		} else {
			u.setLogin(user);
			u.setPassword(passwordEncoder.encode(password));
			u.setCiudad(ciudad);
			u.setEnabled((byte) 1);
			u.setEdad(edad);
			u.setEmail(email);
			u.setResumen(resumen);
			u.setRoles("USER");
			
			entityManager.persist(u);
			
			entityManager.flush();
			
			m.addAttribute("users", entityManager
					.createQuery("select u from User u").getResultList());
			return "/login";
		}
	}
	
}

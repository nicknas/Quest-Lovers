package es.ucm.fdi.iw.controller;

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

import es.ucm.fdi.iw.LocalData;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.UserQueries;

@Controller
public class EditoresController {
	private static Logger log = Logger.getLogger(EditoresController.class);
	
	@Autowired
	private LocalData localData;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("s", "/static");
    }
	
	@GetMapping("/editores") 
	@Transactional
	public String editores(Model m){
		List<User> lista = UserQueries.findEditores(entityManager);
		m.addAttribute("editores", lista);
		return "editores";
	}
	
	@RequestMapping(value = "/addEditor", method = RequestMethod.POST)
	@Transactional
	public String addEditor(
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
			u.setEdad(edad);
			u.setEmail(email);
			u.setResumen(resumen);
			u.setRoles("EDITOR");
			
			entityManager.persist(u);
			
			entityManager.flush();
			
			List<User> lista = UserQueries.findEditores(entityManager);
			m.addAttribute("editores", lista);
			return "editores";
		}
	}
	
	@RequestMapping(value = "/deleteEditor", method = RequestMethod.POST)
	@Transactional
	public String deleteEditor(
			@RequestParam int id, Model m){
			
		User u = UserQueries.findWithId(entityManager, id);
		if(u != null) {
			entityManager.remove(u);
			entityManager.flush();
			List<User> lista = UserQueries.findEditores(entityManager);
			m.addAttribute("editores", lista);
			return "editores";
		}
		else {
			return "error";
		}
	}
}

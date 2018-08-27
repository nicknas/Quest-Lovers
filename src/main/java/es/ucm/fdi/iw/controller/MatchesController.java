package es.ucm.fdi.iw.controller;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import es.ucm.fdi.iw.LocalData;
import es.ucm.fdi.iw.model.MatchQueries;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.UserQueries;

@Controller
public class MatchesController {
	private static Logger log = Logger.getLogger(MatchesController.class);
	
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
	
	@GetMapping("/matches")
	public String matches(Model m, Authentication authentication) {
		User u = UserQueries.findWithName(entityManager, authentication.getName());
		Set<User> lista_matches = MatchQueries.findMatchesUser(entityManager,u.getId());
		m.addAttribute("lista_matches", lista_matches);
		m.addAttribute("user_actual", u);
		return "matches";
	}
	
	
}

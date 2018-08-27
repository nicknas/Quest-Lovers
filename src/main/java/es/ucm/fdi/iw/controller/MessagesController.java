package es.ucm.fdi.iw.controller;

import java.util.List;

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
import es.ucm.fdi.iw.model.Conversacion;
import es.ucm.fdi.iw.model.ConversacionQueries;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.UserQueries;

@Controller
public class MessagesController {
	private static Logger log = Logger.getLogger(MessagesController.class);
	
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
	
	@GetMapping("/messages") 
	public String messages(Model m, Authentication authentication){
		User user_actual = UserQueries.findWithName(entityManager, authentication.getName());
		
		List<Conversacion> lista_conversaciones = ConversacionQueries.findConversacionesUser(entityManager, user_actual.getId());
		
		m.addAttribute("lista", lista_conversaciones);
		
		return "messages";
	}
}

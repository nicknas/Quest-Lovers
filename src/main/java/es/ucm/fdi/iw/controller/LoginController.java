package es.ucm.fdi.iw.controller;

import java.util.Enumeration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import es.ucm.fdi.iw.LocalData;

@Controller
public class LoginController {
	private static Logger log = Logger.getLogger(LoginController.class);
	
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
	
	@GetMapping("/login")
	public String login(HttpServletRequest request, Model m) {	
		Enumeration<String> parameters = request.getParameterNames();	
		while (parameters.hasMoreElements()) {
			if (parameters.nextElement().contains("error")) {
				m.addAttribute("errorLogin", true);
			}
		}
		return "login";
	}
}

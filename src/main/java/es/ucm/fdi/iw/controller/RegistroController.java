package es.ucm.fdi.iw.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.owasp.encoder.Encode;
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

@Controller
public class RegistroController {
	private static Logger log = Logger.getLogger(RegistroController.class);
	
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
	
	@GetMapping("/registro") 
	public String registro(){
		return "registro";
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
			u.setLogin(scapedparameter(user));
			u.setPassword(passwordEncoder.encode(scapedparameter(password)));
			u.setCiudad(scapedparameter(ciudad));
			u.setEnabled((byte) 1);
			u.setEdad(edad);
			u.setEmail(scapedparameter(email));
			u.setResumen(scapedparameter(resumen));
			u.setRoles("USER");
			
			entityManager.persist(u);
			
			entityManager.flush();
			
			m.addAttribute("users", entityManager
					.createQuery("select u from User u").getResultList());
			return "/login";
		}
	}
	
	String scapedparameter(String parameterToScape) {
		String e = Encode.forCDATA(parameterToScape);
		e = Encode.forHtml(e);
		e = Encode.forJavaScript(e);
		e = Encode.forJava(e);
		e = Encode.forCssString(e);
		
		return e;
		
	}
}

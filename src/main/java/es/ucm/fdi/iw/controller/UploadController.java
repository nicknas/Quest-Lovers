package es.ucm.fdi.iw.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import es.ucm.fdi.iw.LocalData;
import es.ucm.fdi.iw.model.User;

@Controller
public class UploadController {
	private static Logger log = Logger.getLogger(UploadController.class);
	
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
	
	@GetMapping("/upload")
	public String upload() {
		return "upload";
	}
	
	@Transactional
	@GetMapping("/test")
	@ResponseBody
	public String testBd() {
		User u = new User();
		u.setLogin("admin");
		u.setPassword(passwordEncoder.encode("admin"));
		u.setRoles("ADMIN");
		u.setCiudad("Madrid");
		u.setEnabled((byte) 1);
		u.setEdad(33);
		u.setEmail("admin@admin.com");
		u.setResumen("resumen_del_administrador");
		entityManager.persist(u);
		return "/login";
	}
}

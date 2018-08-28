package es.ucm.fdi.iw.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.ucm.fdi.iw.LocalData;

@Controller
public class VerHistoriaAdminController {
	private static Logger log = Logger.getLogger(VerHistoriaAdminController.class);
	
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
	
	@RequestMapping(value = "/ver_historia_admin", method = RequestMethod.POST)
	public String verHistoriaAdmin(HttpServletRequest request, Model m) {
		long id = Long.parseLong(request.getParameter("id_quest"));
		m.addAttribute("id", id);
		return "ver_historia_admin";
	}
	
}

package es.ucm.fdi.iw.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import es.ucm.fdi.iw.LocalData;
import es.ucm.fdi.iw.model.Match;
import es.ucm.fdi.iw.model.MatchQueries;
import es.ucm.fdi.iw.model.Reporte;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.UserQueries;

@Controller
public class MatchController {
	private static Logger log = Logger.getLogger(MatchController.class);
	
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
	
	@RequestMapping(value = "/match", method = RequestMethod.GET)
	@Transactional
	public String match(
			@RequestParam int id, Model m, Authentication authentication
			) {
		User u = UserQueries.findWithId(entityManager, id);
		User user_actual = UserQueries.findWithName(entityManager, authentication.getName());
		
		Match match = MatchQueries.findMatchId(entityManager, u.getId(), user_actual.getId());
		
		m.addAttribute("match", match);
		m.addAttribute("user", u);
		m.addAttribute("user_actual", user_actual);
		
		return "match";
	}
	
	@Transactional
	@GetMapping("/reportar")
	@ResponseBody
	public RedirectView reportar(Model m, HttpServletRequest request) {
		String id_reportador =request.getParameter("id1");
		String id_reportado = request.getParameter("id2");
		String motivo = request.getParameter("m");
		User reportador = UserQueries.findWithId(entityManager, Integer.parseInt(id_reportador));
		User reportado = UserQueries.findWithId(entityManager, Integer.parseInt(id_reportado));
		Reporte reporte = new Reporte();
		reporte.setComentario(motivo);
		reporte.setReportado(reportado);
		reporte.setReportador(reportador);
		reporte.setVisto((byte) 0);
		reporte.setBaneado((byte) 0);
		entityManager.persist(reporte);
		
		return new RedirectView("/matches");
	}
}

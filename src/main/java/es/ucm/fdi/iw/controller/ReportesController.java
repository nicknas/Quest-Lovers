package es.ucm.fdi.iw.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import es.ucm.fdi.iw.LocalData;
import es.ucm.fdi.iw.model.Reporte;
import es.ucm.fdi.iw.model.ReporteQueries;
import es.ucm.fdi.iw.model.User;

@Controller
public class ReportesController {
	private static Logger log = Logger.getLogger(ReportesController.class);
	
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
	
	@GetMapping("/reportes")
	public String reportes(Model m) {
		List<Reporte> listaNuevos = ReporteQueries.findNoVistos(entityManager);
		m.addAttribute("reportes_nuevos", listaNuevos);
		List<Reporte> listaVistos = ReporteQueries.findVistos(entityManager);
		m.addAttribute("reportes_vistos", listaVistos);
		return "reportes";
	}
	
	@Transactional
	@GetMapping("/confirmar_reporte")
	public String confirmar_reporte(Model m, HttpServletRequest request) {
		long id_reporte =Integer.parseInt(request.getParameter("id"));
		int baneado = Integer.parseInt(request.getParameter("baneado"));
		Reporte r = ReporteQueries.findReporteById(entityManager, id_reporte);
		User u = r.getReportado();
		
		if(baneado == 1) {
			r.setBaneado((byte) 1); 

			u.setEnabled((byte) 0);
		}else {
			r.setBaneado((byte) 0);
		}
		r.setVisto((byte) 1);
		entityManager.merge(r);
		entityManager.merge(u);

		
		return "redirect:/reportes";
	}
}

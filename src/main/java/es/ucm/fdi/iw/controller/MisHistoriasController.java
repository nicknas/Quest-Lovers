package es.ucm.fdi.iw.controller;

import java.io.File;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import es.ucm.fdi.iw.LocalData;
import es.ucm.fdi.iw.model.Quest;
import es.ucm.fdi.iw.model.QuestQueries;

@Controller
public class MisHistoriasController {
	private static Logger log = Logger.getLogger(MisHistoriasController.class);
	
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
	
	@GetMapping("/mis_historias")
	public String getMyQuests(Authentication authentication, Model model, HttpServletRequest request) {
		List<Quest> questList = QuestQueries.findQuestsByEditorName(entityManager, authentication.getName());
		model.addAttribute("questList", questList);
		boolean success = Boolean.parseBoolean(request.getParameter("success"));
		if (success) {
			model.addAttribute("success_message", "La quest se ha borrado correctamente");
		}
		return "mis_historias";
	}
	
	@Transactional
	@RequestMapping(value="/borrar_quest", method=RequestMethod.POST)
	@ResponseBody
	public RedirectView deleteQuest(HttpServletRequest request) {
		long questId = Long.parseLong(request.getParameter("id_quest"));
		Quest q = QuestQueries.findQuestById(entityManager, questId);
		File questFile = localData.getFile("quest", q.getUrl());
		questFile.delete();
		entityManager.remove(q);
		entityManager.flush();
		return new RedirectView("/mis_historias?success=true");
	}
}

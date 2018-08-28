package es.ucm.fdi.iw.controller;

import java.io.File;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import es.ucm.fdi.iw.LocalData;
import es.ucm.fdi.iw.model.*;

@Controller
public class QuestAdminController {
	private static Logger log = Logger.getLogger(QuestAdminController.class);
	
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
	
	@GetMapping("/quest_admin") 
	public String quest_admin(Model m){
		List<Quest> listQuest = QuestQueries.findAllQuests(entityManager);
		m.addAttribute("listQuest", listQuest);
		return "quest_admin";
	}
	
	@Transactional
	@RequestMapping(value="/ban_quest", method=RequestMethod.POST)
	public String banQuest(HttpServletRequest request) {
		long questId = Long.parseLong(request.getParameter("id_quest"));
		Quest q = QuestQueries.findQuestById(entityManager, questId);
		File questFile = localData.getFile("quest", q.getUrl());
		questFile.delete();
		q.setEnabled(Byte.parseByte("0"));
		entityManager.merge(q);
		entityManager.flush();
		return "redirect:/quest_admin";
	}
}

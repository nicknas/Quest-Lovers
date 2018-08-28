package es.ucm.fdi.iw.controller;

import java.util.ArrayList;
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
import es.ucm.fdi.iw.model.Quest;
import es.ucm.fdi.iw.model.QuestQueries;
import es.ucm.fdi.iw.model.RespuestasQuest;
import es.ucm.fdi.iw.model.RespuestasQuestQueries;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.UserQueries;

@Controller
public class QuestsController {
	private static Logger log = Logger.getLogger(QuestsController.class);
	
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
	
	@GetMapping("/quests")
	public String quest(Model m, Authentication authentication) {
		List<Quest> listaQuests = QuestQueries.findAllQuests(entityManager);
		List<Quest> questsToRemove = new ArrayList<Quest>();
		User u = UserQueries.findWithName(entityManager, authentication.getName());
		List<RespuestasQuest> listaQuestsUser = RespuestasQuestQueries.findQuestsByUser(entityManager, u.getId());
		for(RespuestasQuest questTerminada : listaQuestsUser) {
			for(Quest quest : listaQuests) {
				if (questTerminada.getIdQuest() == quest.getId()) {
					questsToRemove.add(quest);
				}
			}
		}
		for(Quest quest : questsToRemove) {
			listaQuests.remove(quest);
		}
		m.addAttribute("all_quests", listaQuests);
		return "quests";
	}
	
	
}
